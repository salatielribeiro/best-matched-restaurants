package com.best_restaurants.service;

import com.best_restaurants.config.Constants;
import com.best_restaurants.model.Restaurant;
import com.best_restaurants.repository.CuisineCsvRepository;
import com.best_restaurants.repository.RestaurantCsvRepository;
import com.best_restaurants.util.RestaurantComparators;
import com.best_restaurants.util.RestaurantFilters;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantCsvRepository _restaurantRepository;
    private final CuisineCsvRepository _cuisineRepository;

    public RestaurantService(RestaurantCsvRepository restaurantRepository, CuisineCsvRepository cuisineRepository ) {
        this._restaurantRepository = restaurantRepository;
        this._cuisineRepository = cuisineRepository;
    }

    public List<Restaurant> findBestMatch(String name, Double customerRating, Double distance, Double price, String cuisineName){

        List<Restaurant> restaurantsList = _restaurantRepository.readFromCsv();
        Map<Integer, String> cuisineMap= _cuisineRepository.readFromCsv();

        List<Restaurant> filteredRestaurant = restaurantsList.stream()
                .filter(RestaurantFilters.filter(name, customerRating, distance,price, cuisineName, cuisineMap))
                .collect(Collectors.toList());

        if(filteredRestaurant.size() > Constants.RESTAURANTS_LIST_MAX_SIZE){

            Collections.shuffle(filteredRestaurant, new Random());
            return filteredRestaurant.stream()
                    .sorted(RestaurantComparators.order())
                    .limit(Constants.RESTAURANTS_LIST_MAX_SIZE)
                    .collect(Collectors.toList());
        }
        return filteredRestaurant;
    }
}
