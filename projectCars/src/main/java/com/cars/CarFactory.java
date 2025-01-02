package com.cars;

import java.util.ArrayList;
import java.util.List;

class CarFactory {

    public static Car createCar(Producent producent, boolean isAutomaticGear,
                                Market market, Segment segment,
                                ArrayList<Dimension> dimensions) {

        return new Car(producent, isAutomaticGear, market, segment, dimensions);
    }
}
