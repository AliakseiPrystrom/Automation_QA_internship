package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.GamePageForm;
import forms.WelcomePageForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestCase4 extends BaseTest {
    private final ISettingsFile environment = new JsonSettingsFile("testdata.json");

    @BeforeMethod
    @Override
    protected void beforeMethod() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(environment.getValue("/homePage").toString());
        browser.waitForPageToLoad();
    }

    @Test
    public void testTimer(){
        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();
        WelcomePageForm wpf = new WelcomePageForm();
        wpf.goGameLink();
        browser.waitForPageToLoad();
        GamePageForm gpf = new GamePageForm();
        Assert.assertTrue(gpf.isTimerOK(),"Timer did not start from 00:00");
    }

}
