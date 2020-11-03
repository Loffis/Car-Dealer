package se.ecutb.cardealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cardealer.entities.Car;
import se.ecutb.cardealer.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(carService.findAll(name, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable String id){
        return ResponseEntity.ok(carService.findbyId(id));
    }

    @GetMapping("/status={status}")
    public ResponseEntity<List<Car>> findCarsByStatus(@PathVariable String status){
        return ResponseEntity.ok(carService.findByStatus(status));
    }

    @GetMapping("/brand={brand}")
    public ResponseEntity<List<Car>> findCarsByBrand(@PathVariable String brand){
        return ResponseEntity.ok(carService.findByBrand(brand));
    }

    @GetMapping("/model={model}")
    public ResponseEntity<Car> findCarByModel(@PathVariable String model){
        return ResponseEntity.ok(carService.findByModel(model));
    }

    @GetMapping("/seats={seats}")
    public ResponseEntity<List<Car>> findCarsBySeats(@PathVariable int seats){
        return ResponseEntity.ok(carService.findBySeats(seats));
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@Validated @RequestBody Car car){
        return ResponseEntity.ok(carService.save(car));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCar(@PathVariable String id, @Validated @RequestBody Car car){
        carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable String id){
        carService.delete(id);
    }




}
