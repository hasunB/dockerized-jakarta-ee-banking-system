package org.example.ee.account;

import jakarta.inject.Inject;
import org.example.ee.core.service.TestService;

public class TestServiceBean implements TestService {

    @Inject
    private InterestCalculator calculator;

    @Override
    public void testInterest() {
        calculator.applyDailyInterest();
    }
}
