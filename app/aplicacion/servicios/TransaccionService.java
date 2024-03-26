package aplicacion.servicios;

import aplicacion.dtos.TransaccionDTO;
import aplicacion.transformadores.TransaccionTransformer;
import dominio.modelos.Cliente;
import dominio.modelos.ProductoFinanciero;
import dominio.modelos.TipoTransaccion;
import dominio.modelos.Transaccion;
import dominio.repositorios.TransaccionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    private final ProductoFinancieroService productoFinancieroService;

    private final ClienteService clienteService;


    @Inject
    public TransaccionService(TransaccionRepository transaccionRepository, ProductoFinancieroService productoFinancieroService, ClienteService clienteService) {
        this.transaccionRepository = transaccionRepository;
        this.productoFinancieroService = productoFinancieroService;
        this.clienteService = clienteService;
    }

    public CompletableFuture<Transaccion> crear(String idCliente, TransaccionDTO transaccionDTO) {
        UUID idClienteUuid = UUID.fromString(idCliente);
        CompletableFuture<Optional<Cliente>> clienteFuture = clienteService.buscarPorId(idClienteUuid);
        return clienteFuture.thenCompose(clienteOpt -> {
            if (clienteOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("El cliente no existe"));
            }

            TipoTransaccion tipoTransaccion = TipoTransaccion.fromString(transaccionDTO.getTipo());
            if (tipoTransaccion == TipoTransaccion.DESCONOCIDO) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("Tipo de transacción no válido"));
            }

            switch (tipoTransaccion) {
                case CONSIGNACION:
                    return procesarConsignacion(transaccionDTO, clienteOpt.get());
                case RETIRO:
                    return procesarRetiro(transaccionDTO, clienteOpt.get());
                case TRANSFERENCIA:
                    return procesarTransferencia(transaccionDTO, clienteOpt.get());
                default:
                    return CompletableFuture.failedFuture(new IllegalArgumentException("Tipo de transacción no reconocido"));
            }
        });
    }

    private CompletableFuture<Transaccion> procesarTransferencia(TransaccionDTO transaccionDTO, Cliente cliente) {
        CompletableFuture<Optional<ProductoFinanciero>> cuentaOrigenFuture = productoFinancieroService.consultar(cliente.getId().toString(), transaccionDTO.getIdCuentaOrigen());
        CompletableFuture<Optional<ProductoFinanciero>> cuentaDestinoFuture = productoFinancieroService.consultar(cliente.getId().toString(), transaccionDTO.getIdCuentaDestino());

        return cuentaOrigenFuture.thenCompose(cuentaOrigenOpt -> {
            if (cuentaOrigenOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("La cuenta de origen no existe"));
            }
            ProductoFinanciero cuentaOrigen = cuentaOrigenOpt.get();

            return cuentaDestinoFuture.thenCompose(cuentaDestinoOpt -> {
                if (cuentaDestinoOpt.isEmpty()) {
                    return CompletableFuture.failedFuture(new IllegalArgumentException("La cuenta de destino no existe"));
                }
                ProductoFinanciero cuentaDestino = cuentaDestinoOpt.get();

                if (cuentaOrigen.getSaldo() < transaccionDTO.getMonto()) {
                    return CompletableFuture.failedFuture(new IllegalArgumentException("Saldo insuficiente en la cuenta de origen"));
                }

                cuentaOrigen.restarSaldo(transaccionDTO.getMonto());
                cuentaDestino.sumarSaldo(transaccionDTO.getMonto());

                CompletableFuture<Void> actualizarCuentasFuture = CompletableFuture.allOf(
                        productoFinancieroService.actualizarSaldo(cuentaOrigen),
                        productoFinancieroService.actualizarSaldo(cuentaDestino)
                );

                return actualizarCuentasFuture.thenCompose(updatedCuentas -> {
                    Transaccion transaccion = TransaccionTransformer.fromDTO(transaccionDTO, Optional.of(cuentaOrigen), Optional.of(cuentaDestino));
                    return transaccionRepository.crear(transaccion); // Guardar la transacción en el repositorio
                });
            });
        });
    }

    private CompletableFuture<Transaccion> procesarRetiro(TransaccionDTO transaccionDTO, Cliente cliente) {
        CompletableFuture<Optional<ProductoFinanciero>> cuentaOrigenFuture = productoFinancieroService.consultar(cliente.getId().toString(), transaccionDTO.getIdCuentaOrigen());
        return cuentaOrigenFuture.thenCompose(cuentaOrigenOpt -> {
            if (cuentaOrigenOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("La cuenta de origen no existe"));
            }

            ProductoFinanciero cuentaOrigen = cuentaOrigenOpt.get();

            if (cuentaOrigen.getSaldo() < transaccionDTO.getMonto()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("Saldo insuficiente en la cuenta de origen"));
            }

            cuentaOrigen.restarSaldo(transaccionDTO.getMonto());
            CompletableFuture<ProductoFinanciero> actualizarCuentaOrigenFuture = productoFinancieroService.actualizarSaldo(cuentaOrigen);

            Transaccion transaccion = TransaccionTransformer.fromDTO(transaccionDTO, Optional.of(cuentaOrigen), Optional.empty());

            return actualizarCuentaOrigenFuture.thenCompose(updatedCuentaOrigen -> {
                return transaccionRepository.crear(transaccion);
            }).thenApply(createdTransaccion -> {
                return transaccion;
            });
        });
    }


    private CompletableFuture<Transaccion> procesarConsignacion(TransaccionDTO transaccionDTO,  Cliente cliente) {
        CompletableFuture<Optional<ProductoFinanciero>> cuentaDestinoFuture = productoFinancieroService.consultar(cliente.getId().toString(), transaccionDTO.getIdCuentaDestino());

        return cuentaDestinoFuture.thenCompose(cuentaDestinoOpt -> {
            if (cuentaDestinoOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("La cuenta de destino no existe"));
            }
            ProductoFinanciero cuentaDestino = cuentaDestinoOpt.get();

            cuentaDestino.sumarSaldo(transaccionDTO.getMonto());

            CompletableFuture<ProductoFinanciero> actualizarCuentaDestinoFuture = productoFinancieroService.actualizarSaldo(cuentaDestino);

            return actualizarCuentaDestinoFuture.thenCompose(updatedCuentaDestino -> {
                Transaccion transaccion = TransaccionTransformer.fromDTO(transaccionDTO, Optional.empty(), Optional.of(cuentaDestino));
                return transaccionRepository.crear(transaccion);
            });
        });
    }

    public CompletableFuture<List<Transaccion>> listar(String idCliente, String idProducto) {
        return transaccionRepository.listarPorIdClienteYIdProducto(UUID.fromString(idCliente), UUID.fromString(idProducto));
    }

}
