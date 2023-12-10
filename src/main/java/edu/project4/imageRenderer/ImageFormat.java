package edu.project4.imageRenderer;

// сохранение изображения на файловую систему
public enum ImageFormat {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg");

    private final String stringRepresentation;

    ImageFormat(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    @Override public String toString() {
        return stringRepresentation;
    }
}
