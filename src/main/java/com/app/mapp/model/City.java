package com.app.mapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.Data;

@Data
public class City {
  @JsonIgnore
  private LocalDate date;
  private String city;
  private Integer count;
}
