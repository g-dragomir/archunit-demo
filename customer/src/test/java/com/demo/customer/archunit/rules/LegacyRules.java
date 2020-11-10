package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class LegacyRules {

    @ArchTest
    public static final ArchRule  ALL_THE_LEGACY_CLASSES_SHOULD_BE_DEPRECATED = classes().that()
            .resideInAPackage("..legacy..")
            .should().beAnnotatedWith(Deprecated.class)
            .because("this is a legacy package and the new classes should not be added here.");
}
