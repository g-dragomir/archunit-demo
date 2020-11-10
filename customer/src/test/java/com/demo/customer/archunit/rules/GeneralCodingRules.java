package com.demo.customer.archunit.rules;

import com.demo.customer.archunit.custom.Predicates;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public class GeneralCodingRules {

    @ArchIgnore
    @ArchTest
    public static final ArchRule  SHOULD_NOT_USE_JODA_TIME = com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME
            .because("Java 8 version is used.");

    @ArchTest
    public static final ArchRule  SHOULD_NOT_USE_JAVA_UTIL_LOGGING = com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .because("we are using slf4j-api library.");

    @ArchTest
    public static final ArchRule FEATURE_FLAGS_SHOULD_BE_DEFINED_ONLY_IN_SERVICES = fields()
            .that(Predicates.areFeatureFlags())
            .should().beDeclaredInClassesThat().resideInAPackage("..service..")
            .because("feature flags are allowed only in services.");

}
