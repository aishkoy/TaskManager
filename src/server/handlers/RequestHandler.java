package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import config.RouterConfig;

import java.io.IOException;

public class RequestHandler {
    private void registerRoutes() {
        RouterConfig.registerGet("/", MainHandler::handle);
        RouterConfig.registerGet("/task", TaskHandler::handle);
    }

    public void handleRequest(HttpExchange exchange) throws IOException {
        RouteHandler handler = RouterConfig.getHandler(exchange);

        if(handler != null) {
            handler.handle(exchange);
            return;
        }
    }
}
