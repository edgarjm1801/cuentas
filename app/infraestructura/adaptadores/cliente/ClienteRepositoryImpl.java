package infraestructura.adaptadores.cliente;

import dominio.modelos.Cliente;
import dominio.repositorios.ClienteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.Date;
import java.util.concurrent.CompletionStage;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public CompletableFuture<Cliente> crear(Cliente cliente) {
        return CompletableFuture.completedFuture(cliente);//TODO por implemetar
    }

    @Override
    public CompletableFuture<Optional<Cliente>> buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        Cliente clienteEncontrado = new Cliente(UUID.randomUUID(), "C.C.", numeroIdentificacion, "John", "Doe", "john.doe@example.com", new Date(), new Date());
        return CompletableFuture.completedFuture(Optional.of(clienteEncontrado));//TODO por implemetar
    }

    @Override
    public CompletableFuture<Boolean> tieneProductosFinancieros(UUID id) {
        return CompletableFuture.completedFuture(false);//TODO por implemetar
    }

    @Override
    public CompletableFuture<Boolean> eliminar(UUID id) {
        return CompletableFuture.completedFuture(true);//TODO por implemetar
    }

    @Override
    public CompletableFuture<Cliente> actualizar(Cliente cliente) {
        return CompletableFuture.completedFuture(cliente);//TODO por implemetar
    }

    @Override
    public CompletableFuture<Optional<Cliente>> buscarPorId(UUID idCliente) {
        Cliente clienteEncontrado = new Cliente(UUID.randomUUID(), "C.C.", "numeroIdentificacion", "John", "Doe", "john.doe@example.com", new Date(), new Date());
        return CompletableFuture.completedFuture(Optional.of(clienteEncontrado));//TODO por implemetar
    }

    @Override
    public CompletableFuture<List<Cliente>> listar() {
        return CompletableFuture.completedFuture(Collections.emptyList());//TODO por implemetar
    }

}
