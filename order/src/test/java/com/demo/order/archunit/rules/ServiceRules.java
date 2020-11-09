package com.demo.order.archunit.rules;

import com.demo.order.archunit.custom.Predicates;
import com.demo.order.service.OrderService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ServiceRules {

    @ArchTest
    public void servicesShouldResideInServicePackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Service")
                .should().resideInAPackage("..service..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void servicesShouldNotAccessControllers(JavaClasses importedClasses) {

        ArchRule rule = noClasses().that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..rest..")
                .as("Services should not access the Controllers");

        rule.check(importedClasses);
    }

    @ArchTest
    public void featureFlagsShouldBeDefinedOnlyInServices(JavaClasses importedClasses) {

        ArchRule rule = fields()
                .that(Predicates.areFeatureFlags())
                .should().beDeclaredInClassesThat().resideInAPackage("..service..")
                .because("feature flags are allowed only in services. This should be added as a GeneralCodingRule!");

        rule.check(importedClasses);
    }

    @ArchTest
    public void orderServiceShouldBeImplementedWithASpecificName(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(OrderService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }
}
