package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class LegacyRules {

    @ArchTest
    public void allTheLegacyClassesShouldBeDeprecated(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..legacy..")
                .should().beAnnotatedWith(Deprecated.class)
                .because("this is a legacy package and the new classes should not be added here.");

        rule.check(importedClasses);
    }
}
