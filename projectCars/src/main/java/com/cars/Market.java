package com.cars;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
public class Market {
    private String name;
    private List<Country> countries;
}
