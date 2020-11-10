package com.demo.customer.archunit.rules;

import com.demo.customer.service.CustomerService;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ServiceRules {

    @ArchTest
    public static final ArchRule SERVICES_SHOULD_RESIDE_IN_SERVICE_PACKAGES = classes().that()
            .haveNameMatching(".*Service")
            .should().resideInAPackage("..service..")
            .orShould().beAnnotatedWith(Deprecated.class);

    @ArchTest
    public static final ArchRule SERVICES_SHOULD_NOT_ACCESS_CONTROLLERS = noClasses().that()
            .resideInAPackage("..service..")
            .should().accessClassesThat().resideInAPackage("..rest..")
            .as("Services should not access the Controllers");

    @ArchTest
    public static final ArchRule CUSTOMER_SERVICE_SHOULD_BE_IMPLEMENTED_WITH_A_SPECIFIC_NAME = classes()
                .that().implement(CustomerService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");
}
