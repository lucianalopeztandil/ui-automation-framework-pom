package TestComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

        WebDriver driver;
        protected LandingPage landingPage;

        public WebDriver setup() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
        else{
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
            driver = setup();
            landingPage = new LandingPage(driver);
            landingPage.goTo();
            return landingPage;
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Closing driver...");
        driver.quit();
        System.out.println("Driver closed.");

    }

}
