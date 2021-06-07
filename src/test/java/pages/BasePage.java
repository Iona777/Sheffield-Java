package pages;

import HelperClasses.ConfigHelper;
import HelperClasses.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePage
{
    //By setting these here, Base Page does not need to go to Driver class  each time
    protected int BaseTimout;
    protected String BaseURL;
    protected WebDriver BaseDriver = Driver.MyDriver.driver;
    protected String ScreenShotsDir;

    public BasePage()
    {
        ConfigHelper config = new ConfigHelper();
        BaseURL = config.getProperty("baseUrl");
        BaseTimout = Integer.parseInt(config.getProperty("defaultTimeout")) ;
        ScreenShotsDir = config.getProperty("screenshotsDir");
    }

    public WebElement GetElementByVisibleText(String searchText)
    {
        WebDriverWait wait = new WebDriverWait(BaseDriver, BaseTimout);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'" + searchText + "')]")));
    }

    public boolean IsElementDisplayedByLocator(By locator)
    {
        try
        {
            return GetClickableElementByLocator(locator).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean IsElementDisplayedByText(String searchText)
    {
        try
        {
            return GetElementByVisibleText(searchText).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void ScrollToEndOfPage()
    {
        ((JavascriptExecutor) BaseDriver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public WebElement GetClickableElementByLocator(By elementLocator)
    {

        WebDriverWait wait = new WebDriverWait(BaseDriver, BaseTimout);

        return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public void ClickOnElement(By elementLocator)
    {
        WebElement ele =
        GetClickableElementByLocator(elementLocator);
        ele.click();
    }

    public void EnterText(By elementLocator, String value, Boolean clear)
    {
        WebElement element = GetClickableElementByLocator(elementLocator);
        if (clear.equals(true))
            element.clear();

        element.sendKeys(value);
    }

    public void TakeAScreenShot(String fileName) throws IOException
    {
        //String logFileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
        //logFileName = "loggerFile_" + logFileName;
        String logFileName = new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date());
        logFileName = "SheffieldLogFile_" + logFileName;

        File scrFile = ((TakesScreenshot)BaseDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(ScreenShotsDir+fileName+logFileName));
        //FileUtils.copyFile(scrFile, new File(ScreenShotsDir+fileName+logFileName+".png"));
    }
}
