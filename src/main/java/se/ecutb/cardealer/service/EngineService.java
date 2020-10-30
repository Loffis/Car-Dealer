package se.ecutb.cardealer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.cardealer.entities.Engine;
import se.ecutb.cardealer.repository.EngineRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    @Cacheable(value = "engineCache")
    public List<Engine> findAll(){
        log.info("Request to find all engines");
        log.info("Fresh data...");
        var engines = engineRepository.findAll();
        return engines;
    }

    @Cacheable(value = "engineCache")
    public Engine findByEffect(int effect) {
        return engineRepository.findByEffect(effect)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the engine %s by effect", effect)));
    }

    @Cacheable(value = "engineCache")
    public Engine findByCylinder(int cylinder){
        return engineRepository.findByCylinders(cylinder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the engine %s by amount of cylinders", cylinder)));
    }

    @Cacheable(value = "engineCache")
    public Engine findByDisplacement(int volume) {
        return engineRepository.findByDisplacement(volume)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the engine %s by volume", volume)));
    }
    @Cacheable(value = "engineCache")
    public Engine findByTorque(int turns) {
        return engineRepository.findByTorque(turns)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the engine %s", turns)));
    }

    @Cacheable(value = "engineCache")
    public List<Engine> findByFuel(String fuel) {
        var engines = engineRepository.findAll();
        engines = engines.stream()
                .filter(engine -> engine.getFuel().toLowerCase() == fuel.toLowerCase())
                .collect(Collectors.toList());
        return engines;
    }

    @CachePut(value = "engineCache", key = "#id")
    public void update(String id, Engine engine) {
        if(!engineRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find engine %s by id", id));
        }
        engine.setId(id);
        engineRepository.save(engine);
    }
    @CacheEvict(value = "engineCache", key = "#id")
    public void delete(String id){
        if(!engineRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the engine by id %s", id));
        }
        engineRepository.deleteById(id);
    }
}