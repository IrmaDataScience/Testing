package com.cars;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Country {
    private Continent continent;
    private String countryName;
    private Character countrySign;

    @Override
    public String toString() {
        return "Country: " + countryName + '-' + countrySign;
    }
}
