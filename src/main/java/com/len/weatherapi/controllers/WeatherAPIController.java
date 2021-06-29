package com.len.weatherapi.controllers;

import com.len.weatherapi.service.WeatherForecastService;
import com.test.models.Forecast;
import com.test.models.ForecastDay;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
@ComponentScan("com.test")
@SecurityRequirement(name = "weatherapi")
@Tag(name = "Weather", description = "Weather check api")

public class WeatherAPIController {

    private final WeatherForecastService weatherForecastServiceImpl;

    public WeatherAPIController(WeatherForecastService currentWeather) {
        this.weatherForecastServiceImpl = currentWeather;
    }

    @Operation(summary = "Get weather forecast between dates")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    List<Forecast> getWeather(@Parameter(description = "Name of the city",required = true)@RequestParam String city,
                              @Parameter(description = "Country code",required = true)@RequestParam String country,
                              @Parameter(description = "From date in yyyy-MM-dd format") @RequestParam(value = "fromDate",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                              @Parameter(description = "To date in yyyy-MM-dd format") @RequestParam(value = "toDate",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        return weatherForecastServiceImpl.getWeatherForecastBetweenDates(city, country, fromDate, toDate);


    }
    @Operation(summary = "Get todays forecast")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    ForecastDay getWeather(@Parameter(description = "Name of the city",required = true)@RequestParam String city,
                              @Parameter(description = "Country code",required = true)@RequestParam String country ) {

        return weatherForecastServiceImpl.getTodayForecast(city, country);


    }

}
