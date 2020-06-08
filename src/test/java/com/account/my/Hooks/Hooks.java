package com.account.my.Hooks;
import com.account.my.common.CommonConstants;
import com.account.my.common.UtilitiesUI;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks {
    public static WebDriver driver;
    public static Scenario scenario;
    public static final Logger log = LoggerFactory.getLogger(Hooks.class);
    public static String randomBrowser;

    public static void setRandomBrowser(String randomBrowser) {
        Hooks.randomBrowser = randomBrowser;
    }

    @Before
    public void setUpScenario(Scenario scenario) {
        UtilitiesUI.scenarioMsg = scenario;
    }

    public static void openBrowser() {
        log.info("Called openBrowser");
        String browser = null;
        if (randomBrowser == null) {
            browser = "chrome";
        } else {
            browser = randomBrowser;
            log.info("Browser Selected is from feature" + browser);
        }

        switch (browser) {
            case CommonConstants.CHROME:
                log.info("Browser selected is:" + browser);
                initChromeDriver();
                break;

            case CommonConstants.FIREFOX:
                log.info("Browser selected is:" + browser);
                // initSafariDriver();
                break;

            case CommonConstants.IE:
                log.info("Browser selected is:" + browser);
                // initIEDriver();
                break;

            default:
                initChromeDriver();
                break;
        }
        log.info("Opening browser" + browser);

    }

    public static void initChromeDriver() {
        System.setProperty(CommonConstants.CHROME_DRIVER_PROPERTY, CommonConstants.CHROME_DRIVER_DIRECTORY);
        ChromeOptions chromeOption = new ChromeOptions();
        String loggedInUser = System.getProperty("user.name");
        log.info("Opening browser");
//    String chromeBrowserLoc = "D:\\Users\\"+loggedInUser+CommonConstants.CHROME_BINARY_LOCATION;
//    if(!FileUtilities.verifyFile(chromeBrowserLoc))
//    { }
        String chromeBrowserLoc = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        chromeOption.setBinary(chromeBrowserLoc);
        chromeOption.addArguments("disable-extensions");
        chromeOption.addArguments("no-sandbox");
        chromeOption.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(chromeOption);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        log.info("Opening browser chrome and maximize");
    }


    @After
    public void embedScreenshotAndUpdateResult() {
        if (scenario.isFailed()) {
            try {
                if (driver != null) {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                }
            } catch (WebDriverException screenshotNotSupported) {
                System.err.println(screenshotNotSupported.getMessage());
            }
        }
        if (driver != null){
            driver.quit();
    }

}
}

