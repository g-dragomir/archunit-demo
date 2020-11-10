package com.demo.customer.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static com.tngtech.archunit.library.freeze.FreezingArchRule.freeze;

public class SliceRules {

    @ArchTest
    public static final ArchRule NO_CYCLES_IN_SLICES = slices()
                .matching("com.demo.customer.(*)..")
                .should().beFreeOfCycles();

    @ArchTest
//    @ArchIgnore
    public void noDependenciesBetweenSlices(JavaClasses importedClasses) {

        ArchRule rule = slices()
                .matching("com.demo.customer.(*)..")
                .should().notDependOnEachOther();

        ArchRule frozenRule = freeze(rule);

        frozenRule.check(importedClasses);
    }
}
