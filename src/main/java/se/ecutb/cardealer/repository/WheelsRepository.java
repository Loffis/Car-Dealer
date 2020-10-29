package se.ecutb.cardealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.cardealer.entities.Wheels;

@Repository
public interface WheelsRepository extends MongoRepository<Wheels, Repository>{

}
