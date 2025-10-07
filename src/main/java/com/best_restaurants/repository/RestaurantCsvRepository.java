package com.best_restaurants.repository;

import com.best_restaurants.model.Restaurant;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class RestaurantCsvRepository {

    private static final String RESTAURANTS_CSV = "restaurants.csv";
    private final ResourceLoader _loader;
    private final String _location;

    public RestaurantCsvRepository(
            ResourceLoader loader,
            @Value("${csv.restaurants-location:classpath:restaurants.csv}") String location) {
        this._loader = loader;
        this._location = location;
    }

    public List<Restaurant> readFromCsv() {
        try {
            var resource = _loader.getResource(_location);

            try (var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                return new CsvToBeanBuilder<Restaurant>(reader)
                        .withType(Restaurant.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV from classpath: " + RESTAURANTS_CSV, e);
        }
    }
}
