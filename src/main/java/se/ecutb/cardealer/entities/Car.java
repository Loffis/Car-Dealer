package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static se.ecutb.cardealer.constants.Messages.EMPTY_FIELD_MSG;
import static se.ecutb.cardealer.constants.Messages.WRONG_SIZE_MSG;

@Data
@Builder
public class Car implements Serializable {

    private static final long serialVersionUID = -7965663487015996420L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String brand;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String model;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 1800, max = 2021, message = WRONG_SIZE_MSG) // OBS! Hårdkodat max-år!
    private int yearModel;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 50, max = 4000, message = WRONG_SIZE_MSG)
    private int weight;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 1, max = 9)
    private int seats;
    private List<String> equipment;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private Engine engine;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private Wheels wheels;
    //in stock, sold, reserved, scrapped
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private String status;
}
