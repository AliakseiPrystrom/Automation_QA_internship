package enums;

public enum ApiMethods {

    WALL_POST("/method/wall.post"),

    WALL_EDIT("/method/wall.edit"),

    WALL_ADD_COMMENT("/method/wall.createComment"),

    WALL_GET_LIKES("/method/wall.getLikes"),

    WALL_DELETE_POST("/method/wall.delete"),

    SERVER_PHOTO("/method/photos.getWallUploadServer"),

    SAVE_WALL_PHOTO("/method/photos.saveWallPhoto");

    private final String title;

    ApiMethods(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "apiMethods{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
