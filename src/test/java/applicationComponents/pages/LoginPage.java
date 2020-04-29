package applicationComponents.pages;


import applicationComponents.ApplicationComponents;
import org.openqa.selenium.support.FindBy;
import util.WebConnector;
import webElements.TextBox;

public class LoginPage extends ApplicationComponents {

    @FindBy(id = "")
    private TextBox UsernameTextBox;
    @FindBy(id = "")
    private TextBox PasswordTextBox;
    @FindBy(id = "")
    private TextBox LoginButton;

    private String loginPageURL = "";

    private static LoginPage loginPage;

    static {
        loginPage = new LoginPage();
    }

    private LoginPage() {
        super();
    }

    public static LoginPage getInstance() {
        return loginPage;
    }

    public boolean loginPageIsDisplayed() {
        return UsernameTextBox.isDisplayed();
    }

    public void navigateToLoginPage() {
        WebConnector.getInstance().getBrowser().get(loginPageURL);
    }

    public void enterEmailAddress(String emailAddress) {
        UsernameTextBox.sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        PasswordTextBox.sendKeys(password);
    }

    public void clickLoginButton() {
        LoginButton.click();
    }
}