package pages;

import HelperClasses.Driver;
import org.openqa.selenium.By;

public class SheffieldHome extends BasePage
{

    private final String copyrightLocatorText = "© 2021 The University of Sheffield";
    private final By rejectCookiesLocator = By.cssSelector("button[id='onetrust-reject-all-handler']");
    private final String loginHrefLocator = "https://www.sheffield.ac.uk/nap/panel/login";


    public void GoToHomePage()
    {
        Driver.MyDriver.navigateTo(BaseURL);
    }

    public void ClickOnRejectCookies()
    {
        if (IsElementDisplayedByLocator(rejectCookiesLocator))
            ClickOnElement(rejectCookiesLocator);
    }

    public boolean IsCopyrightFieldDisplayed()
    {
        return IsElementDisplayedByText(copyrightLocatorText);
    }

    public void ClickOnMuseLink()
    {
        //Using ClickOnElement(loginLocator); should work but does not, so navigating to site directly
        Driver.MyDriver.navigateTo(loginHrefLocator);
    }

}
