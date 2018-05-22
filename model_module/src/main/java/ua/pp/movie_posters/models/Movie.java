package ua.pp.movie_posters.models;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.pp.movie_posters.models.serialization.MovieDeserializer;
import ua.pp.movie_posters.models.serialization.MovieSerializer;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@JsonDeserialize(using = MovieDeserializer.class)
@JsonSerialize(using = MovieSerializer.class)
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Movie implements Serializable{
    @Id
    private String id;
    private String name;
    private int year;
    private String image;
}