package se.ecutb.cardealer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cardealer.entities.Engine;
import se.ecutb.cardealer.service.EngineService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/engines")
@Slf4j
public class EngineController {

    @Autowired
    private EngineService engineService;

    @GetMapping
    public ResponseEntity<List<Engine>> findAllEngines(){
        return ResponseEntity.ok(engineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Engine> findEngineById(@PathVariable String id){
        return ResponseEntity.ok(engineService.findById(id));
    }

    @GetMapping("/effect={effect}")
    public ResponseEntity<Engine> findEngineByEffect(@PathVariable int effect){
        return ResponseEntity.ok(engineService.findByEffect(effect));
    }

    @GetMapping("/cylinder={cylinder}")
    public ResponseEntity<Engine> findEngineByCylinder(@PathVariable int cylinder){
        return ResponseEntity.ok(engineService.findByCylinder(cylinder));
    }

    @GetMapping("/displacement={displacement}")
    public ResponseEntity<Engine> findEngineByDisplacement(@PathVariable int volume){
        return ResponseEntity.ok(engineService.findByDisplacement(volume));
    }

    @GetMapping("/torque={torque}")
    public ResponseEntity<Engine> findEngineByTorque(@PathVariable int turns){
        return ResponseEntity.ok(engineService.findByTorque(turns));
    }

    @GetMapping("/fuel={fuel}")
    public ResponseEntity<List<Engine>> findEngineByFuel(@PathVariable String fuel){
        return ResponseEntity.ok(engineService.findByFuel(fuel));
    }

    @PostMapping
    public void saveEngine(@Validated @RequestBody Engine engine){
        engineService.save(engine);
    }

    @PutMapping("/{id}")
    public void updateEngine(@PathVariable String id, @Validated @RequestBody Engine engine){
        engineService.update(id, engine);
    }

    @DeleteMapping("/{id}")
    public void deleteEngine(@PathVariable String id) {
        engineService.delete(id);
    }
}
