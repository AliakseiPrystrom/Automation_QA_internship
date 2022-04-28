package enums;

public enum ContentType {
    JSON ("application/json; charset=utf-8");

    private final String title;

    ContentType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "title='" + title + '\'' +
                '}';
    }

}
