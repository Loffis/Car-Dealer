package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static se.ecutb.cardealer.constants.Messages.EMPTY_FIELD_MSG;
import static se.ecutb.cardealer.constants.Messages.WRONG_SIZE_MSG;

@Data
@Builder
public class Engine implements Serializable {

    private static final long serialVersionUID = -1907638875128888095L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 10, max = 2000, message = WRONG_SIZE_MSG)
    private int effect;         // kw
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 1, max = 24, message = WRONG_SIZE_MSG)
    private int cylinders;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 49, max = 12000, message = WRONG_SIZE_MSG)
    private int displacement;   // cm3
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 10, max = 2000, message = WRONG_SIZE_MSG)
    private int torque;         // Nm
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 30, message = WRONG_SIZE_MSG)
    private String fuel;

}
