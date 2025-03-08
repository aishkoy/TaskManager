package models;

public class CalendarDay {
    private final int num;
    private final boolean isToday;

    public CalendarDay(int day, boolean isToday) {
        this.num = day;
        this.isToday = isToday;
    }

    public int getDay() {
        return num;
    }

    public boolean isToday() {
        return isToday;
    }
}
