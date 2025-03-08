package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import models.CalendarGenerator;
import services.TaskService;
import utils.ResponseWriter;

import java.util.Map;

public class MainHandler{
    private MainHandler() {}
    public static void handle(HttpExchange exchange) {
        String template = "index.ftlh";
        Map<String, Object> data = CalendarGenerator.generateCalendarData();

        data.put("tasks", TaskService.getTasks());
        ResponseWriter.renderTemplate(exchange, template, data);
    }
}
