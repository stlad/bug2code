package ru.vaganov.bug2core_testframework.utils;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import ru.vaganov.bug2core_testframework.domain.Contribution;
import ru.vaganov.bug2core_testframework.domain.Depositor;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepositorBuilder {

    private final ObjectProvider<ContributionBuilder> contributionBuilders;
    private final Depositor depositor;
    private final List<Contribution> contributions;

    public DepositorBuilder(ObjectProvider<ContributionBuilder> contributionBuilders) {
        this.contributionBuilders = contributionBuilders;
        contributions = new ArrayList<>();
        depositor = Depositor.builder()
                .snils("000-000-000 01")
                .inn("00000000001")
                .build();
    }

    /**
     * СНИЛС
     */
    public DepositorBuilder snils(String snils) {
        depositor.setSnils(snils);
        return this;
    }

    /**
     * ИНН
     */
    public DepositorBuilder inn(String inn) {
        depositor.setInn(inn);
        return this;
    }

    public DepositorBuilder addContribution(Function<ContributionBuilder, Contribution> creator) {
        var builder = contributionBuilders.getObject();
        var contribution = creator.apply(builder);
        contributions.add(contribution);
        return this;
    }

    public Depositor build() {
        depositor.setContributions(contributions);
        contributions.forEach(dc -> dc.setDepositor(depositor));
        return depositor;
    }
}
