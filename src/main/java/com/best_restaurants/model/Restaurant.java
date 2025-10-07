package com.best_restaurants.model;

import com.opencsv.bean.CsvBindByName;

public class Restaurant {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "customer_rating")
    private Double customerRating;

    @CsvBindByName(column = "distance")
    private Double distance;

    @CsvBindByName(column = "price")
    private Double price;

    @CsvBindByName(column = "cuisine_id")
    private Integer cuisineId;

    public String getName() {
        return name;
    }

    public Double getCustomerRating() {
        return customerRating;
    }

    public Integer getCuisineId() {
        return cuisineId;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getPrice() {
        return price;
    }

    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    public void setCustomerRating(Double customerRating) {
        this.customerRating = customerRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | "
                + "Rating: " + customerRating + "|"
                + "Distance: " + distance + "|"
                + "Price: " + price + "|"
                + "Cuisine ID: " + cuisineId + "|";
    }
}