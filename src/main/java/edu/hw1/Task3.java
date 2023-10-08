package edu.hw1;

public final class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            return false;
        }
        return findMin(arr1) > findMin(arr2) || findMax(arr1) < findMax(arr2);
    }

    private static int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
