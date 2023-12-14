package edu.project4;

import edu.project4.entity.Point;
import edu.project4.transformation.DiskFunction;
import edu.project4.transformation.HeartFunction;
import edu.project4.transformation.RombFunction;
import edu.project4.transformation.SinusoidalFunction;
import edu.project4.transformation.SphericalFunction;
import edu.project4.transformation.SwirlFunction;
import edu.project4.transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransformationTest {
    @Test
    @DisplayName("Test that DiskFunction returned valid transformation")
    void testThatDiskFunctionReturnedValidTransformation() {
        // given
        Point point = new Point(1, 1);
        List<Transformation> transformation = List.of(new SwirlFunction(), new DiskFunction(), new SinusoidalFunction(),
            new HeartFunction(), new RombFunction(), new SphericalFunction()
        );

        // when
        for (Transformation value : transformation) {
            Point result = value.apply(point);
            //then
            assertThat(result).isNotNull();
        }
    }

}
