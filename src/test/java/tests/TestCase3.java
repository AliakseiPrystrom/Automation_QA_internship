package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.CookieForm;
import forms.WelcomePageForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest {
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
    public void testAcceptCookie(){
        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();
        WelcomePageForm wpf = new WelcomePageForm();
        Assert.assertTrue(wpf.state().isDisplayed(), "Page did not open");
        wpf.goGameLink();
        browser.waitForPageToLoad();
        CookieForm cf = new CookieForm();
        cf.acceptCookie();
        Assert.assertFalse(cf.state().isDisplayed(),"Form is open");
    }

}
