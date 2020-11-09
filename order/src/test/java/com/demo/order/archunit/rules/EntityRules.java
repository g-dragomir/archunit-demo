package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
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
    public void entitiesShouldResideInEntityPackages(JavaClasses importedClasses) {

        ArchRule rule = classes().that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..domain.entity..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void entitiesShouldOnlyBeAccessedByDao(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..entity..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..dao..", "..entity..");

        rule.check(importedClasses);
    }

    @ArchTest
    public void entitiesShouldBeAnnotatedWithEntityAndTable(JavaClasses importedClasses) {

        ArchRule rule = classes().that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class)
                .andShould().beAnnotatedWith(Table.class);

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
}
