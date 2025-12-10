package ru.vaganov.bug2core_testframework.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    private Contribution contribution;
    private BigDecimal contributionSum;
    private String contractNum;
    private LocalDate contactDate;
}
