package com.demo.customer.archunit.rules;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

public class PlantUmlRules {

    public static final String PLANT_UML_FILE = "src/test/resources/plantuml-example.puml";

    @ArchTest
    public static final ArchRule PLANT_UML = classes()
                .should(adhereToPlantUmlDiagram(PLANT_UML_FILE, consideringOnlyDependenciesInDiagram()));

}
