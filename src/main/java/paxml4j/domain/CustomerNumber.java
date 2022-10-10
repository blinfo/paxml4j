package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class CustomerNumber implements Entity {

    private final String number;
    private final String info;

    @JsonCreator
    private CustomerNumber(@JsonProperty("customerNumber") String customerNumber, @JsonProperty("info") String info) {
        this.number = customerNumber;
        this.info = info;
    }

    public static CustomerNumber of(String customerNumber, String info) {
        return new CustomerNumber(customerNumber, info);
    }

    public static CustomerNumber of(XmlNode node) {
        return new CustomerNumber(node.getText(), node.getAttribute("info"));
    }

    public String number() {
        return number;
    }

    public Optional<String> info() {
        return Optional.ofNullable(info);
    }

    @Override
    public String toString() {
        return "CustomerNumber{" + "number=" + number + ", info=" + info + '}';
    }
}
