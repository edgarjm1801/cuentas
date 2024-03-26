package infraestructura.adaptadores.productoFinaciero;

import dominio.modelos.Cliente;
import dominio.modelos.EstadoProductoFinanciero;
import dominio.modelos.ProductoFinanciero;
import dominio.modelos.TipoProductoFinanciero;
import dominio.repositorios.ProductoFinancieroRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProductoFinancieroRepositoryImpl implements ProductoFinancieroRepository {

    @Override
    public CompletableFuture<ProductoFinanciero> crear(Cliente cliente, ProductoFinanciero productoFinanciero) {
        return CompletableFuture.completedFuture(productoFinanciero);//TODO por implemetar
    }

    @Override
    public CompletableFuture<Optional<ProductoFinanciero>> buscarPorIdClienteYIdProducto(UUID idCliente, UUID idProducto) {
        return CompletableFuture.completedFuture(Optional.of(
                new ProductoFinanciero(idProducto, TipoProductoFinanciero.CUENTA_AHORRO,"numeroCuenta", EstadoProductoFinanciero.ACTIVO, 1000.0, true, new Date(), new Date())
        ));//TODO por implemetar
    }

    @Override
    public CompletableFuture<ProductoFinanciero> actualizarEstado(ProductoFinanciero productoFinanciero, EstadoProductoFinanciero estadoProducto) {
        return CompletableFuture.completedFuture(
                productoFinanciero
        );//TODO por implemetar
    }

    @Override
    public CompletableFuture<List<ProductoFinanciero>> listarPorIdCliente(UUID idCliente) {
        return CompletableFuture.completedFuture(List.of(
                new ProductoFinanciero(UUID.randomUUID(), TipoProductoFinanciero.CUENTA_AHORRO,"numeroCuenta", EstadoProductoFinanciero.ACTIVO, 1000.0, true, new Date(), new Date())
        ));//TODO por implemetar
    }
}
