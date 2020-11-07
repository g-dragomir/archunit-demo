package com.demo.product.archunit;

import com.demo.product.archunit.custom.CustomPredicates;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.members;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.product", importOptions = {ImportOption.DoNotIncludeTests.class})
public class ClassContainmentTest {

    @ArchTest
    public void publicMethodsOfControllersShouldBeAnnotatedWithRequestMapping(JavaClasses importedClasses) {

        ArchRule rule = methods()
                .that().arePublic()
                .and().areDeclaredInClassesThat().resideInAPackage("..rest..")
                .should().beAnnotatedWith(RequestMapping.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void entitiesShouldHaveAGeneratedId(JavaClasses importedClasses) {

        ArchRule rule = members()
                .that().haveName("id")
                .and().areDeclaredInClassesThat().areAnnotatedWith(Entity.class)
                .should().beAnnotatedWith(Id.class)
                .andShould().beAnnotatedWith(GeneratedValue.class)
                .andShould().beAnnotatedWith(Column.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void exceptionsShouldHaveConstructor(JavaClasses importedClasses) {

        ArchRule rule = constructors()
                .that().areDeclaredInClassesThat().resideInAPackage("..exception..")
                .should().haveRawParameterTypes(String.class);

        rule.check(importedClasses);
    }

    @ArchTest
    public void featureFlagsShouldBeDefinedInServices(JavaClasses importedClasses) {

        ArchRule rule = fields()
                .that(CustomPredicates.areFeatureFlags())
                .should().beDeclaredInClassesThat().resideInAPackage("..service..");

        rule.check(importedClasses);
    }
}
