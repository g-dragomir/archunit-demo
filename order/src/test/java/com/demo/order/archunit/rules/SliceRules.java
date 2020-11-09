package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static com.tngtech.archunit.library.freeze.FreezingArchRule.freeze;

public class SliceRules {

    @ArchTest
    public void noCyclesInSlices(JavaClasses importedClasses) {

        ArchRule rule = slices()
                .matching("com.demo.order.(*)..")
                .should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @ArchTest
    @ArchIgnore
    public void noDependenciesBetweenLegacySlices(JavaClasses importedClasses) {

        ArchRule rule = slices()
                .matching("com.demo.order.legacy(*)..")
                .should().notDependOnEachOther();

        ArchRule frozenRule = freeze(rule);

        frozenRule.check(importedClasses);
    }
}
