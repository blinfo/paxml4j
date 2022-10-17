package paxml4j.xml;

import paxml4j.domain.Code;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxCode {

    public static XmlNode parse(Code entity) {
        XmlNode node = NodeFactory.createNode("kod");
        node.addAttribute("id", entity.id());
        node.addAttribute("namn", entity.name());
        entity.info().ifPresent(s -> node.addAttribute("info", s));
        return node;
    }

}
