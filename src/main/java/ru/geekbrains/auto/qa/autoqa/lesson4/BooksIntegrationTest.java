package ru.geekbrains.auto.qa.autoqa.lesson4;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import ru.geekbrains.auto.qa.autoqa.lesson2.Books;
import ru.geekbrains.auto.qa.autoqa.lesson2.controller.rest.example.dto.books;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.booksEntity;
import ru.geekbrains.auto.qa.autoqa.lesson4.repository.booksRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class BooksIntegrationTest {

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private HttpClient httpClient;

        @Autowired
        private booksRepository booksRepository;

        @LocalServerPort
        private int port;

        private String getRootUrl() {
            return "http://localhost:" + port;
        }

        @Test
        void saveBooksTest() throws Exception {
            //pre-condition
            Books books = new Books();
            books.setPrice(50);
            books.setName("Name");
            books.setAuthor("Author");

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getRootUrl() + "/books-rest"))
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(books)))
                    .build();

            //step 1
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            //intermediate assert after first step
            Assertions.assertThat(response.statusCode()).isEqualTo(SC_OK);

            //step 2
            BooksEntity booksEntity = booksRepository.findAll().stream()
                    .findFirst()
                    .orElseThrow();

            //assert
            SoftAssertions.assertSoftly(s -> {
                s.assertThat(booksEntity.getName()).isEqualTo(books.getName());
                s.assertThat(booksEntity.getAthor()).isEqualTo(books.getAthor());
                s.assertThat(booksEntity.getPrice()).isEqualTo(books.getPrice());
            });
        }

        @Test
        void getbooksTest() throws Exception {
            //pre-condition
            booksEntity booksEntity = new booksEntity();
            booksEntity.setName("Name");
            booksEntity.setAthor("Athor");
            booksEntity.setPrice(50);
            booksEntity entity = booksRepository.saveAndFlush(booksEntity);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(getRootUrl() + "/books-rest/" + entity.getId()))
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .GET()
                    .build();

            //step 1
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            //intermediate assert after first step
            Assertions.assertThat(response.statusCode()).isEqualTo(SC_OK);

            //assert
            Books books = objectMapper.readValue(response.body(), books.class);
            SoftAssertions.assertSoftly(s -> {
                s.assertThat(booksEntity.getName()).isEqualTo(books.getName());
                s.assertThat(booksEntity.getAthor()).isEqualTo(books.getAthor());
                s.assertThat(booksEntity.getPrice()).isEqualTo(books.getPrice());
            });
        }
}
