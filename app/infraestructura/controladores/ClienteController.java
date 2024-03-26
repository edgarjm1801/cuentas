package infraestructura.controladores;

import aplicacion.dtos.ClienteDTO;
import aplicacion.servicios.ClienteService;
import com.fasterxml.jackson.databind.JsonNode;
import dominio.modelos.Cliente;
import play.mvc.*;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.Json;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public class ClienteController extends Controller {

    private final ClienteService clienteService;
    private final HttpExecutionContext httpExecutionContext;

    @Inject
    public ClienteController(ClienteService clienteService, HttpExecutionContext httpExecutionContext) {
        this.clienteService = clienteService;
        this.httpExecutionContext = httpExecutionContext;
    }

    public CompletionStage<Result> crear(Http.Request request) {
        JsonNode json = request.body().asJson();
        ClienteDTO clienteDTO = Json.fromJson(json, ClienteDTO.class);
        return clienteService.crear(clienteDTO).thenApplyAsync(cliente -> {
            return created(Json.toJson(cliente));
        }, httpExecutionContext.current());
    }

    public CompletionStage<Result> consultar(String identificacion) {
        return clienteService.consultar(identificacion)
                .thenApply(optCliente -> {
                    if (optCliente.isPresent()) {
                        Cliente cliente = optCliente.get();
                        return ok(Json.toJson(cliente));
                    } else {
                        return notFound("Cliente no encontrado");
                    }
                })
                .exceptionally(throwable -> {
                    return internalServerError("Error al consultar el cliente: " + throwable.getMessage());
                });

    }

    public CompletionStage<Result> eliminar(String id) {
        UUID uuid = UUID.fromString(id);
        return clienteService.eliminar(uuid)
                .thenApply(result -> ok("Cliente eliminado exitosamente"))
                .exceptionally(throwable -> {
                    return internalServerError("Error al eliminar el cliente: " + throwable.getMessage());
                });
    }

    public CompletionStage<Result> listar() {
        return clienteService.listar()
                .thenApply(clientes -> {
                    JsonNode jsonClientes = Json.toJson(clientes);
                    return ok(jsonClientes);
                })
                .exceptionally(throwable -> {
                    return internalServerError("Error al listar los clientes: " + throwable.getMessage());
                });

    }

}