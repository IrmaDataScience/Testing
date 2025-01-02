package com.cars;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Car {
    private Producent producent;
    private boolean isAutomaticGear;
    private Market market;
    private Segment segment;
    private ArrayList<Dimension> dimensions;

//    Utwórz metodę w klasie Car, która przeszuka wszystkie obiekty i wypisze na konsoli kraj - countryName, oraz oznaczenie kraju - countrySign (w formacie: Poland - P) 
//                gdzie producentem jest BMW, z automatyczną skrzynią biegów oraz pojemnością bagażnika - trankCapacity większą niż 300 litrów.

    public static void filteringCar(List<Car> cars, String searchedProducentModel, Boolean searchedIsAutomaticGear, int searchedTrankCapacity) {
        for (Car car : cars) {
            int count = 0;
            if (car.getProducent().model().equals(searchedProducentModel) && searchedIsAutomaticGear.equals(car.isAutomaticGear)) {
                System.out.println(car.getProducent().model());
                System.out.println(car.isAutomaticGear());
                count = (int) car.dimensions.stream().filter(dimension -> dimension.trankCapacity() > searchedTrankCapacity).count();
            }
            boolean thisCarMetRequirements = (count == 0) ? false : true;
            if (thisCarMetRequirements) {
                System.out.println(car.getProducent().toString());
                car.market.getCountries().forEach(System.out::println);
            }
        }
    }
}
