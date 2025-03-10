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
        DesiredCapabilities caps = new DesiredCapabilities();
        // BrowserStack credentials
        caps.setCapability("browserstack.user", "");
        caps.setCapability("browserstack.key", "");

        // Browser and OS configuration
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "11");
        caps.setCapability("project", "QEI AUTOMATION");
        caps.setCapability("build", "QEI TEST BUILD");

        // Read Test Observability flag from command line
        String testObservability = System.getProperty("testObservability", "false"); // Default to false if not set
        caps.setCapability("browserstack.testObservability", testObservability);
        // Initialize WebDriver for BrowserStack
        driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
