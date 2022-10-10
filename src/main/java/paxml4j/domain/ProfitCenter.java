package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class ProfitCenter implements Entity {

    private final Boolean delete;
    private final Integer dimension;
    private final String id;
    private final String name;
    private final String info;

    @JsonCreator
    private ProfitCenter(@JsonProperty("delete") Boolean delete,
            @JsonProperty("dimension") Integer dimension,
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("info") String info) {
        this.delete = delete;
        this.dimension = dimension;
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public static ProfitCenter of(XmlNode node) {
        Boolean delete = node.hasAttribute("delete") ? Boolean.valueOf(node.getAttribute("delete")) : null;
        return new ProfitCenter(delete,
                Integer.valueOf(node.getAttribute("dim")),
                node.getAttribute("id"),
                node.getAttribute("namn"),
                node.getAttribute("info"));
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer dimension() {
        return dimension;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Optional<Boolean> delete() {
        return Optional.ofNullable(delete);
    }

    public Optional<String> info() {
        return Optional.ofNullable(info);
    }

    @Override
    public String toString() {
        return "ProfitCenter{" + "delete=" + delete + ", dimension=" + dimension + ", id=" + id + ", name=" + name + ", info=" + info + '}';
    }

    public static class Builder {

        private Boolean delete;
        private Integer dimension;
        private String id;
        private String name;
        private String info;

        private Builder() {
        }

        public Builder delete(Boolean delete) {
            this.delete = delete;
            return this;
        }

        public Builder dimension(Integer dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public ProfitCenter build() {
            return new ProfitCenter(delete, dimension, id, name, info);
        }
    }

    public static class Reference implements Entity {

        private final Integer dimension;
        private final String id;

        @JsonCreator
        private Reference(@JsonProperty("dimension") Integer dimension,
                @JsonProperty("id") String id) {
            this.dimension = dimension;
            this.id = id;
        }

        public static Reference of(Integer dimension, String id) {
            return new Reference(dimension, id);
        }

        public static Reference of(XmlNode node) {
            return new Reference(Integer.valueOf(node.getAttribute("dim")), node.getAttribute("id"));
        }

        public Integer dimension() {
            return dimension;
        }

        public String id() {
            return id;
        }

        @Override
        public String toString() {
            return "Reference{" + "dimension=" + dimension + ", id=" + id + '}';
        }
    }
}
