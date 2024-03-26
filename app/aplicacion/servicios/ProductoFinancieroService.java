package aplicacion.servicios;

import aplicacion.dtos.ProductoFinancieroDTO;
import aplicacion.transformadores.ProductoFinancieroTransformer;
import dominio.modelos.Cliente;
import dominio.modelos.EstadoProductoFinanciero;
import dominio.modelos.ProductoFinanciero;
import dominio.modelos.TipoProductoFinanciero;
import dominio.repositorios.ProductoFinancieroRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class ProductoFinancieroService {

    private final ClienteService clienteService;
    private final ProductoFinancieroRepository productoFinancieroRepository;

    @Inject
    public ProductoFinancieroService(ClienteService clienteService, ProductoFinancieroRepository productoFinancieroRepository) {
        this.clienteService = clienteService;
        this.productoFinancieroRepository = productoFinancieroRepository;
    }

    public CompletableFuture<ProductoFinanciero> crear(ProductoFinancieroDTO productoFinancieroDTO) {
        TipoProductoFinanciero tipoProducto = TipoProductoFinanciero.fromString(productoFinancieroDTO.getTipo());
        if (tipoProducto == TipoProductoFinanciero.DESCONOCIDO) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Tipo de producto financiero no válido"));
        }

        UUID idCliente = productoFinancieroDTO.getIdCliente();
        CompletableFuture<Optional<Cliente>> clienteFuture = clienteService.buscarPorId(idCliente);
        return clienteFuture.thenCompose(clienteOpt -> {
            if (clienteOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("El cliente no existe"));
            }

            Cliente cliente = clienteOpt.get();
            ProductoFinanciero productoFinanciero = ProductoFinancieroTransformer.fromDTO(productoFinancieroDTO);
            return productoFinancieroRepository.crear(cliente, productoFinanciero);
        });
    }

    public CompletableFuture<ProductoFinanciero> actualizarEstado(String idCliente, String idProducto, String estado) {
        UUID idClienteUUID = UUID.fromString(idCliente);
        UUID idProductoUUID = UUID.fromString(idProducto);
        EstadoProductoFinanciero estadoProducto = EstadoProductoFinanciero.fromString(estado);
        if (estadoProducto == EstadoProductoFinanciero.DESCONOCIDO) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Estado de producto financiero no válido"));
        }

        CompletableFuture<Optional<ProductoFinanciero>> productoFuture = productoFinancieroRepository.buscarPorIdClienteYIdProducto(idClienteUUID, idProductoUUID);
        return productoFuture.thenCompose(productoOpt -> {
            if (productoOpt.isEmpty()) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("El producto financiero no existe o no pertenece al cliente"));
            }

            ProductoFinanciero productoFinanciero = productoOpt.get();
            if (estadoProducto == EstadoProductoFinanciero.CANCELADA && productoFinanciero.getSaldo() != 0) {
                return CompletableFuture.failedFuture(new IllegalArgumentException("No se puede cancelar el producto financiero con saldo diferente de $0"));
            }

            return productoFinancieroRepository.actualizarEstado(productoFinanciero, estadoProducto);
        });
    }

    public CompletableFuture<Optional<ProductoFinanciero>> consultar(String idCliente, String idProducto) {
        return productoFinancieroRepository.buscarPorIdClienteYIdProducto(UUID.fromString(idCliente), UUID.fromString(idProducto));
    }

    public CompletableFuture<List<ProductoFinanciero>> listar(String idCliente) {
        return productoFinancieroRepository.listarPorIdCliente(UUID.fromString(idCliente));
    }


}
