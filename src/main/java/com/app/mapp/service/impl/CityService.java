package com.app.mapp.service.impl;

import com.app.mapp.model.City;
import com.app.mapp.model.Dates;
import com.app.mapp.service.ICityService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CityService implements ICityService {

  public static String fileName = "/Users/sumeet/Downloads/warpBackendChallenge/warpBackendChallengeDataset.csv";

  @Override
  public List<Dates> getCitiesInformation(@NonNull String from, @NonNull String until,
      @NonNull String cityNameRegex) {
    HashMap<String, City> cityMap = new HashMap<>();
    List<Dates> datesList = new ArrayList<>();

    try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
      String row = "";

      while ((row = csvReader.readLine()) != null) {
        City cityEntity;
        String[] cityData = row.split(",");

        if (cityData.length > 4) {
          String city = cityData[5];

          if (cityMap.containsKey(city)) {
            cityEntity = cityMap.get(city);
            cityEntity.setCount(cityEntity.getCount() + 1);
          } else {
            cityEntity = new City();
            cityEntity.setCount(0);
          }

          cityEntity.setDate(LocalDate.parse(cityData[1].split(" ")[0]));
          cityEntity.setCity(city);
          cityMap.put(city, cityEntity);
        }

      }
      Map<LocalDate, List<City>> cities = cityMap.values().stream()
          .filter(d -> d.getDate().isAfter(LocalDate.parse(from)) && d.getDate()
              .isBefore(LocalDate.parse(until)) && d.getCity().matches(cityNameRegex))
          .collect(Collectors.groupingBy(City::getDate));

      for (Entry<LocalDate, List<City>> entry : cities.entrySet()) {
        Dates dates = new Dates();
        dates.setDate(entry.getKey().toString());
        dates.setCities(entry.getValue());
        datesList.add(dates);
      }
    } catch (IOException exception) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "File not found", exception);
    } catch (Exception exception) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Bad request", exception);
    }

    return datesList.stream().sorted(Comparator.comparing(Dates::getDate))
        .collect(Collectors.toList());
  }


}
