package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private int yearModel;
    private int weight;
    private int seats;
    private List<String> equipment;
    private Engine engine;
    private Wheels wheels;
    //in stock, sold ,reserved, scrapped
    private String status;
}
