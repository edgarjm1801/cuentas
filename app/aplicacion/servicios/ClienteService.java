package aplicacion.servicios;

import aplicacion.dtos.ClienteDTO;
import aplicacion.transformadores.ClienteTransformer;
import dominio.modelos.Cliente;
import dominio.repositorios.ClienteRepository;
import dominio.servicios.ValidarClienteService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ValidarClienteService validarclienteService;


    @Inject
    public ClienteService(ClienteRepository clienteRepository, ValidarClienteService validarclienteService) {

        this.clienteRepository = clienteRepository;
        this.validarclienteService = validarclienteService;
    }

    public CompletableFuture<Cliente> crear(ClienteDTO clienteDTO) {
        LocalDate fechaNacimiento = clienteDTO.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (validarclienteService.validaionEdad(fechaNacimiento)) {
            return clienteRepository.buscarPorNumeroIdentificacion(clienteDTO.getNumeroIdentificacion()).thenCompose(optionalCliente -> {
                if (optionalCliente.isPresent()) {
                    Cliente clienteExistente = optionalCliente.get();
                    return clienteRepository.actualizar(ClienteTransformer.fromDTOUpdate(clienteDTO, clienteExistente))
                            .thenApply(updated -> updated);
                } else {
                    return clienteRepository.crear(ClienteTransformer.fromDTO(clienteDTO)).thenApply(created -> created);
                }
            }).exceptionally(throwable -> {
                throw new RuntimeException("Error al crear o actualizar cliente", throwable);
            });
        } else {
            return CompletableFuture.failedFuture(new RuntimeException("El cliente debe ser mayor de edad."));
        }
    }

    public CompletableFuture<Boolean> eliminar(UUID id){
        return clienteRepository.tieneProductosFinancieros(id).thenCompose(tieneProductos -> {
            if (tieneProductos) {
                return CompletableFuture.failedFuture(new RuntimeException("No se puede eliminar el cliente porque tiene productos financieros vinculados."));
            } else {
                return clienteRepository.eliminar(id);
            }
        });
    }

    public CompletableFuture<Optional<Cliente>> consultar(String identificacion){
        return clienteRepository.buscarPorNumeroIdentificacion(identificacion);
    }

    public CompletableFuture<List<Cliente>> listar(){
        return clienteRepository.listar();
    }

    public CompletableFuture<Optional<Cliente>> buscarPorId(UUID idCliente) {
        return clienteRepository.buscarPorId(idCliente);
    }
}
