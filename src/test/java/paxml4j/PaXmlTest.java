package paxml4j;

import java.io.IOException;
import java.nio.charset.*;
import org.apache.commons.io.input.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import paxml4j.domain.Root;
import xmlight.*;

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

    @Test
    public void test_XmlStreamReader_with_iso88591() {
        try ( XmlStreamReader reader = new XmlStreamReader(getClass().getResourceAsStream("/PAXml 2.0.pax"), StandardCharsets.UTF_8.name())) {
            assertEquals("ISO-8859-1", reader.getEncoding());
            XmlNode node = new DocumentToXmlNodeParser(reader).parse();
            assertTrue(node.toString().contains("Kostnadsställe"));
        } catch (IOException ex) {
            fail();
        }
    }

    @Test
    public void test_XmlStreamReader_with_BOM() {
        try ( XmlStreamReader reader = new XmlStreamReader(getClass().getResourceAsStream("/DEMO - 2022-10-01-2022-10-31_with_BOM.xml"))) {
            XmlNode node = new DocumentToXmlNodeParser(reader).parse();
            assertEquals("UTF-8", reader.getEncoding());
            assertTrue(node.toString().contains("LÖNIN"));
        } catch (IOException ex) {
            fail();
        }
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
