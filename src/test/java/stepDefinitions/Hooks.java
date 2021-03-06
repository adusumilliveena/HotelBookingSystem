package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks{

    public static WebDriver driver;

    private static Logger Log = Logger.getLogger(Hooks.class.getName());

    /**
     * This will initialize the driver instance.
     */
    @Before
    public void openBrowser() throws IOException {
        Properties configProperties = Helpers.readPropertisFile();

        String browser = System.getProperty("browser");

        if(browser==null)
        {
            browser = configProperties.getProperty("browser");
        }

        switch (browser)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", configProperties.getProperty("chromeDriver"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",configProperties.getProperty("geckoDriver"));
                driver = new FirefoxDriver();
                break;
        }

        System.out.println("Opening Browser...."+browser);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            driver.get(configProperties.getProperty("url"));
    }

    /**
     * This will quit the driver instance
     */
    @After
    public void quiteDriver(Scenario scenario) {
        driver.quit();
    }
    
}