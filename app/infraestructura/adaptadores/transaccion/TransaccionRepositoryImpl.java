package infraestructura.adaptadores.transaccion;

import dominio.modelos.Transaccion;
import dominio.repositorios.TransaccionRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TransaccionRepositoryImpl implements TransaccionRepository {

    @Override
    public CompletableFuture<Transaccion> crear(Transaccion transaccion) {
        return CompletableFuture.completedFuture(transaccion);//TODO por implemetar
    }

    @Override
    public CompletableFuture<List<Transaccion>> listarPorIdClienteYIdProducto(UUID idCliente, UUID idProducto) {
        return CompletableFuture.completedFuture(Collections.emptyList());//TODO por implemetar
    }
}
