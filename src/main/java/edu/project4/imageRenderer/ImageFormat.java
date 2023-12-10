package edu.project4.imageRenderer;

// сохранение изображения на файловую систему
public enum ImageFormat {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg");

    private final String stringImgFormat;

    ImageFormat(String stringImgFormat) {
        this.stringImgFormat = stringImgFormat;
    }

    @Override public String toString() {
        return stringImgFormat;
    }
}
