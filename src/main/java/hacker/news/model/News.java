package hacker.news.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;

@Introspected
@Data
public class News {
    @NotBlank
    private String news;

    @BsonCreator
    @JsonCreator
    public News(@BsonProperty("news")
                @JsonProperty("news") String news) {
        this.news = news;
    }
}
