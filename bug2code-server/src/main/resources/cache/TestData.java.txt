package ru.vaganov.bug2core_testframework.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestData {

    private final ObjectProvider<DepositorBuilder> depositorBuilders;

    public DepositorBuilder depositor() {
        return depositorBuilders.getObject();
    }

}
