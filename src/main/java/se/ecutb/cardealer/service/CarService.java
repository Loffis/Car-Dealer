package se.ecutb.cardealer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.cardealer.entities.Car;
import se.ecutb.cardealer.repository.CarRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Backup-plan: En sökmetod istället för flera

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Cacheable(value = "carCache")
    public List<Car> findAll(String regNumber ,String brand, String model, int yearModel, int weight, int seats,
            String equipment, String status, boolean sortByModel , boolean sortByWeight, boolean sortBySeats,
                             boolean sortByYear, boolean sortByStatus){
        log.info("Requesting to find all cars...");
        log.info("Fresh data");
        var cars = carRepository.findAll();

        if(regNumber != null){
            carRepository.findByRegistrationNumber(regNumber).
                    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Can't find the car %s by regnumber", regNumber)));
        }


        if (brand!= null){
            log.info("Search by brand " + brand);
            cars = cars.stream()
                    .filter(car -> car.getBrand().contains(brand))
                    .collect(Collectors.toList());
        }

        if(model != null){
            log.info("Search by model " + model);
            carRepository.findByModel(model)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Can't find the car %s by model", model)));
        }

        if (yearModel != 0){
            log.info("Search by yearmodel " + yearModel);
            cars = cars.stream()
                    .filter(car -> car.getYearModel() == yearModel)
                    .collect(Collectors.toList());
        }

        if (weight != 0){
            log.info("Search by weight " + weight);
            cars = cars.stream()
                    .filter(car -> car.getWeight() == weight)
                    .collect(Collectors.toList());
        }

        if (seats != 0) {
            log.info("Search by number of seats: " + seats);
            cars = cars.stream()
                    .filter(car -> car.getSeats() == seats)
                    .collect(Collectors.toList());
        }

        if (status != null) {
            log.info("Search by car that is " + status);
            cars = cars.stream()
                    .filter(car -> car.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        if (equipment != null) {
            log.info("Search by equipment " + equipment);
            cars.stream()
                    .filter(car -> car.getEquipment().contains(equipment));
        }

        if (sortByModel){
            log.info("Sorting by model");
            cars.sort(Comparator.comparing(Car::getModel));
        }

        if (sortByWeight){
            log.info("Sorting by weight");
            cars.sort(Comparator.comparing(Car::getWeight));
        }
        if (sortBySeats){
            log.info("Sorting by number of seats");
            cars.sort(Comparator.comparing(Car::getSeats));
        }
        if (sortByYear) {
            log.info("Sorting by yearmodel");
            cars.sort(Comparator.comparing(Car::getYearModel));
        }
        if (sortByStatus){
            log.info("Sorting by status");
            cars.sort(Comparator.comparing(Car::getStatus));
        }
        return cars;
    }
   @Cacheable(value = "carCache", key = "#id")
    public Car findbyId(String id) {
        return carRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find the car %s by id", id)));
    }
    /*@Cacheable(value = "carCache")
    public List<Car> findByStatus(String status) {
        var cars = carRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getStatus().equalsIgnoreCase(status)) // ändrade "==" till equals. Borde nog kunna funka!
                .collect(Collectors.toList());
        return cars;
    }

    @Cacheable(value = "carCache")
    public List<Car> findByBrand(String brand) {
        var cars = carRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        return cars;
    }

    @Cacheable(value = "carCache")
    public Car findByModel(String model) {
        return carRepository.findByModel(model)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find the car %s by model", model)));
    }

    @Cacheable(value = "carCache")
    public List<Car> findBySeats(int seats) {
        var cars = carRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getSeats() == seats)
                .collect(Collectors.toList());
        return cars;
    }*/


    @CachePut(value = "carCache", key = "#result.id")
    public Car save(Car car){
        return carRepository.save(car);
    }

    @CachePut(value = "carCache", key = "#id")
    public void update(String id, Car car) {
        if(!carRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find car %s by id", id));
        }
        car.setId(id);
        carRepository.save(car);
    }

    @CacheEvict(value = "carCache", key = "#id")
    public void delete(String id) {
        if (!carRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the car by id %s", id));
        }
        carRepository.deleteById(id);
    }
}
