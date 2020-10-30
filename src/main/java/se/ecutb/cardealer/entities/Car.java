package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Car implements Serializable {

    private static final long serialVersionUID = -7965663487015996420L;

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
