package dominio.repositorios;

import dominio.modelos.Cliente;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ClienteRepository {

    CompletableFuture<Cliente> crear(Cliente cliente);
    CompletableFuture<Optional<Cliente>> buscarPorNumeroIdentificacion(String numeroIdentificacion);
    CompletableFuture<List<Cliente>> listar();
    CompletableFuture<Boolean> tieneProductosFinancieros(UUID id);
    CompletableFuture<Boolean> eliminar(UUID id);
    CompletableFuture<Cliente> actualizar(Cliente cliente);

    CompletableFuture<Optional<Cliente>> buscarPorId(UUID idCliente);
}

