package com.app.mapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.app.mapp.model.Dates;
import com.app.mapp.service.impl.CityService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class CityServiceTests {

  @Autowired
  private ICityService cityService;

  @Test
  public void test_getCitiesInformation_success() {
    List<Dates> datesList = cityService
        .getCitiesInformation("2017-09-20", "2017-12-20", ".*");

    assertTrue(datesList.size() > 0);
    assertTrue(datesList.get(0).getCities().size() > 0);

  }

  @Test
  public void test_getCitiesInformation_malformedInput_failure() {

    Exception exception = assertThrows(ResponseStatusException.class, () -> {
      cityService
          .getCitiesInformation("sas", "sasa", ".*");
    });

    String expectedMessage = "Bad request";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

}
