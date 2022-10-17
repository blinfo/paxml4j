package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import java.util.Optional;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Dimension implements Entity {

    private final Integer dimension;
    private final String name;
    private final String info;

    @JsonCreator
    private Dimension(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("info") String info) {
        this.dimension = id;
        this.name = name;
        this.info = info;
    }

    public static Dimension of(XmlNode node) {
        return new Dimension(Integer.valueOf(node.getAttribute("dim")), node.getAttribute("namn"), node.getAttribute("info"));
    }

    public static Dimension of(Integer dimension, String name) {
        return of(dimension, name, null);
    }

    public static Dimension of(Integer dimension, String name, String info) {
        return new Dimension(dimension, name, info);
    }

    public Integer dimension() {
        return dimension;
    }

    public String name() {
        return name;
    }

    public Optional<String> info() {
        return Optional.ofNullable(info);
    }

    @Override
    public String toString() {
        return "Dimension{" + "dimension=" + dimension + ", name=" + name + ", info=" + info + '}';
    }
}
