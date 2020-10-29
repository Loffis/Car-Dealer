package se.ecutb.cardealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.cardealer.entities.Engine;

@Repository
public interface EngineRepository extends MongoRepository<Engine, String> {
}
