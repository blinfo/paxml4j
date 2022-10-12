package paxml4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.*;
import paxml4j.domain.Root;
import xmlight.*;

/**
 *
 * @author hakan
 */
class PaXmlReader {

    private static final String ENCODING_ATTR = "encoding=\"";

    static Root read(InputStream source) {
        try {
            byte[] content = source.readAllBytes();
            String contentString = contentString(content);
            XmlDocument doc = new XmlDocument(new DocumentToXmlNodeParser(contentString).parse());
            return Root.of(doc.getRoot());
        } catch (IOException ex) {
            throw new Paxml4jException("Could not parse input stream", ex);
        }
    }

    private static String contentString(byte[] source) {
        String contentString = new String(source, charset(source));
        if (!contentString.startsWith("<")) {
            contentString = contentString.substring(contentString.indexOf("<"));
        }
        return contentString;
    }

    private static Charset charset(byte[] source) {
        String header = new String(source).split("\n")[0];
        return charset(header);
    }

    private static Charset charset(String header) {
        if (header.contains(ENCODING_ATTR)) {
            String encoding = header.substring(header.indexOf(ENCODING_ATTR) + ENCODING_ATTR.length());
            encoding = encoding.substring(0, encoding.indexOf("\""));
            return Charset.forName(encoding);
        }
        return StandardCharsets.UTF_8;
    }
}
