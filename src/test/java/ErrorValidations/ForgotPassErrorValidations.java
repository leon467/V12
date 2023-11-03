package ErrorValidations;

import Infra.BasePage;
import Pages.ForgotPasswordPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
            boolean isErrorMessageVisible = forgotPass.getInvalidUserIDPopup();
            Assert.assertTrue(isErrorMessageVisible, "INVALID USER ID VALIDATION POPUP IS VISIBLE");
            logger.error(forgotPass.popupValidationMsg()+"\n");
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
                Assert.fail("INVALID USER ID");
                logger.info("INVALID USER ID\n");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("INVALID USER ID");
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
        try {
            boolean isSuccessMessageVisible = forgotPass.getResetSuccessPopup();
            Assert.assertTrue(isSuccessMessageVisible, "PASSWORD SENT SUCCESS POPUP IS VISIBLE");
            logger.error(forgotPass.resetSuccessValidationMsg()+"\n");
        } catch (Exception e) {
            Assert.fail("PASSWORD SENT SUCCESS POPUP IS NOT VISIBLE");
        }
    }

    public void newPasswordMailRedirect(String URL) throws InterruptedException {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        String ExpectedURL = forgotPasswordPage.getNewPassMailURL();
        String ActualURL = URL;

        try {
            if(ExpectedURL.equals(ActualURL))
            {
                Assert.assertEquals(ExpectedURL, ActualURL, "NEW PASSWORD SENT TO THE MAIL");
                Thread.sleep(2000);
                logger.info("NEW PASSWORD SENT TO THE MAIL: " + ActualURL);
            }
            else
            {
                Assert.fail("PASSWORD RESET FAILED");
                logger.info("PASSWORD RESET FAILED\n");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("PASSWORD RESET FAILED");
        }
    }
}
