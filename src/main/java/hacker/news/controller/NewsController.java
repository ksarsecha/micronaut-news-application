package hacker.news.controller;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.Success;
import hacker.news.model.News;
import hacker.news.repository.Repository;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@Controller("/${news.api.version}/news")
@Validated
public class NewsController {

    private Repository<News> repository;

    public NewsController(Repository<News> repository) {
        this.repository = repository;
    }

    @Get
    public Flux<News> getNews() {
        return Flux.from(repository.findAll());
    }

    @Post
    public Flux<Success> save(@Body @Valid News news) {
        return Flux.from(repository.insertOne(news));
    }

    @Get("/search")
    public Flux<News> searchByString(@QueryValue("param") String param) {
        return this.repository.find(Filters.eq("news", param));
    }
}
