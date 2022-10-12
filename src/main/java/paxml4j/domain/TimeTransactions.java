package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import paxml4j.util.Helper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import paxml4j.Paxml4jException;
import paxml4j.json.io.LocalDateDeserializer;
import paxml4j.json.io.LocalDateSerializer;
import paxml4j.json.io.TemporalDeserializer;
import paxml4j.json.io.TemporalSerializer;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class TimeTransactions implements Entity {

    private final List<EventItem> done;
    private final List<EventItem> attested;
    private final List<TimeTransaction> transactions;

    @JsonCreator
    private TimeTransactions(@JsonProperty("done") List<EventItem> done,
            @JsonProperty("attested") List<EventItem> attested,
            @JsonProperty("transactions") List<TimeTransaction> transactions) {
        this.done = Objects.requireNonNull(done);
        this.attested = Objects.requireNonNull(attested);
        this.transactions = Objects.requireNonNull(transactions);
    }

    public static TimeTransactions of(List<EventItem> done, List<EventItem> attested, List<TimeTransaction> transactions) {
        return new TimeTransactions(done, attested, transactions);
    }

    public static TimeTransactions of(XmlNode node) {
        List<EventItem> done = node.getChildren("klart").stream().map(EventItem::of).collect(Collectors.toList());
        List<EventItem> attested = node.getChildren("attesterat").stream().map(EventItem::of).collect(Collectors.toList());
        List<TimeTransaction> timeTransactions = node.getChildren("tidtrans").stream().map(TimeTransaction::of).collect(Collectors.toList());
        return new TimeTransactions(done, attested, timeTransactions);
    }

    public List<EventItem> done() {
        return List.copyOf(done);
    }

    public List<EventItem> attested() {
        return List.copyOf(attested);
    }

    public List<TimeTransaction> transactions() {
        return List.copyOf(transactions);
    }

    @Override
    public String toString() {
        return "TimeTransactions{" + "done=" + done + ", attested=" + attested + ", transactions=" + transactions + '}';
    }

    public static class TimeTransaction implements Entity {

        private final Integer postId;
        private final String employmentId;
        private final String personalIdentityNumber;
        private final TimeCode timeCode;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate startDate;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate endDate;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal startDateTime;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal endDateTime;
        private final Double hours;
        private final Double extentPercentage;
        private final String childPersonalIdentityNumber;
        private final String summaryId;
        private final Boolean absenceAccruingHolidayPay;
        private final String accountNumber;
        private final CustomerNumber customerNumber;
        private final List<ProfitCenter.Reference> profitCenters;
        private final String info;

        @JsonCreator
        private TimeTransaction(@JsonProperty("postId") Integer postId,
                @JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonProperty("timeCode") TimeCode timeCode,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("startDate") LocalDate startDate,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("endDate") LocalDate endDate,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("startDateTime") Temporal startDateTime,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("endDateTime") Temporal endDateTime,
                @JsonProperty("hours") Double hours,
                @JsonProperty("extentPercentage") Double extentPercentage,
                @JsonProperty("childPersonalIdentityNumber") String childPersonalIdentityNumber,
                @JsonProperty("summaryId") String summaryId,
                @JsonProperty("absenceAccruingHolidayPay") Boolean absenceAccruingHolidayPay,
                @JsonProperty("accountNumber") String accountNumber,
                @JsonProperty("customerNumber") CustomerNumber customerNumber,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters,
                @JsonProperty("info") String info) {
            this.postId = postId;
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.timeCode = timeCode;
            this.date = date;
            this.startDate = startDate;
            this.endDate = endDate;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.hours = hours;
            this.extentPercentage = extentPercentage;
            this.childPersonalIdentityNumber = childPersonalIdentityNumber;
            this.summaryId = summaryId;
            this.absenceAccruingHolidayPay = absenceAccruingHolidayPay;
            this.accountNumber = accountNumber;
            this.customerNumber = customerNumber;
            this.profitCenters = Objects.requireNonNull(profitCenters);
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static TimeTransaction of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "postid").map(Integer::valueOf).ifPresent(builder::postId);
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.nodeText(node, "tidkod").map(TimeCode::find).ifPresent(builder::timeCode);
            Helper.nodeText(node, "datum").map(LocalDate::parse).ifPresent(builder::date);
            Helper.nodeText(node, "datumfrom").map(LocalDate::parse).ifPresent(builder::startDate);
            Helper.nodeText(node, "datumtom").map(LocalDate::parse).ifPresent(builder::endDate);
            Helper.nodeText(node, "starttid").map(Helper::temporalFromText).ifPresent(builder::startDateTime);
            Helper.nodeText(node, "sluttid").map(Helper::temporalFromText).ifPresent(builder::endDateTime);
            Helper.nodeText(node, "timmar").map(Double::valueOf).ifPresent(builder::hours);
            Helper.nodeText(node, "omfattning").map(Double::valueOf).ifPresent(builder::extentPercentage);
            Helper.nodeText(node, "barn").ifPresent(builder::childPersonalIdentityNumber);
            Helper.nodeText(node, "samlingid").ifPresent(builder::summaryId);
            Helper.nodeText(node, "semgrund").map(Boolean::valueOf).ifPresent(builder::absenceAccruingHolidayPay);
            Helper.nodeText(node, "kontonr").ifPresent(builder::accountNumber);
            Helper.childNode(node, "kundnr").map(CustomerNumber::of).ifPresent(builder::customerNumber);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet")
                        .stream()
                        .map(ProfitCenter.Reference::of)
                        .collect(Collectors.toList()));
            }
            Helper.nodeText(node, "info").ifPresent(builder::info);
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

        public TimeCode timeCode() {
            return timeCode;
        }

        public Optional<LocalDate> date() {
            return Optional.ofNullable(date);
        }

        public Optional<LocalDate> startDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<LocalDate> endDate() {
            return Optional.ofNullable(endDate);
        }

        public Optional<Temporal> startDateTime() {
            return Optional.ofNullable(startDateTime);
        }

        public Optional<Temporal> endDateTime() {
            return Optional.ofNullable(endDateTime);
        }

        public Optional<Double> hours() {
            return Optional.ofNullable(hours);
        }

        public Optional<Double> extentPercentage() {
            return Optional.ofNullable(extentPercentage);
        }

        public Optional<String> childPersonalIdentityNumber() {
            return Optional.ofNullable(childPersonalIdentityNumber);
        }

        public Optional<String> summaryId() {
            return Optional.ofNullable(summaryId);
        }

        public Optional<Boolean> absenceAccruingHolidayPay() {
            return Optional.ofNullable(absenceAccruingHolidayPay);
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
            return "TimeTransaction{" + "postId=" + postId + ", employmentId=" + employmentId + ", personalIdentityNumber=" + personalIdentityNumber + ", timeCode=" + timeCode + ", date=" + date + ", startDate=" + startDate + ", endDate=" + endDate + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", hours=" + hours + ", extentPercentage=" + extentPercentage + ", childPersonalIdentityNumber=" + childPersonalIdentityNumber + ", summaryId=" + summaryId + ", absenceAccruingHolidayPay=" + absenceAccruingHolidayPay + ", accountNumber=" + accountNumber + ", customerNumber=" + customerNumber + ", profitCenters=" + profitCenters + ", info=" + info + '}';
        }

        public static class Builder {

            private Integer postId;
            private String employmentId;
            private String personalIdentityNumber;
            private TimeCode timeCode;
            private LocalDate date;
            private LocalDate startDate;
            private LocalDate endDate;
            private Temporal startDateTime;
            private Temporal endDateTime;
            private Double hours;
            private Double extentPercentage;
            private String childPersonalIdentityNumber;
            private String summaryId;
            private Boolean absenceAccruingHolidayPay;
            private String accountNumber;
            private CustomerNumber customerNumber;
            private List<ProfitCenter.Reference> profitCenters = List.of();
            private String info;

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

            public Builder timeCode(TimeCode timeCode) {
                this.timeCode = timeCode;
                return this;
            }

            public Builder date(LocalDate date) {
                this.date = date;
                return this;
            }

            public Builder startDate(LocalDate startDate) {
                this.startDate = startDate;
                return this;
            }

            public Builder endDate(LocalDate endDate) {
                this.endDate = endDate;
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

            public Builder hours(Double hours) {
                this.hours = hours;
                return this;
            }

            public Builder extentPercentage(Double extentPercentage) {
                this.extentPercentage = extentPercentage;
                return this;
            }

            public Builder childPersonalIdentityNumber(String childPersonalIdentityNumber) {
                this.childPersonalIdentityNumber = childPersonalIdentityNumber;
                return this;
            }

            public Builder summaryId(String summaryId) {
                this.summaryId = summaryId;
                return this;
            }

            public Builder absenceAccruingHolidayPay(Boolean absenceAccruingHolidayPay) {
                this.absenceAccruingHolidayPay = absenceAccruingHolidayPay;
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

            public TimeTransaction build() {
                return new TimeTransaction(postId,
                        employmentId,
                        personalIdentityNumber,
                        timeCode,
                        date,
                        startDate,
                        endDate,
                        startDateTime,
                        endDateTime,
                        hours,
                        extentPercentage,
                        childPersonalIdentityNumber,
                        summaryId,
                        absenceAccruingHolidayPay,
                        accountNumber,
                        customerNumber,
                        profitCenters,
                        info);
            }
        }

    }

    public static class EventItem implements Entity {

        private final Integer postId;
        private final String employmentId;
        private final String personalIdentityNumber;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate startDate;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate endDate;

        @JsonCreator
        private EventItem(@JsonProperty("postId") Integer postId,
                @JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("startDate") LocalDate startDate,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("endDate") LocalDate endDate) {
            this.postId = postId;
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.date = date;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static EventItem of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "postid").map(Integer::valueOf).ifPresent(builder::postId);
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.attrText(node, "datum").map(LocalDate::parse).ifPresent(builder::date);
            Helper.attrText(node, "datumfrom").map(LocalDate::parse).ifPresent(builder::startDate);
            Helper.attrText(node, "datumtom").map(LocalDate::parse).ifPresent(builder::endDate);
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

        public Optional<LocalDate> date() {
            return Optional.ofNullable(date);
        }

        public Optional<LocalDate> startDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<LocalDate> endDate() {
            return Optional.ofNullable(endDate);
        }

        @Override
        public String toString() {
            return "EventItem{" + "postId=" + postId + ", employmentId=" + employmentId + ", personalIdentityNumber=" + personalIdentityNumber + ", date=" + date + ", startDate=" + startDate + ", endDate=" + endDate + '}';
        }

        public static class Builder {

            private Integer postId;
            private String employmentId;
            private String personalIdentityNumber;
            private LocalDate date;
            private LocalDate startDate;
            private LocalDate endDate;

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

            public Builder date(LocalDate date) {
                this.date = date;
                return this;
            }

            public Builder startDate(LocalDate startDate) {
                this.startDate = startDate;
                return this;
            }

            public Builder endDate(LocalDate endDate) {
                this.endDate = endDate;
                return this;
            }

            public EventItem build() {
                return new EventItem(postId,
                        employmentId,
                        personalIdentityNumber,
                        date,
                        startDate,
                        endDate);
            }
        }
    }

    public enum TimeCode {
        SICKNESS("SJK"),
        SICKNESS_QUALIFYING_PERIOD("SJK_KAR"),
        SICKNESS_STATUTORY_PAY("SJK_LÖN"),
        SICKNESS_BENEFIT("SJK_ERS"),
        SICKNESS_ALLOWANCE("SJK_PEN"),
        INDUSTRIAL_INJURY("ASK"),
        MATERNITY_ALLOWANCE("HAV"),
        PARENTAL_LEAVE("FPE"),
        CARE_OF_CHILD("VAB"),
        CARRIER("SMB"),
        TRAINING("UTB"),
        MILITARY_SERVICE("MIL"),
        SWEDISH_FOR_IMMIGRANTS("SVE"),
        CARE_OF_IMMEDIATE_FAMILY("NÄR"),
        OFF_DUTY("TJL"),
        VACATION("SEM"),
        VACATION_WITH_PAY("SEM_BET"),
        VACATION_SAVED("SEM_SPA"),
        VACATION_WITHOUT_PAY("SEM_OBE"),
        VACATION_ADVANCED("SEM_FÖR"),
        COMPENSATORY_LEAVE("KOM"),
        LEAV_OF_ABSENCE("PEM"),
        DISMISSED("PER"),
        UNION_WORK("FAC"),
        WORKING_HOURS_ACCOUNT("ATK"),
        CONTACT_DAYS("KON"),
        PATERNITY_LEAVE("PAP"),
        SICKNESS_STAND_BY_1("JS1"),
        SICKNESS_STAND_BY_2("JS2"),
        SICKNESS_STAND_BY_3("JS3"),
        SICKNESS_PREPARED_1("BS1"),
        SICKNESS_PREPARED_2("BS2"),
        SICKNESS_PREPARED_3("BS3"),
        REDUCTION_OF_WORKING_HOURS("ATF"),
        OTHER_ABSENCE_1("FR1"),
        OTHER_ABSENCE_2("FR2"),
        OTHER_ABSENCE_3("FR3"),
        OTHER_ABSENCE_4("FR4"),
        OTHER_ABSENCE_5("FR5"),
        OTHER_ABSENCE_6("FR6"),
        OTHER_ABSENCE_7("FR7"),
        OTHER_ABSENCE_8("FR8"),
        OTHER_ABSENCE_9("FR9"),
        SCHEDULED_TIME("SCH"),
        FLEXIBLE_HOURS("FLX"),
        TIME_BALANCE_1("TS1"),
        TIME_BALANCE_2("TS2"),
        TIME_BALANCE_3("TS3"),
        TIME_BALANCE_4("TS4"),
        TIME_BALANCE_5("TS5"),
        TIME_BALANCE_6("TS6"),
        TIME_BALANCE_7("TS7"),
        TIME_BALANCE_8("TS8"),
        TIME_BALANCE_9("TS9"),
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
        INCONVENIENT_HOURS_WITH_SICKNESS_1("OS1"),
        INCONVENIENT_HOURS_WITH_SICKNESS_2("OS2"),
        INCONVENIENT_HOURS_WITH_SICKNESS_3("OS3"),
        INCONVENIENT_HOURS_WITH_SICKNESS_4("OS4"),
        INCONVENIENT_HOURS_WITH_SICKNESS_5("OS5"),
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
        WAGE_SUPPLEMENT_9("LT9");
        private final String code;

        private TimeCode(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static TimeCode find(String text) {
            return Stream.of(values()).filter(tc -> tc.name().equalsIgnoreCase(text) || tc.code().equalsIgnoreCase(text)).findFirst().orElseThrow(() -> new Paxml4jException("No TimeCode found for code: " + text));
        }
    }
}