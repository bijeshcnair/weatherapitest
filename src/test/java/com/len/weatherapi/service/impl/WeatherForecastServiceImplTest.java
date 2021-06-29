package com.len.weatherapi.service.impl;

import com.len.weatherapi.data.ForecastDayMother;
import com.len.weatherapi.external.CurrentWeatherService;
import com.len.weatherapi.service.WeatherForecastServiceImpl;
import com.test.models.Forecast;
import com.test.models.ForecastDay;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WeatherForecastServiceImplTest implements WithAssertions {

    @Mock
    CurrentWeatherService currentWeatherService;
    WeatherForecastServiceImpl weatherForecastService;

    @BeforeEach
    void before(){

        weatherForecastService= new WeatherForecastServiceImpl(currentWeatherService,"");
    }
    @Test
    void shouldReturnWeatherForecastBetweenDatesIfCityNameCountryNameToDateAndFromDatesAreGiven(){
        Mockito.when(currentWeatherService.forecastDailycitycitycountrycountryGet(any(),any(), any(), any(),
                any(), any(), any(), any())).thenReturn(ForecastDayMother.forecastDayMinimalWithTodayForeCast());


        List<Forecast> weatherForecastBetweenDates = weatherForecastService.getWeatherForecastBetweenDates("amsterdam", "nl", LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        assertThat(weatherForecastBetweenDates.size()).isGreaterThan(0);
        assertThat(weatherForecastBetweenDates).extracting(Forecast::getTs).isNotNull();
    }

    @Test
    void shouldReturnTodaysWeatherForecastIfCityNameAndCountryNameAreGiven(){
        Mockito.when(currentWeatherService.forecastDailycitycitycountrycountryGet(any(),any(), any(), any(),
                any(), any(), any(), any())).thenReturn(ForecastDayMother.forecastDayMinimalWithTodayForeCast());


        ForecastDay weatherForecastBetweenDates = weatherForecastService.getTodayForecast("amsterdam", "nl");
        assertThat(weatherForecastBetweenDates).isNotNull();
        assertThat(weatherForecastBetweenDates.getCityName()).isNotNull();
    }

}