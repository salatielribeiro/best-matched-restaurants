package com.best_restaurants.util;

import com.best_restaurants.model.Restaurant;

import java.util.Map;
import java.util.function.Predicate;

public class RestaurantFilters {

    public static Predicate<Restaurant> filter(String name, Double customerRating, Double distance, Double price, String cuisine, Map<Integer, String> cuisineMap) {


        Predicate<Restaurant> pred = r -> true;
        if (name != null && !name.isEmpty()) pred = pred.and(r -> r.getName().toLowerCase().contains(name.toLowerCase()));
        if (customerRating != null) pred = pred.and(r -> r.getCustomerRating() >= customerRating);
        if (distance != null) pred = pred.and(r -> r.getDistance() <= distance);
        if (price != null) pred = pred.and(r -> r.getPrice() <= price);

        if (cuisine != null && !cuisine.isEmpty()) pred = pred.and(r -> {
            String cuisineName = cuisineMap.get(r.getCuisineId());
            return cuisineName != null && cuisineName.toLowerCase().contains(cuisine.toLowerCase());
        });
        return pred;
    }
}
