package com.cars;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Map<String,Character> countryMap = new HashMap<>();
        countryMap.put("Polska", 'P');
        countryMap.put("Niemcy", 'D');
        countryMap.put("Chiny", 'C');
        countryMap.put("Holandia", 'N');
        countryMap.put("Korea", 'K');

        List<Country> countries = new ArrayList<>();

        countryMap.forEach((name, code) -> countries.add(new Country(name, code)));
        countries.forEach(System.out::println);


        Producent toyota = new Producent("Toyota","Corolla");
        Map<String,String> producersMap = Map.of(
            "Audi","A4",
            "Kia","Stinger",
            "Cupra", "Formentor",
            "Ford","Fiesta"
        );

        List<Producent> producents = new ArrayList<>();
        producersMap.forEach((model,type)->producents.add(new Producent(model,type)));
        producents.forEach(System.out::println);

        Random random = new Random();
        List<Dimension> dimensions = IntStream.range(0, 9).mapToObj(i -> new Dimension(random.nextInt(100) + i,
                        random.nextInt(100) + i,
                        random.nextInt(1000) + 20))
                .collect(Collectors.toList());

        dimensions.forEach(System.out::println);

        List<Market> markets = new ArrayList<>();
        Set<String> marketNames = Set.of("business, cargo, transport, taxi, bus");
        Set<Country>  marketCountries =  List.of(countries.get(random.nextInt(0, countries.size()),
                countries.get(random.nextInt(0, countries.size()),
                        countries.get(random.nextInt(0, countries.size()))))));
        Set<Country>  marketCountries;
        for(Market market:markets){
            Set<Country>  marketCountries =  List.of(countries.get(random.nextInt(0, countries.size()),
                    countries.get(random.nextInt(0, countries.size()),
                            countries.get(random.nextInt(0, countries.size()))))));
        }
        List<Market>  marketList = IntStream.range(0, marketNames.size()).mapToObj(i -> new Market(marketNames(i), marketCountries(i))
        ).collect(Collectors.toList());


    }
}
