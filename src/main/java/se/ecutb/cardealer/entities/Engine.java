package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static se.ecutb.cardealer.constants.Messages.EMPTY_FIELD_MSG;

//Vad för egenskaper har en motor??

@Data
@Builder
public class Engine implements Serializable {

    private static final long serialVersionUID = -1907638875128888095L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private int effect;         // typ 10-2000 hp alt. 7.5-1491 kW
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private int cylinders;      // 1-24
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private int displacement;   // motorvolym 49-12000 cm3
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private int torque;         // vridmoment, har inga data just nu
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private String fuel;        // Ska vi sätta bränslet till Engine eller till Car?

}
