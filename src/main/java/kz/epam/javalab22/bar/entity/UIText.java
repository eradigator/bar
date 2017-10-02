package kz.epam.javalab22.bar.entity;

/**
 * @author vten
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

    public String getTextRu() {
        return textRu;
    }

    public String getTextEn() {
        return textEn;
    }
}
