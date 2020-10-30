package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 13, max = 24, message = WRONG_SIZE_MSG)
    private int rimSizeFront;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 13, max = 24, message = WRONG_SIZE_MSG)
    private int rimSizeRear;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_WIDTH_PATTERN,
            message = "Valid sizes are 135-355 mm, with steps of 10 mm.")
    private int tireWidthFront;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_WIDTH_PATTERN,
            message = "Valid sizes are 135-355 mm, with steps of 10 mm.")
    private int tireWidthRear;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_PROFILE_PATTERN, message = "Valid sizes are 30-80 with steps of 5.")
    private int tireProfileFront;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_PROFILE_PATTERN, message = "Valid sizes are 30-80 with steps of 5.")
    private int tireProfileRear;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_SPEED_RATING_PATTERN, message = "Valid ratings are B to Y.")
    private char tireSpeedRating;

}
