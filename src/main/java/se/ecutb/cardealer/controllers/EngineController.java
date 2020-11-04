package se.ecutb.cardealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cardealer.entities.Engine;
import se.ecutb.cardealer.service.EngineService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/engines")
public class EngineController {

    @Autowired
    private EngineService engineService;

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Engine>> findAllEngines(){
        return ResponseEntity.ok(engineService.findAll());
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Engine> findEngineById(@PathVariable String id){
        return ResponseEntity.ok(engineService.findById(id));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/effect={effect}")
    public ResponseEntity<Engine> findEngineByEffect(@PathVariable int effect){
        return ResponseEntity.ok(engineService.findByEffect(effect));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/cylinder={cylinder}")
    public ResponseEntity<Engine> findEngineByCylinder(@PathVariable int cylinder){
        return ResponseEntity.ok(engineService.findByCylinder(cylinder));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/displacement={volume}")
    public ResponseEntity<Engine> findEngineByDisplacement(@PathVariable int volume){
        return ResponseEntity.ok(engineService.findByDisplacement(volume));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/torque={turns}")
    public ResponseEntity<Engine> findEngineByTorque(@PathVariable int turns){
        return ResponseEntity.ok(engineService.findByTorque(turns));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/fuel={fuel}")
    public ResponseEntity<List<Engine>> findEngineByFuel(@PathVariable String fuel){
        return ResponseEntity.ok(engineService.findByFuel(fuel));
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PostMapping
    public void saveEngine(@Validated @RequestBody Engine engine){
        engineService.save(engine);
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public void updateEngine(@PathVariable String id, @Validated @RequestBody Engine engine){
        engineService.update(id, engine);
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public void deleteEngine(@PathVariable String id) {
        engineService.delete(id);
    }
}
