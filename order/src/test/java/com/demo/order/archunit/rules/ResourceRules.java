package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ResourceRules {

    @ArchTest
    public void controllersShouldResideInRestPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().haveNameMatching(".*Resource")
                .should().resideInAPackage("..rest..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersShouldNotAccessTheDomainAndDao(JavaClasses importedClasses) {

        ArchRule rule = noClasses().that().resideInAPackage("..rest..")
                .should().dependOnClassesThat().resideInAnyPackage("..dao..", "..domain..")
                .because("The business logic should be exposed through the services.");

        rule.check(importedClasses);
    }

    @ArchTest
    public void controllersShouldUseSpringWebAnnotations(JavaClasses importedClasses) {

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

    @ArchTest
    public void publicMethodsOfControllersShouldBeAnnotatedWithRequestMapping(JavaClasses importedClasses) {

        ArchRule rule = methods()
                .that().arePublic()
                .and().areDeclaredInClassesThat().resideInAPackage("..rest..")
                .should().beAnnotatedWith(RequestMapping.class);

        rule.check(importedClasses);
    }
}
