package hacker.news.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import hacker.news.model.News;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;

import javax.inject.Singleton;

@Factory
public class RepositoryFactory {

    private MongoClient mongoClient;

    @Property(name = "mongodb.database")
    private String database;

    public RepositoryFactory(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Singleton
    public Repository<News> getNewsRepository() {
        return () -> mongoClient.getDatabase(database).getCollection("news", News.class);
    }
}
