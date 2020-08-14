package com.app.mapp.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public interface ICityController {

  @ApiOperation(value = "Fetch City data with date")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 500, message = "Failure")})
  @GetMapping("v1.0/cities")
  ResponseEntity getCitiesInformation(@NonNull @RequestParam("from") String from
      , @NonNull @RequestParam("until") String until
      , @NonNull @RequestParam("city") String city);
}
