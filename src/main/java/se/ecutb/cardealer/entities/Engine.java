package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;

//Vad för egenskaper har en motor??

@Data
@Builder
public class Engine {

    private int effect;
    private int horsepower;

}
