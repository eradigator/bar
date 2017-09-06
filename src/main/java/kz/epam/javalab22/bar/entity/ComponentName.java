package kz.epam.javalab22.bar.entity;

/**
 * Created by vten on 25.08.2017.
 */
public class ComponentName {
    private int id;
    private String en;
    private String ru;

    public ComponentName(int id) {
        this.id = id;
    }

    public ComponentName(String en, String ru) {
        this.en = en;
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public String getRu() {
        return ru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
