package com.account.my.pageObjects;

import com.account.my.common.CommonConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class LandingPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class.getName());
    private HashMap<String, String> li_hash_map = new HashMap<>();
    private SoftAssert sa = new SoftAssert();

    public LandingPage(WebDriver driver){
        this.driver=driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, CommonConstants.WAIT_INSECONDS);
        PageFactory.initElements(factory, this);
    }


    @FindBy(how = How.XPATH, using = "//*[@id='identifierId']")
    public WebElement testBox_userName;

    @FindBy(how = How.XPATH, using = "//*[text()='Next']")
    public WebElement button_next;

    @FindBy(how = How.XPATH, using = "//*[@name='password']")
    public WebElement testBox_password;

    @FindBy(how = How.XPATH, using = "//*[@id='passwordNext']")
    public WebElement button_passwordNext;

}
