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

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Cacheable(value = "carCache")
    public List<Car> findAll(String name, boolean sortByYear){
        log.info("Requesting to find all cars...");
        log.info("Fresh data");
        var cars = carRepository.findAll();
        if (name!= null){
            cars = cars.stream()
                    .filter(car -> car.getBrand().contains(name))
                    .collect(Collectors.toList());
        }
        if (sortByYear) {
            cars.sort(Comparator.comparing(Car::getYearModel));
        }
        return cars;
    }
    @Cacheable(value = "carCache", key = "#id")
    public Car findbyId(String id) {
        return carRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Can't find the car %s by id", id)));
    }
    /*Hitta en lista av (helst) tillgängliga bilar.
    Inte helt 100% säker om det funkar.
    Loffe's input required!
    */
    @Cacheable(value = "carCache")
    public List<Car> findByStatus() {
        var cars = carRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getStatus().equals("available")) // ändrade "==" till equals. Borde nog kunna funka!
                .collect(Collectors.toList());
        return cars;
    }

    @Cacheable(value = "carCache")
    public List<Car> findByBrand(String brand) {
        var cars = carRepository.findAll();
        cars = cars.stream()
                .filter(car -> car.getBrand().toLowerCase().contains(brand))
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
    }


    @CachePut(value = "carCache", key = "#result.id")
    public Car save(Car car){
        return carRepository.save(car);
    }

    @CachePut(value = "carCache", key = "#id")
    public void update(String id, Car car) {
        //Ska status enbart updateras eller mer?
        //Jaa... uhm. Måste fundera på den!
        //Man kan ju byta däck på en bil. Ja motor också, men ganska otroligt.
        //Tillbehör! De kan ändras.
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
