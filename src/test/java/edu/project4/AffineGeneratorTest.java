package edu.project4;

import edu.project4.transformation.AffineGenerator;
import edu.project4.transformation.AffineUtils;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AffineGeneratorTest {
    @Test
    @DisplayName("Test that getListOfAffineTransformations returns a list with the correct number of transformations")
    void testThatGetListOfAffineTransformationsReturnsCorrectNumber() {
        // given
        int numberOfTransformations = 5;

        // when
        List<AffineGenerator> transformationsList = AffineUtils.getListOfAffineTransformations(numberOfTransformations);

        // then
        assertThat(transformationsList).hasSize(numberOfTransformations);
    }
}
