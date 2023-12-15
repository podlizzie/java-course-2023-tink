package edu.hw10.task1test;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record MyRecord(@NotNull @Min(minVal = 45) int weight,
                       @Max(maxVal = 160) int height) {
}
