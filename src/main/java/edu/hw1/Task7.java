package edu.hw1;

public class Task7 {
    private Task7() {

    }

    public static int rotateRight(int num, int shift) {
        String binaryNum = Integer.toBinaryString(num);
        int reallyShift = shift % binaryNum.length();
        String shiftPart = binaryNum.substring(binaryNum.length() - reallyShift);
        String remainingPart = binaryNum.substring(0, binaryNum.length() - reallyShift);
        return Integer.parseInt(shiftPart + remainingPart, 2);
    }

    public static int rotateLeft(int num, int shift) {
        String binaryNum = Integer.toBinaryString(num);
        int reallyShift = shift % binaryNum.length();
        String shiftPart = binaryNum.substring(reallyShift);
        String remainingPart = binaryNum.substring(0, reallyShift);
        return Integer.parseInt(shiftPart + remainingPart, 2);
    }

}
