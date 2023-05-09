package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import static paxml4j.util.Helper.*;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Root implements Entity {

    private final Header header;
    private final List<Dimension> dimensions;
    private final List<ProfitCenter> profitCenters;
    private final List<Code> codes;
    private final TravelTransactions travelTransactions;
    private final TimeTransactions timeTransactions;
    private final SalaryTransactions salaryTransactions;
    private final SchemeTransactions schemeTransactions;
    private final Staff staff;
    private final SalaryPayments salaryPayments;
    private final BalanceList balanceList;

    @JsonCreator
    private Root(@JsonProperty("header") Header header,
            @JsonProperty("dimensions") List<Dimension> dimensions,
            @JsonProperty("profitCenters") List<ProfitCenter> profitCenters,
            @JsonProperty("codes") List<Code> codes,
            @JsonProperty("travelTransactions") TravelTransactions travelTransactions,
            @JsonProperty("timeTransactions") TimeTransactions timeTransactions,
            @JsonProperty("salaryTransactions") SalaryTransactions salaryTransactions,
            @JsonProperty("schemeTransactions") SchemeTransactions schemeTransactions,
            @JsonProperty("staff") Staff staff,
            @JsonProperty("salaryPayments") SalaryPayments salaryPayments,
            @JsonProperty("balanceList") BalanceList balanceList) {
        this.header = header;
        this.dimensions = Objects.requireNonNull(dimensions);
        this.profitCenters = Objects.requireNonNull(profitCenters);
        this.codes = Objects.requireNonNull(codes);
        this.travelTransactions = travelTransactions;
        this.timeTransactions = timeTransactions;
        this.salaryTransactions = salaryTransactions;
        this.schemeTransactions = schemeTransactions;
        this.staff = staff;
        this.salaryPayments = salaryPayments;
        this.balanceList = balanceList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Root of(XmlNode node) {
        Builder builder = builder();
        builder.header(Header.of(node.getChild("header")));
        if (node.hasChildNamed("dimensioner")) {
            builder.dimensions(node.getChild("dimensioner").getChildren("dimension").stream().map(Dimension::of).toList());
        }
        if (node.hasChildNamed("resultatenheter")) {
            builder.profitCenters(node.getChild("resultatenheter").getChildren("resultatenhet").stream().map(ProfitCenter::of).toList());
        }
        if (node.hasChildNamed("koder")) {
            builder.codes(node.getChild("koder").getChildren("kod").stream().map(Code::of).toList());
        }
        childNode(node, "resetransaktioner").map(TravelTransactions::of).ifPresent(builder::travelTransactions);
        childNode(node, "tidtransaktioner").map(TimeTransactions::of).ifPresent(builder::timeTransactions);
        childNode(node, "lonetransaktioner").map(SalaryTransactions::of).ifPresent(builder::salaryTransactions);
        childNode(node, "schematransaktioner").map(SchemeTransactions::of).ifPresent(builder::schemeTransactions);
        childNode(node, "personal").map(Staff::of).ifPresent(builder::staff);
        childNode(node, "loneutbetalning").map(SalaryPayments::of).ifPresent(builder::salaryPayments);
        childNode(node, "saldon").map(BalanceList::of).ifPresent(builder::balanceList);
        return builder.build();
    }

    public Header header() {
        return header;
    }

    public List<Dimension> dimensions() {
        return List.copyOf(dimensions);
    }

    public List<ProfitCenter> profitCenters() {
        return List.copyOf(profitCenters);
    }

    public List<Code> codes() {
        return List.copyOf(codes);
    }

    public Optional<TravelTransactions> travelTransactions() {
        return Optional.ofNullable(travelTransactions);
    }

    public Optional<TimeTransactions> timeTransactions() {
        return Optional.ofNullable(timeTransactions);
    }

    public Optional<SalaryTransactions> salaryTransactions() {
        return Optional.ofNullable(salaryTransactions);
    }

    public Optional<SchemeTransactions> schemeTransactions() {
        return Optional.ofNullable(schemeTransactions);
    }

    public Optional<Staff> staff() {
        return Optional.ofNullable(staff);
    }

    public Optional<SalaryPayments> salaryPayments() {
        return Optional.ofNullable(salaryPayments);
    }

    public Optional<BalanceList> balanceList() {
        return Optional.ofNullable(balanceList);
    }

    public static class Builder {

        private Header header;
        private List<Dimension> dimensions = List.of();
        private List<ProfitCenter> profitCenters = List.of();
        private List<Code> codes = List.of();
        private TravelTransactions travelTransactions;
        private TimeTransactions timeTransactions;
        private SalaryTransactions salaryTransactions;
        private SchemeTransactions schemeTransactions;
        private Staff staff;
        private SalaryPayments salaryPayments;
        private BalanceList balanceList;

        private Builder() {
        }

        public Builder header(Header header) {
            this.header = header;
            return this;
        }

        public Builder dimensions(List<Dimension> dimensions) {
            this.dimensions = Objects.requireNonNull(dimensions);
            return this;
        }

        public Builder profitCenters(List<ProfitCenter> profitCenters) {
            this.profitCenters = Objects.requireNonNull(profitCenters);
            return this;
        }

        public Builder codes(List<Code> codes) {
            this.codes = Objects.requireNonNull(codes);
            return this;
        }

        public Builder travelTransactions(TravelTransactions travelTransactions) {
            this.travelTransactions = travelTransactions;
            return this;
        }

        public Builder timeTransactions(TimeTransactions timeTransactions) {
            this.timeTransactions = timeTransactions;
            return this;
        }

        public Builder salaryTransactions(SalaryTransactions salaryTransactions) {
            this.salaryTransactions = salaryTransactions;
            return this;
        }

        public Builder schemeTransactions(SchemeTransactions schemeTransactions) {
            this.schemeTransactions = schemeTransactions;
            return this;
        }

        public Builder staff(Staff staff) {
            this.staff = staff;
            return this;
        }

        public Builder salaryPayments(SalaryPayments salaryPayments) {
            this.salaryPayments = salaryPayments;
            return this;
        }

        public Builder balanceList(BalanceList balanceList) {
            this.balanceList = balanceList;
            return this;
        }

        public Root build() {
            return new Root(header,
                    dimensions,
                    profitCenters,
                    codes,
                    travelTransactions,
                    timeTransactions,
                    salaryTransactions,
                    schemeTransactions,
                    staff,
                    salaryPayments,
                    balanceList);
        }
    }
}
