package paxml4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import paxml4j.domain.Root;

/**
 *
 * @author hakan
 */
public class PaJson {

    public static Root read(InputStream source) {
        try {
            return new ObjectMapper().readValue(source, Root.class);
        } catch (JsonProcessingException ex) {
            throw new Paxml4jException("Could not parse source as json", ex);
        } catch (IOException ex) {
            throw new Paxml4jException("Could not read input stream", ex);
        }
    }

    public static Root read(String json) {
        try {
            return new ObjectMapper().readValue(json, Root.class);
        } catch (JsonProcessingException ex) {
            throw new Paxml4jException("Could not parse source as json", ex);
        }
    }

    public static String write(Root root) {
        try {
            return new ObjectMapper().writeValueAsString(root);
        } catch (JsonProcessingException ex) {
            throw new Paxml4jException("Could not write json from root", ex);
        }
    }
}
