package stepDefs;

import HelperClasses.ConfigHelper;
import HelperClasses.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SheffieldHome;
import pages.SheffieldLogin;

import java.io.IOException;

import static HelperClasses.Driver.MyDriver.driver;

public class SheffieldSteps
{
    //Class level variables
    private SheffieldHome _theHomePage;
    private SheffieldLogin _theLoginPage;

    //Constructor
    public SheffieldSteps()
    {
        _theHomePage = new SheffieldHome();
        _theLoginPage = new SheffieldLogin();
    }
    @Before
    public static void StartBrowser()
    {
        ConfigHelper config = new ConfigHelper();
        Driver.MyDriver.getDriver(config.getProperty("browser"));
    }

    @Given("I have navigated to the Sheffield University homepage")
    public void i_have_navigated_to_the_sheffield_university_homepage()
    {
     _theHomePage.GoToHomePage();
     _theHomePage.ClickOnRejectCookies();
    }

    @When("I scroll down to bottom of page")
    public void i_scroll_down_to_bottom_of_page()
    {
     _theHomePage.ScrollToEndOfPage();
    }

    @Then("the copyright text is displayed")
    public void the_copyright_text_is_displayed()
    {
        Assert.assertTrue("copyright text not displayed", _theHomePage.IsCopyrightFieldDisplayed());
    }

    @Given("I click on Login to MUSE link")
    public void i_click_on_login_to_muse_link()
    {
        _theHomePage.ClickOnMuseLink();
    }

    @Given("the login page is displayed")
    public void the_login_page_is_displayed()
    {
        Assert.assertTrue(_theLoginPage.LoginControlsDisplayed());
    }

    @When("I enter credentials {string} amd {string}")
    public void i_enter_credentials_amd(String username, String password)
    {
        _theLoginPage.EnterCredentials(username,password);
    }

    @Then("the welcome message is displayed")
    public void the_welcome_message_is_displayed() throws IOException {
        if (_theLoginPage.IsWelcomePageDisplayed())
        {
            Assert.assertTrue(true);
        }
        else
        {
            _theLoginPage.TakeAScreenShot("WelcomeMessage");
            Assert.assertTrue(false);
        }

    }

    @After
    public static void StopBrowser()
    {
        driver.quit();
        System.out.println("Browser stopped");
    }

}
