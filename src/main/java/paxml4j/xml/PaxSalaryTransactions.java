package paxml4j.xml;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import paxml4j.domain.SalaryTransactions;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxSalaryTransactions {

    public static XmlNode parse(SalaryTransactions entity) {
        XmlNode node = NodeFactory.createNode("lonetransaktioner");
        node.addChildren(entity.transactions().stream().map(PaxTransaction::parse).toList());
        return node;
    }

    public static class PaxTransaction {

        public static XmlNode parse(SalaryTransactions.Transaction entity) {
            XmlNode node = NodeFactory.createNode("lonetrans");
            entity.postId().ifPresent(s -> node.addAttribute("postid", s));
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            entity.salaryCode().map(SalaryTransactions.SalaryCode::code).map(s -> NodeFactory.createNode("lonkod", s)).ifPresent(node::addChild);
            entity.typeOfSalary().map(s -> NodeFactory.createNode("lonart", s)).ifPresent(node::addChild);
            entity.nameOfSalary().map(s -> NodeFactory.createNode("benamning", s)).ifPresent(node::addChild);
            entity.comment().map(s -> NodeFactory.createNode("kommentar", s)).ifPresent(node::addChild);
            entity.date().map(LocalDate::toString).map(s -> NodeFactory.createNode("datum", s)).ifPresent(node::addChild);
            entity.startDateTime().map(Temporal::toString).map(s -> NodeFactory.createNode("datumfrom", s)).ifPresent(node::addChild);
            entity.endDateTime().map(Temporal::toString).map(s -> NodeFactory.createNode("datumtom", s)).ifPresent(node::addChild);
            entity.quantity().map(d -> d.toString()).map(s -> NodeFactory.createNode("antal", s)).ifPresent(node::addChild);
            entity.unitPrice().map(BigDecimal::toString).map(s -> NodeFactory.createNode("apris", s)).ifPresent(node::addChild);
            entity.amount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("belopp", s)).ifPresent(node::addChild);
            entity.typeOfGoods().map(s -> NodeFactory.createNode("varugrupp", s)).ifPresent(node::addChild);
            entity.vat().map(BigDecimal::toString).map(s -> NodeFactory.createNode("moms", s)).ifPresent(node::addChild);
            entity.summaryId().map(s -> NodeFactory.createNode("samlingsid", s)).ifPresent(node::addChild);
            entity.accountNumber().map(s -> NodeFactory.createNode("kontonr", s)).ifPresent(node::addChild);
            entity.customerNumber().map(PaxCustomerNumber::parse).ifPresent(node::addChild);
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
