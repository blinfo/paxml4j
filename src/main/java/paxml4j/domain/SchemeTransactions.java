package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;
import paxml4j.io.*;
import paxml4j.util.Helper;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class SchemeTransactions implements Entity {

    private final List<Transaction> transactions;

    @JsonCreator
    private SchemeTransactions(@JsonProperty("transactions") List<Transaction> transactions) {
        this.transactions = Objects.requireNonNull(transactions);
    }

    public static SchemeTransactions of(List<Transaction> transactions) {
        return new SchemeTransactions(transactions);
    }

    public static SchemeTransactions of(XmlNode node) {
        return of(node.getChildren("schema").stream().map(Transaction::of).collect(Collectors.toList()));
    }

    public List<Transaction> transactions() {
        return List.copyOf(transactions);
    }

    @Override
    public String toString() {
        return "SchemeTransactions{" + "transactions=" + transactions + '}';
    }

    public static class Transaction implements Entity {

        private final String employmentId;
        private final String privateIdentificationNumber;
        private final List<Day> days;

        @JsonCreator
        private Transaction(@JsonProperty("employmentId") String employmentId,
                @JsonProperty("privateIdentificationNumber") String privateIdentificationNumber,
                @JsonProperty("days") List<Day> days) {
            this.employmentId = employmentId;
            this.privateIdentificationNumber = privateIdentificationNumber;
            this.days = Objects.requireNonNull(days);
        }

        public static Transaction of(String employmentId, String privateIdentificationNumber, List<Day> days) {
            return new Transaction(employmentId, privateIdentificationNumber, days);
        }

        public static Transaction of(XmlNode node) {
            return of(Helper.attrText(node, "anstid").orElse(null),
                    Helper.attrText(node, "persnr").orElse(null),
                    node.getChildren("dag").stream().map(Day::of).collect(Collectors.toList()));
        }

        public Optional<String> employmentId() {
            return Optional.ofNullable(employmentId);
        }

        public Optional<String> personalIdentityNumber() {
            return Optional.ofNullable(privateIdentificationNumber);
        }

        public List<Day> days() {
            return List.copyOf(days);
        }

        @Override
        public String toString() {
            return "Transaction{" + "employmentId=" + employmentId + ", privateIdentificationNumber=" + privateIdentificationNumber + ", days=" + days + '}';
        }
    }

    public static class Day implements Entity {

        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal startTime;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal endTime;
        private final Double hours;

        @JsonCreator
        private Day(@JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("startTime") Temporal startTime,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("endTime") Temporal endTime,
                @JsonProperty("hours") Double hours) {
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
            this.hours = hours;
        }

        public static Day of(LocalDate date, Temporal startTime, Temporal endTime, Double hours) {
            return new Day(date, startTime, endTime, hours);
        }

        public static Day of(XmlNode node) {
            return of(LocalDate.parse(node.getAttribute("datum")),
                    Helper.attrText(node, "starttid").map(Helper::temporalFromText).orElse(null),
                    Helper.attrText(node, "sluttid").map(Helper::temporalFromText).orElse(null),
                    Helper.attrText(node, "timmar").map(Double::valueOf).orElse(null));
        }

        public LocalDate date() {
            return date;
        }

        public Optional<Temporal> startTime() {
            return Optional.ofNullable(startTime);
        }

        public Optional<Temporal> endTime() {
            return Optional.ofNullable(endTime);
        }

        public Optional<Double> hours() {
            return Optional.ofNullable(hours);
        }

        @Override
        public String toString() {
            return "Day{" + "date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", hours=" + hours + '}';
        }
    }
}
