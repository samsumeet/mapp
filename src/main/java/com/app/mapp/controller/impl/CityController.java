package com.app.mapp.controller.impl;

import com.app.mapp.controller.ICityController;
import com.app.mapp.service.ICityService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CityController implements ICityController {

  @Autowired
  private ICityService cityService;

  @Override
  public ResponseEntity getCitiesInformation(@NonNull String from, @NonNull String until,
      @NonNull String city) {

    return ResponseEntity.ok(cityService.getCitiesInformation(from, until, city));
  }
}
