package Tests;

import ErrorValidations.ForgotPassErrorValidations;
import ErrorValidations.HomePageErrorValidations;
import ErrorValidations.LoginErrorValidations;
import Infra.BasePage;
import Infra.DataProviders;
import Infra.EnvInfo;
import Pages.EmailInboxPage;
import Pages.ForgotPasswordPage;
import Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
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

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        driver.navigate().refresh();

        userID = "abc";

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

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        driver.navigate().refresh();

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

    @Test(priority = 4, description = "Validating Empty Secret Answer")
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

    @Test(priority = 5, description = "Validating Invalid secret answer")
    public void InvalidAnswer() throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID SECRET ANSWER");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        driver.navigate().refresh();

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

    @Test(priority = 6, description = "Verifying if the new password is sent to the mail", dataProvider = "getSecretAnswer", dataProviderClass = DataProviders.class)
    public void sendNewPassword(String secretAnswer, String emailURL) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING IF NEW PASSWORD SENT");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        driver.navigate().refresh();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        EmailInboxPage emailInboxPage = new EmailInboxPage(driver);
        ForgotPassErrorValidations forgotPassErrorValidations = new ForgotPassErrorValidations(driver);

        //Test code
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage(loginPage.setUrl());
        logger.info("REDIRECT TO: " +loginPage.setUrl());

        logger.info("CLICK ON THE FORGOT PASSWORD LINK");
        Thread.sleep(2000);
        forgotPasswordPage.clickOnForgotLink();

        userID = "Leon123";

        logger.info("ENTER USER ID: " + userID);
        forgotPasswordPage.fillUserID(userID);
        Thread.sleep(2000);

        logger.info("CLICK ON CONTINUE BUTTON \n");
        forgotPasswordPage.clickContinueButton();
        Thread.sleep(2000);
        //Test code end

        logger.info("SECRET ANSWER: "+secretAnswer);
        forgotPasswordPage.fillSecretAnswer(secretAnswer);
        Thread.sleep(2000);

        logger.info("CLICK ON SEND NOW BUTTON \n");
        forgotPasswordPage.clickSendNowButton();
        Thread.sleep(2000);

        forgotPassErrorValidations.passwordSentSuccess();
        Thread.sleep(1500);

        logger.info("CLICK ON CHECK YOUR MAIL BUTTON \n");
        forgotPasswordPage.clickCheckYourMailButton();
        Thread.sleep(1500);

        logger.info("NAVIGATE TO THE EMAIL LINK \n");
        forgotPasswordPage.switchToEmailTab();
        Thread.sleep(1500);

        logger.info("SIGNING IN TO THE EMAIL ACCOUNT \n");
        logger.info("CLICK ON SIGN IN BUTTON \n");
        emailInboxPage.clickOnSignIn();
        Thread.sleep(3000);

        logger.info("ENTER EMAIL \n");
        emailInboxPage.fillEmail("leonisaac99@hotmail.com");
        Thread.sleep(3000);

        logger.info("CLICK ON NEXT BUTTON \n");
        emailInboxPage.clickOnNextButton();
        Thread.sleep(2000);

        logger.info("ENTER PASSWORD \n");
        emailInboxPage.fillPassword("LeonOutlook99");
        Thread.sleep(3000);

        logger.info("CLICK ON SIGN IN BUTTON \n");
        emailInboxPage.clickOnSignInButton();
        Thread.sleep(4000);

        logger.info("CLICK ON STAY SIGNED IN BUTTON \n");
        emailInboxPage.clickOnStaySingedButton();
        Thread.sleep(4000);

        logger.info("VALIDATING THE INBOX URL.. \n");
        forgotPassErrorValidations.newPasswordMailRedirect(driver.getCurrentUrl());

        logger.info("GET THE DATE & TIME OF THE NEWEST MAIL \n");
        logger.info("DATE & TIME OF THE NEWEST MAIL: " + emailInboxPage.getFirstNewestMailDateTime());
        Thread.sleep(4000);

        logger.info("GET THE CURRENT DATE & TIME \n");
        logger.info("CURRENT DATE & TIME: " + emailInboxPage.getCurrentDateTime());
        Thread.sleep(4000);

        logger.info("GET THE DATE AND TIME DIFFERENCE \n");
        forgotPassErrorValidations.NewPasswordMailValidation();
        Thread.sleep(4000);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING IF NEW PASSWORD IS SENT TO THE EMAIL IS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }
}
