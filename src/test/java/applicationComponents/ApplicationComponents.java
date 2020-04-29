package applicationComponents;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebConnector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class ApplicationComponents {

    @FindBy(tagName = "a")
    private List<WebElement> AllLinks;

    public ApplicationComponents() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebConnector.getInstance().getBrowser()), this);
    }

    public void checkAllLinksAreWorking() throws IOException {
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        for (WebElement link : AllLinks) {

            url = link.getAttribute("href");

            System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is a broken link");
                } else {
                    System.out.println(url + " is a valid link");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebDriver driver, WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitAndClick(WebDriver driver, WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void switchToNewTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void waitUntilNewTabIsOpen(WebDriver driver, int timeOut) {
        new WebDriverWait(driver, timeOut).until(driverWait -> driverWait.getWindowHandles().size() == 2);
    }

    public void closeAllTabsExceptOriginalWindow(WebDriver driver, String originalWindowHandle) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindowHandle)) {
                WebConnector.getInstance().getBrowser().switchTo().window(handle);
                WebConnector.getInstance().getBrowser().close();
            }
        }
    }

    public String getOriginalWindowHandle() {
        return WebConnector.getInstance().getBrowser().getWindowHandle();
    }

    public void switchToOriginalTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(WebConnector.getInstance().getBrowser().getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void scrollToBottomOfPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public boolean isNotDisplayed(WebElement element) {
        boolean result = false;
        try {
            element.isDisplayed();
            result = false;
        } catch (Exception e) {
            result = true;
        }
        return result;
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static WebElement getFirstItemOfList(WebDriver driver, List<WebElement> element) {
        return element.get(0);
    }

    public static WebElement getLastItemOfList(WebDriver driver, List<WebElement> element) {
        if (!element.isEmpty()) {
            return element.get(element.size() - 1);
        } else {
            // To be sure that ln251 is fully covered.
            Assert.fail("There are no elements for: " + element.toString());
            return null;
        }
    }
}