package TDMeasurement.MeasurementService.controller;

import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import TDMeasurement.MeasurementService.entity.GenericStatistic;
import TDMeasurement.MeasurementService.service.MeasurementService;
import TDMeasurement.MeasurementService.vo.DirResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

  /*  @PostMapping("/saveMeasurement")
    public GenericStatistic saveMeasurement() throws URISyntaxException, IOException {
        return measurementService.saveMeasurement();
    } */

    @PostMapping("/calculate")
    public String calculate() throws IOException, URISyntaxException {
        return measurementService.calculate();
    }

    @GetMapping("/saveToRepository")
    public String saveToRepository()  throws IOException, URISyntaxException {
        return measurementService.saveToRepository();
    }

    @GetMapping("/{id}")
    public GenericStatistic findByGenericStatisticID(@PathVariable("id") long genericStaticticID) {
        return measurementService.findByGenericStatisticID(genericStaticticID);
    }

}