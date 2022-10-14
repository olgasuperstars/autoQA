package ru.geekbrains.auto.qa.autoqa.lesson5.petstore.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("ru.geekbrains.auto.qa.autoqa.lesson5.restassured.petstore")
public class PetStoreTestConfig {
}
