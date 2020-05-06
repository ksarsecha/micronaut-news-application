package hacker.news.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface Repository<T> {
    MongoCollection<T> getCollection();

    default Flux<T> findAll() {
        return Flux.from(getCollection().find());
    }

    default Flux<Success> insertOne(T data) {
        return Flux.from(getCollection().insertOne(data));
    }

    default Flux<T> find(Bson news) {
        return Flux.from(getCollection().find(news));
    }
}
