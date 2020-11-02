package se.ecutb.cardealer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ecutb.cardealer.service.EngineService;

@RestController
@RequestMapping("/api/v1/engines")
public class EngineController {

    private EngineService engineService;
}
