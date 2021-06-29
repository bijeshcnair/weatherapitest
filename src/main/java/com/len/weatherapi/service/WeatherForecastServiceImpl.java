package com.len.weatherapi.service;

import com.len.weatherapi.exception.ForecastNotFoundException;
import com.len.weatherapi.external.CurrentWeatherService;
import com.test.models.Forecast;
import com.test.models.ForecastDay;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {


    private final String apiKey;
    private final CurrentWeatherService currentWeatherService;

    public WeatherForecastServiceImpl(CurrentWeatherService currentWeatherService, @Value("${app.apikey}") String apiKey) {
        this.currentWeatherService = currentWeatherService;
        this.apiKey = apiKey;
    }

    @Override
    public List<Forecast>  getWeatherForecastBetweenDates(String city, String country, LocalDate fromDate, LocalDate toDate) {

        Validate.notNull(city);
        Validate.notNull(country);
        Validate.notNull(fromDate);
        Validate.notNull(toDate);

        ForecastDay forecastDay = currentWeatherService.forecastDailycitycitycountrycountryGet(city,
                country, apiKey, null,null, null, null, null);

        if(forecastDay == null || forecastDay.getData().size() == 0){
           throw new ForecastNotFoundException("Failed to fetch forecast");
        }
        long fromTime = fromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long toTime = toDate.plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        List<Forecast> collect = forecastDay.getData().stream().filter(forecast -> forecast.getTs().longValue() * 1000 >= fromTime
                && forecast.getTs().longValue() * 1000 <= toTime).collect(Collectors.toList());
        return collect;

    }

    @Override
    public ForecastDay getTodayForecast(String city, String country) {

        Validate.notNull(city);
        Validate.notNull(country);

        ForecastDay forecastDay = currentWeatherService.forecastDailycitycitycountrycountryGet(city,
                country, apiKey, null, BigDecimal.ONE, null, null, null);
        if(forecastDay == null || forecastDay.getData().size() ==0 ){
            throw new ForecastNotFoundException("Failed to fetch forecast");
        }
        return forecastDay;

    }
}
