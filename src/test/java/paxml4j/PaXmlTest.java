package paxml4j;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import paxml4j.domain.Root;
import xmlight.XmlDocument;

/**
 *
 * @author hakan
 */
public class PaXmlTest {

    @Test
    public void test_two_conversions_is_unchanged1() {
        Root root1 = getRoot(1);
        XmlDocument doc1 = PaXml.write(root1);
        Root root2 = PaXml.read(doc1);
        XmlDocument doc2 = PaXml.write(root2);
        assertEquals(doc1.toString(), doc2.toString());
    }

    @Test
    public void test_two_conversions_is_unchanged2() {
        Root root1 = getRoot(2);
        XmlDocument doc1 = PaXml.write(root1);
        Root root2 = PaXml.read(doc1);
        XmlDocument doc2 = PaXml.write(root2);
        assertEquals(doc1.toString(), doc2.toString());
    }

    @Test
    public void test_two_conversions_is_unchanged3() {
        Root root1 = getRoot(3);
        XmlDocument doc1 = PaXml.write(root1);
        Root root2 = PaXml.read(doc1);
        XmlDocument doc2 = PaXml.write(root2);
        assertEquals(doc1.toString(), doc2.toString());
    }

    @Test
    public void test_two_conversions_is_unchanged4() {
        Root root1 = getRoot(4);
        XmlDocument doc1 = PaXml.write(root1);
        Root root2 = PaXml.read(doc1);
        XmlDocument doc2 = PaXml.write(root2);
        assertEquals(doc1.toString(), doc2.toString());
    }

    @Test
    public void test_two_conversions_is_unchanged5() {
        Root root1 = getRoot(4);
        XmlDocument doc1 = PaXml.write(root1);
        Root root2 = PaXml.read(doc1);
        XmlDocument doc2 = PaXml.write(root2);
        assertEquals(doc1.toString(), doc2.toString());
    }

    private static Root getRoot(int i) {
        switch (i) {
            case 1:
                return PaXml.read(PaXmlTest.class.getResourceAsStream("/Workify_DEMOBOLAGET_TimeRegistrationexport (5).xml"));
            case 2:
                return PaXml.read(PaXmlTest.class.getResourceAsStream("/PAXml 2.0.pax"));
            case 3:
                return PaXml.read(PaXmlTest.class.getResourceAsStream("/DEMO - 2022-10-01-2022-10-31 (1).pax"));
            case 4:
                return PaXml.read(PaXmlTest.class.getResourceAsStream("/DEMO - 2022-10-01-2022-10-31.xml"));
            case 5:
                return PaXml.read(PaXmlTest.class.getResourceAsStream("/paxml_salary_1665487846.xml"));
        }
        return PaXml.read(PaXmlTest.class.getResourceAsStream("/PAXml 2.0.pax"));
    }
}
