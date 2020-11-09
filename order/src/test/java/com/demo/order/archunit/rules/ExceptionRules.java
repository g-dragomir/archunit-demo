package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

public class ExceptionRules {

    @ArchTest
    public void exceptionsShouldResideInExceptionPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Exception")
                .should().resideInAPackage("..exception..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void exceptionsShouldHaveConstructor(JavaClasses importedClasses) {

        ArchRule rule = constructors()
                .that().areDeclaredInClassesThat().resideInAPackage("..exception..")
                .should().haveRawParameterTypes(String.class);

        rule.check(importedClasses);
    }
}
