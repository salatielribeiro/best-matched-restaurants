package com.best_restaurants.model;

import com.opencsv.bean.CsvBindByName;

public class Cuisine {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}