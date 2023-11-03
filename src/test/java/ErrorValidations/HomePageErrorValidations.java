package ErrorValidations;

import Infra.BasePage;
import Pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePageErrorValidations extends HomePage {
    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public HomePageErrorValidations(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void HomePageRedirectErrorValidation(String Title) throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        String ExpectedTitle = homePage.getHomePageURL();
        String ActualTitle = Title;

        if(ExpectedTitle.equals(ActualTitle))
        {
            logger.info("LOG IN SUCCESS\n");
            Assert.assertEquals(ExpectedTitle, ActualTitle, "LOG IN SUCCESS");
            Thread.sleep(2000);
        }
        else
        {
            logger.info("LOG IN FAILED\n");
            Assert.fail("LOGIN FAILED");
            Thread.sleep(2000);
        }
    }
}
