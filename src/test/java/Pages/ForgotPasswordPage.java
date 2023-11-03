package Pages;

import Infra.EnvInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    private WebDriver driver;
    @FindBy(css = "a[href='/forget-password']")
    private WebElement forgotLink;

    @FindBy(id = "mui-2")
    private WebElement userID;

    @FindBy(id = "mui-2-helper-text")
    private WebElement emptyIDValidation;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement invalidUserIDPopup;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement userIDContinueButton;

    @FindBy(id = "mui-5")
    private WebElement secretAnswer;

    @FindBy(id = "mui-5-helper-text")
    private WebElement emptyAnswerValidation;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement invalidAnswerPopup;

    @FindBy(xpath = " //button[contains(text(),'Send Now')]")
    private WebElement answerSendNowButton;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement passSentSuccessPopup;

    @FindBy(xpath = " //button[contains(text(),'Check your mail')]")
    private WebElement checkYourMailButton;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnForgotLink() throws InterruptedException {
        forgotLink.click();
    }

    public void fillField(WebElement field, String value) {
        field.sendKeys(value);
    }

    public void fillUserID(String userId) throws InterruptedException {
        this.fillField(userID, userId);
        Thread.sleep(2000);
    }

    public boolean getEmptyUserIDError() {
        boolean emptyUserIdErrorMsg = emptyIDValidation.isDisplayed();
        return emptyUserIdErrorMsg;
    }

    public String getEmptyUserIDErrorMsg() {
        return emptyIDValidation.getText();
    }

    public void clickContinueButton() {
        userIDContinueButton.click();
    }

    public boolean getInvalidUserIDPopup() {
        boolean popup = invalidUserIDPopup.isDisplayed();
        return popup;
    }

    public String popupValidationMsg() {
        return invalidUserIDPopup.getText();
    }

    public String getSecretQuestionPageURL() {

        EnvInfo envInfo = new EnvInfo();
        String secretQuestionPageURL = envInfo.getSecretQuestionPageURL();
        return secretQuestionPageURL;
    }

    public void fillSecretAnswer(String answer) throws InterruptedException {
        this.fillField(secretAnswer, answer);
        Thread.sleep(2000);
    }

    public void clickSendNowButton() throws InterruptedException {
        answerSendNowButton.click();
        Thread.sleep(1500);
    }

    public boolean getEmptyAnswerError() {
        boolean emptyAnswerErrorMsg = emptyAnswerValidation.isDisplayed();
        return emptyAnswerErrorMsg;
    }

    public String getEmptyAnswerErrorMsg() {
        return emptyAnswerValidation.getText();
    }

    public boolean getInvalidAnswerPopup() {
        boolean popup = invalidAnswerPopup.isDisplayed();
        return popup;
    }

    public String answerPopupValidationMsg() {
        return invalidAnswerPopup.getText();
    }

    public boolean getResetSuccessPopup() {
        boolean popup = passSentSuccessPopup.isDisplayed();
        return popup;
    }

    public String resetSuccessValidationMsg() {
        return passSentSuccessPopup.getText();
    }

    public void clickCheckYourMailButton() throws InterruptedException {
        checkYourMailButton.click();
        Thread.sleep(1500);
    }

    public void switchToEmailTab() {
        // Switch to the new window/tab (popup)
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public String getNewPassMailURL() {
        EnvInfo envInfo = new EnvInfo();
        String secretQuestionPageURL = envInfo.getSecretQuestionPageURL();
        return secretQuestionPageURL;
    }
}
