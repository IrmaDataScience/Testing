package com.cars;

import lombok.*;

@AllArgsConstructor
@Data
public class Country {
    private String countryName;
    private Character countrySign;

    @Override
    public String toString() {
        return "Country: " + countryName + '-' + countrySign;
    }
}
