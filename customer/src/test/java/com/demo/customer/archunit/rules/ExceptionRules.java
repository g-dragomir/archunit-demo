package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

public class ExceptionRules {

    @ArchTest
    public static final ArchRule EXCEPTIONS_SHOULD_RESIDE_IN_EXCEPTION_PACKAGES = classes().that()
            .haveNameMatching(".*Exception")
            .should().resideInAPackage("..exception..");

    @ArchTest
    public static final ArchRule EXCEPTIONS_SHOULD_HAVE_CONSTRUCTOR = constructors().that()
            .areDeclaredInClassesThat().resideInAPackage("..exception..")
            .should().haveRawParameterTypes(String.class);
}
