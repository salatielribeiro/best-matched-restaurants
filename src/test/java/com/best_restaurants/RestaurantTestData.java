package com.best_restaurants;

import com.best_restaurants.model.Restaurant;

public class RestaurantTestData {

    public static Restaurant create(
            String name, Double distance, Double rating, Double price, Integer cuisineId) {
        Restaurant r = new Restaurant();
        r.setName(name);
        r.setDistance(distance);
        r.setCustomerRating(rating);
        r.setPrice(price);
        r.setCuisineId(cuisineId);
        return r;
    }
}
