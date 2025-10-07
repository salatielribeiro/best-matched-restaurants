package com.best_restaurants.services;

import com.best_restaurants.RestaurantTestData;
import com.best_restaurants.model.Restaurant;
import com.best_restaurants.repository.CuisineCsvRepository;
import com.best_restaurants.repository.RestaurantCsvRepository;
import com.best_restaurants.service.RestaurantService;
import com.best_restaurants.util.RestaurantComparators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    RestaurantCsvRepository restaurantRepo;

    @Mock
    CuisineCsvRepository cuisineRepo;

    @InjectMocks
    RestaurantService service;

    @BeforeEach
    void setup() {
        when(restaurantRepo.readFromCsv()).thenReturn(List.of(
                RestaurantTestData.create("Mcdonald's", 1.0, 4.0, 10.0, 10),
                RestaurantTestData.create("KFC",         1.0, 3.0,  8.0, 20),
                RestaurantTestData.create("Subway",      2.0, 5.0, 12.0, 30),
                RestaurantTestData.create("Burger King", 1.5, 4.0,  9.0, 10),
                RestaurantTestData.create("Five Guys",   1.2, 4.5, 11.0, 10),
                RestaurantTestData.create("Shake Shack", 1.3, 4.2, 13.0, 10)
        ));
        when(cuisineRepo.readFromCsv()).thenReturn(Map.of(
                10, "Burger",
                20, "Chicken",
                30, "Sandwich"
        ));
    }

    @Test
    void shouldFindByPartialName() {
        var result = service.findBestMatch("mc", null, null, null, null);

        assertThat(result)
                .extracting(Restaurant::getName)
                .containsExactly("Mcdonald's");
    }

    @Test
    void shouldReturnUpToFiveResultsSorted() {
        var result = service.findBestMatch(null, null, 5.0, 20.0, null);

        assertThat(result).hasSizeLessThanOrEqualTo(5);
        assertThat(result).isSortedAccordingTo(RestaurantComparators.order());
    }

    @Test
    void shouldApplyAllFilters() {

        var result = service.findBestMatch(null, 4.0, 2.0, 11.0, "burger");

        assertThat(result)
                .extracting(Restaurant::getName)
                .containsExactlyInAnyOrder("Mcdonald's", "Five Guys", "Burger King");
    }
}

