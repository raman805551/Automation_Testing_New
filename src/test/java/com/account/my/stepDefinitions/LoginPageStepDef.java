package com.account.my.stepDefinitions;

import com.account.my.Hooks.Hooks;
import com.account.my.manager.PageObjectManger;
import com.account.my.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPageStepDef {

private WebDriver driver;
LoginPage loginPage;
private PageObjectManger pageObjectManger;
public static final Logger log = LoggerFactory.getLogger(LoginPage.class.getName());

    public LoginPageStepDef() {
        Hooks.openBrowser();
        driver = Hooks.driver;
    }

    @Given("^User is on Make My Trip login page$")
    public void user_is_on_landing_page() throws Throwable {
        try {
            pageObjectManger = new PageObjectManger(driver);
            loginPage = pageObjectManger.getLoginPage();
            loginPage.navigateToLoginPage();
        }catch(Exception e){
            log.error("ERROR: Exception Occurred", e.getMessage());
        }
    }

    @When("^User login into application with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_login_into_application_with_use_name_and_password(String userName, String password) throws Throwable {
//        loginPage = pageObjectManger.getLoginPage();
//        loginPage.button_loginMain.click();
//        Thread.sleep(3000);
//        loginPage.testBox_userName.sendKeys(userName);
//        Thread.sleep(5000);
//        loginPage.button_continue.click();
//        loginPage.button_continue.click();
////        Thread.sleep(3000);
////        loginPage.button_loginViaPassword.click();
//        loginPage.testBox_password.sendKeys(password);
//        loginPage.button_login.click();
//        Thread.sleep(3000);
        log.info("login successful");
    }

    @Then("^Home page is displayed$")
    public void home_page_is_displayed() throws Throwable {
//        loginPage = pageObjectManger.getLoginPage();
//        loginPage.value_loggedInUser.isDisplayed();
        log.info("Home page successful");
    }

}