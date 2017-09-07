package kz.epam.javalab22.bar.entity;

/**
 * Created by vten on 07.09.2017.
 */
public class CocktailName {
    private int id;
    private String nameRu;
    private String nameEn;

    public CocktailName(int id, String nameRu, String nameEn) {
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

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
