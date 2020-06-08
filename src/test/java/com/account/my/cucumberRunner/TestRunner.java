package com.account.my.cucumberRunner;


import io.cucumber.testng.CucumberOptions;
//import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "json:target/cucumber_reports/cucumber.json"},
        monochrome = true,
        glue = {"com.account.my.stepDefinitions"},
        tags = {"@my-first-login"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
