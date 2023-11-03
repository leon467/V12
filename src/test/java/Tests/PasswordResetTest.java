package Tests;

import ErrorValidations.HomePageErrorValidations;
import ErrorValidations.ResetPassErrorValidations;
import Infra.BasePage;
import Infra.DataProviders;
import Pages.LoginPage;
import Pages.PasswordResetPage;
import org.testng.annotations.Test;

/**
 * 1. Run the test using the class (Full password reset workflow)
 * 2. If you need to run individual methods for debugging, add the below code at the beginning of the method (no need for the 1st method)
 *
 * logger.info("CLICK ON THE ADMIN MODULE ICON");
 * passwordResetPage.clickAdminModuleIcon();
 * Thread.sleep(2000);
 *
 * logger.info("CLICK ON THE SETUPS DROPDOWN");
 * passwordResetPage.clickSetupsDropdown();
 * Thread.sleep(2000);
 *
 * logger.info("CLICK ON THE PASSWORD CHANGE LINK");
 * passwordResetPage.clickPasswordChangeLink();
 * Thread.sleep(3000);
*/

public class PasswordResetTest extends BasePage {

    @Test(priority = 1, description = "VerifyingCredentials", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
    public void VerifyingCredentials(String username, String password) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VERIFYING CREDENTIALS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        HomePageErrorValidations homePageErrorValidations = new HomePageErrorValidations(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);
        logger.info("REDIRECT TO: " + loginPage.setUrl());

        logger.info("ENTER USERNAME: " + username);
        loginPage.fillUsername(username);
        Thread.sleep(2000);

        logger.info("ENTER PASSWORD: " + password);
        loginPage.fillPassword(password);
        Thread.sleep(2000);

        logger.info("CLICK ON SIGN IN BUTTON \n");
        loginPage.clickOnLogIn();
        Thread.sleep(2000);

        homePageErrorValidations.HomePageRedirectErrorValidation(driver.getCurrentUrl());
        Thread.sleep(2000);

        logger.info("-------------------------------------------------------");
        logger.info("VERIFYING CREDENTIALS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Validating mandatory fields in the reset password screen", dependsOnMethods = "VerifyingCredentials")
    public void emptyFieldsValidation() throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING MANDATORY FIELDS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("CLICK ON THE ADMIN MODULE ICON");
        passwordResetPage.clickAdminModuleIcon();
        Thread.sleep(2000);

        logger.info("CLICK ON THE SETUPS DROPDOWN");
        passwordResetPage.clickSetupsDropdown();
        Thread.sleep(2000);

        logger.info("CLICK ON THE PASSWORD CHANGE LINK");
        passwordResetPage.clickPasswordChangeLink();
        Thread.sleep(3000);

        logger.info("CLICK ON THE SAVE BUTTON");
        passwordResetPage.clickSaveButton();
        Thread.sleep(2000);

        resetPassErrorValidations.EmptyMandatoryFieldsValidation();

        logger.info("CLOSE THE VALIDATION POPUP AND CHECK THE OTHER VALIDATIONS FOR EACH FIELDS");
        passwordResetPage.clickTryAgainButton();
        Thread.sleep(1500);

        resetPassErrorValidations.EmptyMandatoryFieldsValidationMsgs();
        passwordResetPage.clearFields();
        Thread.sleep(3000);
    }

