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
    String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 64, message = WRONG_LENGTH_MSG)
    String rimMaterial;     // steel, aluminium or magnesium
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 64, message = WRONG_LENGTH_MSG)
    String rimColor;        // a color
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = RIM_SIZE_PATTERN, message = "Valid sizes are 13-24 inches.")
    int rimSizeFront;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = RIM_SIZE_PATTERN, message = "Valid sizes are 13-24 inches.")
    int rimSizeRear;        // 13-24 inches
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_WIDTH_PATTERN,
            message = "Valid sizes are 135-355 mm, with steps of 10 mm.")
    int tireWidthFront;     // 135-355 mm. Steps of 10 mm.
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_WIDTH_PATTERN,
            message = "Valid sizes are 135-355 mm, with steps of 10 mm.")
    int tireWidthRear;      // 135-355 mm
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_PROFILE_PATTERN, message = "Valid sizes are 30-80 with steps of 5.")
    int tireProfileFront;   // 30-80 % of tire width. Steps of 5 %.
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_PROFILE_PATTERN, message = "Valid sizes are 30-80 with steps of 5.")
    int tireProfileRear;    // 30-80 % of tire width
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = TIRE_SPEED_RATING_PATTERN, message = "Valid ratings are B to Y.")
    char tireSpeedRating;   // B-Y. B means 50 km/h. Y means 300 km/h.

}
