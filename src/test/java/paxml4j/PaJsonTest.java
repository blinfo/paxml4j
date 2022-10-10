package paxml4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import paxml4j.domain.Root;

/**
 *
 * @author hakan
 */
public class PaJsonTest {

    @Test
    public void test_write_to_json1() {
        Root root1 = getRoot(1);
        String json1 = PaJson.write(root1);
        Root root2 = PaJson.read(json1);
        String json2 = PaJson.write(root2);
        assertEquals(json1, json2);
    }

    @Test
    public void test_write_to_json2() {
        Root root1 = getRoot(2);
        String json1 = PaJson.write(root1);
        Root root2 = PaJson.read(json1);
        String json2 = PaJson.write(root2);
        assertEquals(json1, json2);
    }

    private static Root getRoot(int i) {
        if (i == 1) {
            return PaXml.read(PaXmlTest.class.getResourceAsStream("/Workify_DEMOBOLAGET_TimeRegistrationexport (5).xml"));
        }
        return PaXml.read(PaXmlTest.class.getResourceAsStream("/PAXml 2.0.pax"));
    }

}