    @Test(priority = 3, description = "Validating current password field in the reset password screen", dataProvider = "resetPassDetails", dataProviderClass = DataProviders.class)
    public void invalidCurrentPassValidation(String currentPass, String newPass, String reEnterPass, String question, String answer) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING CURRENT PASSWORD FIELD");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER AN INVALID CURRENT PASSWORD");
        passwordResetPage.fillCurrentPass(currentPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID CURRENT NEW PASSWORD");
        passwordResetPage.fillNewPass(newPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID CURRENT RE ENTER PASSWORD");
        passwordResetPage.fillReEnterPass(reEnterPass);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET QUESTION");
        passwordResetPage.fillSecretQuestion(question);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET ANSWER");
        passwordResetPage.fillSecretAnswer(answer);
        Thread.sleep(2000);

        logger.info("CLICK ON THE SAVE BUTTON");
        passwordResetPage.clickSaveButton();
        Thread.sleep(2000);

        resetPassErrorValidations.InvalidCurrentPasswordFieldValidation();
        Thread.sleep(2000);
        passwordResetPage.clickTryAgainButton();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 4, description = "Validating character limit of the new password", dataProvider = "newPassInvalidCharLimit", dataProviderClass = DataProviders.class)
    public void newPassCharLimitValidation(String newPass) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING NEW PASSWORD CHARACTER LIMIT");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER NEW PASSWORD WITH CHARACTERS LESS THAN 8");
        passwordResetPage.fillNewPass(newPass);
        Thread.sleep(2000);
        logger.info("ENTERED NEW PASSWORD WITH CHARACTERS LESS THAN 8: " + newPass);

        resetPassErrorValidations.NewPassMinCharLimitValidation();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 5, description = "Validating exclusion of capital letters in the new password", dataProvider = "newPassExcludingCapitals", dataProviderClass = DataProviders.class)
    public void newPassExcludeCapitalsValidation(String newPass) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING NEW PASSWORD EXCLUDING CAPITAL LETTERS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER NEW PASSWORD EXCLUDING CAPITALS");
        passwordResetPage.fillNewPassForOtherValidations(newPass);
        Thread.sleep(2000);
        logger.info("ENTERED NEW PASSWORD WITH EXCLUDING CAPITALS: " + newPass);

        resetPassErrorValidations.InvalidNewPassValidation();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 6, description = "Validating exclusion of simple letters in the new password", dataProvider = "newPassExcludingSimples", dataProviderClass = DataProviders.class)
    public void newPassExcludeSimplesValidation(String newPass) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING NEW PASSWORD EXCLUDING SIMPLE LETTERS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER NEW PASSWORD EXCLUDING SIMPLES");
        passwordResetPage.fillNewPassForOtherValidations(newPass);
        Thread.sleep(2000);
        logger.info("ENTERED NEW PASSWORD WITH EXCLUDING SIMPLES: " + newPass);

        resetPassErrorValidations.InvalidNewPassValidation();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 7, description = "Validating exclusion of numbers in the new password", dataProvider = "newPassExcludingNumbers", dataProviderClass = DataProviders.class)
    public void newPassExcludeNumbersValidation(String newPass) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING NEW PASSWORD EXCLUDING NUMBERS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER NEW PASSWORD EXCLUDING NUMBERS");
        passwordResetPage.fillNewPassForOtherValidations(newPass);
        Thread.sleep(2000);
        logger.info("ENTERED NEW PASSWORD WITH EXCLUDING NUMBERS: " + newPass);

        resetPassErrorValidations.InvalidNewPassValidation();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 8, description = "Validating exclusion of symbols in the new password", dataProvider = "newPassExcludingSymbols", dataProviderClass = DataProviders.class)
    public void newPassExcludeSymbolsValidation(String newPass) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING NEW PASSWORD EXCLUDING SYMBOLS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER NEW PASSWORD EXCLUDING SYMBOLS");
        passwordResetPage.fillNewPassForOtherValidations(newPass);
        Thread.sleep(2000);
        logger.info("ENTERED NEW PASSWORD WITH EXCLUDING SYMBOLS: " + newPass);

        resetPassErrorValidations.InvalidNewPassValidation();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 9, description = "Validating mismatching new, re-enter passwords", dataProvider = "passMismatchDetails", dataProviderClass = DataProviders.class)
    public void passwordMismatchValidation(String currentPass, String newPass, String reEnterPass, String question, String answer) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING PASSWORD MISMATCH");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER A VALID CURRENT PASSWORD");
        passwordResetPage.fillCurrentPass(currentPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID NEW PASSWORD");
        passwordResetPage.fillNewPass(newPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID RE ENTER PASSWORD DIFFERENT FROM THE NEW PASSWORD");
        passwordResetPage.fillReEnterPass(reEnterPass);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET QUESTION");
        passwordResetPage.fillSecretQuestion(question);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET ANSWER");
        passwordResetPage.fillSecretAnswer(answer);
        Thread.sleep(2000);

        logger.info("CLICK ON THE SAVE BUTTON");
        passwordResetPage.clickSaveButton();
        Thread.sleep(2000);

        logger.info("ENTERED NEW PASSWORD: " + newPass);
        logger.info("ENTERED RE-ENTER PASSWORD: " + reEnterPass);

        resetPassErrorValidations.PasswordMismatchPopupValidation();
        Thread.sleep(2000);
        passwordResetPage.clickTryAgainButton();
        Thread.sleep(1500);
        passwordResetPage.clearFields();
    }

    @Test(priority = 9, description = "Validating password reset", dataProvider = "correctResetPassDetails", dataProviderClass = DataProviders.class)
    public void passwordResetSuccessValidation(String currentPass, String newPass, String reEnterPass, String question, String answer) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING PASSWORD RESET STATUS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        ResetPassErrorValidations resetPassErrorValidations = new ResetPassErrorValidations(driver);

        logger.info("ENTER A VALID CURRENT PASSWORD");
        passwordResetPage.fillCurrentPass(currentPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID NEW PASSWORD");
        passwordResetPage.fillNewPass(newPass);
        Thread.sleep(2000);

        logger.info("ENTER A VALID RE ENTER PASSWORD");
        passwordResetPage.fillReEnterPass(reEnterPass);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET QUESTION");
        passwordResetPage.fillSecretQuestion(question);
        Thread.sleep(2000);

        logger.info("ENTER A SECRET ANSWER");
        passwordResetPage.fillSecretAnswer(answer);
        Thread.sleep(2000);

        logger.info("CLICK ON THE SAVE BUTTON");
        passwordResetPage.clickSaveButton();
        Thread.sleep(2000);

        resetPassErrorValidations.PasswordResetSuccessPopupValidation();
        Thread.sleep(2000);

        passwordResetPage.clickDetailsSavedButton();
        Thread.sleep(1500);

        logger.info("VALIDATING URL...");
        resetPassErrorValidations.RedirectToLoginURLValidation(driver.getCurrentUrl());

        logger.info("UPDATING DATASET WITH THE NEW PASSWORD...");
        passwordResetPage.updateNewPassword(newPass);
    }

    @Test(priority = 9, description = "Validating password reset", dependsOnMethods = "VerifyingCredentials")
    public void loginWithNewPassword() throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("LOGGED IN WITH THE NEW PASSWORD");
        logger.info("-------------------------------------------------------\n");
    }
}
