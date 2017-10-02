package kz.epam.javalab22.bar.entity;

/**
 * @author vten
 */
public class Glass {

    private int id;
    private String nameRu;
    private String nameEn;

    public Glass(int id) {
        this.id = id;
    }

    public Glass(int id, String nameRu, String nameEn) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }
}
