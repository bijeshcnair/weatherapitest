package com.len.weatherapi.service;

import com.test.models.Forecast;
import com.test.models.ForecastDay;

import java.time.LocalDate;
import java.util.List;

public interface WeatherForecastService {
    List<Forecast> getWeatherForecastBetweenDates(String city, String country, LocalDate fromDate, LocalDate toDate);

    ForecastDay getTodayForecast(String city, String country);
}
