package stepDefinitions.hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import util.WebConnector;


public class Hooks {

    @Before
    public void beforeTest() {
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver augmentedDriver = new Augmenter().augment(WebConnector.getInstance().getBrowser());
            byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/jpeg");
        }
        WebConnector.getInstance().getBrowser().manage().deleteAllCookies();
    }
}