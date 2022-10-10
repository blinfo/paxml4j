package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import paxml4j.Paxml4jException;
import paxml4j.io.LocalDateDeserializer;
import paxml4j.io.LocalDateSerializer;
import paxml4j.util.Helper;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Staff implements Entity {

    private final List<Employee> employees;

    @JsonCreator
    private Staff(@JsonProperty("employees") List<Employee> employees) {
        this.employees = Objects.requireNonNull(employees);
    }

    public static Staff of(List<Employee> employees) {
        return new Staff(employees);
    }

    public static Staff of(XmlNode node) {
        return of(node.getChildren("person").stream().map(Employee::of).collect(Collectors.toList()));
    }

    public List<Employee> employees() {
        return List.copyOf(employees);
    }

    public static class Employee implements Entity {

        private final String employmentId;
        private final String personalIdentityNumber;
        private final Boolean delete;
        private final String firstName;
        private final String lastName;
        private final String extraAddress;
        private final String postalAddress;
        private final String zipCode;
        private final String city;
        private final String country;
        private final String mobilePhone;
        private final String homePhone;
        private final String workPhone;
        private final String workEmail;
        private final String homeEmail;
        private final String typeOfEmployee;
        private final String category;
        private final String position;
        private final String positionCode;
        private final String formOfEmployment;
        private final String vacationAgreement;
        private final String bankClearingNumber;
        private final String bankAccountNumber;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate dateOfEmployment;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate dateOfRetirement;
        private final SalaryType salaryType;
        private final Boolean salaryForPresentPeriod;
        private final DateAmount hourlySalary;
        private final DateAmount monthlySalary;
        private final List<TaxFreeAmount> taxFreeAmounts;
        private final List<Text> employeeTexts;
        private final DateAmount degreeOfEmployment;
        private final Double daysOfVacation;
        private final Double taxTable;
        private final Integer taxColumn;
        private final TaxReconciliation taxReconciliation;
        private final WageDestraint wageDestraint;
        private final List<ProfitCenter.Reference> profitCenters;
        private final String info;

        @JsonCreator
        private Employee(@JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonProperty("delete") Boolean delete,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("extraAddress") String extraAddress,
                @JsonProperty("postalAddress") String postalAddress,
                @JsonProperty("zipCode") String zipCode,
                @JsonProperty("city") String city,
                @JsonProperty("country") String country,
                @JsonProperty("mobilePhone") String mobilePhone,
                @JsonProperty("homePhone") String homePhone,
                @JsonProperty("workPhone") String workPhone,
                @JsonProperty("workEmail") String workEmail,
                @JsonProperty("homeEmail") String homeEmail,
                @JsonProperty("typeOfEmployee") String typeOfEmployee,
                @JsonProperty("category") String category,
                @JsonProperty("position") String position,
                @JsonProperty("positionCode") String positionCode,
                @JsonProperty("formOfEmployment") String formOfEmployment,
                @JsonProperty("vacationAgreement") String vacationAgreement,
                @JsonProperty("bankClearingNumber") String bankClearingNumber,
                @JsonProperty("bankAccountNumber") String bankAccountNumber,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("dateOfEmployment") LocalDate dateOfEmployment,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("dateOfRetirement") LocalDate dateOfRetirement,
                @JsonProperty("salaryType") SalaryType salaryType,
                @JsonProperty("salaryForPresentPeriod") Boolean salaryForPresentPeriod,
                @JsonProperty("hourlySalary") DateAmount hourlySalary,
                @JsonProperty("monthlySalary") DateAmount monthlySalary,
                @JsonProperty("taxFreeAmounts") List<TaxFreeAmount> taxFreeAmounts,
                @JsonProperty("employeeTexts") List<Text> employeeTexts,
                @JsonProperty("degreeOfEmployment") DateAmount degreeOfEmployment,
                @JsonProperty("daysOfVacation") Double daysOfVacation,
                @JsonProperty("taxTable") Double taxTable,
                @JsonProperty("taxColumn") Integer taxColumn,
                @JsonProperty("taxReconciliation") TaxReconciliation taxReconciliation,
                @JsonProperty("wageDestraint") WageDestraint wageDestraint,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters,
                @JsonProperty("info") String info) {
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.delete = delete;
            this.firstName = firstName;
            this.lastName = lastName;
            this.extraAddress = extraAddress;
            this.postalAddress = postalAddress;
            this.zipCode = zipCode;
            this.city = city;
            this.country = country;
            this.mobilePhone = mobilePhone;
            this.homePhone = homePhone;
            this.workPhone = workPhone;
            this.workEmail = workEmail;
            this.homeEmail = homeEmail;
            this.typeOfEmployee = typeOfEmployee;
            this.category = category;
            this.position = position;
            this.positionCode = positionCode;
            this.formOfEmployment = formOfEmployment;
            this.vacationAgreement = vacationAgreement;
            this.bankClearingNumber = bankClearingNumber;
            this.bankAccountNumber = bankAccountNumber;
            this.dateOfEmployment = dateOfEmployment;
            this.dateOfRetirement = dateOfRetirement;
            this.salaryType = salaryType;
            this.salaryForPresentPeriod = salaryForPresentPeriod;
            this.hourlySalary = hourlySalary;
            this.monthlySalary = monthlySalary;
            this.taxFreeAmounts = Objects.requireNonNull(taxFreeAmounts);
            this.employeeTexts = Objects.requireNonNull(employeeTexts);
            this.degreeOfEmployment = degreeOfEmployment;
            this.daysOfVacation = daysOfVacation;
            this.taxTable = taxTable;
            this.taxColumn = taxColumn;
            this.taxReconciliation = taxReconciliation;
            this.wageDestraint = wageDestraint;
            this.profitCenters = Objects.requireNonNull(profitCenters);
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Employee of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.attrText(node, "delete").map(Boolean::valueOf).ifPresent(builder::delete);
            Helper.nodeText(node, "fornamn").ifPresent(builder::firstName);
            Helper.nodeText(node, "efternamn").ifPresent(builder::lastName);
            Helper.nodeText(node, "extraadress").ifPresent(builder::extraAddress);
            Helper.nodeText(node, "postadress").ifPresent(builder::postalAddress);
            Helper.nodeText(node, "postnr").ifPresent(builder::zipCode);
            Helper.nodeText(node, "ort").ifPresent(builder::city);
            Helper.nodeText(node, "land").ifPresent(builder::country);
            Helper.nodeText(node, "mobiltelefon").ifPresent(builder::mobilePhone);
            Helper.nodeText(node, "hemtelefon").ifPresent(builder::homePhone);
            Helper.nodeText(node, "arbetstelefon").ifPresent(builder::workPhone);
            Helper.nodeText(node, "epostarb").ifPresent(builder::workEmail);
            Helper.nodeText(node, "eposthem").ifPresent(builder::homeEmail);
            Helper.nodeText(node, "peronaltyp").ifPresent(builder::typeOfEmployee);
            Helper.nodeText(node, "kategori").ifPresent(builder::category);
            Helper.nodeText(node, "befattning").ifPresent(builder::position);
            Helper.nodeText(node, "befattningskod").ifPresent(builder::positionCode);
            Helper.nodeText(node, "anstform").ifPresent(builder::formOfEmployment);
            Helper.nodeText(node, "semesteravtal").ifPresent(builder::vacationAgreement);
            Helper.nodeText(node, "bankclearing").ifPresent(builder::bankClearingNumber);
            Helper.nodeText(node, "bankkonto").ifPresent(builder::bankAccountNumber);
            Helper.nodeText(node, "anstdatum").map(LocalDate::parse).ifPresent(builder::dateOfEmployment);
            Helper.nodeText(node, "avgdatum").map(LocalDate::parse).ifPresent(builder::dateOfRetirement);
            Helper.nodeText(node, "lonform").map(SalaryType::find).ifPresent(builder::salaryType);
            Helper.nodeText(node, "innevarande").map(Boolean::valueOf).ifPresent(builder::salaryForPresentPeriod);
            Helper.childNode(node, "timlon").map(DateAmount::of).ifPresent(builder::hourlySalary);
            Helper.childNode(node, "manlon").map(DateAmount::of).ifPresent(builder::monthlySalary);
            if (node.hasChildNamed("personbelopp")) {
                builder.taxFreeAmounts(node.getChild("personbelopp").getChildren("belopp").stream().map(TaxFreeAmount::of).collect(Collectors.toList()));
            }
            if (node.hasChildNamed("persontexter")) {
                builder.employeeTexts(node.getChild("persontexter").getChildren("text").stream().map(Text::of).collect(Collectors.toList()));
            }
            Helper.childNode(node, "sysgrad").map(DateAmount::of).ifPresent(builder::degreeOfEmployment);
            Helper.nodeText(node, "semesterdagar").map(Double::valueOf).ifPresent(builder::daysOfVacation);
            Helper.nodeText(node, "skattetabell").map(Double::valueOf).ifPresent(builder::taxTable);
            Helper.nodeText(node, "skattekolumn").map(Integer::valueOf).ifPresent(builder::taxColumn);
            Helper.childNode(node, "skattekolumn").map(TaxReconciliation::of).ifPresent(builder::taxReconciliation);
            Helper.childNode(node, "loneutmatning").map(WageDestraint::of).ifPresent(builder::wageDestraint);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet").stream().map(ProfitCenter.Reference::of).collect(Collectors.toList()));
            }
            Helper.nodeText(node, "info").ifPresent(builder::info);
            return builder.build();
        }

        public Optional<String> employmentId() {
            return Optional.ofNullable(employmentId);
        }

        public Optional<String> personalIdentityNumber() {
            return Optional.ofNullable(personalIdentityNumber);
        }

        public Optional<Boolean> delete() {
            return Optional.ofNullable(delete);
        }

        public Optional<String> firstName() {
            return Optional.ofNullable(firstName);
        }

        public Optional<String> lastName() {
            return Optional.ofNullable(lastName);
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

        public Optional<String> mobilePhone() {
            return Optional.ofNullable(mobilePhone);
        }

        public Optional<String> homePhone() {
            return Optional.ofNullable(homePhone);
        }

        public Optional<String> workPhone() {
            return Optional.ofNullable(workPhone);
        }

        public Optional<String> workEmail() {
            return Optional.ofNullable(workEmail);
        }

        public Optional<String> homeEmail() {
            return Optional.ofNullable(homeEmail);
        }

        public Optional<String> typeOfEmployee() {
            return Optional.ofNullable(typeOfEmployee);
        }

        public Optional<String> category() {
            return Optional.ofNullable(category);
        }

        public Optional<String> position() {
            return Optional.ofNullable(position);
        }

        public Optional<String> positionCode() {
            return Optional.ofNullable(positionCode);
        }

        public Optional<String> formOfEmployment() {
            return Optional.ofNullable(formOfEmployment);
        }

        public Optional<String> vacationAgreement() {
            return Optional.ofNullable(vacationAgreement);
        }

        public Optional<String> bankClearingNumber() {
            return Optional.ofNullable(bankClearingNumber);
        }

        public Optional<String> bankAccountNumber() {
            return Optional.ofNullable(bankAccountNumber);
        }

        public Optional<LocalDate> dateOfEmployment() {
            return Optional.ofNullable(dateOfEmployment);
        }

        public Optional<LocalDate> dateOfRetirement() {
            return Optional.ofNullable(dateOfRetirement);
        }

        public Optional<SalaryType> salaryType() {
            return Optional.ofNullable(salaryType);
        }

        public Optional<Boolean> salaryForPresentPeriod() {
            return Optional.ofNullable(salaryForPresentPeriod);
        }

        public Optional<DateAmount> hourlySalary() {
            return Optional.ofNullable(hourlySalary);
        }

        public Optional<DateAmount> monthlySalary() {
            return Optional.ofNullable(monthlySalary);
        }

        public List<TaxFreeAmount> taxFreeAmounts() {
            return List.copyOf(taxFreeAmounts);
        }

        public List<Text> employeeTexts() {
            return List.copyOf(employeeTexts);
        }

        public Optional<DateAmount> degreeOfEmployment() {
            return Optional.ofNullable(degreeOfEmployment);
        }

        public Optional<Double> daysOfVacation() {
            return Optional.ofNullable(daysOfVacation);
        }

        public Optional<Double> taxTable() {
            return Optional.ofNullable(taxTable);
        }

        public Optional<Integer> taxColumn() {
            return Optional.ofNullable(taxColumn);
        }

        public Optional<TaxReconciliation> taxReconciliation() {
            return Optional.ofNullable(taxReconciliation);
        }

        public Optional<WageDestraint> wageDestraint() {
            return Optional.ofNullable(wageDestraint);
        }

        public List<ProfitCenter.Reference> profitCenters() {
            return List.copyOf(profitCenters);
        }

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }

    }

    public static class DateAmount implements Entity {

        private final BigDecimal amount;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate changeDate;

        @JsonCreator
        private DateAmount(@JsonProperty("amount") BigDecimal amount,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("changeDate") LocalDate changeDate) {
            this.amount = amount;
            this.changeDate = changeDate;
        }

        public static DateAmount of(BigDecimal wage, LocalDate changeDate) {
            return new DateAmount(wage, changeDate);
        }

        public static DateAmount of(XmlNode node) {
            return new DateAmount(new BigDecimal(node.getText()), Helper.attrText(node, "datum").map(LocalDate::parse).orElse(null));
        }

        public BigDecimal amount() {
            return amount;
        }

        public Optional<LocalDate> changeDate() {
            return Optional.ofNullable(changeDate);
        }
    }

    public static class TaxFreeAmount implements Entity {

        private final BigDecimal amount;
        private final String id;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;

        @JsonCreator
        private TaxFreeAmount(@JsonProperty("amount") BigDecimal amount,
                @JsonProperty("id") String id,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date) {
            this.amount = amount;
            this.id = id;
            this.date = date;
        }

        public static TaxFreeAmount of(BigDecimal amount, String id, LocalDate date) {
            return new TaxFreeAmount(amount, id, date);
        }

        public static TaxFreeAmount of(XmlNode node) {
            return new TaxFreeAmount(new BigDecimal(node.getText()), node.getAttribute("id"), Helper.attrText(node, "datum").map(LocalDate::parse).orElse(null));
        }

        public BigDecimal amount() {
            return amount.setScale(Entity.SCALE, Entity.ROUND);
        }

        public String id() {
            return id;
        }

        public Optional<LocalDate> date() {
            return Optional.ofNullable(date);
        }
    }

    public static class Text implements Entity {

        private final String text;
        private final String id;

        @JsonCreator
        private Text(@JsonProperty("text") String text, @JsonProperty("id") String id) {
            this.text = text;
            this.id = id;
        }

        public static Text of(String text, String id) {
            return new Text(text, id);
        }

        public static Text of(XmlNode node) {
            return new Text(node.getText(), node.getAttribute("id"));
        }

        public String text() {
            return text;
        }

        public String id() {
            return id;
        }
    }

    public static class TaxReconciliation implements Entity {

        private final Integer percentage;
        private final BigDecimal amount;
        private final BigDecimal maxAmount;

        @JsonCreator
        private TaxReconciliation(@JsonProperty("percentage") Integer percentage, 
                @JsonProperty("amount") BigDecimal amount, 
                @JsonProperty("maxAmount") BigDecimal maxAmount) {
            this.percentage = percentage;
            this.amount = amount;
            this.maxAmount = maxAmount;
        }

        public static TaxReconciliation of(Integer percentage, BigDecimal amount, BigDecimal maxAmount) {
            return new TaxReconciliation(percentage, amount, maxAmount);
        }

        public static TaxReconciliation of(XmlNode node) {
            return of(Helper.attrText(node, "procent").map(Integer::valueOf).orElse(null),
                    Helper.attrText(node, "belopp").map(BigDecimal::new).orElse(null),
                    Helper.attrText(node, "maxbelopp").map(BigDecimal::new).orElse(null));
        }

        public Optional<Integer> percentage() {
            return Optional.ofNullable(percentage);
        }

        public Optional<BigDecimal> amount() {
            return Optional.ofNullable(amount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> maxAmount() {
            return Optional.ofNullable(maxAmount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }
    }

    public static class WageDestraint implements Entity {

        private final BigDecimal amount;
        private final BigDecimal reservedAmount;

        @JsonCreator
        private WageDestraint(@JsonProperty("amount") BigDecimal amount, @JsonProperty("reservedAmount") BigDecimal reservedAmount) {
            this.amount = amount;
            this.reservedAmount = reservedAmount;
        }

        public static WageDestraint of(BigDecimal amount, BigDecimal reservedAmount) {
            return new WageDestraint(amount, reservedAmount);
        }

        public static WageDestraint of(XmlNode node) {
            return of(new BigDecimal(node.getAttribute("belopp")), Helper.attrText(node, "forbeholl").map(BigDecimal::new).orElse(null));
        }

        public BigDecimal amount() {
            return amount.setScale(Entity.SCALE, Entity.ROUND);
        }

        public Optional<BigDecimal> reservedAmount() {
            return Optional.ofNullable(reservedAmount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }
    }

    public enum SalaryType {
        MONTHLY("MÅN"),
        HOURLY("TIM");
        private final String code;

        private SalaryType(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static SalaryType find(String text) {
            return Stream.of(values()).filter(st -> st.name().equalsIgnoreCase(text) || st.code().equalsIgnoreCase(text)).findFirst().orElseThrow(() -> new Paxml4jException("No SalaryType found for code: " + text));
        }
    }

    public static class Builder {

        private String employmentId;
        private String personalIdentityNumber;
        private Boolean delete;
        private String firstName;
        private String lastName;
        private String extraAddress;
        private String postalAddress;
        private String zipCode;
        private String city;
        private String country;
        private String mobilePhone;
        private String homePhone;
        private String workPhone;
        private String workEmail;
        private String homeEmail;
        private String typeOfEmployee;
        private String category;
        private String position;
        private String positionCode;
        private String formOfEmployment;
        private String vacationAgreement;
        private String bankClearingNumber;
        private String bankAccountNumber;
        private LocalDate dateOfEmployment;
        private LocalDate dateOfRetirement;
        private SalaryType salaryType;
        private Boolean salaryForPresentPeriod;
        private DateAmount hourlySalary;
        private DateAmount monthlySalary;
        private List<TaxFreeAmount> taxFreeAmounts = List.of();
        private List<Text> employeeTexts = List.of();
        private DateAmount degreeOfEmployment;
        private Double daysOfVacation;
        private Double taxTable;
        private Integer taxColumn;
        private TaxReconciliation taxReconciliation;
        private WageDestraint wageDestraint;
        private List<ProfitCenter.Reference> profitCenters = List.of();
        private String info;

        private Builder() {
        }

        public Builder employmentId(String employmentId) {
            this.employmentId = employmentId;
            return this;
        }

        public Builder personalIdentityNumber(String personalIdentityNumber) {
            this.personalIdentityNumber = personalIdentityNumber;
            return this;
        }

        public Builder delete(Boolean delete) {
            this.delete = delete;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
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

        public Builder mobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public Builder homePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public Builder workPhone(String workPhone) {
            this.workPhone = workPhone;
            return this;
        }

        public Builder workEmail(String workEmail) {
            this.workEmail = workEmail;
            return this;
        }

        public Builder homeEmail(String homeEmail) {
            this.homeEmail = homeEmail;
            return this;
        }

        public Builder typeOfEmployee(String typeOfEmployee) {
            this.typeOfEmployee = typeOfEmployee;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder positionCode(String positionCode) {
            this.positionCode = positionCode;
            return this;
        }

        public Builder formOfEmployment(String formOfEmployment) {
            this.formOfEmployment = formOfEmployment;
            return this;
        }

        public Builder vacationAgreement(String vacationAgreement) {
            this.vacationAgreement = vacationAgreement;
            return this;
        }

        public Builder bankClearingNumber(String bankClearingNumber) {
            this.bankClearingNumber = bankClearingNumber;
            return this;
        }

        public Builder bankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
            return this;
        }

        public Builder dateOfEmployment(LocalDate dateOfEmployment) {
            this.dateOfEmployment = dateOfEmployment;
            return this;
        }

        public Builder dateOfRetirement(LocalDate dateOfRetirement) {
            this.dateOfRetirement = dateOfRetirement;
            return this;
        }

        public Builder salaryType(SalaryType salaryType) {
            this.salaryType = salaryType;
            return this;
        }

        public Builder salaryForPresentPeriod(Boolean salaryForPresentPeriod) {
            this.salaryForPresentPeriod = salaryForPresentPeriod;
            return this;
        }

        public Builder hourlySalary(DateAmount hourlySalary) {
            this.hourlySalary = hourlySalary;
            return this;
        }

        public Builder monthlySalary(DateAmount monthlySalary) {
            this.monthlySalary = monthlySalary;
            return this;
        }

        public Builder taxFreeAmounts(List<TaxFreeAmount> taxFreeAmounts) {
            this.taxFreeAmounts = Objects.requireNonNull(taxFreeAmounts);
            return this;
        }

        public Builder employeeTexts(List<Text> employeeTexts) {
            this.employeeTexts = Objects.requireNonNull(employeeTexts);
            return this;
        }

        public Builder degreeOfEmployment(DateAmount degreeOfEmployment) {
            this.degreeOfEmployment = degreeOfEmployment;
            return this;
        }

        public Builder daysOfVacation(Double daysOfVacation) {
            this.daysOfVacation = daysOfVacation;
            return this;
        }

        public Builder taxTable(Double taxTable) {
            this.taxTable = taxTable;
            return this;
        }

        public Builder taxColumn(Integer taxColumn) {
            this.taxColumn = taxColumn;
            return this;
        }

        public Builder taxReconciliation(TaxReconciliation taxReconciliation) {
            this.taxReconciliation = taxReconciliation;
            return this;
        }

        public Builder wageDestraint(WageDestraint wageDestraint) {
            this.wageDestraint = wageDestraint;
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

        public Employee build() {
            return new Employee(employmentId,
                    personalIdentityNumber,
                    delete,
                    firstName,
                    lastName,
                    extraAddress,
                    postalAddress,
                    zipCode,
                    city,
                    country,
                    mobilePhone,
                    homePhone,
                    workPhone,
                    workEmail,
                    homeEmail,
                    typeOfEmployee,
                    category,
                    position,
                    positionCode,
                    formOfEmployment,
                    vacationAgreement,
                    bankClearingNumber,
                    bankAccountNumber,
                    dateOfEmployment,
                    dateOfRetirement,
                    salaryType,
                    salaryForPresentPeriod,
                    hourlySalary,
                    monthlySalary,
                    taxFreeAmounts,
                    employeeTexts,
                    degreeOfEmployment,
                    daysOfVacation,
                    taxTable,
                    taxColumn,
                    taxReconciliation,
                    wageDestraint,
                    profitCenters,
                    info);
        }
    }
}
