package ru.vaganov.bug2core_testframework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@SpringBootTest
public class TestDataExample {

    @Autowired
    private TestData testData;


    /**
     * Один вкладчик со снилс=000-000-000 01, инн=01231203120312, в 2025 году имеет один договор от 2025-01-01.
     * Номер догвора=Договор1, суммой вклада=10000
     */
    @Test
    void exampleTest1() {
        var depositor = testData.depositor()
                .snils("000-000-000 01")
                .inn("01231203120312")
                .addContribution(dc -> dc
                        .fiscalYear(2025)
                        .addContract(con -> con
                                .date(LocalDate.parse("2025-01-01"))
                                .num("Договор1")
                                .contribution(new BigDecimal("10000.00"))
                                .build()
                        ).build()
                ).build();

        Assertions.assertNotNull(depositor);
    }


    /**
     * Вкладчик имеет вклады в 2025 и 2024 годах.
     * В 2025 году у него два договора: на сумму 10000 и 5000.
     * В 2024 году у него один договор: на сумму 100.
     */
    @Test
    void exampleTest2() {
        var depositor = testData.depositor()
                .addContribution(dc -> dc
                        .fiscalYear(2025)
                        .addContract(con -> con
                                .contribution(new BigDecimal("10000.00"))
                                .num("Договор1")
                                .build()
                        )
                        .addContract(con -> con
                                .contribution(new BigDecimal("5000.00"))
                                .num("Договор2")
                                .build()
                        ).build()
                )
                .addContribution(dc -> dc
                        .fiscalYear(2024)
                        .addContract(con -> con
                                .contribution(new BigDecimal("100.00"))
                                .num("Договор3")
                                .build()
                        ).build()
                )
                .build();

        Assertions.assertNotNull(depositor);
    }
}
