package Infra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
public class BasePage {
        protected WebDriver driver;

        public static Logger logger = LogManager.getLogger(Infra.BasePage.class);

        public ChromeOptions getOptions()
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            return options;
        }

        @BeforeClass
        public void openBrowser() throws InterruptedException {
            logger.info("=======================================================");
            logger.info("OPENING WEB BROWSER");
            logger.info("=======================================================\n");
            Thread.sleep(2000);

            driver = new ChromeDriver(getOptions());
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        @AfterClass
        public void closeBrowser() throws InterruptedException {
            logger.info("=======================================================");
            logger.info("CLOSING WEB BROWSER");
            logger.info("=======================================================\n");
            Thread.sleep(2000);

            driver.quit();
        }
}
