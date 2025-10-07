package com.best_restaurants.comparators;

import com.best_restaurants.RestaurantTestData;
import com.best_restaurants.model.Restaurant;
import com.best_restaurants.util.RestaurantComparators;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantComparatorsTest {

    @Test
    void shouldSortByDistanceThenRatingDescThenPriceAsc() {
        var r1 = RestaurantTestData.create("A", 1.0, 4.0, 10.0, 12);
        var r2 = RestaurantTestData.create("B", 1.0, 3.0,  8.0, 12);
        var r3 = RestaurantTestData.create("C", 2.0, 5.0, 12.0, 12);
        var r4 = RestaurantTestData.create("D", 1.0, 4.0,  9.0, 12);

        List<Restaurant> restaurants = new ArrayList<>(List.of(r1, r2, r3, r4));
        restaurants.sort(RestaurantComparators.order());

        assertThat(restaurants)
                .extracting(Restaurant::getName)
                .containsExactly("D", "A", "B", "C");
    }

}
