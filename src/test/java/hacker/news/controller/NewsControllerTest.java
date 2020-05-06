package hacker.news.controller;

import hacker.news.model.News;
import hacker.news.repository.Repository;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import javax.inject.Inject;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
@Property(value = "v1", name = "news.api.version")
class NewsControllerTest {
    @Inject
    @Client("/v1")
    HttpClient client;

    @Inject
    Repository<News> newsCollection;

    @Test
    void shouldGetAllNews() {
        newsCollection.insertOne(new News("first news")).log().subscribe();

        List<News> news = Flux.from(client.retrieve(HttpRequest.GET("/news"), Argument.listOf(News.class))).blockFirst();
        Assertions.assertIterableEquals(List.of(new News("first news")), news);
    }

    @Test
    void shouldCreateNews() {
        Flux.from(client.retrieve(HttpRequest.POST("/news", new News("new one")))).blockFirst();

        Flux<News> newsFlux = newsCollection.find(eq("news", "new one"));
        Assertions.assertEquals(Long.valueOf(1), newsFlux.count().block());
    }

    @Test
    void shouldValidateNews() {
        try {
            Flux.from(client.retrieve(HttpRequest.POST("/news", new News("")))).blockFirst();
            fail("Should throw HttpClientResponseException");
        } catch(HttpClientResponseException ignored) { }
    }
}
