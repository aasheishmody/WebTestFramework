package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static util.TestLogger.getLogger;
import static util.TestLogger.setLogger;


public class WebConnector {

    private static WebConnector webconnector;
    private WebDriver driver;
    private String baseURL;
    private Properties properties;

    static {
        webconnector = new WebConnector();
    }

    private WebConnector() {

    }

    public static WebConnector getInstance() {
        return webconnector;
    }

    public void initializeTestFramework() throws Exception {
        setLogger();
        getLogger().info("Initializing Test Framework");
        setProperties();
        setBaseURL();
        //setLanguage();
        setBrowser();
        getLogger().info("Test Framework initialized");
    }


    public void setBrowser() throws Exception {
        getLogger().info("Setting browser");
        if (driver == null) {
            switch (properties.getProperty("browser")) {

                case "chrome":
                    useChrome();
                    break;

                case "firefox":
                    useFirefox();
                    break;

                case "safari":
                    //if ((properties.getProperty("operatingSystem")).contains("Windows"))
                    //throw new Exception("Tests on Safari can only run on Mac operating systems. Please select appropriate operating system");
                    useSafari();
                    break;

                case "edge":
                    //if ((properties.getProperty("operatingSystem")).contains("mac"))
                    //throw new Exception("Tests on Edge can only run on Windows operating systems. Please select appropriate operating system");
                    useEdge();
                    break;

                case "internetExplorer":
                    //if ((properties.getProperty("operatingSystem")).contains("mac"))
                    //throw new Exception("Tests on Internet Explorer can only run on Windows operating systems. Please select appropriate operating system");
                    useInternetExplorer();
                    break;
            }
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        getLogger().info("Browser set");
    }

    public WebDriver getBrowser() {
        return driver;
    }

    public void setBaseURL() {
        getLogger().info("Setting base URL");
        baseURL = properties.getProperty("environment");
        getLogger().info("Base URL set");
    }

    public String getBaseURL() {
        return baseURL;
    }


    public void setProperties() {
        properties = new Properties();
        try {
            properties.load(WebConnector.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    private void useChrome() throws MalformedURLException {
        switch (properties.getProperty("host")) {

            case "local":
                WebDriverManager.chromedriver().setup();
                //WebDriverManager.getInstance(CHROME).setup();
                driver = new ChromeDriver();
                break;

            case "cloud":
                ChromeOptions caps = new ChromeOptions();
                caps.setCapability("version", properties.getProperty("browserVersion"));
                //caps.setCapability("platform", properties.getProperty("operatingSystem"));
                caps.setExperimentalOption("w3c", true);
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", properties.getProperty("sauceUserName"));
                sauceOptions.setCapability("accessKey", properties.getProperty("sauceAccessKey"));
                caps.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(new URL(properties.getProperty("cloudURL")), caps);
                break;
        }
    }

    private void useFirefox() throws MalformedURLException {
        switch (properties.getProperty("host")) {

            case "local":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "cloud":
                FirefoxOptions caps = new FirefoxOptions();
                caps.setCapability("version", properties.getProperty("browserVersion"));
                //caps.setCapability("platform", properties.getProperty("operatingSystem"));
                //caps.setExperimentalOption("w3c", true);
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", properties.getProperty("sauceUserName"));
                sauceOptions.setCapability("accessKey", properties.getProperty("sauceAccessKey"));
                caps.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(new URL(properties.getProperty("cloudURL")), caps);
                break;
        }
    }

    private void useSafari() throws MalformedURLException {
        switch (properties.getProperty("host")) {

            case "local":
                driver = new SafariDriver();
                break;

            case "cloud":
                SafariOptions caps = new SafariOptions();
                caps.setCapability("version", properties.getProperty("browserVersion"));
                //caps.setCapability("platform", properties.getProperty("operatingSystem"));
                //caps.setExperimentalOption("w3c", true);
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", properties.getProperty("sauceUserName"));
                sauceOptions.setCapability("accessKey", properties.getProperty("sauceAccessKey"));
                caps.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(new URL(properties.getProperty("cloudURL")), caps);
                break;
        }
    }

    private void useEdge() throws MalformedURLException {
        switch (properties.getProperty("host")) {

            case "local":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "cloud":
                EdgeOptions caps = new EdgeOptions();
                caps.setCapability("version", properties.getProperty("browserVersion"));
                //caps.setCapability("platform", properties.getProperty("operatingSystem"));
                //caps.setExperimentalOption("w3c", true);
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", properties.getProperty("sauceUserName"));
                sauceOptions.setCapability("accessKey", properties.getProperty("sauceAccessKey"));
                caps.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(new URL(properties.getProperty("cloudURL")), caps);
                break;
        }
    }

    private void useInternetExplorer() throws MalformedURLException {
        switch (properties.getProperty("host")) {

            case "local":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case "cloud":
                InternetExplorerOptions caps = new InternetExplorerOptions();
                caps.setCapability("version", properties.getProperty("browserVersion"));
                //caps.setCapability("platform", properties.getProperty("operatingSystem"));
                //caps.setExperimentalOption("w3c", true);
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", properties.getProperty("sauceUserName"));
                sauceOptions.setCapability("accessKey", properties.getProperty("sauceAccessKey"));
                caps.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(new URL(properties.getProperty("cloudURL")), caps);
                break;
        }
    }
}
