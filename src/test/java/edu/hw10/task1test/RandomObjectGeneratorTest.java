package edu.hw10.task1test;

import edu.hw10.task1.RandomObjectGenerator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {
    @Test
    public void testThatValidMyRecordIsReturned() throws Exception {
        // Given
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // When
        MyRecord record = generator.nextObject(MyRecord.class);

        // Then
        assertNotNull(record);
        assertThat(record.height()).isLessThanOrEqualTo(160);
        assertThat(record.weight()).isBetween(45, 55);
    }

    @Test
    public void testThatValidPOJOIsReturnedWithNonNullProperties() throws Exception {
        // Given
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // When
        POJO pojo = generator.nextObject(POJO.class);

        // Then
        assertThat(pojo.getFirstName()).isNotNull();
        assertThat(pojo.getLastName()).isNotNull();
        assertTrue(pojo.getAge() >= 20);
    }

    @Test
    public void testThatExceptionIsThrownWhenParametersAreInvalid() {
        // Given
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // When & Then
        assertThatThrownBy(() -> generator.nextObject(WrongRecord.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Min value 55 must be less than max value 45 for field weight");
    }
}
