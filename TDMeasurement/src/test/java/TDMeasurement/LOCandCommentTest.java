package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.HalsteadMetrics;
import TDMeasurement.MaintainabilityIndexService.controller.LOCandComment;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LOCandCommentTest {
    String pathFile ="C:\\Users\\Octanty\\Documents\\Course\\Masaryk\\thesis\\microservice_technicaldebt\\TDMeasurement\\src\\main\\java\\TDMeasurement\\MaintainabilityIndexService\\controller\\CyclomaticComplexity.java";

    @Test
    public void testGetLineOfCode() throws IOException {
        LOCandComment loc = new LOCandComment();
        int lineOfCode = loc.getLineOfCode(pathFile);
        assertEquals(61, lineOfCode);
    }

    @Test
    public void testGetComment() throws IOException {
        LOCandComment comment = new LOCandComment();
        int numComment = comment.getCommentNumber(pathFile);
        assertEquals(2, numComment);
    }

}
