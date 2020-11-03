package se.ecutb.cardealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Wheels>> findAllWheels(){
        return ResponseEntity.ok(wheelsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wheels> findWheelsById(@PathVariable String id){
        return ResponseEntity.ok(wheelsService.findById(id));
    }

    @GetMapping("/rimMaterial={type}")
    public ResponseEntity<List<Wheels>> findWheelsByRimMaterial(@PathVariable String type){
        return ResponseEntity.ok(wheelsService.findByRimMaterial(type));
    }

    @GetMapping("/rimColor={color}")
    public ResponseEntity<List<Wheels>> findWheelsByRimColor(@PathVariable String color) {
        return ResponseEntity.ok(wheelsService.findByRimColor(color));
    }

    @GetMapping("/rimSize={size}")
    public ResponseEntity<List<Wheels>> findWheelsByRimSize(@PathVariable int size) {
        return ResponseEntity.ok(wheelsService.findByRimSize(size));
    }

    @GetMapping("/tireWidth={width}")
    public ResponseEntity<List<Wheels>> findWheelsByTireWidth(@PathVariable int width){
        return ResponseEntity.ok(wheelsService.findByTireWidth(width));
    }

    @GetMapping("/tireProfile={profile}")
    public ResponseEntity<List<Wheels>> findWheelsByTireProfile(@PathVariable int profile){
        return ResponseEntity.ok(wheelsService.findByTireProfile(profile));
    }

    @GetMapping("/tireSpeedRating={rating}")
    public ResponseEntity<Wheels> findWheelsByTireSpeedRating(@PathVariable char rating){
        return ResponseEntity.ok(wheelsService.findByTireSpeedRating(rating));
    }

    @PostMapping
    public ResponseEntity<Wheels> saveWheels(@Validated @RequestBody Wheels wheels){
        return ResponseEntity.ok(wheelsService.save(wheels));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateWheels(@PathVariable String id, @Validated @RequestBody Wheels wheels){
        wheelsService.update(id, wheels);
    }

    @DeleteMapping("/{id}")
    public void deleteWheels(@PathVariable String id){
        wheelsService.delete(id);
    }

}
