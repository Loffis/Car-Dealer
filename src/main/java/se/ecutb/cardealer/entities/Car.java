package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

import static se.ecutb.cardealer.constants.Messages.EMPTY_FIELD_MSG;
import static se.ecutb.cardealer.constants.Messages.WRONG_SIZE_MSG;

@Data
@Builder
public class Car implements Serializable {

    private static final long serialVersionUID = -7965663487015996420L;
    int year = Year.now().getValue();

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String brand;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String model;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 1800, max = 2020, message = WRONG_SIZE_MSG)
    private int yearModel;
    private int weight;
    private int seats;
    private List<String> equipment;
    @DBRef
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private Engine engine;
    @DBRef
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private Wheels wheels;
    //in stock, sold ,reserved, scrapped
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private String status;
}
