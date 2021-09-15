package com.amazon.ata.remotedebuggingandlogging;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Knows historic temperature data for cities.
 */
public class TemperatureDao {

    /**
     * Returns a list of the hourly temperatures for the given day and the given city.
     * @param city The name of the city to find the temperatures for
     * @param date The historic day to find hourly temperatures for
     * @return A list of hourly temperatures ordered from 12:00 AM to 11:00 PM
     * @throws CityNotFoundException Requesting temperatures for a City we don't have records for.
     * @throws TemperatureServiceUnavailableException The temperatures service is currently not available and we
     * can't return any data at the moment.
     */
    public List<Integer> getTemperatures(String city, LocalDate date) throws CityNotFoundException,
            TemperatureServiceUnavailableException {
        switch (city) {
            case "Atlanta":
                return Arrays.asList(
                        78, 77, 74, 73, 73, 72, 72, 72, 73, 74, 76, 77, 79, 79, 79, 80, 81, 80, 79, 78, 77, 76, 74, 74);
            case "Fairbanks":
                return Arrays.asList(
                        -12, -13, -13, -13, -13, -13, -11, -9, -6, -6, -5, -3, -1, -1, -1, -1, -1, -1, -2, -2, -5,
                        -6, -7, -10);
            case "Seattle":
                String errorMessage = "The TemperatureService is currently facing technical difficulties.";
                throw new TemperatureServiceUnavailableException(errorMessage);
            default:
                errorMessage = "City was not found in TemperatureDao.";
                throw new CityNotFoundException(errorMessage);
        }
    }
}
