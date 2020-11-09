package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayeredArchitectureRules {

    @ArchTest
    public void layeredArchitectureForOrderModule(JavaClasses importedClasses) {

        ArchRule rule = layeredArchitecture()
                .layer("Controller").definedBy("..rest..")
                .layer("Service").definedBy("..service..")
                .layer("DataAccessObject").definedBy("..dao..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("DataAccessObject").mayOnlyBeAccessedByLayers("Service");

        rule.check(importedClasses);
    }
}
