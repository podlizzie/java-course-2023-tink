package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String mixed) {
        StringBuilder normal = new StringBuilder();

        for (int i = 0; i < mixed.length(); i += 2) {
            if (i + 1 < mixed.length()) {
                normal.append(mixed.charAt(i + 1));
            }
            normal.append(mixed.charAt(i));
        }
        return normal.toString();
    }

}
