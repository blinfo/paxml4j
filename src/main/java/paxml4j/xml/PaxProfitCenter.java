package paxml4j.xml;

import paxml4j.domain.ProfitCenter;
import paxml4j.domain.ProfitCenter.Reference;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxProfitCenter {

    public static XmlNode parse(ProfitCenter entity) {
        XmlNode node = NodeFactory.createNode("resultatenhet");
        node.addAttribute("id", entity.id());
        node.addAttribute("namn", entity.name());
        node.addAttribute("dim", entity.dimension());
        entity.delete().map(b -> b.toString()).ifPresent(s -> node.addAttribute("delete", s));
        entity.info().ifPresent(s -> node.addAttribute("info", s));
        return node;
    }

    public static class PaxReference {

        public static XmlNode parse(Reference entity) {
            XmlNode node = NodeFactory.createNode("resenhet");
            node.addAttribute("id", entity.id());
            node.addAttribute("dim", entity.dimension());
            return node;
        }
    }
}
