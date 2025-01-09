package com.cars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Car {
    private Producent producent;
    private boolean isAutomaticGear;
    private Market market;
    private Segment segment;
    private Dimension dimension;

    public static List<Car> filteringCar(List<Car> cars, String searchedProducentModel, Boolean searchedIsAutomaticGear, int searchedTrankCapacity) {
        List<Car> searchedCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getProducent().model().equals(searchedProducentModel) && searchedIsAutomaticGear.equals(car.isAutomaticGear)
                    && car.dimension.trankCapacity() == searchedTrankCapacity) {
                searchedCars.add(car);
            } else {
                System.out.println("Searched car not found");
            }
        }
        return searchedCars;
    }
}
