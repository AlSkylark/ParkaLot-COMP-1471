package com.parkalot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication(scanBasePackages = "com.parkalot")
@EntityScan("com.parkalot.infrastructure.models")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
