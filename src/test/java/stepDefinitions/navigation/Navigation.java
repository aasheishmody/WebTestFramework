package stepDefinitions.navigation;

import applicationComponents.pages.HomePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import stepDefinitions.StepDefinitions;

import static util.TestLogger.asserting;

public class Navigation extends StepDefinitions {

    @Then("the 'Home' page is displayed")
    public void theHomePageIsDisplayed() {
        asserting("that the 'Home' page is displayed", () -> Assert.assertTrue(HomePage.getInstance().homePageIsDisplayed()));
    }
}