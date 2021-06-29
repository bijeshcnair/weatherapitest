package com.len.weatherapi.integration;

import com.len.weatherapi.controllers.WeatherAPIController;
import com.len.weatherapi.external.CurrentWeatherService;
import com.len.weatherapi.service.WeatherForecastService;
import com.len.weatherapi.service.WeatherForecastServiceImpl;
import com.test.ApiClient;
import com.test.models.ForecastDay;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@Import(WeatherForeCastServiceIntegrationTest.WeatherIntegrationTestConfiguration.class)
public class WeatherForeCastServiceIntegrationTest implements WithAssertions {

    @Autowired
    CurrentWeatherService currentWeatherService;


    @Test
    void whenRealWeatherForeCastServiceIsCalledWithValidInputAndAValidAPIKeyReturnValidResponse(){
        WeatherForecastService weatherForecastService = new WeatherForecastServiceImpl(currentWeatherService,"891b88bb59444e99ba357058423e9ae0");
        ForecastDay todayForecast = weatherForecastService.getTodayForecast("amsterdam", "nl");

        assertThat(todayForecast.getCityName()).isEqualToIgnoringCase("amsterdam");
    }



    @TestConfiguration
    public static class WeatherIntegrationTestConfiguration{

        @Bean
        ApiClient apiClient(){
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            RestTemplate restTemplate = restTemplateBuilder.build();
            ApiClient apiClient = new ApiClient(restTemplate);
            apiClient.setBasePath("https://api.weatherbit.io/v2.0/");
            return new ApiClient(restTemplate);
        }

        @Bean
        CurrentWeatherService currentWeatherService(ApiClient apiClient){
            return new CurrentWeatherService(apiClient);
        }
    }

}

