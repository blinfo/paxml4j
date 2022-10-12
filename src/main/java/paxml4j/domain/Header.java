package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import paxml4j.util.Helper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Optional;
import paxml4j.json.io.LocalDateDeserializer;
import paxml4j.json.io.LocalDateSerializer;
import paxml4j.json.io.TemporalDeserializer;
import paxml4j.json.io.TemporalSerializer;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Header implements Entity {

    private static final String VERSION = "2.0";
    private final String format;
    @JsonSerialize(using = TemporalSerializer.class)
    private final Temporal timestamp;
    private final NewExport newExport;
    private final String companyId;
    private final String corporateIdentityNumber;
    private final String companyName;
    private final String extraAddress;
    private final String postalAddress;
    private final String zipCode;
    private final String city;
    private final String country;
    private final String email;
    private final String homepage;
    private final String contact;
    private final String staffManager;
    private final String attestant;
    private final String phone;
    private final String fax;
    private final String programName;
    private final String programLicense;
    private final String info;

    @JsonCreator
    private Header(@JsonProperty("format") String format,
            @JsonDeserialize(using = TemporalDeserializer.class)
            @JsonProperty("timestamp") Temporal timestamp,
            @JsonProperty("newExport") NewExport newExport,
            @JsonProperty("companyId") String companyId,
            @JsonProperty("corporateIdentityNumber") String corporateIdentityNumber,
            @JsonProperty("companyName") String companyName,
            @JsonProperty("extraAddress") String extraAddress,
            @JsonProperty("postalAddress") String postalAddress,
            @JsonProperty("zipCode") String zipCode,
            @JsonProperty("city") String city,
            @JsonProperty("country") String country,
            @JsonProperty("email") String email,
            @JsonProperty("homepage") String homepage,
            @JsonProperty("contact") String contact,
            @JsonProperty("staffManager") String staffManager,
            @JsonProperty("attestant") String attestant,
            @JsonProperty("phone") String phone,
            @JsonProperty("fax") String fax,
            @JsonProperty("programName") String programName,
            @JsonProperty("programLicense") String programLicense,
            @JsonProperty("info") String info) {
        this.format = format;
        this.timestamp = timestamp;
        this.newExport = newExport;
        this.companyId = companyId;
        this.corporateIdentityNumber = corporateIdentityNumber;
        this.companyName = companyName;
        this.extraAddress = extraAddress;
        this.postalAddress = postalAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.homepage = homepage;
        this.contact = contact;
        this.staffManager = staffManager;
        this.attestant = attestant;
        this.phone = phone;
        this.fax = fax;
        this.programName = programName;
        this.programLicense = programLicense;
        this.info = info;
    }

    public static Header of(XmlNode node) {
        Builder builder = builder();
        Helper.nodeText(node, "format").ifPresent(builder::format);
        Helper.nodeText(node, "datum").map(Helper::temporalFromText).ifPresent(builder::timestamp);
        if (node.hasChildNamed("nyexport")) {
            builder.newExport(NewExport.of(node.getChild("nyexport")));
        }
        Helper.nodeText(node, "foretagid").ifPresent(builder::companyId);
        Helper.nodeText(node, "foretagorgnr").ifPresent(builder::corporateIdentityNumber);
        Helper.nodeText(node, "foretagnamn").ifPresent(builder::companyName);
        Helper.nodeText(node, "extraadress").ifPresent(builder::extraAddress);
        Helper.nodeText(node, "postadress").ifPresent(builder::postalAddress);
        Helper.nodeText(node, "postnr").ifPresent(builder::zipCode);
        Helper.nodeText(node, "ort").ifPresent(builder::city);
        Helper.nodeText(node, "land").ifPresent(builder::country);
        Helper.nodeText(node, "epost").ifPresent(builder::email);
        Helper.nodeText(node, "hemsida").ifPresent(builder::homepage);
        Helper.nodeText(node, "kontaktperson").ifPresent(builder::contact);
        Helper.nodeText(node, "personalansvarig").ifPresent(builder::staffManager);
        Helper.nodeText(node, "attestansvarig").ifPresent(builder::attestant);
        Helper.nodeText(node, "telefon").ifPresent(builder::phone);
        Helper.nodeText(node, "telefax").ifPresent(builder::fax);
        Helper.nodeText(node, "programnamn").ifPresent(builder::programName);
        Helper.nodeText(node, "programlicens").ifPresent(builder::programLicense);
        Helper.nodeText(node, "info").ifPresent(builder::info);
        return builder.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String version() {
        return VERSION;
    }

    public Optional<String> format() {
        return Optional.ofNullable(format);
    }

    public Optional<Temporal> timestamp() {
        return Optional.ofNullable(timestamp);
    }

    public Optional<NewExport> newExport() {
        return Optional.ofNullable(newExport);
    }

    public Optional<String> companyId() {
        return Optional.ofNullable(companyId);
    }

    public Optional<String> corporateIdentityNumber() {
        return Optional.ofNullable(corporateIdentityNumber);
    }

    public Optional<String> companyName() {
        return Optional.ofNullable(companyName);
    }

    public Optional<String> extraAddress() {
        return Optional.ofNullable(extraAddress);
    }

    public Optional<String> postalAddress() {
        return Optional.ofNullable(postalAddress);
    }

    public Optional<String> zipCode() {
        return Optional.ofNullable(zipCode);
    }

    public Optional<String> city() {
        return Optional.ofNullable(city);
    }

    public Optional<String> country() {
        return Optional.ofNullable(country);
    }

    public Optional<String> email() {
        return Optional.ofNullable(email);
    }

    public Optional<String> homepage() {
        return Optional.ofNullable(homepage);
    }

    public Optional<String> contact() {
        return Optional.ofNullable(contact);
    }

    public Optional<String> staffManager() {
        return Optional.ofNullable(staffManager);
    }

    public Optional<String> attestant() {
        return Optional.ofNullable(attestant);
    }

    public Optional<String> phone() {
        return Optional.ofNullable(phone);
    }

    public Optional<String> fax() {
        return Optional.ofNullable(fax);
    }

    public Optional<String> programName() {
        return Optional.ofNullable(programName);
    }

    public Optional<String> programLicense() {
        return Optional.ofNullable(programLicense);
    }

    public Optional<String> info() {
        return Optional.ofNullable(info);
    }

    @Override
    public String toString() {
        return "Header{" + "format=" + format + ", timestamp=" + timestamp + ", newExport=" + newExport + ", companyId=" + companyId + ", corporateIdentityNumber=" + corporateIdentityNumber + ", companyName=" + companyName + ", extraAddress=" + extraAddress + ", postalAddress=" + postalAddress + ", zipCode=" + zipCode + ", city=" + city + ", country=" + country + ", email=" + email + ", homepage=" + homepage + ", contact=" + contact + ", staffManager=" + staffManager + ", attestant=" + attestant + ", phone=" + phone + ", fax=" + fax + ", programName=" + programName + ", programLicense=" + programLicense + ", info=" + info + '}';
    }

    public static class NewExport {

        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate startDate;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate endDate;

        @JsonCreator
        private NewExport(@JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("startDate") LocalDate startDate,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("endDate") LocalDate endDate) {
            this.date = date;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public static NewExport of(XmlNode node) {
            LocalDate date = LocalDate.parse(node.getAttribute("datum"));
            LocalDate startDate = node.hasAttribute("datumfrom") ? LocalDate.parse(node.getAttribute("datumfrom")) : null;
            LocalDate endDate = node.hasAttribute("datumtom") ? LocalDate.parse(node.getAttribute("datumtom")) : null;
            return of(date, startDate, endDate);
        }

        public static NewExport of(LocalDate date) {
            return of(date, null, null);
        }

        public static NewExport of(LocalDate date, LocalDate startDate, LocalDate endDate) {
            return new NewExport(date, startDate, endDate);
        }

        public LocalDate date() {
            return date;
        }

        public Optional<LocalDate> startDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<LocalDate> endDate() {
            return Optional.ofNullable(endDate);
        }

        @Override
        public String toString() {
            return "NewExport{" + "date=" + date + ", startDate=" + startDate + ", endDate=" + endDate + '}';
        }

    }

    public static class Builder {

        private String format;
        private Temporal timestamp;
        private NewExport newExport;
        private String companyId;
        private String corporateIdentityNumber;
        private String companyName;
        private String extraAddress;
        private String postalAddress;
        private String zipCode;
        private String city;
        private String country;
        private String email;
        private String homepage;
        private String contact;
        private String staffManager;
        private String attestant;
        private String phone;
        private String fax;
        private String programName;
        private String programLicense;
        private String info;

        private Builder() {
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder timestamp(Temporal timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder newExport(NewExport newExport) {
            this.newExport = newExport;
            return this;
        }

        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder corporateIdentityNumber(String corporateIdentityNumber) {
            this.corporateIdentityNumber = corporateIdentityNumber;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder extraAddress(String extraAddress) {
            this.extraAddress = extraAddress;
            return this;
        }

        public Builder postalAddress(String postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder homepage(String homepage) {
            this.homepage = homepage;
            return this;
        }

        public Builder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public Builder staffManager(String staffManager) {
            this.staffManager = staffManager;
            return this;
        }

        public Builder attestant(String attestant) {
            this.attestant = attestant;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder programName(String programName) {
            this.programName = programName;
            return this;
        }

        public Builder programLicense(String programLicense) {
            this.programLicense = programLicense;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Header build() {
            return new Header(format,
                    timestamp,
                    newExport,
                    companyId,
                    corporateIdentityNumber,
                    companyName,
                    extraAddress,
                    postalAddress,
                    zipCode,
                    city,
                    country,
                    email,
                    homepage,
                    contact,
                    staffManager,
                    attestant,
                    phone,
                    fax,
                    programName,
                    programLicense,
                    info);
        }
    }
}
