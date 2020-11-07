package com.demo.payment.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.payment")
public class ClassDependencyTest {

    @ArchTest
    public void entitiesShouldOnlyBeAccessedByDao(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveSimpleName("PaymentEvent")
                .should().onlyBeAccessed().byClassesThat().haveNameMatching("Event");

        rule.check(importedClasses);
    }
}
