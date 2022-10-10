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
    public void test_two_conversions_is_unchanged() {
//        try {
            Root root1 = getRoot();
            XmlDocument doc1 = PaXml.write(root1);
//            System.out.println(doc1);
            Root root2 = PaXml.read(doc1);
            XmlDocument doc2 = PaXml.write(root2);
//            System.out.println(doc2);
            assertEquals(doc1.toString(), doc2.toString());
//        } catch (JsonProcessingException ex) {
//            fail();
//        }
    }

    private static Root getRoot() {
        return PaXml.read(PaXmlTest.class.getResourceAsStream("/Workify_DEMOBOLAGET_TimeRegistrationexport (5).xml"));
//        return PaXml.read(PaXmlTest.class.getResourceAsStream("/PAXml 2.0.pax"));
    }
}
