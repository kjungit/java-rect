package spring.singletone_1;

public class Settings {
    private static Settings instance;
    private String theme = "dark";

    private Settings() {};

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    String getTheme() {
        return theme;
    }

    void setTheme(String t) {
        this.theme = t;
    }
}
