package Pages;

import Infra.EnvInfo;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(name="userId")
    private WebElement userIdField;
    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement LoginButton;

    @FindBy(xpath = "//p[contains(text(),'User Id should be more than 5 characters')]")
    private WebElement userIdCharValidation;

    @FindBy(xpath = "//p[contains(text(),'Password should be more than 7 characters')]")
    private WebElement passwordCharValidation;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement invalidCredPopup;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div[2]/div[2]/div[2]/span[1]/input")
    private WebElement rememberMe;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        //This associates/connects the annotated(@FindBy) elements with the actual elements on the web page
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value)
    {
        field.sendKeys(value);
    }

    public String setEnv()
    {
        EnvInfo envInfo = new EnvInfo();
        String env = envInfo.getEnv();
        return env;
    }

    public String setUrl()
    {
        EnvInfo envInfo = new EnvInfo();
        String url = envInfo.getUrl();
        return url;
    }

    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void fillUsername(String userId) throws InterruptedException {
        this.fillField(userIdField,userId);
        Thread.sleep(2000);
    }

    public void fillPassword(String password) throws InterruptedException {
        this.fillField(passwordField,password);
        Thread.sleep(2000);
    }

    public void pressTabKey() throws InterruptedException {
        passwordField.sendKeys(Keys.TAB);
        Thread.sleep(1500);
    }

    public void clickOnLogIn() throws InterruptedException {
        LoginButton.click();
        Thread.sleep(2000);
    }

    public boolean getUserCharLimitError()
    {
        boolean userIdErrorMsg = userIdCharValidation.isDisplayed();
        return userIdErrorMsg;
    }

    public boolean getPassCharLimitError()
    {
        boolean passErrorMsg = passwordCharValidation.isDisplayed();
        return passErrorMsg;
    }

    public String getLoginErrorMsg()
    {
        String ErrorString = userIdCharValidation.getText() + " and " + passwordCharValidation.getText() ;
        return ErrorString;
    }

    public boolean getInvalidCredPopup()
    {
        boolean popup = invalidCredPopup.isDisplayed();
        return popup;
    }

    public String popupValidationMsg(){
        return invalidCredPopup.getText();
    }

    public void clickRememberMe() throws InterruptedException {
        rememberMe.click();
        Thread.sleep(2000);
    }

}
