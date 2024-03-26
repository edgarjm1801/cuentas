package dominio.repositorios;

import dominio.modelos.Transaccion;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface TransaccionRepository {
    CompletableFuture<Transaccion> crear(Transaccion transaccion);

    CompletableFuture<List<Transaccion>> listarPorIdClienteYIdProducto(UUID idCliente, UUID idProducto);
}
