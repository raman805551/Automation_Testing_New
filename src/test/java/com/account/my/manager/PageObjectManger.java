package com.account.my.manager;

import com.account.my.pageObjects.LandingPage;
import com.account.my.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManger {

    private WebDriver driver;
    private LoginPage loginPage;
    private LandingPage landingPage;

    public PageObjectManger(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public LandingPage getLandingPage() {
        return (landingPage==null) ? landingPage = new LandingPage(driver) : landingPage;
    }
}
