package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

import static se.ecutb.cardealer.constants.Messages.*;
import static se.ecutb.cardealer.constants.RegExp.REGNUMBER_PATTERN;

@Data
@Builder
public class Car implements Serializable {

    private static final long serialVersionUID = -7965663487015996420L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = REGNUMBER_PATTERN, message = WRONG_REGNUMBER_MSG)
    private String registrationNumber;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String brand;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String model;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 1800, message = WRONG_SIZE_MSG)
    @Max(value = 2021, message = WRONG_SIZE_MSG) // OBS! Hårdkodat max-år!
    private int yearModel;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 50, message = WRONG_SIZE_MSG)
    @Max(value = 4000, message = WRONG_SIZE_MSG)
    private int weight;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 1, message = WRONG_SIZE_MSG)
    @Max(value = 9, message = WRONG_SIZE_MSG)
    private int seats;
    private List<String> equipment;
    @NotNull(message = EMPTY_FIELD_MSG)
    private Engine engine;
    @NotNull(message = EMPTY_FIELD_MSG)
    private Wheels wheels;
    //in stock, sold, reserved, scrapped
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private String status;
}
