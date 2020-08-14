package com.app.mapp.service;

import com.app.mapp.model.Dates;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface ICityService {

  List<Dates> getCitiesInformation(@NonNull final String from, @NonNull final String until,
      @NonNull final String city);
}
