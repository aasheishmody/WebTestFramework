package applicationComponents.pages;


import applicationComponents.ApplicationComponents;
import org.openqa.selenium.support.FindBy;
import webElements.Text;

public class HomePage extends ApplicationComponents {

    @FindBy(xpath = "")
    private Text HomePageIdentifier;

    private String homePageURL = "";

    private static HomePage homePage;

    static {
        homePage = new HomePage();
    }

    HomePage() {
        super();
    }

    public static HomePage getInstance() {
        return homePage;
    }

    public boolean homePageIsDisplayed() {
        return HomePageIdentifier.isDisplayed();
    }
}