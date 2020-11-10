package com.demo.customer.archunit.rules;

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
    public static final ArchRule CONTROLLERS_SHOULD_RESIDE_IN_REST_PACKAGES = classes().that()
            .haveNameMatching(".*Resource")
            .should().resideInAPackage("..rest..");

    @ArchTest
    public static final ArchRule CONTROLLERS_SHOULD_NOT_ACCESS_THE_DOMAIN_AND_DAO = noClasses().that()
            .resideInAPackage("..rest..")
            .should().dependOnClassesThat().resideInAnyPackage("..dao..", "..domain..")
            .because("The business logic should be exposed through the services.");

    @ArchTest
    public static final ArchRule CONTROLLERS_SHOULD_USE_SPRING_WEB_ANNOTATIONS = classes().that()
            .resideInAPackage("..rest..")
            .should().beAnnotatedWith(RestController.class)
            .andShould().beAnnotatedWith(RequestMapping.class)
            .because("spring-web is used.");

    @ArchTest
    public static final ArchRule CONTROLLERS_SHOULD_HAVE_SWAGGER_DOCUMENTATION = classes().that().
            resideInAPackage("..rest..")
            .should().beAnnotatedWith(Api.class)
            .because("the APIs should have Swagger documentation.");

    @ArchTest
    public static final ArchRule PUBLIC_METHODS_OF_CONTROLLERS_SHOULD_BE_ANNOTATED_WITH_REQUEST_MAPPING = methods()
            .that().arePublic()
            .and().areDeclaredInClassesThat().resideInAPackage("..rest..")
            .should().beAnnotatedWith(RequestMapping.class);

}
