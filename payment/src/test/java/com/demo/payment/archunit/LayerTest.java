package com.demo.payment.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.payment")
public class LayerTest {

    @ArchTest
    public void layeredArchitectureForPaymentModule(JavaClasses importedClasses) {

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
