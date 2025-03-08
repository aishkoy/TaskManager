package models.enums;

public enum TaskType {
    TASK("Обычная задача", "#3B82F6"),
    URGENT("Срочное дело", "#EF4444"),
    WORK("Работа", "#8B5CF6"),
    SHOPPING("Покупки", "#10B981"),
    OTHER("Прочее", "#6B7280");

    private final String name;
    private final String colorCode;

    TaskType(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getColorCode() {
        return colorCode;
    }

}
