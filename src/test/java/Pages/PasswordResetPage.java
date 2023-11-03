package Pages;

import Infra.EnvInfo;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PasswordResetPage {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[2]/div/div[2]/div[6]")
    private WebElement adminModule;

    @FindBy(xpath = "//div[contains(text(),'Setups')]")
    private WebElement setupsDropdown;

    @FindBy(xpath = "//div[contains(text(),'User Password Change')]")
    private WebElement passwordChangeLink;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement mandatoryFieldErrorPopup;

    @FindBy(xpath = "//button[contains(text(),'Try Again')]")
    private WebElement tryAgainButton;

    @FindBy(xpath = "//p[contains(text(),'Temporary Password should be entered')]")
    private WebElement currentPassValidation;

    @FindBy(xpath = "//p[contains(text(),'New Password should be entered')]")
    private WebElement newPassValidation;

    @FindBy(xpath = "//p[contains(text(),'Re-Enter Password should be entered')]")
    private WebElement reEnterPassValidation;

    @FindBy(xpath = "//p[contains(text(),'Secret Question should be entered')]")
    private WebElement secretQuestionValidation;

    @FindBy(xpath = "//p[contains(text(),'Answer should be entered')]")
    private WebElement secretAnswerValidation;

    @FindBy(xpath = "/html/body/div[1]/div/div[4]/div[3]/div/div/div[1]/div/div[2]/div[2]/div/div/input")
    private WebElement currentPassword;

    @FindBy(xpath = "//input[@placeholder = 'RezUser@123']")
    private WebElement newPassword;

    @FindBy(xpath = "/html/body/div[1]/div/div[4]/div[3]/div/div/div[1]/div/div[6]/div[2]/div/div/input")
    private WebElement reEnterPassword;

    @FindBy(xpath = "/html/body/div[1]/div/div[4]/div[3]/div/div/div[1]/div/div[7]/div[2]/div[1]/div/input")
    private WebElement secretQuestion;

    @FindBy(xpath = "/html/body/div[1]/div/div[4]/div[3]/div/div/div[1]/div/div[8]/div[2]/div[1]/div/input")
    private WebElement secretAnswer;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement invalidCurrentPassErrorPopup;

    @FindBy(xpath = "//p[contains(text(), 'New Password should be more than 7 characters')]")
    private WebElement minCharLimitValidation;

    @FindBy(xpath = "//p[contains(text(), 'Password Invalid')]")
    private WebElement invalidNewPassValidation;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement passwordMismatchErrorPopup;

    @FindBy(xpath = "//div[@role = 'dialog']")
    private WebElement passResetSuccessPopup;

    @FindBy(xpath = "//button[contains(text(),'Details are saved')]")
    private WebElement detailsSavedButton;

    public PasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value) {
        field.sendKeys(value);
    }

    public void clickAdminModuleIcon() {
        adminModule.click();
    }

    public void clickSetupsDropdown() {
        setupsDropdown.click();
    }

    public void clickPasswordChangeLink() {
        passwordChangeLink.click();
    }

    public void clickSaveButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
    }

    public void clickTryAgainButton() {
        tryAgainButton.click();
    }

    public boolean getMandatoryFieldsErrorPopup() {
        boolean popup = mandatoryFieldErrorPopup.isDisplayed();
        return popup;
    }

    public String mandatoryPopupValidationMsg() {
        return mandatoryFieldErrorPopup.getText();
    }

    public boolean getMandatoryFieldsValidations() {
        if (currentPassValidation.isDisplayed() && newPassValidation.isDisplayed() && reEnterPassValidation.isDisplayed()
                && secretQuestionValidation.isDisplayed() && secretAnswerValidation.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String mandatoryFieldsValidationMsgs() {
        return currentPassValidation.getText() + ", " + newPassValidation.getText() + ", " + reEnterPassValidation.getText()
                + ", " + secretQuestionValidation.getText() + ", " + secretAnswerValidation.getText();
    }

    public void fillCurrentPass(String currentPass) throws InterruptedException {
        currentPassword.click(); // Ensure the field is focused
        currentPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(currentPassword, currentPass);
        Thread.sleep(2000);
    }

    public void fillNewPass(String newPass) throws InterruptedException {
        newPassword.click(); // Ensure the field is focused
        newPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(newPassword, newPass);
        Thread.sleep(2000);
    }

    //This 2nd method is used since for other validations(Excluding capitals, simples etc.) need to click outside
    public void fillNewPassForOtherValidations(String newPass) throws InterruptedException {
        newPassword.click(); // Ensure the field is focused
        newPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(newPassword, newPass);
        newPassword.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    public void fillReEnterPass(String reEnterPass) throws InterruptedException {
        reEnterPassword.click(); // Ensure the field is focused
        reEnterPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(reEnterPassword, reEnterPass);
        Thread.sleep(2000);
    }

    public void fillSecretQuestion(String secretQues) throws InterruptedException {
        secretQuestion.click(); // Ensure the field is focused
        secretQuestion.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(secretQuestion, secretQues);
        Thread.sleep(2000);
    }

    public void fillSecretAnswer(String secretAns) throws InterruptedException {
        secretAnswer.click(); // Ensure the field is focused
        secretAnswer.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        this.fillField(secretAnswer, secretAns);
        Thread.sleep(2000);
    }

    public boolean getCurrentPassErrorPopup() {
        boolean invalidPasspopup = invalidCurrentPassErrorPopup.isDisplayed();
        return invalidPasspopup;
    }

    public String currentPassValidationPopupMsg() {
        return invalidCurrentPassErrorPopup.getText();
    }

    public boolean getInvalidNewPassCharLimitValidation() {
        if (minCharLimitValidation.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String invalidNewPassCharLimitValidationMsg() {
        return minCharLimitValidation.getText();
    }

    public void clearFields() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('oninput'); arguments[0].value = '';", currentPassword);
        Thread.sleep(1000);
        newPassword.click(); // Ensure the field is focused
        newPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('oninput'); arguments[0].value = '';", reEnterPassword);
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('oninput'); arguments[0].value = '';", secretQuestion);
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('oninput'); arguments[0].value = '';", secretAnswer);
    }

    public boolean getInvalidNewPassValidation() {
        if (invalidNewPassValidation.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String invalidNewPassValidationMsg() {
        return invalidNewPassValidation.getText();
    }

    public boolean getPassMismatchErrorPopup() {
        boolean invalidPasspopup = passwordMismatchErrorPopup.isDisplayed();
        return invalidPasspopup;
    }

    public String passMismatchPopupMsg() {
        return passwordMismatchErrorPopup.getText();
    }

    public boolean getPassResetSuccessPopup() {
        boolean successPopup = passResetSuccessPopup.isDisplayed();
        return successPopup;
    }

    public String passResetSuccessPopupMsg() {
        return passResetSuccessPopup.getText();
    }

    public void clickDetailsSavedButton() {
        detailsSavedButton.click();
    }

    public String getLoginPageURL() {

        EnvInfo envInfo = new EnvInfo();
        String loginPageURL = envInfo.getUrl();
        return loginPageURL;
    }

    //This method is to update the new password in the data set after the password is reset
    public void updateNewPassword(String newPassword) {
        try {
            FileInputStream fis = new FileInputStream("../V12/src/test/resources/TestData.xlsm");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(1);

            // Assuming you want to update the second column (index 1) of the second row (index 1).
            int rowToUpdate = 1;
            int columnToUpdate = 1;

            // Update the value in the specified cell.
            sheet.getRow(rowToUpdate).getCell(columnToUpdate).setCellValue(newPassword);

            // Save the changes to the Excel file.
            FileOutputStream fos = new FileOutputStream("../V12/src/test/resources/TestData.xlsm");
            workbook.write(fos);
            fos.close();
            workbook.close();
            fis.close();
            System.out.println("NEW PASSWORD SAVED TO THE DATASET SUCCESSFULLY");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
