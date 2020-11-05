package se.ecutb.cardealer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.cardealer.entities.Wheels;
import se.ecutb.cardealer.repository.WheelsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WheelsService {
    private final WheelsRepository wheelsRepository;

    @Cacheable(value = "carCache")
    public List<Wheels> findAll(){
        log.info("Requesting to find all wheels...");
        log.info("Fresh data");
        var wheels = wheelsRepository.findAll();
        return wheels;
    }

    @Cacheable(value = "carCache", key = "#id")
    public Wheels findById(String id) {
        return wheelsRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("can't find the wheel %s by id", id)));
    }

    @Cacheable(value = "carCache")
    public List<Wheels> findByRimMaterial(String material) {
        var wheelsList = wheelsRepository.findAll();
        wheelsList = wheelsList.stream()
                .filter(wheels -> wheels.getRimMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
        return wheelsList;
    }

    @Cacheable(value = "carCache")
    public List<Wheels> findByRimColor(String color) {
        var wheelsList = wheelsRepository.findAll();
        wheelsList = wheelsList.stream()
                .filter(wheels -> wheels.getRimColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        return wheelsList;
    }

   @Cacheable(value = "carCache")
    public List<Wheels> findByRimSize(int size){
        var wheelsList = wheelsRepository.findAll();
        wheelsList = wheelsList.stream()
                .filter(wheels -> (wheels.getRimSizeFront() == size) ||
                        (wheels.getRimSizeRear() == size))
                .collect(Collectors.toList());
        return wheelsList;
   }


   @Cacheable(value = "carCache")
    public List<Wheels> findByTireWidth(int width){
        var wheelsList = wheelsRepository.findAll();
        wheelsList = wheelsList.stream()
                .filter(wheels -> wheels.getTireWidthFront() == width ||
                        wheels.getTireWidthRear() == width)
                .collect(Collectors.toList());
        return wheelsList;
   }

   @Cacheable(value = "carCache")
    public List<Wheels> findByTireProfile(int profile) {
        var wheelsList = wheelsRepository.findAll();
        wheelsList = wheelsList.stream()
                .filter(wheels -> wheels.getTireProfileFront() == profile ||
                        wheels.getTireProfileRear() == profile)
                .collect(Collectors.toList());
        return wheelsList;
   }
   @Cacheable(value = "carCache")
    public Wheels findByTireSpeedRating(char rating) {
        return wheelsRepository.findByTireSpeedRating(rating)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("can't find wheel %s by rating", rating)));
   }

   @CachePut(value = "carCache", key = "#result.id")
    public Wheels save(Wheels wheels) {
        return wheelsRepository.save(wheels);
   }

   @CachePut(value = "carCache", key = "#id")
    public void update(String id, Wheels wheels) {
        if (!wheelsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find car %s by id", id));
        }
        wheels.setId(id);
        wheelsRepository.save(wheels);
    }

   @CacheEvict(value = "carCache", key = "#id")
   public void delete(String id) {
        if(!wheelsRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find wheel %s by id", id));
        }
        wheelsRepository.deleteById(id);
   }

}
