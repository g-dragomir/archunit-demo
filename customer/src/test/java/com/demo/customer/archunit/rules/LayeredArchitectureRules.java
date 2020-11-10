package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayeredArchitectureRules {

    @ArchTest
    public static final ArchRule  LAYERED_ARCHITECTURE_FOR_CUSTOMER_MODULE = layeredArchitecture()
                .layer("Controller").definedBy("..rest..")
                .layer("Service").definedBy("..service..")
                .layer("DataAccessObject").definedBy("..dao..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("DataAccessObject").mayOnlyBeAccessedByLayers("Service");
}
