package com.len.weatherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pvasconcellos on 09/06/2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ForecastNotFoundException extends RuntimeException {
    public ForecastNotFoundException() {
        super();
    }

    public ForecastNotFoundException(String message) {
        super(message);
    }

    public ForecastNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForecastNotFoundException(Throwable cause) {
        super(cause);
    }

    public ForecastNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
