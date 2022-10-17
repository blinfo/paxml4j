package paxml4j.json.io;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.time.temporal.Temporal;
import paxml4j.util.Helper;

/**
 *
 * @author hakan
 */
public class TemporalDeserializer extends JsonDeserializer<Temporal> {

    @Override
    public Temporal deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        return Helper.temporalFromText(jp.getText());
    }

}
