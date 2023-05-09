package paxml4j.xml;

import java.math.BigDecimal;
import paxml4j.domain.TravelTransactions;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxTravelTransactions {

    public static XmlNode parse(TravelTransactions entity) {
        XmlNode node = NodeFactory.createNode("resetransaktioner");
        entity.countryCodeStandard().ifPresent(s -> node.addAttribute("landskodstd", s));
        node.addChildren(entity.transactions().stream().map(PaxTransaction::parse).toList());
        return node;
    }

    public static class PaxTransaction {

        public static XmlNode parse(TravelTransactions.Transaction entity) {
            XmlNode node = NodeFactory.createNode("resetrans");
            entity.postId().ifPresent(s -> node.addAttribute("postid", s));
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            node.addChild(NodeFactory.createNode("tidpunkt", entity.dateTime().toString()));
            node.addChild(NodeFactory.createNode("resekod", entity.travelCode().code()));
            entity.contiune().map(PaxContinued::parse).ifPresent(node::addChild);
            entity.countryCode().map(s -> NodeFactory.createNode("landskod", s)).ifPresent(node::addChild);
            entity.currencyCode().map(s -> NodeFactory.createNode("valutakod", s)).ifPresent(node::addChild);
            entity.exchangeRate().map(BigDecimal::toString).map(s -> NodeFactory.createNode("valutafaktor", s)).ifPresent(node::addChild);
            entity.amount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("belopp", s)).ifPresent(node::addChild);
            entity.vat().map(BigDecimal::toString).map(s -> NodeFactory.createNode("moms", s)).ifPresent(node::addChild);
            entity.companyCard().map(b -> b.toString()).map(s -> NodeFactory.createNode("ftgkort", s)).ifPresent(node::addChild);
            entity.numberOfParticipants().map(i -> i.toString()).map(s -> NodeFactory.createNode("antdeltag", s)).ifPresent(node::addChild);
            if (!entity.participants().isEmpty()) {
                XmlNode partList = NodeFactory.createNode("deltagarlista");
                partList.addChildren(entity.participants().stream().map(PaxParticipant::parse).toList());
                node.addChild(partList);
            }
            entity.typeOfGoods().map(s -> NodeFactory.createNode("varugrupp", s)).ifPresent(node::addChild);
            entity.specification().map(s -> NodeFactory.createNode("specifikation", s)).ifPresent(node::addChild);
            entity.accountNumber().map(s -> NodeFactory.createNode("kontonr", s)).ifPresent(node::addChild);
            entity.carRegistrationNumber().map(s -> NodeFactory.createNode("bilnr", s)).ifPresent(node::addChild);
            entity.carModel().map(s -> NodeFactory.createNode("bilmodell", s)).ifPresent(node::addChild);
            entity.company().map(s -> NodeFactory.createNode("foretag", s)).ifPresent(node::addChild);
            entity.contact().map(s -> NodeFactory.createNode("kontakt", s)).ifPresent(node::addChild);
            entity.purpose().map(s -> NodeFactory.createNode("syfte", s)).ifPresent(node::addChild);
            entity.location().map(s -> NodeFactory.createNode("ort", s)).ifPresent(node::addChild);
            entity.meterIndicatorStart().map(i -> i.toString()).map(s -> NodeFactory.createNode("kmstart", s)).ifPresent(node::addChild);
            entity.meterIndicatorEnd().map(i -> i.toString()).map(s -> NodeFactory.createNode("kmstopp", s)).ifPresent(node::addChild);
            entity.numberOfKilometers().map(i -> i.toString()).map(s -> NodeFactory.createNode("kilometer", s)).ifPresent(node::addChild);
            entity.numberOfPassengers().map(i -> i.toString()).map(s -> NodeFactory.createNode("antpass", s)).ifPresent(node::addChild);
            entity.freightWeightInKilograms().map(i -> i.toString()).map(s -> NodeFactory.createNode("antlast", s)).ifPresent(node::addChild);
            entity.travelTimeInHours().map(d -> d.toString()).map(s -> NodeFactory.createNode("timmar", s)).ifPresent(node::addChild);
            entity.summaryId().map(s -> NodeFactory.createNode("samlingsid", s)).ifPresent(node::addChild);
            entity.customerNumber().map(PaxCustomerNumber::parse).ifPresent(node::addChild);
            if (!entity.profitCenters().isEmpty()) {
                XmlNode pcNode = NodeFactory.createNode("resenheter");
                pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter.PaxReference::parse).toList());
                node.addChild(pcNode);
            }
            entity.note().map(s -> NodeFactory.createNode("note", s)).ifPresent(node::addChild);
            entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
            return node;
        }
    }

    public static class PaxContinued {

        public static XmlNode parse(TravelTransactions.Continue entity) {
            XmlNode node = NodeFactory.createNode("fortsatt", entity.continues().toString());
            node.addAttribute("dagnr", entity.dayNumber());
            return node;
        }
    }

    public static class PaxParticipant {

        public static XmlNode parse(TravelTransactions.Participant entity) {
            XmlNode node = NodeFactory.createNode("deltagare");
            node.addAttribute("foretag", entity.company());
            node.addAttribute("namn", entity.name());
            return node;
        }
    }
}
