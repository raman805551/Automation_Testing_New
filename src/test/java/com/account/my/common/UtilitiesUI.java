package com.account.my.common;

import com.account.my.Hooks.Hooks;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilitiesUI {

    private static WebDriver driver;
    public static Scenario scenarioMsg;
    private static final Logger logger = LoggerFactory.getLogger(UtilitiesUI.class.getName());


    public static void takeScreenshotAfterEveryStep() {
        try {
            driver = Hooks.driver;
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenarioMsg.embed(screenshot, "image/png");
            logger.info("TAGS execution is in progress:" + scenarioMsg.getSourceTagNames());
        } catch (WebDriverException somePlatformsdontSuportScreenshots) {
            scenarioMsg.write(somePlatformsdontSuportScreenshots.getMessage());
            logger.info("TAGS execution is in progress:" + scenarioMsg.getSourceTagNames());
        }
    }


    public static void addScenarioDataToEveryStep(String scenarioData) {
        try {
            scenarioMsg.write(scenarioData);
        }catch(Exception e){
            logger.info("error occurred inside addScenarioDataToEveryStep method");
        }
    }
}
