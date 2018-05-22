package movie_posters.models.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import movie_posters.models.Movie;

import java.io.IOException;

public class MovieSerializer extends JsonSerializer<Movie> {

    @Override
    public void serialize(Movie movie, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", movie.getName());
        jsonGenerator.writeNumberField("year", movie.getYear());
        jsonGenerator.writeStringField("image", movie.getImage());
        jsonGenerator.writeEndObject();
    }

}