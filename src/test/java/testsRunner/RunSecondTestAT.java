package testsRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import util.WebConnector;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/2.json" + ""},
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        tags = {"@,@,@"},
        monochrome = true
)

public class RunSecondTestAT {
    @BeforeClass
    public static void beforeTest() throws Exception {
        WebConnector.getInstance().initializeTestFramework();
    }

    @AfterClass
    public static void closeBrowser() {
        WebConnector.getInstance().getBrowser().quit();
    }

}