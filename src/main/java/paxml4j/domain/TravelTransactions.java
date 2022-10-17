package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import java.math.BigDecimal;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.*;
import paxml4j.json.io.*;
import paxml4j.util.Helper;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class TravelTransactions implements Entity {

    private final List<Transaction> transactions;
    private final String countryCodeStandard;

    @JsonCreator
    private TravelTransactions(@JsonProperty("transactions") List<Transaction> transactions,
            @JsonProperty("countryCodeStandard") String countryCodeStandard) {
        this.transactions = Objects.requireNonNull(transactions);
        this.countryCodeStandard = countryCodeStandard;
    }

    public static TravelTransactions of(List<Transaction> transactions) {
        return of(transactions, null);
    }

    public static TravelTransactions of(List<Transaction> transactions, String countryCodeStandard) {
        return new TravelTransactions(transactions, countryCodeStandard);
    }

    public static TravelTransactions of(XmlNode node) {
        return new TravelTransactions(node.getChildren("resetrans").stream().map(Transaction::of).collect(Collectors.toList()), node.getAttribute("landskodstd"));
    }

    public List<Transaction> transactions() {
        return List.copyOf(transactions);
    }

    public Optional<String> countryCodeStandard() {
        return Optional.ofNullable(countryCodeStandard);
    }

    @Override
    public String toString() {
        return "TravelTransactions{" + "transactions=" + transactions + ", countryCodeStandard=" + countryCodeStandard + '}';
    }

    public static class Transaction implements Entity {

        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal dateTime;
        private final TravelCode travelCode;
        private final Integer postId;
        private final String employmentId;
        private final String personalIdentityNumber;
        private final Continue contiune;
        private final String countryCode;
        private final String currencyCode;
        private final BigDecimal exchangeRate;
        private final BigDecimal amount;
        private final BigDecimal vat;
        private final Boolean companyCard;
        private final Integer numberOfParticipants;
        private final List<Participant> participants;
        private final String typeOfGoods;
        private final String specification;
        private final String accountNumber;
        private final String carRegistrationNumber;
        private final String carModel;
        private final String company;
        private final String contact;
        private final String purpose;
        private final String location;
        private final Integer meterIndicatorStart;
        private final Integer meterIndicatorEnd;
        private final Integer numberOfKilometers;
        private final Integer numberOfPassengers;
        private final Integer freightWeightInKilograms;
        private final Double travelTimeInHours;
        private final String summaryId;
        private final CustomerNumber customerNumber;
        private final List<ProfitCenter.Reference> profitCenters;
        private final String note;
        private final String info;

        @JsonCreator
        private Transaction(@JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("dateTime") Temporal dateTime,
                @JsonProperty("travelCode") TravelCode travelCode,
                @JsonProperty("postId") Integer postId,
                @JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonProperty("contiune") Continue contiune,
                @JsonProperty("countryCode") String countryCode,
                @JsonProperty("currencyCode") String currencyCode,
                @JsonProperty("exchangeRate") BigDecimal exchangeRate,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("vat") BigDecimal vat,
                @JsonProperty("companyCard") Boolean companyCard,
                @JsonProperty("numberOfParticipants") Integer numberOfParticipants,
                @JsonProperty("participants") List<Participant> participants,
                @JsonProperty("typeOfGoods") String typeOfGoods,
                @JsonProperty("specification") String specification,
                @JsonProperty("accountNumber") String accountNumber,
                @JsonProperty("carRegistrationNumber") String carRegistrationNumber,
                @JsonProperty("carModel") String carModel,
                @JsonProperty("company") String company,
                @JsonProperty("contact") String contact,
                @JsonProperty("purpose") String purpose,
                @JsonProperty("location") String location,
                @JsonProperty("meterIndicatorStart") Integer meterIndicatorStart,
                @JsonProperty("meterIndicatorEnd") Integer meterIndicatorEnd,
                @JsonProperty("numberOfKilometers") Integer numberOfKilometers,
                @JsonProperty("numberOfPassengers") Integer numberOfPassengers,
                @JsonProperty("freightWeightInKilograms") Integer freightWeightInKilograms,
                @JsonProperty("travelTimeInHours") Double travelTimeInHours,
                @JsonProperty("summaryId") String summaryId,
                @JsonProperty("customerNumber") CustomerNumber customerNumber,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters,
                @JsonProperty("note") String note,
                @JsonProperty("info") String info) {
            this.dateTime = dateTime;
            this.travelCode = travelCode;
            this.postId = postId;
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.contiune = contiune;
            this.countryCode = countryCode;
            this.currencyCode = currencyCode;
            this.exchangeRate = exchangeRate;
            this.amount = amount;
            this.vat = vat;
            this.companyCard = companyCard;
            this.numberOfParticipants = numberOfParticipants;
            this.participants = Objects.requireNonNull(participants);
            this.typeOfGoods = typeOfGoods;
            this.specification = specification;
            this.accountNumber = accountNumber;
            this.carRegistrationNumber = carRegistrationNumber;
            this.carModel = carModel;
            this.company = company;
            this.contact = contact;
            this.purpose = purpose;
            this.location = location;
            this.meterIndicatorStart = meterIndicatorStart;
            this.meterIndicatorEnd = meterIndicatorEnd;
            this.numberOfKilometers = numberOfKilometers;
            this.numberOfPassengers = numberOfPassengers;
            this.freightWeightInKilograms = freightWeightInKilograms;
            this.travelTimeInHours = travelTimeInHours;
            this.summaryId = summaryId;
            this.customerNumber = customerNumber;
            this.profitCenters = Objects.requireNonNull(profitCenters);
            this.note = note;
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Transaction of(XmlNode node) {
            Builder builder = builder();
            builder.dateTime(Helper.temporalFromText(node.getChild("tidpunkt").getText()));
            builder.travelCode(TravelCode.find(node.getChild("resekod").getText()));
            Helper.attrText(node, "postid").map(Integer::valueOf).ifPresent(builder::postId);
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.nodeText(node, "landskod").ifPresent(builder::countryCode);
            Helper.nodeText(node, "valutakod").ifPresent(builder::currencyCode);
            Helper.nodeText(node, "valutafaktor").map(BigDecimal::new).ifPresent(builder::exchangeRate);
            Helper.nodeText(node, "belopp").map(BigDecimal::new).ifPresent(builder::amount);
            Helper.nodeText(node, "moms").map(BigDecimal::new).ifPresent(builder::vat);
            Helper.nodeText(node, "ftgkort").map(Boolean::valueOf).ifPresent(builder::companyCard);
            Helper.nodeText(node, "antdeltag").map(Integer::valueOf).ifPresent(builder::numberOfParticipants);
            if (node.hasChildNamed("deltagarlista")) {
                builder.participants(node.getChild("deltagarlista").getChildren("deltagare")
                        .stream()
                        .map(Participant::of)
                        .collect(Collectors.toList()));
            }
            Helper.nodeText(node, "varugrupp").ifPresent(builder::typeOfGoods);
            Helper.nodeText(node, "specifikation").ifPresent(builder::specification);
            Helper.nodeText(node, "kontonr").ifPresent(builder::acountNumber);
            Helper.nodeText(node, "bilnr").ifPresent(builder::carRegistrationNumber);
            Helper.nodeText(node, "bilmodell").ifPresent(builder::carModel);
            Helper.nodeText(node, "foretag").ifPresent(builder::company);
            Helper.nodeText(node, "kontakt").ifPresent(builder::contact);
            Helper.nodeText(node, "syfte").ifPresent(builder::purpose);
            Helper.nodeText(node, "ort").ifPresent(builder::location);
            Helper.nodeText(node, "kmstart").map(Integer::valueOf).ifPresent(builder::meterIndicatorStart);
            Helper.nodeText(node, "kmstopp").map(Integer::valueOf).ifPresent(builder::meterIndicatorEnd);
            Helper.nodeText(node, "kilometer").map(Integer::valueOf).ifPresent(builder::numberOfKilometers);
            Helper.nodeText(node, "antpass").map(Integer::valueOf).ifPresent(builder::numberOfPassengers);
            Helper.nodeText(node, "antlast").map(Integer::valueOf).ifPresent(builder::freightWeightInKilograms);
            Helper.nodeText(node, "timmar").map(Double::valueOf).ifPresent(builder::travelTimeInHours);
            Helper.nodeText(node, "samlingsid").ifPresent(builder::summaryId);
            Helper.childNode(node, "kundnr").map(CustomerNumber::of).ifPresent(builder::customerNumber);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet")
                        .stream()
                        .map(ProfitCenter.Reference::of)
                        .collect(Collectors.toList()));
            }
            Helper.nodeText(node, "anteckning").ifPresent(builder::note);
            Helper.nodeText(node, "info").ifPresent(builder::info);
            return builder.build();
        }

        public Temporal dateTime() {
            return dateTime;
        }

        public TravelCode travelCode() {
            return travelCode;
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

        public Optional<Continue> contiune() {
            return Optional.ofNullable(contiune);
        }

        public Optional<String> countryCode() {
            return Optional.ofNullable(countryCode);
        }

        public Optional<String> currencyCode() {
            return Optional.ofNullable(currencyCode);
        }

        public Optional<BigDecimal> exchangeRate() {
            return Optional.ofNullable(exchangeRate).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> amount() {
            return Optional.ofNullable(amount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> vat() {
            return Optional.ofNullable(vat).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<Boolean> companyCard() {
            return Optional.ofNullable(companyCard);
        }

        public Optional<Integer> numberOfParticipants() {
            return Optional.ofNullable(numberOfParticipants);
        }

        public List<Participant> participants() {
            return List.copyOf(participants);
        }

        public Optional<String> typeOfGoods() {
            return Optional.ofNullable(typeOfGoods);
        }

        public Optional<String> specification() {
            return Optional.ofNullable(specification);
        }

        public Optional<String> accountNumber() {
            return Optional.ofNullable(accountNumber);
        }

        public Optional<String> carRegistrationNumber() {
            return Optional.ofNullable(carRegistrationNumber);
        }

        public Optional<String> carModel() {
            return Optional.ofNullable(carModel);
        }

        public Optional<String> company() {
            return Optional.ofNullable(company);
        }

        public Optional<String> contact() {
            return Optional.ofNullable(contact);
        }

        public Optional<String> purpose() {
            return Optional.ofNullable(purpose);
        }

        public Optional<String> location() {
            return Optional.ofNullable(location);
        }

        public Optional<Integer> meterIndicatorStart() {
            return Optional.ofNullable(meterIndicatorStart);
        }

        public Optional<Integer> meterIndicatorEnd() {
            return Optional.ofNullable(meterIndicatorEnd);
        }

        public Optional<Integer> numberOfKilometers() {
            return Optional.ofNullable(numberOfKilometers);
        }

        public Optional<Integer> numberOfPassengers() {
            return Optional.ofNullable(numberOfPassengers);
        }

        public Optional<Integer> freightWeightInKilograms() {
            return Optional.ofNullable(freightWeightInKilograms);
        }

        public Optional<Double> travelTimeInHours() {
            return Optional.ofNullable(travelTimeInHours);
        }

        public Optional<String> summaryId() {
            return Optional.ofNullable(summaryId);
        }

        public Optional<CustomerNumber> customerNumber() {
            return Optional.ofNullable(customerNumber);
        }

        public List<ProfitCenter.Reference> profitCenters() {
            return List.copyOf(profitCenters);
        }

        public Optional<String> note() {
            return Optional.ofNullable(note);
        }

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }

        @Override
        public String toString() {
            return "Transaction{" + "dateTime=" + dateTime + ", travelCode=" + travelCode + ", postId=" + postId + ", employmentId=" + employmentId + ", personalIdentityNumber=" + personalIdentityNumber + ", contiune=" + contiune + ", countryCode=" + countryCode + ", currencyCode=" + currencyCode + ", exchangeRate=" + exchangeRate + ", amount=" + amount + ", vat=" + vat + ", companyCard=" + companyCard + ", numberOfParticipants=" + numberOfParticipants + ", participants=" + participants + ", typeOfGoods=" + typeOfGoods + ", specification=" + specification + ", accountNumber=" + accountNumber + ", carRegistrationNumber=" + carRegistrationNumber + ", carModel=" + carModel + ", company=" + company + ", contact=" + contact + ", purpose=" + purpose + ", location=" + location + ", meterIndicatorStart=" + meterIndicatorStart + ", meterIndicatorEnd=" + meterIndicatorEnd + ", numberOfKilometers=" + numberOfKilometers + ", numberOfPassengers=" + numberOfPassengers + ", freightWeightInKilograms=" + freightWeightInKilograms + ", travelTimeInHours=" + travelTimeInHours + ", summaryId=" + summaryId + ", customerNumber=" + customerNumber + ", profitCenters=" + profitCenters + ", note=" + note + ", info=" + info + '}';
        }
    }

    public static class Participant implements Entity {

        private final String company;
        private final String name;

        @JsonCreator
        private Participant(@JsonProperty("company") String company, @JsonProperty("name") String name) {
            this.company = company;
            this.name = name;
        }

        public static Participant of(String company, String name) {
            return new Participant(company, name);
        }

        public static Participant of(XmlNode node) {
            return new Participant(node.getAttribute("foretag"), node.getAttribute("namn"));
        }

        public String company() {
            return company;
        }

        public String name() {
            return name;
        }

        @Override
        public String toString() {
            return "Participant{" + "company=" + company + ", name=" + name + '}';
        }
    }

    public static class Continue implements Entity {

        private final Boolean continues;
        private final Integer dayNumber;

        @JsonCreator
        private Continue(@JsonProperty("continues") Boolean continues, @JsonProperty("dayNumber") Integer dayNumber) {
            this.continues = continues;
            this.dayNumber = dayNumber;
        }

        public static Continue of(Boolean continues, Integer dayNumber) {
            return new Continue(continues, dayNumber);
        }

        public static Continue of(XmlNode node) {
            return new Continue(Boolean.valueOf(node.getText()), Integer.valueOf(node.getAttribute("dagnr")));
        }

        public Boolean continues() {
            return continues;
        }

        public Integer dayNumber() {
            return dayNumber;
        }

        @Override
        public String toString() {
            return "Continue{" + "continues=" + continues + ", dayNumber=" + dayNumber + '}';
        }
    }

    public enum TravelCode {
        // Här är det många frågetecken
        START("START"),
        END("STOPP"),
        AIR("FLYG"),
        BORDER_CROSSING("LAND"),
        NO_BREAKFAST("FRU_NEJ"),
        HOTEL_BREAKFAST("FRU_HOT"),
        PRIVATE_BREAKFAST("FRU_BET"),
        WORKING_BREAKFAST("FRU_ARB"),
        REIMBURSED_BREAKFAST("FRU_REP"),
        NO_LUNCH("LUN_NEJ"),
        PRIVATE_LUNCH("LUN_BET"),
        WORKING_LUNCH("LUN_ARB"),
        REIMBURSED_LUNCH("LUN_REP"),
        NO_DINNER("MID_NEJ"),
        PRIVATE_DINNER("MID_BET"),
        WORKING_DINNER("MID_ARB"),
        REIMBURSED_DINNER("MID_REP"),
        PRIVATE_LODGING("LOGI_BET"),
        WORK_LODGING("LOGI_ARB"),
        MILE_PRIVATE("MIL_PRI"),
        MILE_COMPANY("MIL_FTG"),
        MILE_SUPPLIED("MIL_TJT"),
        MILE_DIESEL("MIL_DIS"),
        DISBURSEMENT("UTLÄGG"),
        REPRESENTATION_SIMPLE("REP_ENK"),
        REPRESENTATION_LUNCH("REP_LUNCH"),
        REPRESENTATION_DINNER("REP_DINNER"),
        REPRESENTATION_INT("REP_INT"),
        TRAVEL_TIME("RESTID");

        private final String code;

        private TravelCode(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static TravelCode find(String text) {
            return Stream.of(values())
                    .filter(tc -> tc.name().equalsIgnoreCase(text) || tc.code().equalsIgnoreCase(text))
                    .findFirst()
                    .orElseThrow();
        }
    }

    public static class Builder {

        private Temporal dateTime;
        private TravelCode travelCode;
        private Integer postId;
        private String employmentId;
        private String personalIdentityNumber;
        private Continue contiune;
        private String countryCode;
        private String currencyCode;
        private BigDecimal exchangeRate;
        private BigDecimal amount;
        private BigDecimal vat;
        private Boolean companyCard;
        private Integer numberOfParticipants;
        private List<Participant> participants = List.of();
        private String typeOfGoods;
        private String specification;
        private String accountNumber;
        private String carRegistrationNumber;
        private String carModel;
        private String company;
        private String contact;
        private String purpose;
        private String location;
        private Integer meterIndicatorStart;
        private Integer meterIndicatorEnd;
        private Integer numberOfKilometers;
        private Integer numberOfPassengers;
        private Integer freightWeightInKilograms;
        private Double travelTimeInHours;
        private String summaryId;
        private CustomerNumber customerNumber;
        private List<ProfitCenter.Reference> profitCenters = List.of();
        private String note;
        private String info;

        private Builder() {
        }

        public Builder dateTime(Temporal dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder travelCode(TravelCode travelCode) {
            this.travelCode = travelCode;
            return this;
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

        public Builder contiune(Continue contiune) {
            this.contiune = contiune;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder currencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public Builder exchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder vat(BigDecimal vat) {
            this.vat = vat;
            return this;
        }

        public Builder companyCard(Boolean companyCard) {
            this.companyCard = companyCard;
            return this;
        }

        public Builder numberOfParticipants(Integer numberOfParticipants) {
            this.numberOfParticipants = numberOfParticipants;
            return this;
        }

        public Builder participants(List<Participant> participants) {
            this.participants = Objects.requireNonNull(participants);
            return this;
        }

        public Builder typeOfGoods(String typeOfGoods) {
            this.typeOfGoods = typeOfGoods;
            return this;
        }

        public Builder specification(String specification) {
            this.specification = specification;
            return this;
        }

        public Builder acountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder carRegistrationNumber(String carRegistrationNumber) {
            this.carRegistrationNumber = carRegistrationNumber;
            return this;
        }

        public Builder carModel(String carModel) {
            this.carModel = carModel;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public Builder purpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder meterIndicatorStart(Integer meterIndicatorStart) {
            this.meterIndicatorStart = meterIndicatorStart;
            return this;
        }

        public Builder meterIndicatorEnd(Integer meterIndicatorEnd) {
            this.meterIndicatorEnd = meterIndicatorEnd;
            return this;
        }

        public Builder numberOfKilometers(Integer numberOfKilometers) {
            this.numberOfKilometers = numberOfKilometers;
            return this;
        }

        public Builder numberOfPassengers(Integer numberOfPassengers) {
            this.numberOfPassengers = numberOfPassengers;
            return this;
        }

        public Builder freightWeightInKilograms(Integer freightWeightInKilograms) {
            this.freightWeightInKilograms = freightWeightInKilograms;
            return this;
        }

        public Builder travelTimeInHours(Double travelTimeInHours) {
            this.travelTimeInHours = travelTimeInHours;
            return this;
        }

        public Builder summaryId(String summaryId) {
            this.summaryId = summaryId;
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

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Transaction build() {
            return new Transaction(dateTime,
                    travelCode,
                    postId,
                    employmentId,
                    personalIdentityNumber,
                    contiune,
                    countryCode,
                    currencyCode,
                    exchangeRate,
                    amount,
                    vat,
                    companyCard,
                    numberOfParticipants,
                    participants,
                    typeOfGoods,
                    specification,
                    accountNumber,
                    carRegistrationNumber,
                    carModel,
                    company,
                    contact,
                    purpose,
                    location,
                    meterIndicatorStart,
                    meterIndicatorEnd,
                    numberOfKilometers,
                    numberOfPassengers,
                    freightWeightInKilograms,
                    travelTimeInHours,
                    summaryId,
                    customerNumber,
                    profitCenters,
                    note,
                    info);
        }
    }
}
