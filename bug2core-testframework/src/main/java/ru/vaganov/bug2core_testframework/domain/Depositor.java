package ru.vaganov.bug2core_testframework.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Depositor {
    private String snils;
    private String inn;
    private List<Contribution> contributions;
}
