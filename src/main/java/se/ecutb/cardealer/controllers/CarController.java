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

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(@RequestParam(required = false) String regNumber,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String model,
                                                 @RequestParam(required = false) String yearModel,
                                                 @RequestParam(required = false) int weight,
                                                 @RequestParam(required = false) int seats,
                                                 @RequestParam(required = false) String equipment,
                                                 @RequestParam(required = false) String status,
                                                 @RequestParam(required = false) boolean sortByModel,
                                                 @RequestParam(required = false) boolean sortByWeight,
                                                 @RequestParam(required = false) boolean sortBySeats,
                                                 @RequestParam(required = false) boolean sortByYear,
                                                 @RequestParam(required = false) boolean sortByStatus){

        return ResponseEntity.ok(carService.findAll(regNumber ,name, model, yearModel, weight, seats, equipment, status,
                sortByModel, sortByWeight, sortBySeats, sortByYear, sortByStatus));
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable String id){
        return ResponseEntity.ok(carService.findbyId(id));
    }

    /*@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/status={status}")
    public ResponseEntity<List<Car>> findCarsByStatus(@PathVariable String status){
        return ResponseEntity.ok(carService.findByStatus(status));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/brand={brand}")
    public ResponseEntity<List<Car>> findCarsByBrand(@PathVariable String brand){
        return ResponseEntity.ok(carService.findByBrand(brand));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/model={model}")
    public ResponseEntity<Car> findCarByModel(@PathVariable String model){
        return ResponseEntity.ok(carService.findByModel(model));
    }

    //@Secured({"ROLE_USER", "ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/seats={seats}")
    public ResponseEntity<List<Car>> findCarsBySeats(@PathVariable int seats){
        return ResponseEntity.ok(carService.findBySeats(seats));
    }*/

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Car> saveCar(@Validated @RequestBody Car car){
        return ResponseEntity.ok(carService.save(car));
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCar(@PathVariable String id, @Validated @RequestBody Car car){
        carService.update(id, car);
    }

    //@Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable String id){
        carService.delete(id);
    }




}
