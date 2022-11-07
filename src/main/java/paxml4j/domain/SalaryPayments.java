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
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class SalaryPayments implements Entity {

    private final List<Payslip> payslips;

    @JsonCreator
    private SalaryPayments(@JsonProperty("payslips") List<Payslip> payslips) {
        this.payslips = Objects.requireNonNull(payslips);
    }

    public static SalaryPayments of(List<Payslip> payslips) {
        return new SalaryPayments(payslips);
    }

    public static SalaryPayments of(XmlNode node) {
        return of(node.getChildren("lonebesked").stream().map(Payslip::of).collect(Collectors.toList()));
    }

    public List<Payslip> payslips() {
        return List.copyOf(payslips);
    }

    public static class Payslip implements Entity {

        private final String employmentId;
        private final String personalIdentityNumber;
        private final String periodId;
        private final String periodText;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate paymentDate;
        private final String firstName;
        private final String lastName;
        private final String extraAddress;
        private final String postalAddress;
        private final String zipCode;
        private final String city;
        private final String country;
        private final String bankClearingNumber;
        private final String bankAccountNumber;
        private final Double taxPercentage;
        private final Integer taxTable;
        private final Double taxAdjustmentPercentage;
        private final BigDecimal taxAdjustmentAmount;
        private final Integer taxColumn;
        private final BigDecimal tableTax;
        private final BigDecimal oneTimeTax;
        private final BigDecimal capitalTax;
        private final BigDecimal extraTax;
        private final BigDecimal disbursed;
        private final Double employersContributionPercentage;
        private final BigDecimal employersContributionAmount;
        private final List<PaymentRow> paymentRows;
        private final BigDecimal accumulatedGrossWage;
        private final BigDecimal accumulatedPreliminaryTax;
        private final BigDecimal accumulatedNetWage;
        private final Double flexibleHoursBalance;
        private final Double compensatoryLeaveBalance;
        private final Double reductionOfWorkingHours;
        private final BigDecimal reductionOfWorkingHoursAmount;
        private final Double daysOfVacationTotal;
        private final Double daysOfVacationDisbursed;
        private final Double daysOfUnpaidVacationTotal;
        private final Double daysOfUnpaidVacationDisbursed;
        private final Double daysOfAdvancedVacationTotal;
        private final Double daysOfAdvancedVacationDisbursed;
        private final Double daysOfSavedVacationTotal;
        private final Double daysOfSavedVacationDisbursed;
        private final BigDecimal vacationPaymentAmountTotal;
        private final BigDecimal vacationPaymentAmountDisbursed;
        private final List<Transaction> transactions;
        private final String info;

        @JsonCreator
        private Payslip(@JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonProperty("periodId") String periodId,
                @JsonProperty("periodText") String periodText,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("paymentDate") LocalDate paymentDate,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("extraAddress") String extraAddress,
                @JsonProperty("postalAddress") String postalAddress,
                @JsonProperty("zipCode") String zipCode,
                @JsonProperty("city") String city,
                @JsonProperty("country") String country,
                @JsonProperty("bankClearingNumber") String bankClearingNumber,
                @JsonProperty("bankAccountNumber") String bankAccountNumber,
                @JsonProperty("taxPercentage") Double taxPercentage,
                @JsonProperty("taxTable") Integer taxTable,
                @JsonProperty("taxAdjustmentPercentage") Double taxAdjustmentPercentage,
                @JsonProperty("taxAdjustmentAmount") BigDecimal taxAdjustmentAmount,
                @JsonProperty("taxColumn") Integer taxColumn,
                @JsonProperty("tableTax") BigDecimal tableTax,
                @JsonProperty("oneTimeTax") BigDecimal oneTimeTax,
                @JsonProperty("capitalTax") BigDecimal capitalTax,
                @JsonProperty("extraTax") BigDecimal extraTax,
                @JsonProperty("disbursed") BigDecimal disbursed,
                @JsonProperty("employersContributionPercentage") Double employersContributionPercentage,
                @JsonProperty("employersContributionAmount") BigDecimal employersContributionAmount,
                @JsonProperty("paymentRows") List<PaymentRow> paymentRows,
                @JsonProperty("accumulatedGrossWage") BigDecimal accumulatedGrossWage,
                @JsonProperty("accumulatedPreliminaryTax") BigDecimal accumulatedPreliminaryTax,
                @JsonProperty("accumulatedNetWage") BigDecimal accumulatedNetWage,
                @JsonProperty("flexibleHoursBalance") Double flexibleHoursBalance,
                @JsonProperty("compensatoryLeaveBalance") Double compensatoryLeaveBalance,
                @JsonProperty("reductionOfWorkingHours") Double reductionOfWorkingHours,
                @JsonProperty("reductionOfWorkingHoursAmount") BigDecimal reductionOfWorkingHoursAmount,
                @JsonProperty("daysOfVacationTotal") Double daysOfVacationTotal,
                @JsonProperty("daysOfVacationDisbursed") Double daysOfVacationDisbursed,
                @JsonProperty("daysOfUnpaidVacationTotal") Double daysOfUnpaidVacationTotal,
                @JsonProperty("daysOfUnpaidVacationDisbursed") Double daysOfUnpaidVacationDisbursed,
                @JsonProperty("daysOfAdvancedVacationTotal") Double daysOfAdvancedVacationTotal,
                @JsonProperty("daysOfAdvancedVacationDisbursed") Double daysOfAdvancedVacationDisbursed,
                @JsonProperty("daysOfSavedVacationTotal") Double daysOfSavedVacationTotal,
                @JsonProperty("daysOfSavedVacationDisbursed") Double daysOfSavedVacationDisbursed,
                @JsonProperty("vacationPaymentAmountTotal") BigDecimal vacationPaymentAmountTotal,
                @JsonProperty("vacationPaymentAmountDisbursed") BigDecimal vacationPaymentAmountDisbursed,
                @JsonProperty("transactions") List<Transaction> transactions,
                @JsonProperty("info") String info) {
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.periodId = periodId;
            this.periodText = periodText;
            this.paymentDate = paymentDate;
            this.firstName = firstName;
            this.lastName = lastName;
            this.extraAddress = extraAddress;
            this.postalAddress = postalAddress;
            this.zipCode = zipCode;
            this.city = city;
            this.country = country;
            this.bankClearingNumber = bankClearingNumber;
            this.bankAccountNumber = bankAccountNumber;
            this.taxPercentage = taxPercentage;
            this.taxTable = taxTable;
            this.taxAdjustmentPercentage = taxAdjustmentPercentage;
            this.taxAdjustmentAmount = taxAdjustmentAmount;
            this.taxColumn = taxColumn;
            this.tableTax = tableTax;
            this.oneTimeTax = oneTimeTax;
            this.capitalTax = capitalTax;
            this.extraTax = extraTax;
            this.disbursed = disbursed;
            this.employersContributionPercentage = employersContributionPercentage;
            this.employersContributionAmount = employersContributionAmount;
            this.paymentRows = Objects.requireNonNull(paymentRows);
            this.accumulatedGrossWage = accumulatedGrossWage;
            this.accumulatedPreliminaryTax = accumulatedPreliminaryTax;
            this.accumulatedNetWage = accumulatedNetWage;
            this.flexibleHoursBalance = flexibleHoursBalance;
            this.compensatoryLeaveBalance = compensatoryLeaveBalance;
            this.reductionOfWorkingHours = reductionOfWorkingHours;
            this.reductionOfWorkingHoursAmount = reductionOfWorkingHoursAmount;
            this.daysOfVacationTotal = daysOfVacationTotal;
            this.daysOfVacationDisbursed = daysOfVacationDisbursed;
            this.daysOfUnpaidVacationTotal = daysOfUnpaidVacationTotal;
            this.daysOfUnpaidVacationDisbursed = daysOfUnpaidVacationDisbursed;
            this.daysOfAdvancedVacationTotal = daysOfAdvancedVacationTotal;
            this.daysOfAdvancedVacationDisbursed = daysOfAdvancedVacationDisbursed;
            this.daysOfSavedVacationTotal = daysOfSavedVacationTotal;
            this.daysOfSavedVacationDisbursed = daysOfSavedVacationDisbursed;
            this.vacationPaymentAmountTotal = vacationPaymentAmountTotal;
            this.vacationPaymentAmountDisbursed = vacationPaymentAmountDisbursed;
            this.transactions = Objects.requireNonNull(transactions);
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Payslip of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.nodeText(node, "periodid").ifPresent(builder::periodId);
            Helper.nodeText(node, "periodtext").ifPresent(builder::periodText);
            Helper.nodeText(node, "betaldatum").map(LocalDate::parse).ifPresent(builder::paymentDate);
            Helper.nodeText(node, "fornamn").ifPresent(builder::firstName);
            Helper.nodeText(node, "efternamn").ifPresent(builder::lastName);
            Helper.nodeText(node, "extraadress").ifPresent(builder::extraAddress);
            Helper.nodeText(node, "postadress").ifPresent(builder::postalAddress);
            Helper.nodeText(node, "postnr").ifPresent(builder::zipCode);
            Helper.nodeText(node, "ort").ifPresent(builder::city);
            Helper.nodeText(node, "land").ifPresent(builder::country);
            Helper.nodeText(node, "bankclearing").ifPresent(builder::bankClearingNumber);
            Helper.nodeText(node, "bankkonto").ifPresent(builder::bankAccountNumber);
            Helper.nodeText(node, "skattprocent").map(Double::valueOf).ifPresent(builder::taxPercentage);
            Helper.nodeText(node, "skattetabell").map(Integer::valueOf).ifPresent(builder::taxTable);
            Helper.nodeText(node, "jamkningprc").map(Double::valueOf).ifPresent(builder::taxAdjustmentPercentage);
            Helper.nodeText(node, "jamkningbel").map(BigDecimal::new).ifPresent(builder::taxAdjustmentAmount);
            Helper.nodeText(node, "skattekolumn").map(Integer::valueOf).ifPresent(builder::taxColumn);
            Helper.nodeText(node, "tabellskatt").map(BigDecimal::new).ifPresent(builder::tableTax);
            Helper.nodeText(node, "engangsskatt").map(BigDecimal::new).ifPresent(builder::oneTimeTax);
            Helper.nodeText(node, "kapitalskatt").map(BigDecimal::new).ifPresent(builder::capitalTax);
            Helper.nodeText(node, "extraskatt").map(BigDecimal::new).ifPresent(builder::extraTax);
            Helper.nodeText(node, "utbetalt").map(BigDecimal::new).ifPresent(builder::disbursed);
            Helper.nodeText(node, "arbavgiftprc").map(Double::valueOf).ifPresent(builder::employersContributionPercentage);
            Helper.nodeText(node, "arbavgiftbel").map(BigDecimal::new).ifPresent(builder::employersContributionAmount);
            if (node.hasChildNamed("lonerader")) {
                builder.paymentRows(node.getChild("lonerader").getChildren("lonrad").stream().map(PaymentRow::of).collect(Collectors.toList()));
            }
            Helper.nodeText(node, "ackbruttolon").map(BigDecimal::new).ifPresent(builder::accumulatedGrossWage);
            Helper.nodeText(node, "ackprelskatt").map(BigDecimal::new).ifPresent(builder::accumulatedPerliminaryTax);
            Helper.nodeText(node, "acknettolon").map(BigDecimal::new).ifPresent(builder::accumulatedNetWage);
            Helper.nodeText(node, "flexsaldo").map(Double::valueOf).ifPresent(builder::flexibleHoursBalance);
            Helper.nodeText(node, "kompsaldo").map(Double::valueOf).ifPresent(builder::compensatoryLeaveBalance);
            Helper.nodeText(node, "tidbanktim").map(Double::valueOf).ifPresent(builder::reductionOfWorkingHours);
            Helper.nodeText(node, "tidbankbel").map(BigDecimal::new).ifPresent(builder::reductionOfWorkingHoursAmount);
            Helper.nodeText(node, "sembettot").map(Double::valueOf).ifPresent(builder::daysOfVacationTotal);
            Helper.nodeText(node, "sembetutb").map(Double::valueOf).ifPresent(builder::daysOfVacationDisbursed);
            Helper.nodeText(node, "semobetot").map(Double::valueOf).ifPresent(builder::daysOfUnpaidVacationTotal);
            Helper.nodeText(node, "semobeutb").map(Double::valueOf).ifPresent(builder::daysOfUnpaidVacationDisbursed);
            Helper.nodeText(node, "semfortot").map(Double::valueOf).ifPresent(builder::daysOfAdvancedVacationTotal);
            Helper.nodeText(node, "semforutb").map(Double::valueOf).ifPresent(builder::daysOfAdvancedVacationDisbursed);
            Helper.nodeText(node, "semspatot").map(Double::valueOf).ifPresent(builder::daysOfSavedVacationTotal);
            Helper.nodeText(node, "semspautb").map(Double::valueOf).ifPresent(builder::daysOfSavedVacationDisbursed);
            Helper.nodeText(node, "semlontot").map(BigDecimal::new).ifPresent(builder::vacationPaymentAmountTotal);
            Helper.nodeText(node, "semlonutb").map(BigDecimal::new).ifPresent(builder::vacationPaymentAmountDisbursed);
            if (node.hasChildNamed("kontering")) {
                builder.transactions(node.getChild("kontering").getChildren("transaktion").stream().map(Transaction::of).collect(Collectors.toList()));
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

        public Optional<String> periodId() {
            return Optional.ofNullable(periodId);
        }

        public Optional<String> periodText() {
            return Optional.ofNullable(periodText);
        }

        public Optional<LocalDate> paymentDate() {
            return Optional.ofNullable(paymentDate);
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

        public Optional<String> bankClearingNumber() {
            return Optional.ofNullable(bankClearingNumber);
        }

        public Optional<String> bankAccountNumber() {
            return Optional.ofNullable(bankAccountNumber);
        }

        public Optional<Double> taxPercentage() {
            return Optional.ofNullable(taxPercentage);
        }

        public Optional<Integer> taxTable() {
            return Optional.ofNullable(taxTable);
        }

        public Optional<Double> taxAdjustmentPercentage() {
            return Optional.ofNullable(taxAdjustmentPercentage);
        }

        public Optional<BigDecimal> taxAdjustmentAmount() {
            return Optional.ofNullable(taxAdjustmentAmount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<Integer> taxColumn() {
            return Optional.ofNullable(taxColumn);
        }

        public Optional<BigDecimal> tableTax() {
            return Optional.ofNullable(tableTax).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> oneTimeTax() {
            return Optional.ofNullable(oneTimeTax).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> capitalTax() {
            return Optional.ofNullable(capitalTax).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> extraTax() {
            return Optional.ofNullable(extraTax).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> disbursed() {
            return Optional.ofNullable(disbursed).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<Double> employersContributionPercentage() {
            return Optional.ofNullable(employersContributionPercentage);
        }

        public Optional<BigDecimal> employersContributionAmount() {
            return Optional.ofNullable(employersContributionAmount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public List<PaymentRow> paymentRows() {
            return List.copyOf(paymentRows);
        }

        public Optional<BigDecimal> accumulatedGrossWage() {
            return Optional.ofNullable(accumulatedGrossWage).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> accumulatedPreliminaryTax() {
            return Optional.ofNullable(accumulatedPreliminaryTax).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> accumulatedNetWage() {
            return Optional.ofNullable(accumulatedNetWage).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<Double> flexibleHoursBalance() {
            return Optional.ofNullable(flexibleHoursBalance);
        }

        public Optional<Double> compensatoryLeaveBalance() {
            return Optional.ofNullable(compensatoryLeaveBalance);
        }

        public Optional<Double> reductionOfWorkingHours() {
            return Optional.ofNullable(reductionOfWorkingHours);
        }

        public Optional<BigDecimal> reductionOfWorkingHoursAmount() {
            return Optional.ofNullable(reductionOfWorkingHoursAmount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<Double> daysOfVacationTotal() {
            return Optional.ofNullable(daysOfVacationTotal);
        }

        public Optional<Double> daysOfVacationDisbursed() {
            return Optional.ofNullable(daysOfVacationDisbursed);
        }

        public Optional<Double> daysOfUnpaidVacationTotal() {
            return Optional.ofNullable(daysOfUnpaidVacationTotal);
        }

        public Optional<Double> daysOfUnpaidVacationDisbursed() {
            return Optional.ofNullable(daysOfUnpaidVacationDisbursed);
        }

        public Optional<Double> daysOfAdvancedVacationTotal() {
            return Optional.ofNullable(daysOfAdvancedVacationTotal);
        }

        public Optional<Double> daysOfAdvancedVacationDisbursed() {
            return Optional.ofNullable(daysOfAdvancedVacationDisbursed);
        }

        public Optional<Double> daysOfSavedVacationTotal() {
            return Optional.ofNullable(daysOfSavedVacationTotal);
        }

        public Optional<Double> daysOfSavedVacationDisbursed() {
            return Optional.ofNullable(daysOfSavedVacationDisbursed);
        }

        public Optional<BigDecimal> vacationPaymentAmountTotal() {
            return Optional.ofNullable(vacationPaymentAmountTotal).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<BigDecimal> vacationPaymentAmountDisbursed() {
            return Optional.ofNullable(vacationPaymentAmountDisbursed).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public List<Transaction> transactions() {
            return List.copyOf(transactions);
        }

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }
    }

    public static class PaymentRow implements Entity {

        private final Integer rowIndex;
        private final String typeOfSalary;
        private final String font;
        private final String nameOfSalary;
        private final String comment;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal startDate;
        @JsonSerialize(using = TemporalSerializer.class)
        private final Temporal endDate;
        private final Double hours;
        private final Double workingDays;
        private final Double calendarDays;
        private final String unit;
        private final Double quantity;
        private final BigDecimal unitPrice;
        private final BigDecimal amount;
        private final SalaryType salaryType;
        private final TaxationType taxType;
        private final Double taxPercentage;
        private final DueType dueType;
        private final Double duePercentage;
        private final Boolean regionalSupport;
        private final String accountNumber;
        private final CustomerNumber customerNumber;
        private final List<ProfitCenter.Reference> profitCenters;
        private final String statisticsCode;
        private final String statementOfEarnings;
        private final String info;

        @JsonCreator
        private PaymentRow(@JsonProperty("rowIndex") Integer rowIndex,
                @JsonProperty("typeOfSalary") String typeOfSalary,
                @JsonProperty("font") String font,
                @JsonProperty("nameOfSalary") String nameOfSalary,
                @JsonProperty("comment") String comment,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("startDate") Temporal startDate,
                @JsonDeserialize(using = TemporalDeserializer.class)
                @JsonProperty("endDate") Temporal endDate,
                @JsonProperty("hours") Double hours,
                @JsonProperty("workingDays") Double workingDays,
                @JsonProperty("calendarDays") Double calendarDays,
                @JsonProperty("unit") String unit,
                @JsonProperty("quantity") Double quantity,
                @JsonProperty("unitPrice") BigDecimal unitPrice,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("salaryType") SalaryType salaryType,
                @JsonProperty("taxType") TaxationType taxType,
                @JsonProperty("taxPercentage") Double taxPercentage,
                @JsonProperty("dueType") DueType dueType,
                @JsonProperty("duePercentage") Double duePercentage,
                @JsonProperty("regionalSupport") Boolean regionalSupport,
                @JsonProperty("accountNumber") String accountNumber,
                @JsonProperty("customerNumber") CustomerNumber customerNumber,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters,
                @JsonProperty("statisticsCode") String statisticsCode,
                @JsonProperty("statementOfEarnings") String statementOfEarnings,
                @JsonProperty("info") String info) {
            this.rowIndex = rowIndex;
            this.typeOfSalary = typeOfSalary;
            this.font = font;
            this.nameOfSalary = nameOfSalary;
            this.comment = comment;
            this.startDate = startDate;
            this.endDate = endDate;
            this.hours = hours;
            this.workingDays = workingDays;
            this.calendarDays = calendarDays;
            this.unit = unit;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.amount = amount;
            this.salaryType = salaryType;
            this.taxType = taxType;
            this.taxPercentage = taxPercentage;
            this.dueType = dueType;
            this.duePercentage = duePercentage;
            this.regionalSupport = regionalSupport;
            this.accountNumber = accountNumber;
            this.customerNumber = customerNumber;
            this.profitCenters = Objects.requireNonNull(profitCenters);
            this.statisticsCode = statisticsCode;
            this.statementOfEarnings = statementOfEarnings;
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static PaymentRow of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "radnr").map(Integer::valueOf).ifPresent(builder::rowIndex);
            Helper.nodeText(node, "lonart").ifPresent(builder::typeOfSalary);
            Helper.nodeText(node, "font").ifPresent(builder::font);
            Helper.nodeText(node, "benamning").ifPresent(builder::nameOfSalary);
            Helper.nodeText(node, "kommentar").ifPresent(builder::comment);
            Helper.nodeText(node, "datumfrom").map(Helper::temporalFromText).ifPresent(builder::startDate);
            Helper.nodeText(node, "datumtom").map(Helper::temporalFromText).ifPresent(builder::endDate);
            Helper.nodeText(node, "timmar").map(Double::valueOf).ifPresent(builder::hours);
            Helper.nodeText(node, "arbetsdagar").map(Double::valueOf).ifPresent(builder::workingDays);
            Helper.nodeText(node, "dagar").map(Double::valueOf).ifPresent(builder::calendarDays);
            Helper.nodeText(node, "enhet").ifPresent(builder::unit);
            Helper.nodeText(node, "antal").map(Double::valueOf).ifPresent(builder::quantity);
            Helper.nodeText(node, "apris").map(BigDecimal::new).ifPresent(builder::unitPrice);
            Helper.nodeText(node, "belopp").map(BigDecimal::new).ifPresent(builder::amount);
            Helper.nodeText(node, "lonetyp").map(SalaryType::find).ifPresent(builder::salaryType);
            Helper.nodeText(node, "skattetyp").map(TaxationType::find).ifPresent(builder::taxType);
            Helper.nodeText(node, "skatteprocent").map(Double::valueOf).ifPresent(builder::taxPercentage);
            Helper.nodeText(node, "avgifttyp").map(DueType::find).ifPresent(builder::dueType);
            Helper.nodeText(node, "avgiftprocent").map(Double::valueOf).ifPresent(builder::duePercentage);
            // Felstavat i schemat, ska nog vara regional
            Helper.nodeText(node, "regiona").map(Boolean::valueOf).ifPresent(builder::regionalSupport);
            Helper.nodeText(node, "regional").map(Boolean::valueOf).ifPresent(builder::regionalSupport);
            // Båda med för säkerhetsskull
            Helper.nodeText(node, "kontonr").ifPresent(builder::accountNumber);
            Helper.childNode(node, "kudnr").map(CustomerNumber::of).ifPresent(builder::customerNumber);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet").stream().map(ProfitCenter.Reference::of).collect(Collectors.toList()));
            }
            Helper.nodeText(node, "statistikkod").ifPresent(builder::statisticsCode);
            Helper.nodeText(node, "kontrolluppgift").ifPresent(builder::statementOfEarnings);
            Helper.nodeText(node, "info").ifPresent(builder::info);
            return builder.build();
        }

        public Optional<Integer> rowIndex() {
            return Optional.ofNullable(rowIndex);
        }

        public Optional<String> typeOfSalary() {
            return Optional.ofNullable(typeOfSalary);
        }

        public Optional<String> font() {
            return Optional.ofNullable(font);
        }

        public Optional<String> nameOfSalary() {
            return Optional.ofNullable(nameOfSalary);
        }

        public Optional<String> comment() {
            return Optional.ofNullable(comment);
        }

        public Optional<Temporal> startDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<Temporal> endDate() {
            return Optional.ofNullable(endDate);
        }

        public Optional<Double> hours() {
            return Optional.ofNullable(hours);
        }

        public Optional<Double> workingDays() {
            return Optional.ofNullable(workingDays);
        }

        public Optional<Double> calendarDays() {
            return Optional.ofNullable(calendarDays);
        }

        public Optional<String> unit() {
            return Optional.ofNullable(unit);
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

        public Optional<SalaryType> salaryType() {
            return Optional.ofNullable(salaryType);
        }

        public Optional<TaxationType> taxType() {
            return Optional.ofNullable(taxType);
        }

        public Optional<Double> taxPercentage() {
            return Optional.ofNullable(taxPercentage);
        }

        public Optional<DueType> dueType() {
            return Optional.ofNullable(dueType);
        }

        public Optional<Double> duePercentage() {
            return Optional.ofNullable(duePercentage);
        }

        public Optional<Boolean> regionalSupport() {
            return Optional.ofNullable(regionalSupport);
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

        public Optional<String> statisticsCode() {
            return Optional.ofNullable(statisticsCode);
        }

        public Optional<String> statementOfEarnings() {
            return Optional.ofNullable(statementOfEarnings);
        }

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }

        public static class Builder {

            private Integer rowIndex;
            private String typeOfSalary;
            private String font;
            private String nameOfSalary;
            private String comment;
            private Temporal startDate;
            private Temporal endDate;
            private Double hours;
            private Double workingDays;
            private Double calendarDays;
            private String unit;
            private Double quantity;
            private BigDecimal unitPrice;
            private BigDecimal amount;
            private SalaryType salaryType;
            private TaxationType taxType;
            private Double taxPercentage;
            private DueType dueType;
            private Double duePercentage;
            private Boolean regionalSupport;
            private String accountNumber;
            private CustomerNumber customerNumber;
            private List<ProfitCenter.Reference> profitCenters = List.of();
            private String statisticsCode;
            private String statementOfEarnings;
            private String info;

            private Builder() {
            }

            public Builder rowIndex(Integer rowIndex) {
                this.rowIndex = rowIndex;
                return this;
            }

            public Builder typeOfSalary(String typeOfSalary) {
                this.typeOfSalary = typeOfSalary;
                return this;
            }

            public Builder font(String font) {
                this.font = font;
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

            public Builder startDate(Temporal startDate) {
                this.startDate = startDate;
                return this;
            }

            public Builder endDate(Temporal endDate) {
                this.endDate = endDate;
                return this;
            }

            public Builder hours(Double hours) {
                this.hours = hours;
                return this;
            }

            public Builder workingDays(Double workingDays) {
                this.workingDays = workingDays;
                return this;
            }

            public Builder calendarDays(Double calendarDays) {
                this.calendarDays = calendarDays;
                return this;
            }

            public Builder unit(String unit) {
                this.unit = unit;
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

            public Builder salaryType(SalaryType salaryType) {
                this.salaryType = salaryType;
                return this;
            }

            public Builder taxType(TaxationType taxType) {
                this.taxType = taxType;
                return this;
            }

            public Builder taxPercentage(Double taxPercentage) {
                this.taxPercentage = taxPercentage;
                return this;
            }

            public Builder dueType(DueType dueType) {
                this.dueType = dueType;
                return this;
            }

            public Builder duePercentage(Double duePercentage) {
                this.duePercentage = duePercentage;
                return this;
            }

            public Builder regionalSupport(Boolean regionalSupport) {
                this.regionalSupport = regionalSupport;
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

            public Builder statisticsCode(String statisticsCode) {
                this.statisticsCode = statisticsCode;
                return this;
            }

            public Builder statementOfEarnings(String statementOfEarnings) {
                this.statementOfEarnings = statementOfEarnings;
                return this;
            }

            public Builder info(String info) {
                this.info = info;
                return this;
            }

            public PaymentRow build() {
                return new PaymentRow(rowIndex,
                        typeOfSalary,
                        font,
                        nameOfSalary,
                        comment,
                        startDate,
                        endDate,
                        hours,
                        workingDays,
                        calendarDays,
                        unit,
                        quantity,
                        unitPrice,
                        amount,
                        salaryType,
                        taxType,
                        taxPercentage,
                        dueType,
                        duePercentage,
                        regionalSupport,
                        accountNumber,
                        customerNumber,
                        profitCenters,
                        statisticsCode,
                        statementOfEarnings,
                        info);
            }
        }

    }

    public static class Transaction implements Entity {

        private final String accountNumber;
        private final BigDecimal amount;
        private final CustomerNumber customerNumber;
        private final List<ProfitCenter.Reference> profitCenters;

        @JsonCreator
        private Transaction(@JsonProperty("accountNumber") String accountNumber,
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("customerNumber") CustomerNumber customerNumber,
                @JsonProperty("profitCenters") List<ProfitCenter.Reference> profitCenters) {
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.customerNumber = customerNumber;
            this.profitCenters = Objects.requireNonNull(profitCenters);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Transaction of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "kontonr").ifPresent(builder::accountNumber);
            Helper.attrText(node, "belopp").map(BigDecimal::new).ifPresent(builder::amount);
            Helper.childNode(node, "kundnr").map(CustomerNumber::of).ifPresent(builder::customerNumber);
            if (node.hasChildNamed("resenheter")) {
                builder.profitCenters(node.getChild("resenheter").getChildren("resenhet").stream().map(ProfitCenter.Reference::of).collect(Collectors.toList()));
            }
            return builder.build();
        }

        public Optional<String> accountNumber() {
            return Optional.ofNullable(accountNumber);
        }

        public Optional<BigDecimal> amount() {
            return Optional.ofNullable(amount).map(b -> b.setScale(Entity.SCALE, Entity.ROUND));
        }

        public Optional<CustomerNumber> customerNumber() {
            return Optional.ofNullable(customerNumber);
        }

        public List<ProfitCenter.Reference> profitCenters() {
            return List.copyOf(profitCenters);
        }

        public static class Builder {

            private String accountNumber;
            private BigDecimal amount;
            private CustomerNumber customerNumber;
            private List<ProfitCenter.Reference> profitCenters = List.of();

            private Builder() {
            }

            public Builder accountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
                return this;
            }

            public Builder amount(BigDecimal amount) {
                this.amount = amount;
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

            public Transaction build() {
                return new Transaction(accountNumber, amount, customerNumber, profitCenters);
            }
        }
    }

    public enum SalaryType {
        GROSS("BRUTTO"),
        BENEFIT("FÖRMÅN"),
        NET("NETTO"),
        TAX("SKATT"),
        CAPITAL("KAPITAL"),
        CAPITAL_TAX("KAPITALSKATT"),
        PENSION("PENSION");
        private final String code;

        private SalaryType(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static SalaryType find(String text) {
            return Stream.of(values())
                    .filter(st -> st.name().equalsIgnoreCase(text) || st.code().equalsIgnoreCase(text))
                    .findFirst()
                    .orElseThrow(() -> new Paxml4jException("No SalaryType found for code: " + text));
        }
    }

    public enum TaxationType {
        TABLE("TABELL"),
        ONE_TIME("ENGÅNGS"),
        CAPITAL("KAPITAL");
        private final String code;

        private TaxationType(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static TaxationType find(String text) {
            return Stream.of(values())
                    .filter(tt -> tt.name().equalsIgnoreCase(text) || tt.code().equalsIgnoreCase(text))
                    .findFirst().
                    orElseThrow(() -> new Paxml4jException("No TaxationType found for code: " + text));
        }
    }

    public enum DueType {
        FULL("FULL"),
        YOUTH("UNGDOM"),
        RETIRED_PERSON("PENSIONÄR"),
        EMBASSY("AMBASSAD"),
        USA("USA"),
        WAGE_TAX("LÖNESKATT");
        private final String code;

        private DueType(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }

        public static DueType find(String text) {
            return Stream.of(values())
                    .filter(dt -> dt.name().equalsIgnoreCase(text) || dt.code().equalsIgnoreCase(text))
                    .findFirst()
                    .orElseThrow(() -> new Paxml4jException("No DueType found for code: " + text));
        }
    }

    public static class Builder {

        private String employmentId;
        private String personalIdentityNumber;
        private String periodId;
        private String periodText;
        private LocalDate paymentDate;
        private String firstName;
        private String lastName;
        private String extraAddress;
        private String postalAddress;
        private String zipCode;
        private String city;
        private String country;
        private String bankClearingNumber;
        private String bankAccountNumber;
        private Double taxPercentage;
        private Integer taxTable;
        private Double taxAdjustmentPercentage;
        private BigDecimal taxAdjustmentAmount;
        private Integer taxColumn;
        private BigDecimal tableTax;
        private BigDecimal oneTimeTax;
        private BigDecimal capitalTax;
        private BigDecimal extraTax;
        private BigDecimal disbursed;
        private Double employersContributionPercentage;
        private BigDecimal employersContributionAmount;
        private List<PaymentRow> paymentRows = List.of();
        private BigDecimal accumulatedGrossWage;
        private BigDecimal accumulatedPerliminaryTax;
        private BigDecimal accumulatedNetWage;
        private Double flexibleHoursBalance;
        private Double compensatoryLeaveBalance;
        private Double reductionOfWorkingHours;
        private BigDecimal reductionOfWorkingHoursAmount;
        private Double daysOfVacationTotal;
        private Double daysOfVacationDisbursed;
        private Double daysOfUnpaidVacationTotal;
        private Double daysOfUnpaidVacationDisbursed;
        private Double daysOfAdvancedVacationTotal;
        private Double daysOfAdvancedVacationDisbursed;
        private Double daysOfSavedVacationTotal;
        private Double daysOfSavedVacationDisbursed;
        private BigDecimal vacationPaymentAmountTotal;
        private BigDecimal vacationPaymentAmountDisbursed;
        private List<Transaction> transactions = List.of();
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

        public Builder periodId(String periodId) {
            this.periodId = periodId;
            return this;
        }

        public Builder periodText(String periodText) {
            this.periodText = periodText;
            return this;
        }

        public Builder paymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
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

        public Builder bankClearingNumber(String bankClearingNumber) {
            this.bankClearingNumber = bankClearingNumber;
            return this;
        }

        public Builder bankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
            return this;
        }

        public Builder taxPercentage(Double taxPercentage) {
            this.taxPercentage = taxPercentage;
            return this;
        }

        public Builder taxTable(Integer taxTable) {
            this.taxTable = taxTable;
            return this;
        }

        public Builder taxAdjustmentPercentage(Double taxAdjustmentPercentage) {
            this.taxAdjustmentPercentage = taxAdjustmentPercentage;
            return this;
        }

        public Builder taxAdjustmentAmount(BigDecimal taxAdjustmentAmount) {
            this.taxAdjustmentAmount = taxAdjustmentAmount;
            return this;
        }

        public Builder taxColumn(Integer taxColumn) {
            this.taxColumn = taxColumn;
            return this;
        }

        public Builder tableTax(BigDecimal tableTax) {
            this.tableTax = tableTax;
            return this;
        }

        public Builder oneTimeTax(BigDecimal oneTimeTax) {
            this.oneTimeTax = oneTimeTax;
            return this;
        }

        public Builder capitalTax(BigDecimal capitalTax) {
            this.capitalTax = capitalTax;
            return this;
        }

        public Builder extraTax(BigDecimal extraTax) {
            this.extraTax = extraTax;
            return this;
        }

        public Builder disbursed(BigDecimal disbursed) {
            this.disbursed = disbursed;
            return this;
        }

        public Builder employersContributionPercentage(Double employersContributionPercentage) {
            this.employersContributionPercentage = employersContributionPercentage;
            return this;
        }

        public Builder employersContributionAmount(BigDecimal employersContributionAmount) {
            this.employersContributionAmount = employersContributionAmount;
            return this;
        }

        public Builder paymentRows(List<PaymentRow> paymentRows) {
            this.paymentRows = Objects.requireNonNull(paymentRows);
            return this;
        }

        public Builder accumulatedGrossWage(BigDecimal accumulatedGrossWage) {
            this.accumulatedGrossWage = accumulatedGrossWage;
            return this;
        }

        public Builder accumulatedPerliminaryTax(BigDecimal accumulatedPerliminaryTax) {
            this.accumulatedPerliminaryTax = accumulatedPerliminaryTax;
            return this;
        }

        public Builder accumulatedNetWage(BigDecimal accumulatedNetWage) {
            this.accumulatedNetWage = accumulatedNetWage;
            return this;
        }

        public Builder flexibleHoursBalance(Double flexibleHoursBalance) {
            this.flexibleHoursBalance = flexibleHoursBalance;
            return this;
        }

        public Builder compensatoryLeaveBalance(Double compensatoryLeaveBalance) {
            this.compensatoryLeaveBalance = compensatoryLeaveBalance;
            return this;
        }

        public Builder reductionOfWorkingHours(Double reductionOfWorkingHours) {
            this.reductionOfWorkingHours = reductionOfWorkingHours;
            return this;
        }

        public Builder reductionOfWorkingHoursAmount(BigDecimal reductionOfWorkingHoursAmount) {
            this.reductionOfWorkingHoursAmount = reductionOfWorkingHoursAmount;
            return this;
        }

        public Builder daysOfVacationTotal(Double daysOfVacationTotal) {
            this.daysOfVacationTotal = daysOfVacationTotal;
            return this;
        }

        public Builder daysOfVacationDisbursed(Double daysOfVacationDisbursed) {
            this.daysOfVacationDisbursed = daysOfVacationDisbursed;
            return this;
        }

        public Builder daysOfUnpaidVacationTotal(Double daysOfUnpaidVacationTotal) {
            this.daysOfUnpaidVacationTotal = daysOfUnpaidVacationTotal;
            return this;
        }

        public Builder daysOfUnpaidVacationDisbursed(Double daysOfUnpaidVacationDisbursed) {
            this.daysOfUnpaidVacationDisbursed = daysOfUnpaidVacationDisbursed;
            return this;
        }

        public Builder daysOfAdvancedVacationTotal(Double daysOfAdvancedVacationTotal) {
            this.daysOfAdvancedVacationTotal = daysOfAdvancedVacationTotal;
            return this;
        }

        public Builder daysOfAdvancedVacationDisbursed(Double daysOfAdvancedVacationDisbursed) {
            this.daysOfAdvancedVacationDisbursed = daysOfAdvancedVacationDisbursed;
            return this;
        }

        public Builder daysOfSavedVacationTotal(Double daysOfSavedVacationTotal) {
            this.daysOfSavedVacationTotal = daysOfSavedVacationTotal;
            return this;
        }

        public Builder daysOfSavedVacationDisbursed(Double daysOfSavedVacationDisbursed) {
            this.daysOfSavedVacationDisbursed = daysOfSavedVacationDisbursed;
            return this;
        }

        public Builder vacationPaymentAmountTotal(BigDecimal vacationPaymentAmountTotal) {
            this.vacationPaymentAmountTotal = vacationPaymentAmountTotal;
            return this;
        }

        public Builder vacationPaymentAmountDisbursed(BigDecimal vacationPaymentAmountDisbursed) {
            this.vacationPaymentAmountDisbursed = vacationPaymentAmountDisbursed;
            return this;
        }

        public Builder transactions(List<Transaction> transactions) {
            this.transactions = Objects.requireNonNull(transactions);
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Payslip build() {
            return new Payslip(employmentId,
                    personalIdentityNumber,
                    periodId,
                    periodText,
                    paymentDate,
                    firstName,
                    lastName,
                    extraAddress,
                    postalAddress,
                    zipCode,
                    city,
                    country,
                    bankClearingNumber,
                    bankAccountNumber,
                    taxPercentage,
                    taxTable,
                    taxAdjustmentPercentage,
                    taxAdjustmentAmount,
                    taxColumn,
                    tableTax,
                    oneTimeTax,
                    capitalTax,
                    extraTax,
                    disbursed,
                    employersContributionPercentage,
                    employersContributionAmount,
                    paymentRows,
                    accumulatedGrossWage,
                    accumulatedPerliminaryTax,
                    accumulatedNetWage,
                    flexibleHoursBalance,
                    compensatoryLeaveBalance,
                    reductionOfWorkingHours,
                    reductionOfWorkingHoursAmount,
                    daysOfVacationTotal,
                    daysOfVacationDisbursed,
                    daysOfUnpaidVacationTotal,
                    daysOfUnpaidVacationDisbursed,
                    daysOfAdvancedVacationTotal,
                    daysOfAdvancedVacationDisbursed,
                    daysOfSavedVacationTotal,
                    daysOfSavedVacationDisbursed,
                    vacationPaymentAmountTotal,
                    vacationPaymentAmountDisbursed,
                    transactions,
                    info);
        }
    }
}
