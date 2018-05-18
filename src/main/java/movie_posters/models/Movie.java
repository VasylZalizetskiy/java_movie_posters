package movie_posters.models;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

public class Movie implements Serializable{
    @Id
    private String id;
    private String name;
    private int releasedYear;
    private String image;

    public void setName(String name) {
        this.name = name;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public String getImage() {
        return image;
    }

    public Movie() {}
    public Movie(String name, int releasedYear) {
        this.name = name;
        this.releasedYear = releasedYear;
    }
}