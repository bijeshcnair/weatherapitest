package com.len.weatherapi.data;

import com.test.models.Forecast;
import com.test.models.ForecastDay;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

public class ForecastDayMother {


    public static ForecastDay forecastDayMinimalWithTodayForeCast(){
        ForecastDay forecastDay = new ForecastDay();
        forecastDay.setCityName("Amsterdam");
        forecastDay.setCountryCode("nl");
        Forecast forecast = new Forecast();
        forecast.setMaxTemp(BigDecimal.valueOf(33));
        forecast.setAppMinTemp(BigDecimal.valueOf(30));
        forecast.setTs(BigDecimal.valueOf(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000));
        forecastDay.addDataItem(forecast);
        return forecastDay;
    }
}
