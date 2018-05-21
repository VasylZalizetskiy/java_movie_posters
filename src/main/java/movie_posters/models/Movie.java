package movie_posters.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable{
    @Id
    @Getter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private int releasedYear;
    @Getter @Setter private String image;

}