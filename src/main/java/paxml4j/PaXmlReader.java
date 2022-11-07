package paxml4j;

import java.io.*;
import java.nio.charset.*;
import org.apache.commons.io.input.*;
import paxml4j.domain.Root;
import xmlight.*;

/**
 *
 * @author hakan
 */
class PaXmlReader {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    static Root read(InputStream source) {
        try ( XmlStreamReader reader = new XmlStreamReader(source, DEFAULT_CHARSET.name())) {
            return read(new DocumentToXmlNodeParser(reader).parse());
        } catch (IOException ex) {
            throw new Paxml4jException(ex);
        }
    }

    static Root read(XmlDocument doc) {
        return read(doc.getRoot());
    }

    static Root read(XmlNode node) {
        return Root.of(node);
    }
}
