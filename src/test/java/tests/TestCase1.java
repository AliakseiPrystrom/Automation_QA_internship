package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.FirstCardForm;
import forms.SecondCard;
import forms.ThirdCardForm;
import forms.WelcomePageForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Domains;

public class TestCase1 extends BaseTest {
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
    public void testLoginAndUpload() {
        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();
        WelcomePageForm wpf = new WelcomePageForm();
        Assert.assertTrue(wpf.state().isDisplayed(), "Page did not open");
        wpf.goGameLink();
        browser.waitForPageToLoad();
        FirstCardForm firstCard = new FirstCardForm();
        Assert.assertTrue(firstCard.state().isDisplayed(), "Card did not open");
        firstCard.clearPasswordFieldAndType(environment.getValue("/testLogin").toString());
        firstCard.clearEmailFieldAndType(environment.getValue("/testPassword").toString());
        firstCard.clearDomainAndTYpe(environment.getValue("/testDomain").toString());
        firstCard.otherSelect();
        firstCard.otherValueSelect(Domains.COM.toString());
        firstCard.useLicenseCheckBox();
        firstCard.goNextCard();
        browser.waitForPageToLoad();
        SecondCard secondCard = new SecondCard();
        Assert.assertTrue(secondCard.state().isDisplayed(), "Card did not open");
        secondCard.resetAllCB();
        secondCard.selectCB(3);
        secondCard.clickUpload();
        secondCard.uploadFile(environment.getValue("/uploadFileName").toString());
        secondCard.goNextCard();
        browser.waitForPageToLoad();
        ThirdCardForm thirdCard = new ThirdCardForm();
        Assert.assertTrue(thirdCard.state().isDisplayed(), "Card did not open");
    }


}
