package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.HelpForm;
import forms.WelcomePageForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {
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
    public void testCloseFormContent() {
        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();
        WelcomePageForm wpf = new WelcomePageForm();
        Assert.assertTrue(wpf.state().isDisplayed(), "Page did not open");
        wpf.goGameLink();
        browser.waitForPageToLoad();
        HelpForm helpForm = new HelpForm();
        helpForm.hideHelpForm();
        Assert.assertTrue(helpForm.isHidden(), "Form did not hidden");
    }
}
