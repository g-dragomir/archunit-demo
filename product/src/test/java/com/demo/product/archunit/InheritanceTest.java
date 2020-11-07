package com.demo.product.archunit;

import com.demo.product.service.ProductService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.product")
public class InheritanceTest {

    @ArchTest
    public void productServiceShouldBeImplementedWithASpecificName(JavaClasses importedClasses) {

        ArchRule rule = classes()
                .that().implement(ProductService.class)
                .should().haveSimpleNameEndingWith("ServiceImpl");

        rule.check(importedClasses);
    }
}
