package com.demo.customer.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.demo.customer", importOptions = {ImportOption.DoNotIncludeTests.class})
public class GeneralCodingRulesTest {

    @ArchIgnore
    @ArchTest
    ArchRule shouldNotUseJodaTime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME
            .because("Java 8 version is used.");

    @ArchTest
    ArchRule shouldNotUseJavaUtilLogging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .because("we are using slf4j-api library.");

}
