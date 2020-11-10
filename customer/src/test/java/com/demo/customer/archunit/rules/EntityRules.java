package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.members;

public class EntityRules {

    @ArchTest
    public static final ArchRule ENTITIES_SHOULD_RESIDE_IN_ENTITY_PACKAGES = classes().that()
            .areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..domain.entity..");

    @ArchTest
    public static final ArchRule ENTITIES_SHOULD_ONLY_BE_ACCESSED_BY_DAO = classes().that()
            .resideInAPackage("..entity..")
            .should().onlyHaveDependentClassesThat().resideInAnyPackage("..dao..", "..entity..");

    @ArchTest
    public static final ArchRule ENTITIES_SHOULD_BE_ANNOTATED_WITH_ENTITY_AND_TABLE = classes().that()
            .resideInAPackage("..entity..")
            .should().beAnnotatedWith(Entity.class)
            .andShould().beAnnotatedWith(Table.class);

    @ArchTest
    public static final ArchRule ENTITIES_SHOULD_HAVE_A_GENERATED_ID = members().that()
            .haveName("id")
            .and().areDeclaredInClassesThat().areAnnotatedWith(Entity.class)
            .should().beAnnotatedWith(Id.class)
            .andShould().beAnnotatedWith(GeneratedValue.class)
            .andShould().beAnnotatedWith(Column.class);
}
