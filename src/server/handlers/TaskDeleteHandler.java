package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import models.Task;
import services.TaskService;
import utils.HttpUtils;
import utils.ModelUtils;

public class TaskDeleteHandler{
    private TaskDeleteHandler() {}
    public static void handle(HttpExchange exchange) {
        Task task = ModelUtils.getTask(exchange);
        if (task == null) return;

        TaskService.removeTask(task);
        HttpUtils.setSuccessCookie(exchange, "Вы успешно удалили задачу!");
        HttpUtils.redirect303(exchange, "/day?dayId=" + task.getDayNum());
    }
}
