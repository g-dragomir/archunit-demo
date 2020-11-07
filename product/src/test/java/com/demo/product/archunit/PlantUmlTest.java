package com.demo.product.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.product")
public class PlantUmlTest {

    @ArchTest
    public void plantUml(JavaClasses importedClasses) {

        String plantUmlFile = "src/test/resources/plantuml-example.puml";

        ArchRule rule = classes()
                .should(adhereToPlantUmlDiagram(plantUmlFile, consideringOnlyDependenciesInDiagram()));

        rule.check(importedClasses);
    }
}
