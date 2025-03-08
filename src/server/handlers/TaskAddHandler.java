package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import models.CalendarDay;
import models.CalendarGenerator;
import models.Task;
import models.enums.TaskType;
import services.TaskService;
import utils.HttpUtils;
import utils.ModelUtils;
import utils.ResponseWriter;
import utils.UrlFormParser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static utils.ResponseWriter.showError;
import static utils.UrlFormParser.safeParseInt;
import static utils.UrlFormParser.sanitizeField;
import static utils.UrlFormParser.areFieldsValid;

public class TaskAddHandler {
    private TaskAddHandler() {}

    public static void handlePost(HttpExchange exchange) {
        Map<String, Object> data = UrlFormParser.parseFormData(exchange);

        String dayNum = sanitizeField(data.get("dayNum"));
        String title = sanitizeField(data.get("title"));
        String taskType = sanitizeField(data.get("taskType"));
        String description = sanitizeField(data.get("description"));

        CalendarDay calendarDay = ModelUtils.getCalendarDay(exchange);
        if(calendarDay == null) return;

        data.put("day", calendarDay);
        if(areFieldsValid(exchange, data,"add-task.ftlh", dayNum, title, taskType)) return;

        if(Arrays.stream(TaskType.values()).noneMatch(t -> t.name().equals(taskType))){
            data.put("day", calendarDay);
            showError(exchange, data, "add-task.ftlh", "Несуществующий тип задачи!");
            return;
        }

        int day = safeParseInt(dayNum);
        if(day <= 0 || day > 31){
            data.put("day", calendarDay);
            showError(exchange, data, "add-task.ftlh", "Несуществующий день в марте!");
            return;
        }

        if(day != calendarDay.getDay()){
            data = CalendarGenerator.generateCalendarData();
            data.put("tasks", TaskService.getTasks());
            data.put("day", calendarDay);
            showError(exchange, data, "index.ftlh", "Вы не можете добавлять задачи на другой день, не находясь на его странице!");
            return;
        }

        if(day < LocalDate.now().getDayOfWeek().getValue()){
            data.put("day", calendarDay);
            showError(exchange, data, "add-task.ftlh", "Вы не можете создавать задачи в прошлом!");
            return;
        }

        TaskService.addTask(new Task(day, title, TaskType.valueOf(taskType), description));
        HttpUtils.setSuccessCookie(exchange, "Вы успешно создали новую задачу!");
        HttpUtils.redirect303(exchange, "/day?dayId=" + dayNum);
    }

    public static void handleGet(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();

        CalendarDay calendarDay = ModelUtils.getCalendarDay(exchange);
        if (calendarDay == null) return;

        data.put("tasks", TaskService.getTasksByDay(calendarDay.getDay()));
        data.put("day", calendarDay);
        ResponseWriter.renderTemplate(exchange, "add-task.ftlh", data);
    }
}
