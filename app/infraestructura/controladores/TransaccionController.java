package infraestructura.controladores;

import aplicacion.dtos.TransaccionDTO;
import aplicacion.servicios.TransaccionService;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
public class TransaccionController extends Controller {

    private final TransaccionService transaccionService;

    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public TransaccionController(HttpExecutionContext httpExecutionContext, TransaccionService transaccionService) {
        this.httpExecutionContext = httpExecutionContext;
        this.transaccionService = transaccionService;
    }

    public CompletionStage<Result> crear(String idCliente, String idProducto, Http.Request request) {
        JsonNode json = request.body().asJson();
        TransaccionDTO transaccionDTO = Json.fromJson(json, TransaccionDTO.class);
        return transaccionService.crear(idCliente, transaccionDTO)
                .thenApplyAsync(transaccion ->
                        created(Json.toJson(transaccion)), httpExecutionContext.current()
                );
    }

    public CompletionStage<Result> listar(String idCliente, String idProducto) {
        return transaccionService.listar(idCliente, idProducto)
                .thenApply(transacciones -> {
                    return ok(Json.toJson(transacciones));
                });
    }

}