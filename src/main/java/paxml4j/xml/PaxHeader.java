package paxml4j.xml;

import java.time.LocalDate;
import paxml4j.domain.Header;
import paxml4j.domain.Header.NewExport;
import xmlight.NodeFactory;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class PaxHeader {

    public static XmlNode parse(Header entity) {
        XmlNode node = NodeFactory.createNode("header");
        entity.format().map(s -> NodeFactory.createNode("format", s)).ifPresent(node::addChild);
        node.addChild(NodeFactory.createNode("version", entity.version()));
        entity.timestamp().map(t -> t.toString()).map(s -> NodeFactory.createNode("datum", s)).ifPresent(node::addChild);
        entity.newExport().map(PaxNewExport::parse).ifPresent(node::addChild);
        entity.companyId().map(s -> NodeFactory.createNode("foretagid", s)).ifPresent(node::addChild);
        entity.corporateIdentityNumber().map(s -> NodeFactory.createNode("foretagorgnr", s)).ifPresent(node::addChild);
        entity.companyName().map(s -> NodeFactory.createNode("foretagnamn", s)).ifPresent(node::addChild);
        entity.extraAddress().map(s -> NodeFactory.createNode("extraadress", s)).ifPresent(node::addChild);
        entity.postalAddress().map(s -> NodeFactory.createNode("postadress", s)).ifPresent(node::addChild);
        entity.zipCode().map(s -> NodeFactory.createNode("postnr", s)).ifPresent(node::addChild);
        entity.city().map(s -> NodeFactory.createNode("ort", s)).ifPresent(node::addChild);
        entity.country().map(s -> NodeFactory.createNode("land", s)).ifPresent(node::addChild);
        entity.email().map(s -> NodeFactory.createNode("epost", s)).ifPresent(node::addChild);
        entity.homepage().map(s -> NodeFactory.createNode("hemsida", s)).ifPresent(node::addChild);
        entity.contact().map(s -> NodeFactory.createNode("kontaktperson", s)).ifPresent(node::addChild);
        entity.staffManager().map(s -> NodeFactory.createNode("personalansvarig", s)).ifPresent(node::addChild);
        entity.attestant().map(s -> NodeFactory.createNode("attestansvarig", s)).ifPresent(node::addChild);
        entity.phone().map(s -> NodeFactory.createNode("telefon", s)).ifPresent(node::addChild);
        entity.fax().map(s -> NodeFactory.createNode("telefax", s)).ifPresent(node::addChild);
        entity.programName().map(s -> NodeFactory.createNode("programnamn", s)).ifPresent(node::addChild);
        entity.programLicense().map(s -> NodeFactory.createNode("programlicens", s)).ifPresent(node::addChild);
        entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
        return node;
    }

    public static class PaxNewExport {

        public static XmlNode parse(NewExport entity) {
            XmlNode node = NodeFactory.createNode("nyexport");
            node.addChild(NodeFactory.createNode("datum", entity.date().toString()));
            entity.startDate().map(LocalDate::toString).map(s -> NodeFactory.createNode("datumfrom", s)).ifPresent(node::addChild);
            entity.endDate().map(LocalDate::toString).map(s -> NodeFactory.createNode("datumtom", s)).ifPresent(node::addChild);
            return node;
        }
    }
}
