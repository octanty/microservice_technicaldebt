package com.technicaldebtmeasurement.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/sqaleServiceFallBack")
    public String sqaleServiceFallBackMethod() {
        return "Sqale Service is taking longer than Expected." +
                "Please try again later";
    }

    @GetMapping("/maintainabilityServiceFallBack")
    public String maintainabilityServiceFallBackMethod() {
        return "Maintainability Index Service is taking longer than Expected." +
                "Please try again later";
    }

}
