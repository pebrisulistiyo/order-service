package com.pebrisulistiyo.app.service.order.book;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BookClientTests {
    private MockWebServer mockWebServer;
    private BookClient bookClient;

    @BeforeEach
    void setUp() throws IOException {
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();
        var webClient = WebClient.builder()
                .baseUrl(this.mockWebServer.url("/").toString())
                .build();
        this.bookClient = new BookClient(webClient);
    }

    @AfterEach
    void clean() throws IOException {
        this.mockWebServer.shutdown();
    }

    @Test
    void whenBookExistsThenReturnBook() {
        var bookIsbn = "1234567890";
        var mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(
                        """
                        {
                            "isbn": %s,
                            "title": "Title",
                            "author": "Author",
                            "price": 9.90,
                            "published": "Book"
                        }
                        """.formatted(bookIsbn)
                );

        mockWebServer.enqueue(mockResponse);
        Mono<Book> book = bookClient.getBookByIsbn(bookIsbn);
        StepVerifier.create(book)
                .expectNextMatches(
                        b -> b.isbn().equals(bookIsbn)
                ).verifyComplete();
    }

}