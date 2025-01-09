import com.cars.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarTests {

    @ParameterizedTest
    @MethodSource("provideMarketData")
    public void testMarketForCreatedCar(int expectedCountryNumber, Continent continent) {
        Car car = new CarFactory().createCar();
        Market market = car.getMarket();

        List<Country> countries = market.getCountries();
        assertThat(countries.size()).isGreaterThanOrEqualTo(expectedCountryNumber);
        assertThat(countries.stream().filter(e -> e.getContinent().equals(continent)).count()).isGreaterThanOrEqualTo(1);
    }

    private static Stream<Arguments> provideMarketData() {
        return Stream.of(
                Arguments.of(3, Continent.Europe),
                Arguments.of(3, Continent.Asia),
                Arguments.of(3, Continent.South_America)
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testMarketForCreatedCar(boolean expectedIsAutomaticGear) {
        CarFactory carFactory = new CarFactory();
        int carNumber = 15;
        List<Car> cars = carFactory.createCars(carNumber);
        cars.forEach(System.out::println);

        String searchedModel = "BMW";
        boolean searchedAutomaticGear = expectedIsAutomaticGear;
        int searchedTrankCapacity = 240;
        List<Car> filteredCars = Car.filteringCar(cars, searchedModel, searchedAutomaticGear, searchedTrankCapacity);
        for(Car car: filteredCars){
            assertEquals(expectedIsAutomaticGear, car.isAutomaticGear());
        }

    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    public void testMinimalDimenssionsForCreatedCar(int minHigh, int minWidth, int minTrankCapacity) {

        Car car = new CarFactory().createCar();
        Dimension dimension = car.getDimension();
        assertThat(dimension.high()).isGreaterThanOrEqualTo(minHigh);
        assertThat(dimension.width()).isGreaterThanOrEqualTo(minWidth);
        assertThat(dimension.trankCapacity()).isGreaterThanOrEqualTo(minTrankCapacity);
    }

    @ParameterizedTest
    @EnumSource(value = Continent.class, mode = EnumSource.Mode.EXCLUDE,
            names = {"Europe", "Asia", "North_America", "Australia", "South_America"})
    public void testAnyCarMadeInAntarcticaAndAfrica(Continent continent) {
        Car car = new CarFactory().createCar();
        Market market = car.getMarket();
        List<Country> countries = market.getCountries();
        List<Continent> continents = countries.stream()
                .map(Country::getContinent)
                .distinct()
                .collect(Collectors.toList());
        assertFalse(continents.contains(continent));
    }
}
