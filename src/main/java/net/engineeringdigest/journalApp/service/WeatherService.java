package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;
    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY&units=metric";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCahe;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCahe.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }

    //sending POST request
    //HttpHeaders headers = new HttpHeaders();
    //headers.set("key","value");
    //User user = User.builder().userName("vipul").password("Vipul").build();
    //HttpEntity<User> request = new HttpEntity<>(user, headers);
    //ResponseEntity<WeatherResponse> responseResponseEntity = restTemplate.exchange(finalAPI, HttpMethod.POST, request, WeatherResponse.class);
    //response.getStatusCode(); then can check for the status code and send the error accordingly

}
