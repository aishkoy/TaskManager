package utils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

public class CalendarGenerator {
    private CalendarGenerator() {}
    public static Map<String, Object> generateCalendarData(){
        Map<String, Object> model = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        Month month = Month.of(calendar.get(Calendar.MONTH) + 1);
        int year = calendar.get(Calendar.YEAR);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        firstWeekDay = firstWeekDay == Calendar.SUNDAY ? 7 : firstWeekDay - 1;
        String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));


        List<String> weekDays = new ArrayList<>(List.of("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"));
        List<List<CalendarDay>> weeks = new ArrayList<>();
        List<CalendarDay> week = new ArrayList<>();

        for(int i = 1; i < firstWeekDay; i++){
            week.add(new CalendarDay(0,false));
        }

        for(int day = 1; day <= daysInMonth ; day++){
            boolean isToday = day == currentDay;

            week.add(new CalendarDay(day,isToday));

            if((firstWeekDay + day - 1) % 7 == 0 || day == daysInMonth){
                weeks.add(week);
                week = new ArrayList<>();
            }
        }

        if(!weeks.isEmpty()){
            int lastWeekSize  = weeks.getLast().size();

            if(lastWeekSize < 7){
                for(int i = lastWeekSize; i < 7; i++){
                    weeks.getLast().add(new CalendarDay(0,false));
                }
            }
        }

        model.put("month", monthName);
        model.put("year", year);
        model.put("weekDays", weekDays);
        model.put("weeks", weeks);

        return model;
    }

    public static void main(String[] args) {
        System.out.println(CalendarGenerator.generateCalendarData().get("month"));

    }

    public static class CalendarDay {
        private final int day;
        private final boolean isToday;

        public CalendarDay(int day, boolean isToday) {
            this.day = day;
            this.isToday = isToday;
        }

        public int getDay() {
            return day;
        }

        public boolean isToday() {
            return isToday;
        }
    }
}