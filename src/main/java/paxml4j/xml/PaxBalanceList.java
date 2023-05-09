package paxml4j.xml;

import java.math.BigDecimal;
import java.time.LocalDate;
import paxml4j.domain.BalanceList;
import paxml4j.domain.BalanceList.Balance;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxBalanceList {

    public static XmlNode parse(BalanceList entity) {
        XmlNode node = NodeFactory.createNode("saldon");
        node.addChildren(entity.balances().stream().map(PaxBalance::parse).toList());
        return node;
    }

    public static class PaxBalance {

        public static XmlNode parse(Balance entity) {
            XmlNode node = NodeFactory.createNode("saldo");
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            entity.date().map(LocalDate::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("datum", s)));
            entity.accumulatedGrossWage().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("ackbruttolon", s)));
            entity.accumulatedPreliminaryTax().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("ackprelskatt", s)));
            entity.accumulatedNetWage().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("acknettolon", s)));
            entity.flexibleHoursBalance().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("flexsaldo", s)));
            entity.compensatoryLeaveBalance().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("kompsaldo", s)));
            entity.reductionOfWorkingHours().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("tidbanktim", s)));
            entity.reductionOfWorkingHoursAmount().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("tidbankbel", s)));
            entity.daysOfVacationTotal().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("sembettot", s)));
            entity.daysOfVacationDisbursed().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("sembeutb", s)));
            entity.daysOfUnpaidVacationTotal().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semobetot", s)));
            entity.daysOfUnpaidVacationDisbursed().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semobeutb", s)));
            entity.daysOfAdvancedVacationTotal().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semfortot", s)));
            entity.daysOfAdvancedVacationDisbursed().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semforutb", s)));
            entity.daysOfSavedVacationTotal().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semspatot", s)));
            entity.daysOfSavedVacationDisbursed().map(d -> d.toString()).ifPresent(s -> node.addChild(NodeFactory.createNode("semspautb", s)));
            entity.vacationPaymentAmountTotal().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("semlontot", s)));
            entity.vacationPaymentAmountDisbursed().map(BigDecimal::toString).ifPresent(s -> node.addChild(NodeFactory.createNode("semlonutb", s)));
            entity.info().ifPresent(s -> node.addChild(NodeFactory.createNode("info", s)));
            return node;
        }
    }
}
