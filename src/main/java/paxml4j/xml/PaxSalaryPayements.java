package paxml4j.xml;

import java.math.BigDecimal;
import java.time.temporal.Temporal;
import paxml4j.domain.SalaryPayments;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxSalaryPayements {

    public static XmlNode parse(SalaryPayments entity) {
        XmlNode node = NodeFactory.createNode("loneutbetalning");
        node.addChildren(entity.payslips().stream().map(PaxPayslip::parse).toList());
        return node;
    }

    public static class PaxPayslip {

        public static XmlNode parse(SalaryPayments.Payslip entity) {
            XmlNode node = NodeFactory.createNode("lonebesked");
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            entity.periodId().map(s -> NodeFactory.createNode("periodid", s)).ifPresent(node::addChild);
            entity.firstName().map(s -> NodeFactory.createNode("fornamn", s)).ifPresent(node::addChild);
            entity.lastName().map(s -> NodeFactory.createNode("efternamn", s)).ifPresent(node::addChild);
            entity.extraAddress().map(s -> NodeFactory.createNode("extraadress", s)).ifPresent(node::addChild);
            entity.postalAddress().map(s -> NodeFactory.createNode("postadress", s)).ifPresent(node::addChild);
            entity.zipCode().map(s -> NodeFactory.createNode("postnr", s)).ifPresent(node::addChild);
            entity.city().map(s -> NodeFactory.createNode("ort", s)).ifPresent(node::addChild);
            entity.country().map(s -> NodeFactory.createNode("land", s)).ifPresent(node::addChild);
            entity.bankClearingNumber().map(s -> NodeFactory.createNode("bankclearing", s)).ifPresent(node::addChild);
            entity.bankAccountNumber().map(s -> NodeFactory.createNode("bankkonto", s)).ifPresent(node::addChild);
            entity.taxPercentage().map(i -> i.toString()).map(s -> NodeFactory.createNode("skatteprocent", s)).ifPresent(node::addChild);
            entity.taxTable().map(d -> d.toString()).map(s -> NodeFactory.createNode("skattetabell", s)).ifPresent(node::addChild);
            entity.taxColumn().map(i -> i.toString()).map(s -> NodeFactory.createNode("skattekolumn", s)).ifPresent(node::addChild);
            entity.taxAdjustmentPercentage().map(d -> d.toString()).map(s -> NodeFactory.createNode("jämkningprc", s)).ifPresent(node::addChild);
            entity.taxAdjustmentAmount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("jämkningbel", s)).ifPresent(node::addChild);
            entity.tableTax().map(BigDecimal::toString).map(s -> NodeFactory.createNode("tabellskatt", s)).ifPresent(node::addChild);
            entity.oneTimeTax().map(BigDecimal::toString).map(s -> NodeFactory.createNode("engangsskatt", s)).ifPresent(node::addChild);
            entity.capitalTax().map(BigDecimal::toString).map(s -> NodeFactory.createNode("kapitalskatt", s)).ifPresent(node::addChild);
            entity.extraTax().map(BigDecimal::toString).map(s -> NodeFactory.createNode("extraskatt", s)).ifPresent(node::addChild);
            entity.disbursed().map(BigDecimal::toString).map(s -> NodeFactory.createNode("utbetalt", s)).ifPresent(node::addChild);
            entity.employersContributionPercentage().map(d -> d.toString()).map(s -> NodeFactory.createNode("arbavgiftprc", s)).ifPresent(node::addChild);
            entity.employersContributionAmount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("arbavgiftbel", s)).ifPresent(node::addChild);
            if (!entity.paymentRows().isEmpty()) {
                XmlNode rowsNode = NodeFactory.createNode("lonerader");
                rowsNode.addChildren(entity.paymentRows().stream().map(PaxPaymentRow::parse).toList());
                node.addChild(rowsNode);
            }
            entity.accumulatedGrossWage().map(BigDecimal::toString).map(s -> NodeFactory.createNode("ackbruttolon", s)).ifPresent(node::addChild);
            entity.accumulatedPreliminaryTax().map(BigDecimal::toString).map(s -> NodeFactory.createNode("ackprelskatt", s)).ifPresent(node::addChild);
            entity.accumulatedNetWage().map(BigDecimal::toString).map(s -> NodeFactory.createNode("acknettolon", s)).ifPresent(node::addChild);
            entity.flexibleHoursBalance().map(d -> d.toString()).map(s -> NodeFactory.createNode("flexsaldo", s)).ifPresent(node::addChild);
            entity.compensatoryLeaveBalance().map(d -> d.toString()).map(s -> NodeFactory.createNode("kompsaldo", s)).ifPresent(node::addChild);
            entity.reductionOfWorkingHours().map(d -> d.toString()).map(s -> NodeFactory.createNode("tidbanktim", s)).ifPresent(node::addChild);
            entity.reductionOfWorkingHoursAmount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("tidbankbel", s)).ifPresent(node::addChild);
            entity.daysOfVacationTotal().map(d -> d.toString()).map(s -> NodeFactory.createNode("sembettot", s)).ifPresent(node::addChild);
            entity.daysOfVacationDisbursed().map(d -> d.toString()).map(s -> NodeFactory.createNode("sembetutb", s)).ifPresent(node::addChild);
            entity.daysOfUnpaidVacationTotal().map(d -> d.toString()).map(s -> NodeFactory.createNode("semobetot", s)).ifPresent(node::addChild);
            entity.daysOfUnpaidVacationDisbursed().map(d -> d.toString()).map(s -> NodeFactory.createNode("semobeutb", s)).ifPresent(node::addChild);
            entity.daysOfAdvancedVacationTotal().map(d -> d.toString()).map(s -> NodeFactory.createNode("semfortot", s)).ifPresent(node::addChild);
            entity.daysOfAdvancedVacationDisbursed().map(d -> d.toString()).map(s -> NodeFactory.createNode("semforutb", s)).ifPresent(node::addChild);
            entity.daysOfSavedVacationTotal().map(d -> d.toString()).map(s -> NodeFactory.createNode("semspatot", s)).ifPresent(node::addChild);
            entity.daysOfSavedVacationDisbursed().map(d -> d.toString()).map(s -> NodeFactory.createNode("semspautb", s)).ifPresent(node::addChild);
            entity.vacationPaymentAmountTotal().map(BigDecimal::toString).map(s -> NodeFactory.createNode("semlontot", s)).ifPresent(node::addChild);
            entity.vacationPaymentAmountDisbursed().map(BigDecimal::toString).map(s -> NodeFactory.createNode("semlonutb", s)).ifPresent(node::addChild);
            if (!entity.transactions().isEmpty()) {
                XmlNode accountingNode = NodeFactory.createNode("kontering");
                accountingNode.addChildren(entity.transactions().stream().map(PaxTransaction::parse).toList());
                node.addChild(accountingNode);
            }
            entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
            return node;
        }
    }

    public static class PaxPaymentRow {

        public static XmlNode parse(SalaryPayments.PaymentRow entity) {
            XmlNode node = NodeFactory.createNode("lonrad");
            entity.rowIndex().ifPresent(i -> node.addAttribute("radnr", i));
            entity.typeOfSalary().map(s -> NodeFactory.createNode("lonart", s)).ifPresent(node::addChild);
            entity.font().map(s -> NodeFactory.createNode("font", s)).ifPresent(node::addChild);
            entity.nameOfSalary().map(s -> NodeFactory.createNode("benamning", s)).ifPresent(node::addChild);
            entity.comment().map(s -> NodeFactory.createNode("kommentar", s)).ifPresent(node::addChild);
            entity.startDate().map(Temporal::toString).map(s -> NodeFactory.createNode("datumfrom", s)).ifPresent(node::addChild);
            entity.startDate().map(Temporal::toString).map(s -> NodeFactory.createNode("datumtom", s)).ifPresent(node::addChild);
            entity.hours().map(d -> d.toString()).map(s -> NodeFactory.createNode("timmar", s)).ifPresent(node::addChild);
            entity.workingDays().map(d -> d.toString()).map(s -> NodeFactory.createNode("arbetsdagar", s)).ifPresent(node::addChild);
            entity.calendarDays().map(d -> d.toString()).map(s -> NodeFactory.createNode("dagar", s)).ifPresent(node::addChild);
            entity.unit().map(s -> NodeFactory.createNode("enhet", s)).ifPresent(node::addChild);
            entity.quantity().map(d -> d.toString()).map(s -> NodeFactory.createNode("antal", s)).ifPresent(node::addChild);
            entity.unitPrice().map(BigDecimal::toString).map(s -> NodeFactory.createNode("apris", s)).ifPresent(node::addChild);
            entity.amount().map(BigDecimal::toString).map(s -> NodeFactory.createNode("belopp", s)).ifPresent(node::addChild);
            entity.salaryType().map(SalaryPayments.SalaryType::code).map(s -> NodeFactory.createNode("lonetyp", s)).ifPresent(node::addChild);
            entity.taxType().map(SalaryPayments.TaxationType::code).map(s -> NodeFactory.createNode("skattetyp", s)).ifPresent(node::addChild);
            entity.taxPercentage().map(d -> d.toString()).map(s -> NodeFactory.createNode("skattprocent", s)).ifPresent(node::addChild);
            entity.dueType().map(SalaryPayments.DueType::code).map(s -> NodeFactory.createNode("avgifttyp", s)).ifPresent(node::addChild);
            entity.duePercentage().map(d -> d.toString()).map(s -> NodeFactory.createNode("avgiftprocent", s)).ifPresent(node::addChild);
            entity.regionalSupport().map(b -> b.toString()).map(s -> NodeFactory.createNode("regional", s)).ifPresent(node::addChild);
            entity.accountNumber().map(s -> NodeFactory.createNode("kontonr", s)).ifPresent(node::addChild);
            entity.customerNumber().map(PaxCustomerNumber::parse).ifPresent(node::addChild);
            if (!entity.profitCenters().isEmpty()) {
                XmlNode pcNode = NodeFactory.createNode("resenheter");
                pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter.PaxReference::parse).toList());
                node.addChild(pcNode);
            }
            entity.statisticsCode().map(s -> NodeFactory.createNode("statistikkod", s)).ifPresent(node::addChild);
            entity.statementOfEarnings().map(s -> NodeFactory.createNode("kontrolluppgift", s)).ifPresent(node::addChild);
            entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
            return node;
        }
    }

    public static class PaxTransaction {

        public static XmlNode parse(SalaryPayments.Transaction entity) {
            XmlNode node = NodeFactory.createNode("transaktion");
            entity.accountNumber().ifPresent(s -> node.addAttribute("kontonr", s));
            entity.amount().map(BigDecimal::toString).ifPresent(s -> node.addAttribute("belopp", s));
            entity.customerNumber().map(PaxCustomerNumber::parse).ifPresent(node::addChild);
            if (!entity.profitCenters().isEmpty()) {
                XmlNode pcNode = NodeFactory.createNode("resenheter");
                pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter.PaxReference::parse).toList());
                node.addChild(pcNode);
            }
            return node;
        }
    }
}
