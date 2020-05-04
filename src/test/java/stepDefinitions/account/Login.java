package stepDefinitions.account;

import applicationComponents.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import stepDefinitions.StepDefinitions;

import static util.TestLogger.*;

public class Login extends StepDefinitions {

    @Given("I navigate to the 'Login' page")
    public void iNavigateToTheLoginPage() {
        navigating("to the 'Login' page as a logged out user", () -> LoginPage.getInstance().navigateToLoginPage());
    }

    @When("I enter the following details on the 'Login' page")
    public void iEnterTheFollowingDetailsOnTheLoginPage(DataTable loginDetails) {
        entering("details on the 'Login' page", () -> {
            LoginPage.getInstance().enterEmailAddress("Pass email address here");
            LoginPage.getInstance().enterPassword("Pass password here");
        });
    }

    @And("I click the 'Login' button on the 'Login' page")
    public void iClickTheLOGINButtonOnTheLoginPage() {
        clicking("'Login' button on the 'Login' page", () -> LoginPage.getInstance().clickLoginButton());
    }
}