package kz.epam.javalab22.bar.entity;

import java.io.InputStream;

/**
 * @author vten
 */
public class Image {

    private int id;
    private InputStream inputStream;
    private long inputStreamLength;

    public Image(InputStream inputStream, long inputStreamLength) {
        this.inputStream = inputStream;
        this.inputStreamLength = inputStreamLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getInputStreamLength() {
        return inputStreamLength;
    }

    public void setInputStreamLength(int inputStreamLength) {
        this.inputStreamLength = inputStreamLength;
    }
}
