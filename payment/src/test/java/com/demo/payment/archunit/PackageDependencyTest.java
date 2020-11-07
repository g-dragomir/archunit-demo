package com.demo.payment.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.junit.CacheMode;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.payment", cacheMode = CacheMode.PER_CLASS)
public class PackageDependencyTest {

    @ArchTest
    public void controllersShouldNotAccessTheDomainAndDao(JavaClasses importedClasses) {

        ArchRule rule = noClasses().that().resideInAPackage("..rest..")
                .should().dependOnClassesThat().resideInAnyPackage("..dao..", "..domain..")
                .because("The business logic should be exposed through the services.");

        rule.check(importedClasses);
    }

    @ArchTest
    public void entitiesShouldOnlyBeAccessedByDao(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..entity..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..dao..", "..entity..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void servicesShouldNotAccessControllers(JavaClasses importedClasses) {

        ArchRule rule = noClasses().that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..rest..")
                .as("Services should not access the Controllers");

        rule.check(importedClasses);
    }
}
