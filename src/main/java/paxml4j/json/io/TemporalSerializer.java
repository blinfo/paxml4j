package paxml4j.json.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.time.temporal.Temporal;

/**
 *
 * @author hakan
 */
public class TemporalSerializer extends JsonSerializer<Temporal> {

    @Override
    public void serialize(Temporal value, JsonGenerator generator, SerializerProvider sp) throws IOException {
        generator.writeString(value.toString());
    }
}
