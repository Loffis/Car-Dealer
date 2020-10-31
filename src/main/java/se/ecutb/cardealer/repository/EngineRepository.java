package se.ecutb.cardealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.cardealer.entities.Engine;

import java.util.Optional;

@Repository
public interface EngineRepository extends MongoRepository<Engine, String> {
    Optional<Engine> findByEffect(int effect);

    Optional<Engine> findByCylinders(int number);

    Optional<Engine> findByDisplacement(int volume);

    Optional<Engine> findByTorque(int turns);
}
