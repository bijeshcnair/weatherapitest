package com.len.weatherapi.external;



import com.test.ApiClient;
import com.test.apis.Class16DayDailyForecastApi;
import com.test.models.ForecastDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CurrentWeatherService extends Class16DayDailyForecastApi {

    private final ApiClient apiClient;

    public CurrentWeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }



    /**
     * The method name is auto generated in Class16DayDailyForecastApi
     * @param city City search.. Example - &amp;city&#x3D;Raleigh,NC or &amp;city&#x3D;Berlin,DE or city&#x3D;Paris&amp;country&#x3D;FR
     * @param country Country Code (2 letter).
     * @param key Your registered API key.
     * @param state Full name of state.
     * @param days Number of days to return. Default 16.
     * @param units Convert to units. Default Metric See &lt;a target&#x3D;&#x27;blank&#x27; href&#x3D;&#x27;/api/requests&#x27;&gt;units field description&lt;/a&gt;
     * @param lang Language (Default: English) See &lt;a target&#x3D;&#x27;blank&#x27; href&#x3D;&#x27;/api/requests&#x27;&gt;language field description&lt;/a&gt;
     * @param paramCallback Wraps return in jsonp callback. Example - callback&#x3D;func
     * @return
     * @throws RestClientException
     */
    @Override
    public ForecastDay forecastDailycitycitycountrycountryGet(String city, String country,
                                                              String key, String state,
                                                              BigDecimal days, String units,
                                                              String lang, String paramCallback ) throws RestClientException {

        Object postBody = null;
        if (city == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'city' when calling forecastDailycitycitycountrycountryGet");
        }
        if (country == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'country' when calling forecastDailycitycitycountrycountryGet");
        }
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        String path = UriComponentsBuilder.fromPath("/forecast/daily").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "state", state));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "city", city));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "country", country));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "days", days));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "units", units));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "lang", lang));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "callback", paramCallback));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "key", key));

        final String[] accepts = {
                "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ForecastDay> returnType = new ParameterizedTypeReference<ForecastDay>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);

    }
}
