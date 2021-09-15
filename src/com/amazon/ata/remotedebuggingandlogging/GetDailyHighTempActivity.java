package com.amazon.ata.remotedebuggingandlogging;

import com.amazon.ata.alexa.weatherservice.AlexaWeatherClientException;
import com.amazon.ata.alexa.weatherservice.AlexaWeatherServiceException;
import com.amazon.ata.alexa.weatherservice.GetDailyHighTempRequest;
import com.amazon.ata.alexa.weatherservice.GetDailyHighTempResponse;

import com.amazon.coral.annotation.Operation;
import com.amazon.coral.annotation.Service;
import com.amazon.coral.service.Activity;

import java.time.LocalDate;
import java.util.List;

@Service("ATACurriculumAlexaWeatherService")
public class GetDailyHighTempActivity extends Activity {
    private TemperatureDao temperatureDao = new TemperatureDao();

    /**
     * Returns the historic high temperature for a given city on a given day.
     * @param request The request to the service.
     * @return The response with the high temperature.
     */
    @Operation("GetDailyHighTemp")
    public GetDailyHighTempResponse getDailyHighTemp(GetDailyHighTempRequest request) {
        // Converts the Date string passed to the service into a Java LocalDate
        LocalDate date = LocalDate.parse(request.getDate());

        // Get the hourly temperatures from the TemperatureDao for the specified city and date
        List<Integer> temperatures;
        try {
            temperatures = temperatureDao.getTemperatures(request.getCity(), date);
        } catch (CityNotFoundException e) {
            String message = "The city you requested the high temperature for could not be found.";
            throw new AlexaWeatherClientException(message, e);
        } catch (TemperatureServiceUnavailableException e) {
            String message = "The temperature service is currently unavailable. Please try again shortly.";
            throw new AlexaWeatherServiceException(message, e);
        }

        // Finds the highest temp out of the hourly temperatures for the day
        int highTemp = 0;
        for (int temp : temperatures) {
            if (temp > highTemp) {
                highTemp = temp;
            }
        }

        return GetDailyHighTempResponse.builder()
                .withHighTemp(highTemp)
                .build();
    }
}
