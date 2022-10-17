package paxml4j.xml;

import java.util.stream.Collectors;
import paxml4j.domain.Root;
import xmlight.*;

/**
 *
 * @author hakan
 */
public class PaxRoot {

    public static XmlDocument parse(Root entity) {
        XmlNode root = NodeFactory.createNode("paxml");
        root.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.addAttribute("xsi:noNamespaceSchemaLocation", "http://www.paxml.se/2.0/paxml.xsd");

        root.addChild(PaxHeader.parse(entity.header()));

        if (!entity.dimensions().isEmpty()) {
            XmlNode dimNode = NodeFactory.createNode("dimensioner");
            dimNode.addChildren(entity.dimensions().stream().map(PaxDimension::parse).collect(Collectors.toList()));
            root.addChild(dimNode);
        }

        if (!entity.profitCenters().isEmpty()) {
            XmlNode pcNode = NodeFactory.createNode("resultatenheter");
            pcNode.addChildren(entity.profitCenters().stream().map(PaxProfitCenter::parse).collect(Collectors.toList()));
            root.addChild(pcNode);
        }

        if (!entity.codes().isEmpty()) {
            XmlNode codeNode = NodeFactory.createNode("koder");
            codeNode.addChildren(entity.codes().stream().map(PaxCode::parse).collect(Collectors.toList()));
            root.addChild(codeNode);
        }

        entity.travelTransactions().map(PaxTravelTransactions::parse).ifPresent(root::addChild);

        entity.timeTransactions().map(PaxTimeTransactions::parse).ifPresent(root::addChild);

        entity.salaryTransactions().map(PaxSalaryTransactions::parse).ifPresent(root::addChild);

        entity.schemeTransactions().map(PaxSchemeTransactions::parse).ifPresent(root::addChild);

        entity.staff().map(PaxStaff::parse).ifPresent(root::addChild);

        entity.salaryPayments().map(PaxSalaryPayements::parse).ifPresent(root::addChild);

        entity.balanceList().map(PaxBalanceList::parse).ifPresent(root::addChild);

        XmlDocument doc = new XmlDocument(root);
        doc.getHead().addAttribute("encoding", "UTF-8");
        return doc;
    }
}
