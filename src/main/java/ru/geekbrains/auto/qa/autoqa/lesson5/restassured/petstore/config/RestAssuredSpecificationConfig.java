package ru.geekbrains.auto.qa.autoqa.lesson5.restassured.petstore.config;


import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestAssuredSpecificationConfig {

    @Value("${petstore.base.url}")
    private String BASE_URL;

    @Bean
    RequestSpecification requestPetStoreSpecification() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .log().all()
                .when()
                .contentType(ContentType.JSON);
    }
}