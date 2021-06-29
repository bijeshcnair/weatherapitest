package com.len.weatherapi.configuration;

import com.test.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfiguration {

    @Value("${app.apikey}")
    String apiKey;

    @Value("${app.endpoint}")
    String apiEndPoint;

    @Bean(name = "apiClient")
    public ApiClient apiClient(RestOperations restOperations ){

        ApiClient apiClient = new ApiClient((RestTemplate) restOperations);

        apiClient.setBasePath(apiEndPoint);
        return apiClient;
    }
    @Bean
    public RestOperations restOperations(RestTemplateBuilder builder) {
        return builder.build();
    }
}
