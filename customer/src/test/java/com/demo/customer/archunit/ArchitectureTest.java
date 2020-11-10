package com.demo.customer.archunit;

import com.demo.customer.archunit.rules.DaoRules;
import com.demo.customer.archunit.rules.EntityRules;
import com.demo.customer.archunit.rules.ExceptionRules;
import com.demo.customer.archunit.rules.GeneralCodingRules;
import com.demo.customer.archunit.rules.LayeredArchitectureRules;
import com.demo.customer.archunit.rules.LegacyRules;
import com.demo.customer.archunit.rules.PlantUmlRules;
import com.demo.customer.archunit.rules.ResourceRules;
import com.demo.customer.archunit.rules.ServiceRules;
import com.demo.customer.archunit.rules.SliceRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.customer", importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

    @ArchTest
    public static final ArchRules DAO_RULES = ArchRules.in(DaoRules.class);

    @ArchTest
    public static final ArchRules ENTITY_RULES = ArchRules.in(EntityRules.class);

    @ArchTest
    public static final ArchRules EXCEPTION_RULES = ArchRules.in(ExceptionRules.class);

    @ArchTest
    public static final ArchRules GENERAL_CODING_RULES = ArchRules.in(GeneralCodingRules.class);

    @ArchTest
    public static final ArchRules LAYERED_ARCHITECTURE_RULES = ArchRules.in(LayeredArchitectureRules.class);

    @ArchTest
    public static final ArchRules LEGACY_RULES = ArchRules.in(LegacyRules.class);

    @ArchTest
    public static final ArchRules PLANT_UML_RULES = ArchRules.in(PlantUmlRules.class);

    @ArchTest
    public static final ArchRules CONTROLLER_RULES = ArchRules.in(ResourceRules.class);

    @ArchTest
    public static final ArchRules SERVICE_RULES = ArchRules.in(ServiceRules.class);

    @ArchTest
    public static final ArchRules SLICE_RULES = ArchRules.in(SliceRules.class);

}
