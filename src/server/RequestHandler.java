package server;

import com.sun.net.httpserver.HttpExchange;
import config.RouterConfig;
import server.enums.ContentType;
import server.enums.HttpStatus;
import server.handlers.MainHandler;
import server.handlers.RouteHandler;
import server.handlers.DayHandler;
import server.handlers.TaskHandler;
import utils.FileHandler;
import utils.ResponseWriter;

import java.io.IOException;
import java.nio.file.Path;

public class RequestHandler {
    public RequestHandler() {
        registerRoutes();
    }
    private void registerRoutes() {
        RouterConfig.registerGet("/", MainHandler::handle);
        RouterConfig.registerGet("/day", DayHandler::handle);
        RouterConfig.registerGet("/add-task", TaskHandler::handleGet);
        RouterConfig.registerPost("/add-task", TaskHandler::handlePost);
        RouterConfig.registerGet("/delete-task", TaskHandler::handleDelete);
    }

    public void handleRequest(HttpExchange exchange) throws IOException {
        RouteHandler handler = RouterConfig.getHandler(exchange);

        if(handler != null) {
            handler.handle(exchange);
            return;
        }

        Path filePath = FileHandler.resolveAndValidatePath(exchange.getRequestURI().getPath());
        if (filePath != null) {
            FileHandler.sendFile(exchange, filePath);
        } else {
            ResponseWriter.sendResponse(exchange, ContentType.PLAIN_TEXT, HttpStatus.NOT_FOUND);
        }
    }
}
