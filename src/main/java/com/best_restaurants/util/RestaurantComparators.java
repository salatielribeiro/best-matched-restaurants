package com.best_restaurants.util;

import com.best_restaurants.model.Restaurant;

import java.util.Comparator;

public class RestaurantComparators {
    public static Comparator<Restaurant> order() {
        return Comparator
                .comparing(Restaurant::getDistance, Comparator.nullsLast(Double::compare))
                .thenComparing(
                        Comparator.comparing(Restaurant::getCustomerRating, Comparator.nullsLast(Double::compare))
                                .reversed()
                )
                .thenComparing(Restaurant::getPrice, Comparator.nullsLast(Double::compare));
    }
}
