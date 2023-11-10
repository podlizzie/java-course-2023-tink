package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Task2 {
    private static final int DAY_13 = 13;

    private Task2() {

    }

    /**
     * Finds all Fridays falling on the 13th in a given year.
     *
     * @param year year to search Fridays.
     * @return a list of strings representing the Fridays falling on the 13th in the given year.
     */
    public static List<String> findFridaysOf13(String year) {
        List<String> fridaysOf13 = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(year + "-01-13");
        LocalDate endDate = LocalDate.parse(year + "-12-13");

        LocalDate date = startDate;
        while (date.isBefore(endDate)) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysOf13.add(date.toString());
            }
            date = date.plusMonths(1);
        }
        if (endDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
            fridaysOf13.add(endDate.toString());
        }

        return fridaysOf13;
    }

    /**
     * Uses TemporalAdjuster to find the next nearest Friday 13 for the given date.
     *
     * @param date the date to search for the next nearest Friday 13.
     * @return a LocalDate representing the next Friday 13 after the given date.
     */
    public static LocalDate findNextFridayOf13(@NotNull LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).withDayOfMonth(DAY_13);
    }
}

