package ru.vaganov.bug2core_testframework.utils;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import ru.vaganov.bug2core_testframework.domain.Contract;
import ru.vaganov.bug2core_testframework.domain.Contribution;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributionBuilder {

    private final ObjectProvider<ContractBuilder> contractBuilders;
    private final Contribution contribution;
    private final List<Contract> contracts;

    public ContributionBuilder(ObjectProvider<ContractBuilder> contractBuilders) {
        this.contractBuilders = contractBuilders;
        contracts = new ArrayList<>();
        contribution = Contribution.builder()
                .fiscalYear(2025)
                .build();
    }

    /**
     * Отчетный год
     */
    public ContributionBuilder fiscalYear(Integer fiscalYear) {
        contribution.setFiscalYear(fiscalYear);
        return this;
    }

    public ContributionBuilder addContract(Function<ContractBuilder, Contract> creator) {
        var builder = contractBuilders.getObject();
        var contracts = creator.apply(builder);
        this.contracts.add(contracts);
        return this;
    }

    public Contribution build() {
        contribution.setContracts(contracts);
        contracts.forEach(c -> c.setContribution(contribution));
        return contribution;
    }
}
