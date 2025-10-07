package com.best_restaurants.controller;

import com.best_restaurants.model.Restaurant;
import com.best_restaurants.service.RestaurantService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import java.util.List;

@RestController
@Validated
public class RestaurantsController {

    private final RestaurantService _restaurantService;

    public RestaurantsController(RestaurantService restaurantService) {
        this._restaurantService = restaurantService;
    }

    @GetMapping("/restaurants/search")
    public List<Restaurant> bestMatch(
            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            @DecimalMin("1.0")
            @DecimalMax("5.0")
            Double customerRating,

            @RequestParam(required = false)
            @DecimalMin("1.0")
            @DecimalMax("10.0")
            Double distance,

            @DecimalMin("10.0")
            @DecimalMax("50.0")
            @RequestParam(required = false)
            Double price,

            @RequestParam(required = false)
            String cuisineName
    ) {
        if(name == null
            && customerRating == null
            && distance == null
            && price == null
            && cuisineName == null){
            throw new IllegalArgumentException("You must provide at least one search criterion.");
        }
        return _restaurantService.findBestMatch(name, customerRating, distance, price, cuisineName);
    }
}
