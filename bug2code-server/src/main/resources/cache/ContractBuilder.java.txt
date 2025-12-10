package ru.vaganov.bug2core_testframework.utils;

import org.springframework.stereotype.Component;
import ru.vaganov.bug2core_testframework.domain.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ContractBuilder {

    private final Contract contract;

    public ContractBuilder() {
        this.contract = Contract.builder()
                .contractNum("Номер")
                .contactDate(LocalDate.parse("2024-01-01"))
                .contributionSum(new BigDecimal("10000.00"))
                .build();
    }


    /**
     * Номер договора
     */
    public ContractBuilder num(String num) {
        contract.setContractNum(num);
        return this;
    }

    /**
     * Дата договора
     */
    public ContractBuilder date(LocalDate date) {
        contract.setContactDate(date);
        return this;
    }

    /**
     * Сумма вклада
     */
    public ContractBuilder contribution(BigDecimal contributionSum) {
        contract.setContributionSum(contributionSum);
        return this;
    }


    public Contract build() {
        return contract;
    }
}
