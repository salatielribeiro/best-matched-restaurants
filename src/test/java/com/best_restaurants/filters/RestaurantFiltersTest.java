package com.best_restaurants.filters;

import com.best_restaurants.RestaurantTestData;
import com.best_restaurants.model.Restaurant;
import com.best_restaurants.util.RestaurantFilters;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantFiltersTest {

    @Test
    void shouldFilterByPartialName() {
        var r1 = RestaurantTestData.create("Mcdonald's", 1.0, 4.0, 10.0, 10);
        var r2 = RestaurantTestData.create("KFC",1.0, 3.0,  8.0, 10);
        var restaurants = List.of(r1, r2);
        var cuisines = Map.of(10, "Burger", 20, "Chicken");

        var predicate = RestaurantFilters.filter("mc", null, null, null, null,
                cuisines);

        var result = restaurants.stream().filter(predicate).toList();

        assertThat(result).containsExactly(r1);
    }

    @Test
    void shouldFilterByRatingDistanceAndPrice() {
        var r1 = RestaurantTestData.create("A", 1.0, 4.0, 10.0, 10);
        var r2 = RestaurantTestData.create("B", 3.0, 5.0,  9.0, 10);
        var r3 = RestaurantTestData.create("C", 2.0, 2.0, 15.0, 10);

        var predicate = RestaurantFilters.filter(null, 4.0, 2.0, 12.0, null, Map.of(1, "x"));
        var result = Stream.of(r1, r2, r3).filter(predicate).toList();

        assertThat(result).containsExactly(r1);
    }

    @Test
    void shouldFilterByCuisineNameUsingCuisineMap() {
        var r1 = RestaurantTestData.create("A", 1.0, 4.0, 10.0,10);
        var r2 = RestaurantTestData.create("B", 1.0, 3.0,  8.0,11);
        var cuisines = Map.of(10, "Burger", 20, "Sushi");

        var predicate = RestaurantFilters.filter(null, null, null, null, "bur",
                cuisines);

        var result = Stream.of(r1, r2).filter(predicate).toList();

        assertThat(result)
                .extracting(Restaurant::getName)
                .containsExactly("A");
    }
}
