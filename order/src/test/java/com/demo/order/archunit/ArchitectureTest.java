package com.demo.order.archunit;

import com.demo.order.archunit.rules.DaoRules;
import com.demo.order.archunit.rules.EntityRules;
import com.demo.order.archunit.rules.ExceptionRules;
import com.demo.order.archunit.rules.GeneralCodingRules;
import com.demo.order.archunit.rules.LayeredArchitectureRules;
import com.demo.order.archunit.rules.LegacyRules;
import com.demo.order.archunit.rules.PlantUmlRules;
import com.demo.order.archunit.rules.ResourceRules;
import com.demo.order.archunit.rules.ServiceRules;
import com.demo.order.archunit.rules.SliceRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.order", importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

    @ArchTest
    public static final ArchRules daoRules = ArchRules.in(DaoRules.class);

    @ArchTest
    public static final ArchRules entityRules = ArchRules.in(EntityRules.class);

    @ArchTest
    public static final ArchRules exceptionRules = ArchRules.in(ExceptionRules.class);

    @ArchTest
    public static final ArchRules generalCodingRules = ArchRules.in(GeneralCodingRules.class);

    @ArchTest
    public static final ArchRules layeredArchitectureRules = ArchRules.in(LayeredArchitectureRules.class);

    @ArchTest
    public static final ArchRules legacyRules = ArchRules.in(LegacyRules.class);

    @ArchTest
    public static final ArchRules plantUmlRules = ArchRules.in(PlantUmlRules.class);

    @ArchTest
    public static final ArchRules controllerRules = ArchRules.in(ResourceRules.class);

    @ArchTest
    public static final ArchRules serviceRules = ArchRules.in(ServiceRules.class);

    @ArchTest
    public static final ArchRules sliceRules = ArchRules.in(SliceRules.class);

}
