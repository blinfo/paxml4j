package paxml4j.domain;

import paxml4j.util.Helper;
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
import paxml4j.io.LocalDateDeserializer;
import paxml4j.io.LocalDateSerializer;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class BalanceList implements Entity {

    private final List<Balance> balances;

    @JsonCreator
    private BalanceList(@JsonProperty("balances") List<Balance> balances) {
        this.balances = Objects.requireNonNull(balances);
    }

    public static BalanceList of(List<Balance> balances) {
        return new BalanceList(balances);
    }

    public static BalanceList of(XmlNode node) {
        return of(node.getChildren("saldo").stream().map(Balance::of).collect(Collectors.toList()));
    }

    public List<Balance> balances() {
        return List.copyOf(balances);
    }

    public static class Balance implements Entity {

        private final String employmentId;
        private final String personalIdentityNumber;
        @JsonSerialize(using = LocalDateSerializer.class)
        private final LocalDate date;
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
        private final String info;

        private Balance(@JsonProperty("employmentId") String employmentId,
                @JsonProperty("personalIdentityNumber") String personalIdentityNumber,
                @JsonDeserialize(using = LocalDateDeserializer.class)
                @JsonProperty("date") LocalDate date,
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
                @JsonProperty("info") String info) {
            this.employmentId = employmentId;
            this.personalIdentityNumber = personalIdentityNumber;
            this.date = date;
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
            this.info = info;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static Balance of(XmlNode node) {
            Builder builder = builder();
            Helper.attrText(node, "anstid").ifPresent(builder::employmentId);
            Helper.attrText(node, "persnr").ifPresent(builder::personalIdentityNumber);
            Helper.nodeText(node, "datum").map(LocalDate::parse).ifPresent(builder::date);
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
            Helper.nodeText(node, "info").ifPresent(builder::info);
            return builder.build();
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

        public Optional<String> info() {
            return Optional.ofNullable(info);
        }

    }

    public static class Builder {

        private String employmentId;
        private String personalIdentityNumber;
        private LocalDate date;
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

        public Builder date(LocalDate date) {
            this.date = date;
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

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Balance build() {
            return new Balance(employmentId,
                    personalIdentityNumber,
                    date,
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
                    info);
        }
    }
}
