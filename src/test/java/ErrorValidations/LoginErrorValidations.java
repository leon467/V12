package ErrorValidations;

import Infra.BasePage;
import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginErrorValidations extends LoginPage {
    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public LoginErrorValidations(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void EmptySlotValidation(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        try {
            if(username.isEmpty() && password.isEmpty())
            {
                logger.error(loginPage.getLoginErrorMsg()+"\n");
                Thread.sleep(2000);

                boolean isUserIdErrorMessageVisible = loginPage.getUserCharLimitError();
                Assert.assertTrue(isUserIdErrorMessageVisible, "ERROR MESSAGE IS VISIBLE");

                boolean isPassErrorMessageVisible = loginPage.getPassCharLimitError();
                Assert.assertTrue(isPassErrorMessageVisible, "ERROR MESSAGE IS VISIBLE");
            }
            else
                Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        } catch (Exception e) {
            Assert.fail("ERROR MESSAGE IS NOT VISIBLE");
        }

    }

    public void InvalidCredentialsValidation(String username, String password) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        Thread.sleep(2000);

        try {
            boolean isErrorMessageVisible = loginPage.getInvalidCredPopup();
            Assert.assertTrue(isErrorMessageVisible, "POPUP IS VISIBLE");
            logger.error(loginPage.popupValidationMsg()+"\n");
        } catch (Exception e) {
            Assert.fail("POPUP IS NOT VISIBLE");
        }

    }
}
