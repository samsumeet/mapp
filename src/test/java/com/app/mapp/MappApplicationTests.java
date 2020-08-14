package com.app.mapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.mapp.controller.ICityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MappApplicationTests {

  @Autowired
  private ICityController cityController;

  @Test
  void contextLoads() {
    assertThat(cityController).isNotNull();
  }

}
