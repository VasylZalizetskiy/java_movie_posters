package movie_posters.models.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import movie_posters.models.Movie;

import java.io.IOException;

public class MovieDeserializer extends JsonDeserializer {

    @Override

    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        Movie movie = new Movie();
        movie.setName(jsonNode.get("name").asText());
        movie.setYear(jsonNode.get("year").asInt());
        movie.setImage(jsonNode.get("image").asText());

        return movie;
    }
}