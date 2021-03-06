package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.io.Serializable;

import static se.ecutb.cardealer.constants.Messages.*;
import static se.ecutb.cardealer.constants.RegExp.*;

@Data
@Builder
public class Wheels implements Serializable {

    private static final long serialVersionUID = -6908900047840143258L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 64, message = WRONG_SIZE_MSG)
    private String rimMaterial;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 64, message = WRONG_SIZE_MSG)
    private String rimColor;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 13, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 24, message = WRONG_MAX_VALUE_MSG)
    private int rimSizeFront;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 13, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 24, message = WRONG_MAX_VALUE_MSG)
    private int rimSizeRear;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 135, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 355, message = WRONG_MAX_VALUE_MSG)
    private int tireWidthFront;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 135, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 355, message = WRONG_MAX_VALUE_MSG)
    private int tireWidthRear;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 30, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 80, message = WRONG_MAX_VALUE_MSG)
    private int tireProfileFront;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Min(value = 30, message = WRONG_MIN_VALUE_MSG)
    @Max(value = 80, message = WRONG_MAX_VALUE_MSG)
    private int tireProfileRear;
    @NotNull(message = EMPTY_FIELD_MSG)
    private char tireSpeedRating;

}
