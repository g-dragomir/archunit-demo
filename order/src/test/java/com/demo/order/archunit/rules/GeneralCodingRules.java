package com.demo.order.archunit.rules;

import com.demo.order.archunit.custom.Conditions;
import com.demo.order.legacy.util.DateUtils;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public class GeneralCodingRules {

    @ArchIgnore
    @ArchTest
    ArchRule shouldNotUseJodaTime = com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME
            .because("Java 8 version is used.");

    @ArchTest
    ArchRule shouldNotUseJavaUtilLogging = com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .because("we are using slf4j-api library.");

    @ArchTest
    public void dateUtilsClassShouldHaveAllTheFieldEndingWithDate(JavaClasses importedClasses) {

        ArchRule rule = fields()
                .that().areDeclaredIn(DateUtils.class)
                .should(Conditions.haveANameEndingWith("Date"));

        rule.check(importedClasses);
    }

}
