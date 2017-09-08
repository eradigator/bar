package kz.epam.javalab22.bar.entity;

/**
 * Created by vten on 08.09.2017.
 */
public class UIText {
    private int id;
    private String textRu;
    private String textEn;

    public UIText() {
    }

    public UIText(int id, String textRu, String textEn) {
        this.id = id;
        this.textRu = textRu;
        this.textEn = textEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextRu() {
        return textRu;
    }

    public void setTextRu(String textRu) {
        this.textRu = textRu;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }
}
