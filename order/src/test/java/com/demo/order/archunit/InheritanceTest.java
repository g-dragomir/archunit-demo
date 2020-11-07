package com.demo.order.archunit;

import com.demo.order.service.OrderService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.order")
public class InheritanceTest {

    @ArchTest
    public void orderServiceShouldBeImplementedWithASpecificName(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(OrderService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }
}
