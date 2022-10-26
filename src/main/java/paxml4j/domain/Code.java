package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import java.util.Optional;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Code implements Entity {

    private final String id;
    private final String name;
    private final String info;

    @JsonCreator
    private Code(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("info") String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public static Code of(String id, String name, String info) {
        return new Code(id, name, info);
    }

    public static Code of(String id, String name) {
        return of(id, name, null);
    }

    public static Code of(XmlNode node) {
        return new Code(node.getAttribute("id"), node.getAttribute("namn"), node.getAttribute("info"));
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Optional<String> info() {
        return Optional.ofNullable(info);
    }

    @Override
    public String toString() {
        return "Code{" + "id=" + id + ", name=" + name + ", info=" + info + '}';
    }
}
