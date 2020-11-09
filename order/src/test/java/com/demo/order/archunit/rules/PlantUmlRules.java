package com.demo.order.archunit.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

public class PlantUmlRules {

    @ArchTest
    public void plantUml(JavaClasses importedClasses) {

        String plantUmlFile = "src/test/resources/plantuml-example.puml";

        ArchRule rule = classes()
                .should(adhereToPlantUmlDiagram(plantUmlFile, consideringOnlyDependenciesInDiagram()));

        rule.check(importedClasses);
    }
}
