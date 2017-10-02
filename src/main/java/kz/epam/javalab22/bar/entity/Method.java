package kz.epam.javalab22.bar.entity;

/**
 * @author vten
 */
public class Method {

    private int id;
    private String nameRu;
    private String nameEn;

    public Method(int id, String nameRu, String nameEn) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public int getId() {
        return id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }
}
