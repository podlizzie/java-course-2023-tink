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

    @Test
    @DisplayName("Test that generated coefficients respect the constraints")
    void testThatGeneratedCoefficientsRespectConstraints() {
        // given
        AffineGenerator affineGenerator = AffineUtils.generateValidCoefficients();

        // when
        double a = affineGenerator.getA();
        double b = affineGenerator.getB();
        double d = affineGenerator.getD();
        double e = affineGenerator.getE();

        // Conditions from the generateValidCoefficients method
        boolean condition1 = (a * a + b * b + d * d + e * e < 1 + Math.pow(a * e - b * d, 2));
        boolean condition2 = (b * b + e * e < 1);
        boolean condition3 = (a * a + d * d < 1);

        //then
        assertThat(condition1 && condition2 && condition3).isTrue();
    }
}
