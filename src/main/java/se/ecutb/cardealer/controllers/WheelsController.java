package se.ecutb.cardealer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ecutb.cardealer.service.WheelsService;

@RestController
@RequestMapping("/api/v1/wheels")
public class WheelsController {

    private WheelsService wheelsService;

}
