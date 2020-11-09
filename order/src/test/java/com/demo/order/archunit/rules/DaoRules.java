package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class DaoRules {

    @ArchTest
    public void dataAccessObjectsShouldResideInDaoPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Dao")
                .should().resideInAPackage("..dao..");

        rule.check(importedClasses);
    }
}
