package se.ecutb.cardealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.cardealer.entities.Wheels;

import java.util.Optional;

@Repository
public interface WheelsRepository extends MongoRepository<Wheels, String>{


    Optional<Wheels> findByTireSpeedRating(char rating);
}
