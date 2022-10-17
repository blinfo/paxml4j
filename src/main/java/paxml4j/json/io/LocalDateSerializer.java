package paxml4j.json.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author hakan
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate input, JsonGenerator gen, SerializerProvider sp) throws IOException {
        gen.writeString(input.toString());
    }

}
