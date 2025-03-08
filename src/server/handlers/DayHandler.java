package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import models.CalendarDay;
import services.Cookie;
import services.TaskService;
import utils.ModelUtils;
import utils.ResponseWriter;
import java.util.HashMap;
import java.util.Map;


public class DayHandler {
    private DayHandler(){}
    public static void handle(HttpExchange exchange){
        Map<String, Object> cookies = Cookie.parse(Cookie.getCookie(exchange));
        Map<String, Object> data = new HashMap<>();
        CalendarDay calendarDay = ModelUtils.getCalendarDay(exchange);
        if (calendarDay == null) return;

        data.put("success", cookies.get("successMessage"));
        data.put("tasks", TaskService.getTasksByDay(calendarDay.getDay()));
        data.put("day", calendarDay);
        ResponseWriter.renderTemplate(exchange, "day.ftlh", data);
    }
}
