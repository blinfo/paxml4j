package paxml4j.xml;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import paxml4j.domain.Staff;
import paxml4j.domain.Staff.Employee;
import xmlight.NodeFactory;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class PaxStaff {

    public static XmlNode parse(Staff entity) {
        XmlNode node = NodeFactory.createNode("personal");
        node.addChildren(entity.employees().stream().map(PaxEmployee::parse).collect(Collectors.toList()));
        return node;
    }

    public static class PaxEmployee {

        public static XmlNode parse(Employee entity) {
            XmlNode node = NodeFactory.createNode("person");
            entity.employmentId().ifPresent(s -> node.addAttribute("anstid", s));
            entity.personalIdentityNumber().ifPresent(s -> node.addAttribute("persnr", s));
            entity.delete().map(b -> b.toString()).ifPresent(s -> node.addAttribute("delete", s));
            entity.firstName().map(s -> NodeFactory.createNode("fornamn", s)).ifPresent(node::addChild);
            entity.lastName().map(s -> NodeFactory.createNode("efternamn", s)).ifPresent(node::addChild);
            entity.extraAddress().map(s -> NodeFactory.createNode("extraadress", s)).ifPresent(node::addChild);
            entity.postalAddress().map(s -> NodeFactory.createNode("postadress", s)).ifPresent(node::addChild);
            entity.zipCode().map(s -> NodeFactory.createNode("postnr", s)).ifPresent(node::addChild);
            entity.city().map(s -> NodeFactory.createNode("ort", s)).ifPresent(node::addChild);
            entity.country().map(s -> NodeFactory.createNode("land", s)).ifPresent(node::addChild);
            entity.mobilePhone().map(s -> NodeFactory.createNode("mobiltelefon", s)).ifPresent(node::addChild);
            entity.homePhone().map(s -> NodeFactory.createNode("hemtelefon", s)).ifPresent(node::addChild);
            entity.workPhone().map(s -> NodeFactory.createNode("arbetstelefon", s)).ifPresent(node::addChild);
            entity.workEmail().map(s -> NodeFactory.createNode("epostarb", s)).ifPresent(node::addChild);
            entity.homeEmail().map(s -> NodeFactory.createNode("eposthem", s)).ifPresent(node::addChild);
            entity.typeOfEmployee().map(s -> NodeFactory.createNode("personaltyp", s)).ifPresent(node::addChild);
            entity.category().map(s -> NodeFactory.createNode("kategori", s)).ifPresent(node::addChild);
            entity.position().map(s -> NodeFactory.createNode("befattning", s)).ifPresent(node::addChild);
            entity.positionCode().map(s -> NodeFactory.createNode("befattningskod", s)).ifPresent(node::addChild);
            entity.formOfEmployment().map(s -> NodeFactory.createNode("anstform", s)).ifPresent(node::addChild);
            entity.vacationAgreement().map(s -> NodeFactory.createNode("semesteravtal", s)).ifPresent(node::addChild);
            entity.bankClearingNumber().map(s -> NodeFactory.createNode("bankclearing", s)).ifPresent(node::addChild);
            entity.bankAccountNumber().map(s -> NodeFactory.createNode("bankkonto", s)).ifPresent(node::addChild);
            entity.dateOfEmployment().map(LocalDate::toString).map(s -> NodeFactory.createNode("anstdatum", s)).ifPresent(node::addChild);
            entity.dateOfRetirement().map(LocalDate::toString).map(s -> NodeFactory.createNode("avgdatum", s)).ifPresent(node::addChild);
            entity.salaryType().map(Staff.SalaryType::code).map(s -> NodeFactory.createNode("lonform", s)).ifPresent(node::addChild);
            entity.salaryForPresentPeriod().map(b -> b.toString()).map(s -> NodeFactory.createNode("innevarande", s)).ifPresent(node::addChild);
            entity.hourlySalary().map(da -> PaxDateAmount.parse(da, "timlon")).ifPresent(node::addChild);
            entity.monthlySalary().map(da -> PaxDateAmount.parse(da, "manlon")).ifPresent(node::addChild);
            if (!entity.taxFreeAmounts().isEmpty()) {
                XmlNode tfNode = NodeFactory.createNode("personbelopp");
                tfNode.addChildren(entity.taxFreeAmounts().stream().map(PaxTaxFreeAmount::parse).collect(Collectors.toList()));
                node.addChild(tfNode);
            }
            if (!entity.employeeTexts().isEmpty()) {
                XmlNode ptNode = NodeFactory.createNode("persontexter");
                ptNode.addChildren(entity.employeeTexts().stream().map(PaxText::parse).collect(Collectors.toList()));
                node.addChild(ptNode);
            }
            entity.degreeOfEmployment().map(da -> PaxDateAmount.parse(da, "sysgrad")).ifPresent(node::addChild);
            entity.daysOfVacation().map(d -> d.toString()).map(s -> NodeFactory.createNode("semesterdagar", s)).ifPresent(node::addChild);
            entity.taxTable().map(d -> d.toString()).map(s -> NodeFactory.createNode("skattetabell", s)).ifPresent(node::addChild);
            entity.taxColumn().map(i -> i.toString()).map(s -> NodeFactory.createNode("skattekolumn", s)).ifPresent(node::addChild);
            entity.taxReconciliation().map(PaxTaxReconciliation::parse).ifPresent(node::addChild);
            entity.wageDestraint().map(PaxWageDestraint::parse).ifPresent(node::addChild);
            if (!entity.profitCenters().isEmpty()) {
                XmlNode pcNode = NodeFactory.createNode("resenheter");
                pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter.PaxReference::parse).collect(Collectors.toList()));
                node.addChild(pcNode);
            }
            entity.info().map(s -> NodeFactory.createNode("info", s)).ifPresent(node::addChild);
            return node;
        }
    }

    public static class PaxDateAmount {

        public static XmlNode parse(Staff.DateAmount entity, String name) {
            XmlNode node = NodeFactory.createNode(name, entity.amount().toString());
            entity.changeDate().map(LocalDate::toString).ifPresent(s -> node.addAttribute("datum", s));
            return node;
        }
    }

    public static class PaxTaxFreeAmount {

        public static XmlNode parse(Staff.TaxFreeAmount entity) {
            XmlNode node = NodeFactory.createNode("belopp", entity.amount().toString());
            node.addAttribute("id", entity.id());
            entity.date().map(LocalDate::toString).ifPresent(s -> node.addAttribute("datum", s));
            return node;
        }
    }

    public static class PaxText {

        public static XmlNode parse(Staff.Text entity) {
            XmlNode node = NodeFactory.createNode("text", entity.text());
            node.addAttribute("id", entity.id());
            return node;
        }
    }

    public static class PaxTaxReconciliation {

        public static XmlNode parse(Staff.TaxReconciliation entity) {
            XmlNode node = NodeFactory.createNode("skattejamkning");
            entity.percentage().map(i -> i.toString()).ifPresent(s -> node.addAttribute("procent", s));
            entity.amount().map(BigDecimal::toString).ifPresent(s -> node.addAttribute("belopp", s));
            entity.maxAmount().map(BigDecimal::toString).ifPresent(s -> node.addAttribute("maxbelopp", s));
            return node;
        }
    }

    public static class PaxWageDestraint {

        public static XmlNode parse(Staff.WageDestraint entity) {
            XmlNode node = NodeFactory.createNode("loneutmatning");
            node.addAttribute("belopp", entity.amount().toString());
            entity.reservedAmount().map(BigDecimal::toString).ifPresent(s -> node.addAttribute("belopp", s));
            return node;
        }
    }
}
