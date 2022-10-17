package paxml4j.xml;

import paxml4j.domain.Dimension;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxDimension {

    public static XmlNode parse(Dimension entity) {
        XmlNode node = NodeFactory.createNode("dimension");
        node.addAttribute("dim", entity.dimension());
        node.addAttribute("namn", entity.name());
        entity.info().ifPresent(s -> node.addAttribute("info", s));
        return node;
    }
}
