package com.account.my.pageObjects;

import com.account.my.common.AbstractStepDef;
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

public class LoginPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class.getName());
    private HashMap<String, String> li_hash_map = new HashMap<>();
    private SoftAssert sa = new SoftAssert();

    public LoginPage(WebDriver driver){
        this.driver=driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, CommonConstants.WAIT_INSECONDS);
        PageFactory.initElements(factory, this);
    }

//Elements
    @FindBy(how = How.XPATH, using = "//*[@id='username']")
    public WebElement testBox_userName;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='continueBtn']")
    public WebElement button_continue;

    @FindBy(how = How.XPATH, using = "//*[text()='or Login via Password']")
    public WebElement button_loginViaPassword;

    @FindBy(how = How.XPATH, using = "//*[@id='password']")
    public WebElement testBox_password;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='login']")
    public WebElement button_login;

    @FindBy(how = How.XPATH, using = "//*[text()=' Login or Create Account']")
    public WebElement button_loginMain;

    @FindBy(how = How.XPATH, using = "//*[@data-cy='loggedInUser']")
    public WebElement value_loggedInUser;

//Methods

public void navigateToLoginPage()
{
    String url = AbstractStepDef.getUIUrlByEnv();
    //String url = "https://www.makemytrip.com/";
    driver.get(url);
}
}
