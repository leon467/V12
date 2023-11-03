package Tests;

import ErrorValidations.HomePageErrorValidations;
import ErrorValidations.LoginErrorValidations;
import Infra.BasePage;
import Infra.DataProviders;
import Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginTest extends BasePage {
    public static String username, password;

    @Test(priority = 1, description = "Validating EmptyCredentials")
    public void EmptyCredentials() throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING EMPTY CREDENTIALS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        LoginErrorValidations loginErrorValidations = new LoginErrorValidations(driver);
        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);

        logger.info("REDIRECT TO: " + loginPage.setUrl());

        username = "";
        password = "";

        logger.info("ENTER USER ID: Empty");
        loginPage.fillUsername(username);
        Thread.sleep(2000);

        logger.info("ENTER PASSWORD: Empty");
        loginPage.fillPassword(password);
        Thread.sleep(2000);
        loginPage.pressTabKey();

        loginErrorValidations.EmptySlotValidation(username, password);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING EMPTY CREDENTIALS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        logger.info("REFRESHING THE BROWSER");
        driver.navigate().refresh();

    }

    @Test(priority = 2, description = "Validating InvalidCredentials")
    public void InvalidCredentials() throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID CREDENTIALS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        LoginErrorValidations loginErrorValidations = new LoginErrorValidations(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);
        logger.info("REDIRECT TO: " + loginPage.setUrl());

        username = "TestDynamicRateTO";
        password = "12345678";

        logger.info("ENTER USERNAME: " + username);
        loginPage.fillUsername(username);
        Thread.sleep(2000);

        logger.info("ENTER PASSWORD: " + password);
        loginPage.fillPassword(password);
        Thread.sleep(2000);

        logger.info("CLICK ON SIGN IN BUTTON \n");
        loginPage.clickOnLogIn();
        Thread.sleep(2000);

        loginErrorValidations.InvalidCredentialsValidation(username, password);

        logger.info("-------------------------------------------------------");
        logger.info("VALIDATING INVALID CREDENTIALS PROCESSED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        logger.info("REFRESHING THE BROWSER");
        driver.navigate().refresh();

    }

    @Test(priority = 3, description = "VerifyingCredentials", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
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

    @Test(priority = 3, description = "VerifyingCredentials", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
    public void SignInWithRememberMe(String username, String password) throws InterruptedException {
        logger.info("-------------------------------------------------------");
        logger.info("VERIFYING CREDENTIALS");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        HomePageErrorValidations homePageErrorValidations = new HomePageErrorValidations(driver);

        // Step 1: Login
        loginPage.openLoginPage(loginPage.setUrl());
        Thread.sleep(2000);
        logger.info("REDIRECT TO: " + loginPage.setUrl());

        logger.info("ENTER USERNAME: " + username);
        loginPage.fillUsername(username);
        Thread.sleep(2000);

        logger.info("ENTER PASSWORD: " + password);
        loginPage.fillPassword(password);
        Thread.sleep(2000);

        logger.info("CLICK REMEMBER ME CHECKBOX");
        loginPage.clickRememberMe();
        Thread.sleep(2000);

        logger.info("CLICK ON SIGN IN BUTTON \n");
        loginPage.clickOnLogIn();
        Thread.sleep(2000);

        homePageErrorValidations.HomePageRedirectErrorValidation(driver.getTitle());
        Thread.sleep(2000);

        // Step 2: Close the current tab (keeping the session alive)

        // Step 3: Open a new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open('', '_blank');");

        // Step 4: Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Step 5: Navigate to the login page
        loginPage.openLoginPage(loginPage.setUrl());

        // Continue your testing using the current WebDriver instance
        homePageErrorValidations.HomePageRedirectErrorValidation(driver.getTitle());
        Thread.sleep(2000);

        // Step 6: Close the new tab when done
        driver.close();

        // Switch back to the original tab (if needed)
        driver.switchTo().window(tabs.get(0));

        logger.info("-------------------------------------------------------");
        logger.info("REMEMBER ME FUNCTION VALIDATED");
        logger.info("-------------------------------------------------------\n");
        Thread.sleep(2000);
    }

}
