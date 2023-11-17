package Pages;

import Infra.EnvInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;;;

public class HomePage {
    private WebDriver driver;

    //UI elements

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageURL() {

        EnvInfo envInfo = new EnvInfo();
        String HomePageURL =  envInfo.getHomePageUrl();
        return HomePageURL;
    }
}
