package com.cars;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        ArrayList<Dimension> dimensions = new ArrayList<>( IntStream.range(0, 9).mapToObj(i -> new Dimension(random.nextInt(100) + i,
                        random.nextInt(100) + i,
                        random.nextInt(100,600) + 20))
                .collect(Collectors.toList()));
        dimensions.forEach(System.out::println);

        Faker faker = new Faker();
        List<Country> countries = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> countries.add(
                new Country(
                        faker.country().name(),
                        faker.country().countryCode3().toUpperCase().charAt(0)
                )
        ));
        countries.forEach(System.out::println);

        List<Producent> producers = new ArrayList<>();
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("C:\\Users\\Administrator\\IdeaProjects\\Testing\\projectCars\\src\\resources\\producers.json");
            Type type = new TypeToken<Map<String, String>>(){}.getType();

            Map<String, String> mapWithProducents = gson.fromJson(reader, type);

            producers = mapWithProducents.entrySet().stream()
                    .map(entry -> new Producent(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

            producers.stream().forEach(System.out::println);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Market> markets = new ArrayList<>();
        Set<String> marketNames = Set.of("business", "cargo","transport", "taxi", "bus");

        int countryNumber;
        for (String market : marketNames) {
            countryNumber = random.nextInt(3,countries.size());
            Set<Country> marketCountries = new HashSet<>();
            while(marketCountries.size()<countryNumber) {
                marketCountries.add(countries.get(random.nextInt(countries.size())) );
            }
            markets.add(new Market(market,new ArrayList<>(marketCountries)));
        }
        markets.forEach(System.out::println);

        List<Car> cars = new ArrayList<>();
        Car toyotaCar = CarFactory.createCar( new Producent("BMW","jakies"),true,markets.get(0),
                Segment.medium,dimensions);

        cars.add(toyotaCar);
        for (int i = 0; i < 14; i++) {
            Car car = CarFactory.createCar(
                    producers.get(random.nextInt(producers.size())),
                    random.nextBoolean(),
                    markets.get(random.nextInt(markets.size())),
                    random.nextBoolean() ? Segment.medium : (random.nextBoolean() ? Segment.standard : Segment.premium),
                    dimensions
            );
            cars.add(car);
        }


        cars.forEach(System.out::println);
        String searchedModel = "BMW";
        boolean searchedAutomaticGear = true;
        int searchedTrankCapacity = 240;
        Car.filteringCar(cars,searchedModel, searchedAutomaticGear,searchedTrankCapacity);

    }



}
