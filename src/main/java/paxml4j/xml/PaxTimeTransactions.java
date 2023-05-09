package paxml4j.xml;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import paxml4j.domain.TimeTransactions;
import paxml4j.domain.TimeTransactions.*;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxTimeTransactions {

    public static XmlNode parse(TimeTransactions entity) {
        XmlNode node = NodeFactory.createNode("tidtransaktioner");
        node.addChildren(entity.done().stream().map(e -> PaxEventItem.parseDone(e)).toList());
        node.addChildren(entity.attested().stream().map(e -> PaxEventItem.parseAttested(e)).toList());
        node.addChildren(entity.transactions().stream().map(PaxTimeTransaction::parse).toList());
        return node;
    }

    public static class PaxEventItem {

        public static XmlNode parseDone(EventItem entity) {
            return parse(entity, "klar");
        }

        public static XmlNode parseAttested(EventItem entity) {
            return parse(entity, "attesterat");
        }

        private static XmlNode parse(EventItem entity, String name) {
            XmlNode node = NodeFactory.createNode(name);
            entity.postId().ifPresent(s -> node.addAttribute("postid", s));
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            entity.date().map(LocalDate::toString).ifPresent(s -> node.addAttribute("datum", s));
            entity.startDate().map(LocalDate::toString).ifPresent(s -> node.addAttribute("datumfrom", s));
            entity.endDate().map(LocalDate::toString).ifPresent(s -> node.addAttribute("datumtom", s));
            return node;
        }
    }

    public static class PaxTimeTransaction {

        public static XmlNode parse(TimeTransaction entity) {
            XmlNode node = NodeFactory.createNode("tidtrans");
            entity.postId().ifPresent(s -> node.addAttribute("postid", s));
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            node.addChild(NodeFactory.createNode("tidkod", entity.timeCode().code()));
            entity.date().map(LocalDate::toString).map(s -> NodeFactory.createNode("datum", s)).ifPresent(node::addChild);
            entity.startDate().map(LocalDate::toString).map(s -> NodeFactory.createNode("datumfrom", s)).ifPresent(node::addChild);
            entity.endDate().map(LocalDate::toString).map(s -> NodeFactory.createNode("datumtom", s)).ifPresent(node::addChild);
            entity.startDateTime().map(Temporal::toString).map(s -> NodeFactory.createNode("starttid", s)).ifPresent(node::addChild);
            entity.endDateTime().map(Temporal::toString).map(s -> NodeFactory.createNode("sluttid", s)).ifPresent(node::addChild);
            entity.hours().map(d -> d.toString()).map(s -> NodeFactory.createNode("timmar", s)).ifPresent(node::addChild);
            entity.extentPercentage().map(d -> d.toString()).map(s -> NodeFactory.createNode("omfattning", s)).ifPresent(node::addChild);
            entity.childPersonalIdentityNumber().map(s -> NodeFactory.createNode("barn", s)).ifPresent(node::addChild);
            entity.summaryId().map(s -> NodeFactory.createNode("samlingsid", s)).ifPresent(node::addChild);
            entity.absenceAccruingHolidayPay().map(b -> b.toString()).map(s -> NodeFactory.createNode("semgrund", s)).ifPresent(node::addChild);
            entity.accountNumber().map(s -> NodeFactory.createNode("kontonr", s)).ifPresent(node::addChild);
            entity.customerNumber().map(PaxCustomerNumber::parse).ifPresent(node::addChild);
            if (!entity.profitCenters().isEmpty()) {
                XmlNode pcNode = NodeFactory.createNode("resenheter");
                pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter.PaxReference::parse).toList());
                node.addChild(pcNode);
            }
            entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
            return node;
        }
    }
}
