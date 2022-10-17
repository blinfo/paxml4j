package paxml4j.json.io;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author hakan
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        return LocalDate.parse(jp.getText());
    }

}
