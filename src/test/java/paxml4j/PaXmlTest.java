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

    private static Root getRoot(int i) {
        if (i == 1) {
            return PaXml.read(PaXmlTest.class.getResourceAsStream("/Workify_DEMOBOLAGET_TimeRegistrationexport (5).xml"));
        }
        return PaXml.read(PaXmlTest.class.getResourceAsStream("/PAXml 2.0.pax"));
    }
}
