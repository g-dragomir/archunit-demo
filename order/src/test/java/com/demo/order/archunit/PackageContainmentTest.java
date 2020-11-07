package com.demo.order.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.order")
public class PackageContainmentTest {

    @ArchTest
    public void entitiesShouldResideInEntityPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..domain.entity..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void dataAccessObjectsShouldResideInDaoPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Dao")
                .should().resideInAPackage("..dao..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void servicesShouldResideInServicePackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Service")
                .should().resideInAPackage("..service..")
                .orShould().beAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersShouldResideInRestPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Resource")
                .should().resideInAPackage("..rest..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void exceptionsShouldResideInExceptionPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Exception")
                .should().resideInAPackage("..exception..");

        rule.check(importedClasses);
    }
}
