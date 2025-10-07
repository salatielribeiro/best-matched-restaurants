package com.best_restaurants.repository;

import com.best_restaurants.model.Cuisine;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CuisineCsvRepository {

    private static final String CUISINES_CSV = "cuisines.csv";
    private final ResourceLoader _loader;
    private final String _location;

    public CuisineCsvRepository(
            ResourceLoader loader,
            @Value("${csv.cuisines-location:classpath:cuisines.csv}") String location) {
        this._loader = loader;
        this._location = location;
    }

    public Map<Integer, String> readFromCsv() {
        try {
            var resource = _loader.getResource(_location);
            try (var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                return new CsvToBeanBuilder<Cuisine>(reader)
                        .withType(Cuisine.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse()
                        .stream()
                        .collect(Collectors.toMap(Cuisine::getId, Cuisine::getName));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV from classpath: " + CUISINES_CSV, e);
        }
    }
}
