package infraestructura.controladores;

import aplicacion.dtos.ProductoFinancieroDTO;
import aplicacion.servicios.ProductoFinancieroService;
import com.fasterxml.jackson.databind.JsonNode;
import dominio.modelos.ProductoFinanciero;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ProductoFinancieroController extends Controller {

    private final ProductoFinancieroService productoFinancieroService;

    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public ProductoFinancieroController(HttpExecutionContext httpExecutionContext, ProductoFinancieroService productoFinancieroService) {
        this.httpExecutionContext = httpExecutionContext;
        this.productoFinancieroService = productoFinancieroService;
    }

    public CompletionStage<Result> crear(String idCliente, Http.Request request) {
        JsonNode json = request.body().asJson();
        ProductoFinancieroDTO productoFinancieroDTO = Json.fromJson(json, ProductoFinancieroDTO.class);
        return productoFinancieroService.crear(productoFinancieroDTO)
                .thenApplyAsync(productoFinaciero ->
                        created(Json.toJson(productoFinaciero)), httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> actualizarEstado(String idCliente, String idProducto, Http.Request request) {
        JsonNode json = request.body().asJson();
        String estado = json.get("estado").asText();
        return productoFinancieroService.actualizarEstado(idCliente, idProducto, estado)
                .thenApply(result -> ok("Estado actualizado exitosamente"))
                .exceptionally(throwable -> {
                    return internalServerError("Error al actualizar el estado del producto financiero: " + throwable.getMessage());
                });
    }

    public CompletionStage<Result> consultar(String idCliente, String idProducto) {
        return productoFinancieroService.consultar(idCliente, idProducto)
                .thenApply(optProductoFinanciero -> {
                    if (optProductoFinanciero.isPresent()) {
                        ProductoFinanciero productoFinanciero = optProductoFinanciero.get();
                        return ok(Json.toJson(productoFinanciero));
                    } else {
                        return notFound("Producto financiero no encontrado");
                    }
                })
                .exceptionally(throwable -> {
                    return internalServerError("Error al consultar el producto financiero: " + throwable.getMessage());
                });
    }

    public CompletionStage<Result> listar(String idCliente) {
        return productoFinancieroService.listar(idCliente)
                .thenApply(productos -> {
                    return ok(Json.toJson(productos));
                });
    }
}
