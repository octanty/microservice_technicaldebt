package TDMeasurement;

import TDMeasurement.MaintainabilityIndexService.controller.CyclomaticComplexity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CyclomaticComplexityTest {
    //todo: make path relative
    @Test
    public void testGetCCResult() {
        String path ="C:\\Users\\Octanty\\Documents\\Course\\Masaryk\\thesis\\microservice_technicaldebt\\TDMeasurement\\src\\main\\java\\TDMeasurement\\MaintainabilityIndexService\\controller\\CyclomaticComplexity.java";
        CyclomaticComplexity ccResult = new CyclomaticComplexity();
        int result = ccResult.getCCResult(path);
        assertEquals(24, result);
    }

    @Test
    public void testGetCCRateExtraHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(70);
        assertEquals("Extra High", rate);
    }

    @Test
    public void testGetCCRateVeryHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(46);
        assertEquals("Very High", rate);
    }

    @Test
    public void testGetCCRateHigh(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(30);
        assertEquals("High", rate);
    }

    @Test
    public void testGetCCRateNominal(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(15);
        assertEquals("Nominal", rate);
    }

    @Test
    public void testGetCCRateLow(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(7);
        assertEquals("Low", rate);
    }

    @Test
    public void testGetCCRateVeryLow(){
        CyclomaticComplexity ccRate = new CyclomaticComplexity();
        String rate = ccRate.getCCRate(3);
        assertEquals("Very Low", rate);
    }
}
