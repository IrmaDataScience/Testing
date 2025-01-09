package com.cars;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarFactory {
    Random random = new Random();
    ArrayList<Dimension> dimensions;
    List<Producent> producers;
    List<Market> markets;
    List<Country> countries;
    Faker faker = new Faker();


    public CarFactory() {
        this.countries = getListOfCountries();
        this.dimensions = getListofDimensions();
        this.producers = getListOfProducents();
        this.markets = getListOfMarkets();
    }

    public Car createCar() {
        return new Car(getRandomProducent(),
                this.getIsAutomaticGear(),
                this.getRandomMarket(),
                this.getRandomSegment(),
                this.getRandomDimension());
    }

    public List<Car> createCars(int numberOfCars) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(createCar());
        }
        return cars;
    }

    private Dimension getRandomDimension() {
        return dimensions.get(random.nextInt(dimensions.size()));
    }

    private ArrayList<Dimension> getListofDimensions() {
        return IntStream.range(0, 9).mapToObj(i -> new Dimension(random.nextInt(1, 100) + i,
                        random.nextInt(1, 100) + i,
                        random.nextInt(150, 600) + 20))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean getIsAutomaticGear() {
        return random.nextBoolean();
    }

    private Segment getRandomSegment() {
        return Segment.values()[random.nextInt(Segment.values().length)];
    }

    private Producent getRandomProducent() {
        return producers.get(random.nextInt(producers.size()));
    }

    private List<Country> getListOfCountries() {
        List<Country> countries = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> countries.add(
                new Country(
                        Continent.values()[random.nextInt(Continent.values().length)],
                        faker.country().name(),
                        faker.country().countryCode3().toUpperCase().charAt(0))));
        return countries;
    }

    private List<Market> getListOfMarkets() {
        List<Market> markets = new ArrayList<>();
        Set<String> marketNames = Set.of("business", "cargo", "transport", "taxi", "bus");
        int countryNumber;
        for (String market : marketNames) {
            countryNumber = random.nextInt(3, countries.size());
            Set<Country> marketCountries = new HashSet<>();
            while (marketCountries.size() < countryNumber) {
                marketCountries.add(countries.get(random.nextInt(countries.size())));
            }
            markets.add(new Market(market, new ArrayList<>(marketCountries)));
        }
        return markets;
    }

    private Market getRandomMarket() {
        return markets.get(random.nextInt(markets.size()));
    }

    private List<Producent> getListOfProducents() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("C:\\Users\\Administrator\\IdeaProjects\\Testing\\projectCars\\src\\resources\\producers.json");
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> mapWithProducents = gson.fromJson(reader, type);

            producers = mapWithProducents.entrySet().stream()
                    .map(entry -> new Producent(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
            producers.stream().forEach(System.out::println);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return producers;
    }
}
