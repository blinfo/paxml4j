package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.*;
import paxml4j.Paxml4jException;
import paxml4j.json.io.*;
import paxml4j.util.Helper;
import static paxml4j.util.Helper.*;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class SalaryTransactions implements Entity {

    private final List<Transaction> transactions;

    @JsonCreator
    private SalaryTransactions(@JsonProperty("transactions") List<Transaction> transactions) {
        this.transactions = Objects.requireNonNull(transactions);
    }

    public static SalaryTransactions of(List<Transaction> transactions) {
        return new SalaryTransactions(transactions);
    }

    public static SalaryTransactions of(XmlNode node) {
        return of(node.getChildren("lonetrans").stream().map(Transaction::of).toList());
    }

    public List<Transaction> transactions() {
        return List.copyOf(transactions);
    }

    @Override
    public String toString() {
        return "SalaryTransactions{" + "transactions=" + transactions + '}';
    }

    public static class Transaction implements Entity {

        private final Integer postId;
        private final String employmentId;
        private final String personalIdentityNumber;
        private final SalaryCode salaryCode;
        private final String typeOfSalary;
        private final String nameOfSalary;
        private final String comment;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal startDateTime;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal endDateTime;
        private final Double quantity;
        private final BigDecimal unitPrice;
        private final BigDecimal amount;
        private final String typeOfGoods;
        private final BigDecimal vat;
        private final String summaryId;
        private final String accountNumber;
        private final CustomerNumber customerNumber;
        private final List<ProfitCenter.Reference> profitCenters;
        private final String info;

        @JsonCreator
        private Transaction(@JsonProperty("postId") Integer postId,
                @JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonProperty("salaryCode") SalaryCode salaryCode,
                @JsonProperty("typeOfSalary") String typeOfSalary,
                @JsonProperty("nameOfSalary") String nameOfSalary,
                @JsonProperty("comment") String comment,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("startDateTime") Temporal startDateTime,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("endDateTime") Temporal endDateTime,
                @JsonProperty("quantity") Double quantity,
                @JsonProperty("unitPrice") BigDecimal unitPrice,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("typeOfGoods") String typeOfGoods,
                @JsonProperty("vat") BigDecimal vat,
                @JsonProperty("summaryId") String summaryId,
                @JsonProperty("accountNumber") String accountNumber,
                @JsonProperty("customerNumber") CustomerNumber customerNumber,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters,
                @JsonProperty("info") String info) {
            this.postId = postId;
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.salaryCode = salaryCode;
            this.typeOfSalary = typeOfSalary;
            this.nameOfSalary = nameOfSalary;
            this.comment = comment;
            this.date = date;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.amount = amount;
            this.typeOfGoods = typeOfGoods;
            this.vat = vat;
            this.summaryId = summaryId;
            this.accountNumber = accountNumber;
            this.customerNumber = customerNumber;
            this.profitCenters = Objects.requireNonNull(profitCenters);
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Transaction of(XmlNode node) {
            Builder builder = builder();
            attrText(node, "postid").map(Integer::valueOf).ifPresent(builder::postId);
            attrText(node, "anstid").ifPresent(builder::employmentId);
            attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            nodeText(node, "lonkod").map(SalaryCode::find).ifPresent(builder::salaryCode);
            nodeText(node, "lonart").ifPresent(builder::typeOfSalary);
            nodeText(node, "benamning").ifPresent(builder::nameOfSalary);
            nodeText(node, "kommentar").ifPresent(builder::comment);
            nodeText(node, "datum").map(LocalDate::parse).ifPresent(builder::date);
            nodeText(node, "datumfrom").map(Helper::temporalFromText).ifPresent(builder::startDateTime);
            nodeText(node, "datumtom").map(Helper::temporalFromText).ifPresent(builder::endDateTime);
            nodeText(node, "antal").map(Double::valueOf).ifPresent(builder::quantity);
            nodeText(node, "apris").map(BigDecimal::new).ifPresent(builder::unitPrice);
            nodeText(node, "belopp").map(BigDecimal::new).ifPresent(builder::amount);
            nodeText(node, "varugrupp").ifPresent(builder::typeOfGoods);
            nodeText(node, "moms").map(BigDecimal::new).ifPresent(builder::vat);
            nodeText(node, "samlingsid").ifPresent(builder::summaryId);
            nodeText(node, "kontonr").ifPresent(builder::accountNumber);
            childNode(node, "kundnr").map(CustomerNumber::of).ifPresent(builder::customerNumber);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet")
                        .stream()
                        .map(ProfitCenter.Reference::of)
                        .toList());
            }
            nodeText(node, "info").ifPresent(builder::info);
            return builder.build();
        }

        public Optional<Integer> postId() {
            return Optional.ofNullable(postId);
        }

        public Optional<String> employmentId() {
            return Optional.ofNullable(employmentId);
        }

        public Optional<String> personalIdentityNumber() {
            return Optional.ofNullable(personalIdentityNumber);
        }

        public Optional<SalaryCode> salaryCode() {
            return Optional.ofNullable(salaryCode);
        }

        public Optional<String> typeOfSalary() {
            return Optional.ofNullable(typeOfSalary);
        }

        public Optional<String> nameOfSalary() {
            return Optional.ofNullable(nameOfSalary);
        }

        public Optional<String> comment() {
            return Optional.ofNullable(comment);
        }

        public Optional<LocalDate> date() {
            return Optional.ofNullable(date);
        }

        public Optional<Temporal> startDateTime() {
            return Optional.ofNullable(startDateTime);
        }

        public Optional<Temporal> endDateTime() {
            return Optional.ofNullable(endDateTime);
        }

        public Optional<Double> quantity() {
            return Optional.ofNullable(quantity);
        }

        public Optional<BigDecimal> unitPrice() {
            return Optional.ofNullable(unitPrice).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> amount() {
            return Optional.ofNullable(amount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<String> typeOfGoods() {
            return Optional.ofNullable(typeOfGoods);
        }

        public Optional<BigDecimal> vat() {
            return Optional.ofNullable(vat).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<String> summaryId() {
            return Optional.ofNullable(summaryId);
        }

        public Optional<String> accountNumber() {
            return Optional.ofNullable(accountNumber);
        }

        public Optional<CustomerNumber> customerNumber() {
            return Optional.ofNullable(customerNumber);
        }

        public List<ProfitCenter.Reference> profitCenters() {
            return List.copyOf(profitCenters);
        }

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }

        @Override
        public String toString() {
            return "Transaction{" + "postId=" + postId + ", employmentId=" + employmentId + ", personalIdentityNumber=" + personalIdentityNumber + ", salaryCode=" + salaryCode + ", typeOfSalary=" + typeOfSalary + ", nameOfSalary=" + nameOfSalary + ", comment=" + comment + ", date=" + date + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", amount=" + amount + ", typeOfGoods=" + typeOfGoods + ", vat=" + vat + ", summaryId=" + summaryId + ", accountNumber=" + accountNumber + ", customerNumber=" + customerNumber + ", profitCenters=" + profitCenters + ", info=" + info + '}';
        }

        public static class Builder {

            private Integer postId;
            private String employmentId;
            private String personalIdentityNumber;
            private SalaryCode salaryCode;
            private String typeOfSalary;
            private String nameOfSalary;
            private String comment;
            private LocalDate date;
            private Temporal startDateTime;
            private Temporal endDateTime;
            private Double quantity;
            private BigDecimal unitPrice;
            private BigDecimal amount;
            private String typeOfGoods;
            private BigDecimal vat;
            private String summaryId;
            private String accountNumber;
            private CustomerNumber customerNumber;
            private List<ProfitCenter.Reference> profitCenters = List.of();
            private String info;

            private Builder() {
            }

            public Builder postId(Integer postId) {
                this.postId = postId;
                return this;
            }

            public Builder employmentId(String employmentId) {
                this.employmentId = employmentId;
                return this;
            }

            public Builder personalIdentityNumber(String personalIdentityNumber) {
                this.personalIdentityNumber = personalIdentityNumber;
                return this;
            }

            public Builder salaryCode(SalaryCode salaryCode) {
                this.salaryCode = salaryCode;
                return this;
            }

            public Builder typeOfSalary(String typeOfSalary) {
                this.typeOfSalary = typeOfSalary;
                return this;
            }

            public Builder nameOfSalary(String nameOfSalary) {
                this.nameOfSalary = nameOfSalary;
                return this;
            }

            public Builder comment(String comment) {
                this.comment = comment;
                return this;
            }

            public Builder date(LocalDate date) {
                this.date = date;
                return this;
            }

            public Builder startDateTime(Temporal startDateTime) {
                this.startDateTime = startDateTime;
                return this;
            }

            public Builder endDateTime(Temporal endDateTime) {
                this.endDateTime = endDateTime;
                return this;
            }

            public Builder quantity(Double quantity) {
                this.quantity = quantity;
                return this;
            }

            public Builder unitPrice(BigDecimal unitPrice) {
                this.unitPrice = unitPrice;
                return this;
            }

            public Builder amount(BigDecimal amount) {
                this.amount = amount;
                return this;
            }

            public Builder typeOfGoods(String typeOfGoods) {
                this.typeOfGoods = typeOfGoods;
                return this;
            }

            public Builder vat(BigDecimal vat) {
                this.vat = vat;
                return this;
            }

            public Builder summaryId(String summaryId) {
                this.summaryId = summaryId;
                return this;
            }

            public Builder accountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
                return this;
            }

            public Builder customerNumber(CustomerNumber customerNumber) {
                this.customerNumber = customerNumber;
                return this;
            }

            public Builder profitCenters(List<ProfitCenter.Reference> profitCenters) {
                this.profitCenters = Objects.requireNonNull(profitCenters);
                return this;
            }

            public Builder info(String info) {
                this.info = info;
                return this;
            }

            public Transaction build() {
                return new Transaction(postId,
                        employmentId,
                        personalIdentityNumber,
                        salaryCode,
                        typeOfSalary,
                        nameOfSalary,
                        comment,
                        date,
                        startDateTime,
                        endDateTime,
                        quantity,
                        unitPrice,
                        amount,
                        typeOfGoods,
                        vat,
                        summaryId,
                        accountNumber,
                        customerNumber,
                        profitCenters,
                        info);
            }
        }

    }

    public enum SalaryCode {

        WORKING_HOURS("TID"),
        HOURLY_WAGE("ARB"),
        ADDITIONAL_TIME("MER"),
        OVERTIME_PAYMENT_1("ÖT1"),
        OVERTIME_PAYMENT_2("ÖT2"),
        OVERTIME_PAYMENT_3("ÖT3"),
        OVERTIME_PAYMENT_4("ÖT4"),
        OVERTIME_PAYMENT_5("ÖT5"),
        OVERTIME_RECOMPENSE_1("ÖK1"),
        OVERTIME_RECOMPENSE_2("ÖK2"),
        OVERTIME_RECOMPENSE_3("ÖK3"),
        OVERTIME_RECOMPENSE_4("ÖK4"),
        OVERTIME_RECOMPENSE_5("ÖK5"),
        STAND_BY_1("JR1"),
        STAND_BY_2("JR2"),
        STAND_BY_3("JR3"),
        PREPAREDNESS_TIME_1("BE1"),
        PREPAREDNESS_TIME_2("BE2"),
        PREPAREDNESS_TIME_3("BE3"),
        TRAVELLING_TIME_1("RE1"),
        TRAVELLING_TIME_2("RE2"),
        TRAVELLING_TIME_3("RE3"),
        TRAVELLING_TIME_4("RE4"),
        OTHER_PRESENCE_1("NV1"),
        OTHER_PRESENCE_2("NV2"),
        OTHER_PRESENCE_3("NV3"),
        OTHER_PRESENCE_4("NV4"),
        OTHER_PRESENCE_5("NV5"),
        OTHER_PRESENCE_6("NV6"),
        OTHER_PRESENCE_7("NV7"),
        OTHER_PRESENCE_8("NV8"),
        OTHER_PRESENCE_9("NV9"),
        INCONVENIENT_HOURS_1("OB1"),
        INCONVENIENT_HOURS_2("OB2"),
        INCONVENIENT_HOURS_3("OB3"),
        INCONVENIENT_HOURS_4("OB4"),
        INCONVENIENT_HOURS_5("OB5"),
        WEEKEND_SALARY("HLG"),
        SHIFT_SUPPLEMENT("SKI"),
        WAGE_SUPPLEMENT_1("LT1"),
        WAGE_SUPPLEMENT_2("LT2"),
        WAGE_SUPPLEMENT_3("LT3"),
        WAGE_SUPPLEMENT_4("LT4"),
        WAGE_SUPPLEMENT_5("LT5"),
        WAGE_SUPPLEMENT_6("LT6"),
        WAGE_SUPPLEMENT_7("LT7"),
        WAGE_SUPPLEMENT_8("LT8"),
        WAGE_SUPPLEMENT_9("LT9"),
        MONTHLY_SALARY("MÅNLÖN"),
        HOURLY_SALARY("TIMLÖN"),
        BONUS("BONUS"),
        COMMISSION("PROVISION"),
        ADVANCEMENT("FÖRSKOTT"),
        EXPENSE("UTLÄGG"),
        TRAVEL_EXPENSES("RESERS"),
        DOMESTIC_ALLOWANCE_TAX_FREE("INR_FRI"),
        DOMESTIC_REDUCED_ALLOWANCE_TAX_FREE("INR_RED"),
        DOMESTIC_ALLOWANCE_TAXABLE("INR_SKT"),
        DOMESTIC_FULL_ALLOWANCE_TAX_FREE("INRHEL_FRI"),
        DOMESTIC_REDUCED_FULL_ALLOWANCE_TAX_FREE("INRHEL_RED"),
        DOMESTIC_FULL_ALLOWANCE_TAXABLE("INRHEL_SKT"),
        DOMESTIC_HALF_ALLOWANCE_TAX_FREE("INRHLV_FRI"),
        DOMESTIC_REDUCED_HALF_ALLOWANCE_TAX_FREE("INRHLV_RED"),
        DOMESTIC_HALF_ALLOWANCE_TAXABLE("INRHLV_SKT"),
        DOMESTIC_ONE_DAY_ALLOWANCE_TAXABLE("INRDAG_SKT"),
        DOMESTIC_NIGHT_ALLOWANCE_TAX_FREE("INRNAT_FRI"),
        DOMESTIC_NIGHT_ALLOWANCE_TAXABLE("INRNAT_SKT"),
        FOREIGN_ALLOWANCE_TAX_FREE("UTR_FRI"),
        FOREIGN_REDUCED_ALLOWANCE_TAX_FREE("UTR_RED"),
        FOREIGN_ALLOWANCE_TAXABLE("UTR_SKT"),
        FOREIGN_FULL_ALLOWANCE_TAX_FREE("UTRHEL_FRI"),
        FOREIGN_REDUCED_FULL_ALLOWANCE_TAX_FREE("UTRHEL_RED"),
        FOREIGN_FULL_ALLOWANCE_TAXABLE("UTRHEL_SKT"),
        FOREIGN_HALF_ALLOWANCE_TAX_FREE("UTRHLV_FRI"),
        FOREIGN_REDUCED_HALF_ALLOWANCE_TAX_FREE("UTRHLV_RED"),
        FOREIGN_HALF_ALLOWANCE_TAXABLE("UTRHLV_SKT"),
        FOREIGN_ONE_DAY_ALLOWANCE_TAXABLE("UTRDAG_SKT"),
        FOREIGN_NIGHT_ALLOWANCE_TAX_FREE("UTRNAT_FRI"),
        FOREIGN_NIGHT_ALLOWANCE_TAXABLE("UTRNAT_SKT"),
        MILEAGE_TAX_FREE("MIL_FRI"),
        MILEAGE_TAXABLE("MIL_SKT"),
        MILEAGE_ELECTRIC_TAX_FREE("MILEL_FRI"),
        MILEAGE_PRIVATE_CAR_TAX_FREE("MILPRI_FRI"),
        MILEAGE_PRIVATE_CAR_TAXABLE("MILPRI_SKT"),
        MILEAGE_COMPANY_CAR_TAX_FREE("MILFTG_FRI"),
        MILEAGE_COMPANY_CAR_TAXABLE("MILFTG_SKT"),
        MILEAGE_COMPANY_CAR_DIESEL_TAX_FREE("MILDIS_FRI"),
        MILEAGE_COMPANY_CAR_DIESEL_TAXABLE("MILDIS_SKT"),
        BOARD_BENEFIT_DOMESTIC("MATFRM"),
        BOARD_BENEFIT_BREAKFAST_DOMESTIC("MATFRM_FRU"),
        BOARD_BENEFIT_LUNCH_DOMESTIC("MATFRM_LCH"),
        BOARD_BENEFIT_DINNER_DOMESTIC("MATFRM_MID"),
        BOARD_BENEFIT_BREAKFAST("UTRFRM"),
        BOARD_BENEFIT_BREAKFAST_ABROAD("UTRFRM_FRU"),
        BOARD_BENEFIT_LUNCH_ABROAD("UTRFRM_LCH"),
        BOARD_BENEFIT_DINNER_ABROAD("UTRFRM_MID"),
        BOARD_REDUCTION_DOMESTIC("MATRED"),
        BOARD_REDUCTION_BREAKFAST_DOMESTIC("MATRED_FRU"),
        BOARD_REDUCTION_LUNCH_DOMESTIC("MATRED_LCH"),
        BOARD_REDUCTION_DINNER_DOMESTIC("MATRED_MID"),
        BOARD_REDUCTION_FOREIGN("UTRRED"),
        BOARD_REDUCTION_BREAKFAST_FOREIGN("UTRRED_FRU"),
        BOARD_REDUCTION_LUNCH_FOREIGN("UTRRED_LCH"),
        BOARD_REDUCTION_DINNER_FOREIGN("UTRRED_MID"),
        EXTERNAL_REPRESENTATION("REPEXT"),
        EXTERNAL_REPRESENTATION_DEDUCTABLE("REPEXT_FRI"),
        EXTERNAL_REPRESENTATION_NON_DEDUCTABLE("REPEXT_SKT"),
        INTERNAL_REPRESENTATION("REPINT"),
        INTERNAL_REPRESENTATION_DEDUCTABLE("REPINT_FRI"),
        INTERNAL_REPRESENTATION_NON_DEDUCTABLE("REPINT_SKT");
        private final String code;

        private SalaryCode(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static SalaryCode find(String text) {
            return Stream.of(values())
                    .filter(sc -> sc.name().equalsIgnoreCase(text) || sc.code().equalsIgnoreCase(text))
                    .findFirst()
                    .orElseThrow(() -> new Paxml4jException("No SalaryCode found for code: " + text));
        }
    }
}
