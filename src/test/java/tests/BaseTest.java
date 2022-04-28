package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private final ISettingsFile config = new JsonSettingsFile("config.json");
    private final Browser browser = AqualityServices.getBrowser();


    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        browser.goTo(config.getValue("/homePage").toString());
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
