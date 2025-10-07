package com.best_restaurants.controllers;

import com.best_restaurants.controller.RestaurantsController;
import com.best_restaurants.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantsController.class)
class RestaurantsControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    RestaurantService service;

    @Test
    void shouldReturn400AndProblemDetailWhenNoCriteriaProvided() throws Exception {
        mvc.perform(get("/restaurants/search"))
                .andExpect(status().isBadRequest());

    }
}

