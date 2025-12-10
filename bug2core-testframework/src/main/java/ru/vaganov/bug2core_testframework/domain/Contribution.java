package ru.vaganov.bug2core_testframework.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contribution {
    private Depositor depositor;

    private Integer fiscalYear;

    private List<Contract> contracts;
}
