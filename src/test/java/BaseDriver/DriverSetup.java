package BaseDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;


public class DriverSetup {

   public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        String  projectName = System.getenv("BROWSERSTACK_PROJECT");
        String  buildName = System.getenv("BROWSERSTACK_BUILD");
        String  sessionName = System.getenv("BROWSERSTACK_NAME");
        DesiredCapabilities caps = new DesiredCapabilities();
        // BrowserStack credentials
        caps.setCapability("browserstack.user", username);
        caps.setCapability("browserstack.key", accessKey);

        // Browser and OS configuration
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "11");
        caps.setCapability("project", projectName);
        caps.setCapability("build", buildName);
        caps.setCapability("name", sessionName);


        // Read Test Observability flag from command line
        String testObservability = System.getProperty("TEST OBSERVABILITY", "false"); // Default to false if not set
        caps.setCapability("browserstack.testObservability", testObservability);
        // Initialize WebDriver for BrowserStack
        driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
