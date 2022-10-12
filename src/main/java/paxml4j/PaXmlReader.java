package paxml4j;

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
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    static Root read(InputStream source) {
        try {
            XmlNode root = new DocumentToXmlNodeParser(contentString(source.readAllBytes())).parse();
            return read(new XmlDocument(root));
        } catch (IOException ex) {
            throw new Paxml4jException("Could not parse input stream", ex);
        }
    }

    static Root read(XmlDocument doc) {
        return Root.of(doc.getRoot());
    }

    private static String contentString(byte[] source) {
        String contentString = new String(source, charset(source));
        if (!contentString.startsWith("<")) {
            contentString = contentString.substring(contentString.indexOf("<"));
        }
        return contentString;
    }

    private static Charset charset(byte[] source) {
        String header = new String(source).split(">")[0];
        return charset(header);
    }

    private static Charset charset(String header) {
        if (header.contains(ENCODING_ATTR)) {
            String encoding = header.substring(header.indexOf(ENCODING_ATTR) + ENCODING_ATTR.length());
            encoding = encoding.substring(0, encoding.indexOf("\""));
            try {
                return Charset.forName(encoding);
            } catch (IllegalArgumentException ex) {
                throw new Paxml4jException(ex);
            }
        }
        return DEFAULT_CHARSET;
    }
}
