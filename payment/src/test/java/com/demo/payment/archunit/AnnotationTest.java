package com.demo.payment.archunit;

import com.demo.payment.core.ClearCache;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.annotations.Api;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.payment", importOptions = {ImportOption.DoNotIncludeTests.class})
public class AnnotationTest {

    @ArchTest
    public void entitiesShouldBeAnnotatedWithEntityAndTable(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class)
                .andShould().beAnnotatedWith(Table.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void allTheClassesFromCoreShouldBeDeprecated(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..core..")
                .should().beAnnotatedWith(Deprecated.class)
                .because("this is a legacy package and the new classes should not be added here.");

        rule.check(importedClasses);
    }

    @ArchTest
    public void featuresShouldNotClearCache(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideOutsideOfPackage("..core..")
                .should().notBeAnnotatedWith(ClearCache.class)
                .because("for new features the clear cache is not allowed");

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersShouldUseSpringWeb(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..rest..")
                .should().beAnnotatedWith(RestController.class)
                .andShould().beAnnotatedWith(RequestMapping.class)
                .because("spring-web is used.");

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersShouldHaveSwaggerDocumentation(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..rest..")
                .should().beAnnotatedWith(Api.class)
                .because("the APIs should have Swagger documentation.");

        rule.check(importedClasses);
    }
}
