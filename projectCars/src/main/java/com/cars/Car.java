package com.cars;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Car {
    private Producent producent;
    private boolean isAutomaticGear;
    private Market market;
    private Segment segment;
    private ArrayList<Dimension> dimensions;
}
