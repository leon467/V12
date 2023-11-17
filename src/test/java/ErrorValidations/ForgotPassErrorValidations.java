package ErrorValidations;

import Infra.BasePage;
import Pages.EmailInboxPage;
import Pages.ForgotPasswordPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ForgotPassErrorValidations extends ForgotPasswordPage {
    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public ForgotPassErrorValidations(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void EmptyUserIDValidation(String userID) throws InterruptedException {
        ForgotPasswordPage forgotPass = new ForgotPasswordPage(driver);
        try {
            if(userID.isEmpty())
            {
                boolean isUserIdErrorMessageVisible = forgotPass.getEmptyUserIDError();
                Assert.assertTrue(isUserIdErrorMessageVisible, "ERROR MESSAGE IS VISIBLE");
                logger.error(forgotPass.getEmptyUserIDErrorMsg()+"\n");
            }
            else
                Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        } catch (Exception e) {
            Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        }
    }
    public void InvalidUserIDValidation(String userID) throws InterruptedException {
        ForgotPasswordPage forgotPass = new ForgotPasswordPage(driver);

        try {
            if (forgotPass.getInvalidUserIDPopup()) {
                Assert.assertTrue(forgotPass.getInvalidUserIDPopup(), "INVALID USER ID VALIDATION POPUP IS VISIBLE");
                logger.error(forgotPass.popupValidationMsg() + "\n");
            }
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE");
        }

    }

    public void secretQuestionRedirectErrorValidation(String URL) throws InterruptedException {

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        String ExpectedURL = forgotPasswordPage.getSecretQuestionPageURL();
        String ActualURL = URL;

        try {
            if(ExpectedURL.equals(ActualURL))
            {
                Assert.assertEquals(ExpectedURL, ActualURL, "USER ID IS VALID");
                Thread.sleep(2000);
                logger.info("USER ID IS VALID. REDIRECTED TO THE SECRET QUESTION PAGE: " + ActualURL);
            }
            else
            {
                Assert.fail("INVALID USER ID"+ ActualURL);
                logger.info("INVALID USER ID\n");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("INVALID USER ID"+ ActualURL);
        }
    }

    public void EmptyAnswerValidation(String answer) throws InterruptedException {
        ForgotPasswordPage forgotPass = new ForgotPasswordPage(driver);
        try {
            if(answer.isEmpty())
            {
                boolean isAnswerErrorMessageVisible = forgotPass.getEmptyAnswerError();
                Assert.assertTrue(isAnswerErrorMessageVisible, "ERROR MESSAGE IS VISIBLE");
                logger.error(forgotPass.getEmptyAnswerErrorMsg()+"\n");
            }
            else
                Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        } catch (Exception e) {
            Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        }

    }
    public void InvalidAnswerValidation(String answer) throws InterruptedException {
        ForgotPasswordPage forgotPass = new ForgotPasswordPage(driver);

        try {
            boolean isErrorMessageVisible = forgotPass.getInvalidAnswerPopup();
            Assert.assertTrue(isErrorMessageVisible, "INVALID ANSWER VALIDATION POPUP IS VISIBLE");
            logger.error(forgotPass.answerPopupValidationMsg()+"\n");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE");
        }

    }

    public void passwordSentSuccess() throws InterruptedException {
        ForgotPasswordPage forgotPass = new ForgotPasswordPage(driver);
        String expectedPopupMsg = "Great! Generated password and activation link has been sent to your email address successfully. Check your mail";
        String actualPopupMsg = forgotPass.resetSuccessValidationMsg();

        expectedPopupMsg = expectedPopupMsg.replaceAll("\\s", "").toLowerCase();
        actualPopupMsg = actualPopupMsg.replaceAll("\\s", "").toLowerCase();

        try {
            if (forgotPass.getResetSuccessPopup() && expectedPopupMsg.equals(actualPopupMsg)) {
                boolean isSuccessMessageVisible = forgotPass.getResetSuccessPopup();
                Assert.assertTrue(isSuccessMessageVisible, "PASSWORD SENT SUCCESS POPUP IS VISIBLE");
                logger.error("PASSWORD SENT MESSAGE: " + forgotPass.resetSuccessValidationMsg() + "\n");
            }
            else {
                Assert.fail("PASSWORD SENT SUCCESS POPUP IS NOT VISIBLE: " + actualPopupMsg);
                Assert.fail("NEW PASSWORD SENDING FAILED");
            }
        } catch (Exception e) {
            Assert.fail("PASSWORD SENT SUCCESS POPUP IS NOT VISIBLE: " + actualPopupMsg);
            Assert.fail("NEW PASSWORD SENDING FAILED");
        }
    }

    public void newPasswordMailRedirect(String URL) throws InterruptedException {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        String ExpectedURL = forgotPasswordPage.getNewPassMailURL();
        String ActualURL = URL;

        try {
            if(ExpectedURL.equals(ActualURL))
            {
                Assert.assertEquals(ExpectedURL, ActualURL, "MAIL INBOX OPENED");
                Thread.sleep(2000);
                logger.info("MAIL INBOX OPENED: " + ActualURL);
            }
            else
            {
                Assert.fail("FAILED TO OPEN INBOX:\n " + "Actual URL: " + ActualURL + "\nExpected URL: " + ExpectedURL);
                logger.info("FAILED TO OPEN INBOX:\n " + "Actual URL: " + ActualURL + "\nExpected URL: " + ExpectedURL);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("FAILED TO OPEN INBOX:\n " + "Actual URL: " + ActualURL + "\nExpected URL: " + ExpectedURL);
        }
    }

    public void NewPasswordMailValidation() {

        EmailInboxPage emailInboxPage = new EmailInboxPage(driver);
        try {
            // Format the date and time in "h:mm a" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

            // Get the Date and time from the email and the current date and time as string, then parse it to LocalTime type
            LocalTime localTimeEmail = LocalTime.parse(emailInboxPage.getFirstNewestMailDateTime(), formatter);
            LocalTime localTimeCurrent = LocalTime.parse(emailInboxPage.getCurrentDateTime(), formatter);

            // Calculate the time difference in seconds
            long timeDifference = ChronoUnit.SECONDS.between(localTimeCurrent, localTimeEmail);

            // Check if the time difference is within the range of +/- 300 seconds
            if (Math.abs(timeDifference) <= 300) {
                logger.info("THE EMAIL DATE AND TIME IS WITHIN +/- 300 SECONDS OF THE CURRENT DATE AND TIME: " + timeDifference + " seconds");
                logger.info("NEW EMAIL WITH A TEMPORARY PASSWORD RECEIVED");
            } else {
                logger.info("NEW EMAIL WITH A TEMPORARY PASSWORD HAS NOT RECEIVED");
                Assert.fail("THE EMAIL DATE AND TIME IS NOT WITHIN +/- 300 SECONDS OF THE CURRENT DATE AND TIME: " + timeDifference + " seconds");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
