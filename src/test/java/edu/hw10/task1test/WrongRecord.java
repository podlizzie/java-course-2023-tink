package edu.hw10.task1test;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public record WrongRecord(@Max(maxVal = 45) @Min(minVal = 55) int weight,
                          @Max(maxVal = 160) int height) {
}
