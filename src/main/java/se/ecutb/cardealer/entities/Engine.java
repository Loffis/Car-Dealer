package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.io.Serializable;

import static se.ecutb.cardealer.constants.Messages.*;

@Data
@Builder
public class Engine implements Serializable {

    private static final long serialVersionUID = -1907638875128888095L;

    @Id
    private String id;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 10, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 2000, message = WRONG_MAX_VALUE_MSG)
    private int effect;         // kw
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 1, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 24, message = WRONG_MAX_VALUE_MSG)
    private int cylinders;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 49, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 12000, message = WRONG_MAX_VALUE_MSG)
    private int displacement;   // cm3
    @Min(value = 10, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 2000, message = WRONG_MAX_VALUE_MSG)
    private int torque;         // Nm
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String fuel;

}
