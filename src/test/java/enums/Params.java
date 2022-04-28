package enums;

public enum Params {
    STEP_3 ("multipart/form-data");

    private final String title;


    Params(String title) {
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Params{" +
                "title='" + title + '\'' +
                '}';
    }
}
