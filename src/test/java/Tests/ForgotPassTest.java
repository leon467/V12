package Tests;

import ErrorValidations.ForgotPassErrorValidations;
import ErrorValidations.HomePageErrorValidations;
import ErrorValidations.LoginErrorValidations;
import Infra.BasePage;
import Infra.DataProviders;
import Pages.ForgotPasswordPage;
import Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class ForgotPassTest extends BasePage {
    public static String userID;
    public static String secretAnswer;


    @Test(priority = 1, description = "Validating Empty User ID")
    public void emptyUserIDValidation() throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING USER ID");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        logger.info("REDIRECT TO: " +loginPage.setUrl());

        logger.info("CLICK ON THE FORGOT PASSWORD LINK");
        Thread.sleep(2000);
        forgotPasswordPage.clickOnForgotLink();

        userID = "";

        logger.info("ENTER USER ID: Empty");
        forgotPasswordPage.fillUserID(userID);
        Thread.sleep(2000);

        logger.info("CLICK ON CONTINUE BUTTON \n");
        forgotPasswordPage.clickContinueButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.EmptyUserIDValidation(userID);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING EMPTY CREDENTIALS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }

    @Test(priority = 2, description = "Validating Invalid User ID")
    public void InvalidUserID() throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID USER ID");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);
        logger.info("REDIRECT TO: " +loginPage.setUrl());

        userID = "abc";

        logger.info("CLICK ON THE FORGOT PASSWORD LINK");
        Thread.sleep(2000);
        forgotPasswordPage.clickOnForgotLink();

        logger.info("ENTER USERNAME: "+userID);
        forgotPasswordPage.fillUserID(userID);
        Thread.sleep(2000);

        logger.info("CLICK ON CONTINUE BUTTON \n");
        forgotPasswordPage.clickContinueButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.InvalidUserIDValidation(userID);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID USER ID PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        logger.info("REFRESHING THE BROWSER");
        driver.navigate().refresh();

    }

    @Test(priority = 3, description = "Verifying valid user ID", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
    public void validUserID(String username, String passowrd) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VERIFYING VALID USER ID");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);
        logger.info("REDIRECT TO: " +loginPage.setUrl());

        logger.info("CLICK ON THE FORGOT PASSWORD LINK");
        Thread.sleep(2000);
        forgotPasswordPage.clickOnForgotLink();

        logger.info("ENTER USERNAME: "+username);
        forgotPasswordPage.fillUserID(username);
        Thread.sleep(2000);

        logger.info("CLICK ON CONTINUE BUTTON \n");
        forgotPasswordPage.clickContinueButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.secretQuestionRedirectErrorValidation(driver.getCurrentUrl());

        logger.info("-------------------------------------------------------");
        Thread.sleep(2000);
    }

    @Test(priority = 4, description = "Validating Empty Secret Answer", dependsOnMethods = "validUserID")
    public void emptySecretAnswerValidation() throws InterruptedException {
        logger.info("VALIDATING EMPTY SECRET ANSWER");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        secretAnswer = "";

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        logger.info("ENTER SECRET ANSWER: Empty");
        forgotPasswordPage.fillSecretAnswer(secretAnswer);
        Thread.sleep(2000);

        logger.info("CLICK ON SEND NOW BUTTON \n");
        forgotPasswordPage.clickSendNowButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.EmptyAnswerValidation(secretAnswer);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING EMPTY ANSWER PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }

    @Test(priority = 5, description = "Validating Invalid secret answer", dependsOnMethods = "validUserID")
    public void InvalidAnswer() throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID SECRET ANSWER");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        secretAnswer = "abc";

        logger.info("SECRET ANSWER: "+secretAnswer);
        forgotPasswordPage.fillSecretAnswer(secretAnswer);
        Thread.sleep(2000);

        logger.info("CLICK ON SEND NOW BUTTON \n");
        forgotPasswordPage.clickSendNowButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.InvalidAnswerValidation(secretAnswer);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID SECRET ANSWER PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        logger.info("REFRESHING THE BROWSER");
        driver.navigate().refresh();

    }

    @Test(priority = 6, description = "Verifying if the new password is sent to the mail", dataProvider = "getSecretAnswer", dataProviderClass = DataProviders.class, dependsOnMethods = "validUserID")
    public void sendNewPassword(String secretAnswer, String emailURL) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING IF NEW PASSWORD SENT");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        logger.info("SECRET ANSWER: "+secretAnswer);
        forgotPasswordPage.fillSecretAnswer(secretAnswer);
        Thread.sleep(2000);

        Thread.sleep(2000);

        logger.info("CLICK ON SEND NOW BUTTON \n");
        forgotPasswordPage.clickSendNowButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.passwordSentSuccess();
        Thread.sleep(1500);

        forgotPasswordPage.clickCheckYourMailButton();
        Thread.sleep(1500);

        forgotPasswordPage.switchToEmailTab();
        Thread.sleep(1500);

        forgotPassErrorValidations.newPasswordMailRedirect(driver.getCurrentUrl());

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING IF NEW PASSWORD IS SENT TO THE EMAIL IS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }
}
