package edu.project4.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PixelList {

    private final int height;
    private final int width;

    private final boolean hasVerticalSymmetry;
    private final boolean hasHorizontalSymmetry;

    private List<List<Pixel>> pixelList;

    public PixelList(int height, int width, boolean hasVerticalSymmetry, boolean hasHorizontalSymmetry) {
        this.height = height;
        this.width = width;

        this.hasVerticalSymmetry = hasVerticalSymmetry;
        this.hasHorizontalSymmetry = hasHorizontalSymmetry;

        initializePixelsGrid();
    }

    private void initializePixelsGrid() {
        pixelList = IntStream.range(0, height)
            .mapToObj(row -> IntStream.range(0, width)
                .mapToObj(col -> (Pixel) null)
                .collect(Collectors.toList()))
            .collect(Collectors.toList());

        generateSymmetricPixels(hasVerticalSymmetry, hasHorizontalSymmetry);
    }

    private void generateSymmetricPixels(boolean vertical, boolean horizontal) {
        int halfHeight = calculateHalf(height);
        int halfWidth = calculateHalf(width);

        for (int i = 0; i < (horizontal ? halfHeight : height); i++) {
            for (int j = 0; j < (vertical ? halfWidth : width); j++) {
                Pixel currentPixel = new Pixel();

                pixelList.get(i).set(j, currentPixel);

                if (vertical) {
                    int mirrorWidthIndex = width - j - 1;
                    pixelList.get(i).set(mirrorWidthIndex, currentPixel);
                }

                if (horizontal) {
                    int mirrorHeightIndex = height - i - 1;
                    pixelList.get(mirrorHeightIndex).set(j, currentPixel);

                    if (vertical) {
                        int mirrorWidthIndex = width - j - 1;
                        pixelList.get(mirrorHeightIndex).set(mirrorWidthIndex, currentPixel);
                    }
                }
            }
        }
    }

    private int calculateHalf(int size) {
        return (size + 1) / 2;
    }

    public Pixel getPixel(double x, double y) {
        return pixelList.get((int) x).get((int) y);
    }

    public Pixel getPixel(Point p) {
        return getPixel(p.x(), p.y());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
