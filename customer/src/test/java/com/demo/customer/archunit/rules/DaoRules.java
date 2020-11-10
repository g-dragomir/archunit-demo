package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class DaoRules {

    @ArchTest
    public static final ArchRule DATA_ACCESS_OBJECTS_SHOULD_RESIDE_IN_DAO_PACKAGES = classes().that()
            .haveNameMatching(".*Dao")
            .should().resideInAPackage("..dao..");

}
