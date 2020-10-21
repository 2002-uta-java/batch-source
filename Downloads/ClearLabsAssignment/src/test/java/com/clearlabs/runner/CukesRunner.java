package com.clearlabs.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "src/test/java/com/clearlabs/step_definitions",
        dryRun = false,
        tags = "@positiveLogin"
)

public class CukesRunner {
}
