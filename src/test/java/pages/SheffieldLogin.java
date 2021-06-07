package pages;

import org.openqa.selenium.By;

public class SheffieldLogin extends BasePage
{

    private final By usernameLocator =By.id("username");
    private final By passwordLocator =By.id("password");
    private final By loginButtonLocator =By.name("submit");


    public boolean LoginControlsDisplayed()
    {
        if (
        (IsElementDisplayedByLocator(usernameLocator)) &&
        (IsElementDisplayedByLocator(passwordLocator)) &&
        (IsElementDisplayedByLocator(loginButtonLocator))
        )
            return  true;
        else
            return  false;
    }

    public void EnterCredentials(String username, String password)
    {
        EnterText(usernameLocator, username, false);
        EnterText(passwordLocator,password,false);
        ClickOnElement(loginButtonLocator);
    }

    public boolean IsWelcomePageDisplayed()
    {
         return IsElementDisplayedByText("Welcome to Sheffield");
    }
}
