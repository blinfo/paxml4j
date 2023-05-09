package paxml4j.xml;

import java.time.temporal.Temporal;
import paxml4j.domain.SchemeTransactions;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxSchemeTransactions {

    public static XmlNode parse(SchemeTransactions entity) {
        XmlNode node = NodeFactory.createNode("schematransaktioner");
        node.addChildren(entity.transactions().stream().map(PaxTransaction::parse).toList());
        return node;
    }

    public static class PaxTransaction {

        public static XmlNode parse(SchemeTransactions.Transaction entity) {
            XmlNode node = NodeFactory.createNode("schema");
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            node.addChildren(entity.days().stream().map(PaxDay::parse).toList());
            return node;
        }
    }

    public static class PaxDay {

        public static XmlNode parse(SchemeTransactions.Day entity) {
            XmlNode node = NodeFactory.createNode("dag");
            node.addAttribute("datum", entity.date().toString());
            entity.startTime().map(Temporal::toString).ifPresent(s -> node.addAttribute("starttid", s));
            entity.endTime().map(Temporal::toString).ifPresent(s -> node.addAttribute("sluttid", s));
            entity.hours().map(d -> d.toString()).ifPresent(s -> node.addAttribute("timmar", s));
            return node;
        }
    }
}
