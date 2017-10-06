package kz.epam.javalab22.bar.entity;

/**
 * @author vten
 */
public class ComponentName {
    private int id;
    private String nameRu;
    private String nameEn;
    private String anyLanguageName;

    public ComponentName(int id) {
        this.id = id;
    }

    public ComponentName(String anyLanguageName) {
        this.anyLanguageName = anyLanguageName;
    }

    public ComponentName(String nameRu, String nameEn) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public ComponentName(int id, String nameRu, String nameEn) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getAnyLanguageName() {
        return anyLanguageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
