package ErrorValidations;

import Infra.BasePage;
import Pages.PasswordResetPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ResetPassErrorValidations extends PasswordResetPage {

    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public ResetPassErrorValidations(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void EmptyMandatoryFieldsValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            String expectedPopUpMsg = "Oooops! All fields are mandatory TRY AGAIN";
            String actualPopupMsg = passwordResetPage.mandatoryPopupValidationMsg();

            expectedPopUpMsg = expectedPopUpMsg.replaceAll("\\s", "");
            actualPopupMsg = actualPopupMsg.replaceAll("\\s", "");

            expectedPopUpMsg = expectedPopUpMsg.toLowerCase();
            actualPopupMsg = actualPopupMsg.toLowerCase();

            if (passwordResetPage.getMandatoryFieldsErrorPopup() == true && expectedPopUpMsg.equals(actualPopupMsg)) {
                boolean isMandatoryPopupVisible = passwordResetPage.getMandatoryFieldsErrorPopup();
                Assert.assertTrue(isMandatoryPopupVisible, "MANDATORY FIELDS ERROR POPUP IS VISIBLE");
                logger.error(actualPopupMsg + "\n");
            } else
                Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        }
    }

    public void EmptyMandatoryFieldsValidationMsgs() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            if (passwordResetPage.getMandatoryFieldsValidations() == true) {
                boolean isMandatoryMsgsVisible = passwordResetPage.getMandatoryFieldsValidations();
                Assert.assertTrue(isMandatoryMsgsVisible, "MANDATORY FIELDS ERROR MESSAGES ARE VISIBLE");
                logger.error("FOLLOWING VALIDATIONS WERE DISPLAYED SUCCESSFULLY: " + passwordResetPage.mandatoryFieldsValidationMsgs() + "\n");
            } else
                Assert.fail("MESSAGES ARE NOT VISIBLE");
        } catch (Exception e) {
            Assert.fail("MESSAGES ARE NOT VISIBLE");
        }
    }

    public void InvalidCurrentPasswordFieldValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            String expectedPopUpMsg = "Oooops! Invalid Username or Password TRY AGAIN";
            String actualPopupMsg = passwordResetPage.currentPassValidationPopupMsg();

            expectedPopUpMsg = expectedPopUpMsg.replaceAll("\\s", "");
            actualPopupMsg = actualPopupMsg.replaceAll("\\s", "");

            expectedPopUpMsg = expectedPopUpMsg.toLowerCase();
            actualPopupMsg = actualPopupMsg.toLowerCase();

            if (passwordResetPage.getCurrentPassErrorPopup() == true && expectedPopUpMsg.equals(actualPopupMsg)) {
                boolean isInvalidCurrentPassPopupVisible = passwordResetPage.getCurrentPassErrorPopup();
                Assert.assertTrue(isInvalidCurrentPassPopupVisible, "INVALID CURRENT PASSWORD FIELD ERROR POPUP IS VISIBLE");
                logger.error(passwordResetPage.currentPassValidationPopupMsg() + "\n");
            } else
                Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        }
    }

    public void NewPassMinCharLimitValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            if (passwordResetPage.getInvalidNewPassCharLimitValidation() == true) {
                boolean isCharLimitMsgVisible = passwordResetPage.getInvalidNewPassCharLimitValidation();
                Assert.assertTrue(isCharLimitMsgVisible, "CHARACTER LIMIT ERROR MESSAGE IS VISIBLE");
                logger.error("FOLLOWING VALIDATION WAS DISPLAYED SUCCESSFULLY: " + passwordResetPage.invalidNewPassCharLimitValidationMsg() + "\n");
            } else
                Assert.fail("MESSAGES ARE NOT VISIBLE");
        } catch (Exception e) {
            Assert.fail("MESSAGES ARE NOT VISIBLE");
        }
    }

    public void InvalidNewPassValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            if (passwordResetPage.getInvalidNewPassValidation() == true) {
                boolean isInvalidNewPassMsgVisible = passwordResetPage.getInvalidNewPassValidation();
                Assert.assertTrue(isInvalidNewPassMsgVisible, "INVALID NEW PASSWORD ERROR MESSAGE IS VISIBLE");
                logger.error("FOLLOWING VALIDATION WAS DISPLAYED SUCCESSFULLY: " + passwordResetPage.invalidNewPassValidationMsg() + "\n");
            } else
                Assert.fail("MESSAGE IS NOT VISIBLE");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("MESSAGE IS NOT VISIBLE");
        }
    }

    public void PasswordMismatchPopupValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            String expectedPopUpMsg = "Oooops! New password and Re-Entered passwords are mismatching... TRY AGAIN";
            String actualPopupMsg = passwordResetPage.passMismatchPopupMsg();

            expectedPopUpMsg = expectedPopUpMsg.replaceAll("\\s", "");
            actualPopupMsg = actualPopupMsg.replaceAll("\\s", "");

            expectedPopUpMsg = expectedPopUpMsg.toLowerCase();
            actualPopupMsg = actualPopupMsg.toLowerCase();

            if (passwordResetPage.getPassMismatchErrorPopup() == true && expectedPopUpMsg.equals(actualPopupMsg)) {
                boolean isPassMismatchPopupVisible = passwordResetPage.getPassMismatchErrorPopup();
                Assert.assertTrue(isPassMismatchPopupVisible, "PASSWORD MISMATCH ERROR POPUP IS VISIBLE");
                logger.error("FOLLOWING MESSAGE WAS DISPLAYED SUCCESSFULLY IN THE POPUP: " + passwordResetPage.passMismatchPopupMsg() + "\n");
            } else
                Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        }
    }

    public void PasswordResetSuccessPopupValidation() throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        try {
            String expectedPopUpMsg = "Great! <b>Password has been changed successfully.</b><br><br> DETAILS ARE SAVED";
            String actualPopupMsg = passwordResetPage.passResetSuccessPopupMsg();

            expectedPopUpMsg = expectedPopUpMsg.replaceAll("\\s", "");
            actualPopupMsg = actualPopupMsg.replaceAll("\\s", "");

            expectedPopUpMsg = expectedPopUpMsg.toLowerCase();
            actualPopupMsg = actualPopupMsg.toLowerCase();

            if (passwordResetPage.getPassResetSuccessPopup() == true && expectedPopUpMsg.equals(actualPopupMsg)) {
                boolean isPassResetSuccessPopupVisible = passwordResetPage.getPassMismatchErrorPopup();
                Assert.assertTrue(isPassResetSuccessPopupVisible, "PASSWORD RESET SUCCESS POPUP IS VISIBLE");
                logger.error("FOLLOWING MESSAGE WAS DISPLAYED SUCCESSFULLY IN THE POPUP: " + passwordResetPage.passResetSuccessPopupMsg() + "\n");
            } else
                Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE OR AN ERROR IN THE SERVER");
        }
    }

    public void RedirectToLoginURLValidation(String URL) throws InterruptedException {
        PasswordResetPage passwordResetPage = new PasswordResetPage(driver);
        String ExpectedURL = passwordResetPage.getLoginPageURL();
        String ActualURL = URL;

        try {
            if (ExpectedURL.equals(ActualURL)) {
                Assert.assertEquals(ExpectedURL, ActualURL, "PASSWORD RESET COMPLETED");
                Thread.sleep(2000);
                logger.info("PASSWORD RESET COMPLETED. REDIRECTED TO THE LOGIN PAGE: " + ActualURL);
            } else {
                Assert.fail("PASSWORD RESET NOT COMPLETED");
                logger.info("PASSWORD RESET NOT COMPLETED\n");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            Assert.fail("PASSWORD RESET NOT COMPLETED");
        }
    }
}
