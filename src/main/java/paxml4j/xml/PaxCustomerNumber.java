package paxml4j.xml;

import paxml4j.domain.CustomerNumber;
import xmlight.NodeFactory;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class PaxCustomerNumber {

    public static XmlNode parse(CustomerNumber entity) {
        XmlNode node = NodeFactory.createNode("kundnr", entity.number());
        entity.info().ifPresent(s -> node.addAttribute("info", s));
        return node;
    }
}
