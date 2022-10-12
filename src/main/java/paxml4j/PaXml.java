package paxml4j;

import java.io.InputStream;
import paxml4j.domain.Root;
import paxml4j.xml.PaxRoot;
import xmlight.XmlDocument;

/**
 *
 * @author hakan
 */
public class PaXml {

    public static Root read(XmlDocument doc) {
        return PaXmlReader.read(doc);
    }

    public static Root read(InputStream source) {
        return PaXmlReader.read(source);
    }

    public static XmlDocument write(Root root) {
        return PaxRoot.parse(root);
    }
}
