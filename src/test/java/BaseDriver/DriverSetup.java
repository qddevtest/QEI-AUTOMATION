package BaseDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverSetup {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup(); // Automatically downloads and sets up ChromeDriver
        driver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
