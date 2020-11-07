package com.demo.payment.archunit;

import com.demo.payment.service.PaymentService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.payment")
public class InheritanceTest {

    @ArchTest
    public void paymentServiceShouldBeImplementedWithASpecificName(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(PaymentService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }
}
