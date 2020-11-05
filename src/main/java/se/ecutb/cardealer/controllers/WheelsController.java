package se.ecutb.cardealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cardealer.entities.Wheels;
import se.ecutb.cardealer.service.WheelsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wheels")
public class WheelsController {

    @Autowired
    private WheelsService wheelsService;

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Wheels>> findAllWheels(){
        return ResponseEntity.ok(wheelsService.findAll());
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Wheels> findWheelsById(@PathVariable String id){
        return ResponseEntity.ok(wheelsService.findById(id));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/rimMaterial={type}")
    public ResponseEntity<List<Wheels>> findWheelsByRimMaterial(@PathVariable String type){
        return ResponseEntity.ok(wheelsService.findByRimMaterial(type));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/rimColor={color}")
    public ResponseEntity<List<Wheels>> findWheelsByRimColor(@PathVariable String color) {
        return ResponseEntity.ok(wheelsService.findByRimColor(color));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/rimSize={size}")
    public ResponseEntity<List<Wheels>> findWheelsByRimSize(@PathVariable int size) {
        return ResponseEntity.ok(wheelsService.findByRimSize(size));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/tireWidth={width}")
    public ResponseEntity<List<Wheels>> findWheelsByTireWidth(@PathVariable int width){
        return ResponseEntity.ok(wheelsService.findByTireWidth(width));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/tireProfile={profile}")
    public ResponseEntity<List<Wheels>> findWheelsByTireProfile(@PathVariable int profile){
        return ResponseEntity.ok(wheelsService.findByTireProfile(profile));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/tireSpeedRating={rating}")
    public ResponseEntity<Wheels> findWheelsByTireSpeedRating(@PathVariable char rating){
        return ResponseEntity.ok(wheelsService.findByTireSpeedRating(rating));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Wheels> saveWheels(@Validated @RequestBody Wheels wheels){
        return ResponseEntity.ok(wheelsService.save(wheels));
    }

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateWheels(@PathVariable String id, @Validated @RequestBody Wheels wheels){
        wheelsService.update(id, wheels);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWheels(@PathVariable String id){
        wheelsService.delete(id);
    }
}
