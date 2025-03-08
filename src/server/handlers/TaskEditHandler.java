package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import models.CalendarDay;
import models.CalendarGenerator;
import models.Task;
import models.enums.TaskType;
import services.Cookie;
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
import static utils.UrlFormParser.*;

public class TaskEditHandler {
    private TaskEditHandler() {}

    public static void handlePost(HttpExchange exchange) {
        Map<String, Object> data = UrlFormParser.parseFormData(exchange);

        String taskId = sanitizeField(data.get("taskId"));
        String dayNum = sanitizeField(data.get("dayNum"));
        String title = sanitizeField(data.get("title"));
        String taskType = sanitizeField(data.get("taskType"));
        String description = sanitizeField(data.get("description"));

        Task task = ModelUtils.getTask(exchange);
        if(task == null) return;

        CalendarDay calendarDay = ModelUtils.getCalendarDay(exchange);
        if(calendarDay == null) return;

        data.put("day", calendarDay);
        data.put("task", task);
        if(areFieldsValid(exchange, data,"edit-task.ftlh", taskId, dayNum, title, taskType)) return;

        if(Arrays.stream(TaskType.values()).noneMatch(t -> t.name().equals(taskType))){
            data.put("day", calendarDay);
            showError(exchange, data, "edit-task.ftlh", "Несуществующий тип задачи!");
            return;
        }

        int day = safeParseInt(dayNum);
        if(day <= 0 || day > 31){
            data.put("day", calendarDay);
            showError(exchange, data, "add-task.ftlh", "Несуществующий день в марте!");
            return;
        }

        if(day < LocalDate.now().getDayOfWeek().getValue()){
            data.put("day", calendarDay);
            showError(exchange, data, "add-task.ftlh", "Вы не можете редактировать просроченные задачи!");
            return;
        }

        if(day != calendarDay.getDay()){
            data = CalendarGenerator.generateCalendarData();
            data.put("tasks", TaskService.getTasks());
            data.put("day", calendarDay);
            showError(exchange, data, "index.ftlh", "Вы не можете редактировать задачи на другой день, не находясь на его странице!");
            return;
        }

        task.setTaskType(TaskType.valueOf(taskType));
        task.setTitle(title);
        task.setDescription(description);
        TaskService.saveTasks();
        HttpUtils.setSuccessCookie(exchange, "Вы успешно отредактировали задачу!");
        HttpUtils.redirect303(exchange, "/day?dayId=" + dayNum);
    }

    public static void handleGet(HttpExchange exchange) {
        Map<String, Object> cookies = Cookie.parse(Cookie.getCookie(exchange));
        Map<String, Object> data = new HashMap<>();

        Task task = ModelUtils.getTask(exchange);
        if (task == null) return;


        CalendarDay calendarDay = ModelUtils.getCalendarDay(exchange);
        if (calendarDay == null) return;
        data.put("day", calendarDay);

        int currentDay = LocalDate.now().getDayOfMonth();
        data.put("currentDay", currentDay);

        if(calendarDay.getDay() < currentDay) {
            data.put("tasks", TaskService.getTasks());
            showError(exchange, data, "day.ftlh", "Вы не можете редактировать просроченные задачи!");
            return;
        }

        data.put("success", cookies.get("successMessage"));
        data.put("task", task);
        ResponseWriter.renderTemplate(exchange, "edit-task.ftlh", data);
    }
}
