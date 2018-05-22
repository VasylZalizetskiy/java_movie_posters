package ua.pp.movie_posters.models;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.pp.movie_posters.models.serialization.MovieDeserializer;
import ua.pp.movie_posters.models.serialization.MovieSerializer;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@JsonDeserialize(using = MovieDeserializer.class)
@JsonSerialize(using = MovieSerializer.class)
public class Movie implements Serializable{
    @Id
    private String id;
    private String name;
    private int year;
    private String image;

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public Movie() {}
    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
    }
}