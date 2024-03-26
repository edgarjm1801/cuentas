package dominio.repositorios;

import dominio.modelos.Cliente;
import dominio.modelos.EstadoProductoFinanciero;
import dominio.modelos.ProductoFinanciero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface ProductoFinancieroRepository {


    CompletableFuture<ProductoFinanciero> crear(Cliente cliente, ProductoFinanciero productoFinanciero);

    CompletableFuture<Optional<ProductoFinanciero>> buscarPorIdClienteYIdProducto(UUID idCliente, UUID idProducto);

    CompletableFuture<ProductoFinanciero> actualizarEstado(ProductoFinanciero productoFinanciero, EstadoProductoFinanciero estadoProducto);

    CompletableFuture<List<ProductoFinanciero>> listarPorIdCliente(UUID idCliente);
}
