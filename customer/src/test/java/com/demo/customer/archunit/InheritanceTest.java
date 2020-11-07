package com.demo.customer.archunit;

import com.demo.customer.service.CustomerService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.customer")
public class InheritanceTest {

    @ArchTest
    public void customerServiceShouldBeImplementedWithASpecificName(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(CustomerService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }
}
