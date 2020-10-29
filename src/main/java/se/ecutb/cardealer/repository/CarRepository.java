package se.ecutb.cardealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.cardealer.entities.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    Optional<Car> findByModel(String model);
}
