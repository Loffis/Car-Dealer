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
    public List<Car> findAll(String regNumber ,String brand, String model, String yearModel, String weight, String seats,
            String equipment, String status, boolean sortByModel , boolean sortByWeight, boolean sortBySeats,
                             boolean sortByYear, boolean sortByStatus){
        log.info("Requesting to find all cars...");
        log.info("Fresh data");
        var cars = carRepository.findAll();

        if(regNumber != null){
            cars = cars.stream()
                    .filter(car -> car.getRegistrationNumber().toLowerCase().contains(regNumber.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (brand!= null){
            log.info("Search by brand " + brand);
            cars = cars.stream()
                    .filter(car -> car.getBrand().toLowerCase().contains(brand.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if(model != null){
            log.info("Search by model " + model);
            cars = cars.stream()
                    .filter(car -> car.getModel().toLowerCase().contains(model.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (yearModel != null){
            log.info("Search by yearmodel " + yearModel);
            int yearModelInt = 0;
            try {
                yearModelInt = Integer.parseInt(yearModel);
            } catch (NumberFormatException e) {
                log.error("This is not a correct year. ERROR: " + e);
            }
            int finalYearModelInt = yearModelInt;
            cars = cars.stream()
                    .filter(car -> car.getYearModel() == finalYearModelInt)
                    .collect(Collectors.toList());
        }

        if (weight != null){
            log.info("Search by weight " + weight);
            int weightInt = 0;
            try {
                weightInt = Integer.parseInt(weight);
            } catch (NumberFormatException e) {
                log.error("This is not a correct weight. ERROR: " + e);
            }
            int finalWeightInt = weightInt;
            cars = cars.stream()
                    .filter(car -> car.getWeight() == finalWeightInt)
                    .collect(Collectors.toList());
        }

        if (seats != null) {
            log.info("Search by number of seats: " + seats);
            int seatsInt = 0;
            try {
                seatsInt = Integer.parseInt(seats);
            } catch (NumberFormatException e) {
                log.error("This is not a correct number of seats. ERROR: " + e);
            }
            int finalSeatsInt = seatsInt;
            cars = cars.stream()
                    .filter(car -> car.getSeats() == finalSeatsInt)
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
            cars = cars.stream()
                    .filter(car -> car.getEquipment().contains(equipment.toLowerCase()))
                    .collect(Collectors.toList());
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
