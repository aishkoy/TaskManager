package utils;

import com.sun.net.httpserver.HttpExchange;
import models.CalendarDay;
import models.CalendarGenerator;
import models.Task;
import services.TaskService;

import java.time.LocalDate;
import java.util.Map;

import static utils.ResponseWriter.showError;
import static utils.UrlFormParser.safeParseInt;

public class ModelUtils {
    private ModelUtils() {}
    public static CalendarDay getCalendarDay(HttpExchange exchange){
        Map<String, Object> data = CalendarGenerator.generateCalendarData();
        data.put("tasks", TaskService.getTasks());

        String query = HttpUtils.getQueryParams(exchange);
        Map<String, Object> queryParams = UrlFormParser.parseUrlEncoded(query, "&");

        if(query.isEmpty() || queryParams.isEmpty() || !queryParams.containsKey("dayId")) {
            showError(exchange, data,"index.ftlh", "Неверный query запрос");
            return null;
        }

        int dayNumber = safeParseInt(queryParams.get("dayId").toString());

        if (dayNumber <= 0 || dayNumber > 31) {
            showError(exchange, data,"index.ftlh", "Некорректный день");
            return null;
        }

        boolean isToday = dayNumber == LocalDate.now().getDayOfMonth();
        return new CalendarDay(dayNumber, isToday);
    }

    public static Task getTask(HttpExchange exchange) {
        Map<String, Object> data = CalendarGenerator.generateCalendarData();
        data.put("tasks", TaskService.getTasks());

        String query = HttpUtils.getQueryParams(exchange);
        Map<String, Object> queryParams = UrlFormParser.parseUrlEncoded(query, "&");

        if(query.isEmpty() || queryParams.isEmpty() || !queryParams.containsKey("taskId")) {
            showError(exchange, data,"index.ftlh", "Неверный query запрос");
            return null;
        }

        int taskNum = safeParseInt(queryParams.get("taskId").toString());
        if (taskNum <= 0) {
            showError(exchange, data,"index.ftlh", "Неправильный id задачи");
            return null;
        }

        Task task = TaskService.getTaskById(taskNum);
        if(task == null) {
            showError(exchange, data,"index.ftlh", "Неправильный id задачи");
            return null;
        }
        return task;
    }
}
