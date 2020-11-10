package com.demo.product.archunit;

import com.demo.product.ProductBase;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class AnnotationTest {

    @Test
    public void entitiesShouldBeAnnotatedWithEntityAndTable() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.demo.product");

        ArchRule rule = classes().that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class)
                .andShould().beAnnotatedWith(Table.class);

        rule.check(importedClasses);
    }

    @Test
    public void allTheClassesFromCoreShouldBeDeprecated() {

        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.demo.product");

        ArchRule rule = classes().that().resideInAPackage("..core..")
                .should().beAnnotatedWith(Deprecated.class)
                .because("this is a legacy package and the new classes should not be added here.");

        rule.check(importedClasses);
    }

    @Test
    public void controllersShouldUseSpringWeb() {

        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.demo.product");

        ArchRule rule = classes().that().resideInAPackage("..rest..")
                .should().beAnnotatedWith(RestController.class)
                .andShould().beAnnotatedWith(RequestMapping.class)
                .because("spring-web is used.");

        rule.check(importedClasses);
    }

    @Test
    public void controllersShouldHaveSwaggerDocumentation() {

        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackagesOf(ProductBase.class);

        ArchRule rule = classes().that().resideInAPackage("..rest..")
                .should().beAnnotatedWith(Api.class)
                .because("the APIs should have Swagger documentation.");

        rule.check(importedClasses);
    }
}
