package com.len.weatherapi.controller;

import com.len.weatherapi.controllers.WeatherAPIController;
import com.len.weatherapi.service.WeatherForecastService;
import com.test.ApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestOperations;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(WeatherAPIController.class)
public class WeatherControllerSecurityTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherForecastService weatherForecastService;

    @MockBean
    private ApiClient apiClient;

    @MockBean
    private RestOperations restOperations;

    @WithMockUser()
    @Test
    void givenAuthRequestOnAuthenticatedService_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/weather?city=amsterdam&country=nl&fromDate=2021-01-01&toDate=2021-02-02").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @WithMockUser()
    @Test
    void givenAuthRequestOnAuthenticatedServiceWithInvalidInput_shouldReturn400() throws Exception {
        mvc.perform(get("/weather?city=amste").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenAnonymousOnAuthenticatedService_shouldSucceedWith200() throws Exception {


        mvc.perform(get("/weather").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}
