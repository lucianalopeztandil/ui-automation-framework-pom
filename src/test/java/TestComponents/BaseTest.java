package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

        protected WebDriver driver;
        protected LandingPage landingPage;
        private static final Logger logger = LogManager.getLogger(BaseTest.class);


      public WebDriver setup() throws IOException {

        logger.info("Se comienza con el setup de la aplicacion");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        logger.info("El navegador en que se ejecutara la prueba es: " + browserName);

          if (browserName.equalsIgnoreCase("chrome")) {

              ChromeOptions options = new ChromeOptions();
              options.addArguments("--headless=new");
              options.addArguments("--no-sandbox");
              options.addArguments("--disable-dev-shm-usage");
              options.addArguments("--window-size=1920,1080");

              WebDriverManager.chromedriver().setup();

              driver = new ChromeDriver(options);

          } else if (browserName.equalsIgnoreCase("firefox")) {

              FirefoxOptions options = new FirefoxOptions();
              options.addArguments("-headless");
              options.addArguments("--window-size=1920,1080");

              WebDriverManager.firefoxdriver().setup();

              driver = new FirefoxDriver(options);
          }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
            driver = setup();
            landingPage = new LandingPage(driver);
            logger.info("Se carga la url");
            landingPage.goTo();
            return landingPage;
    }

    @AfterMethod
    public void tearDown(){
        logger.info("Closing driver...");
        driver.quit();
        logger.info("Driver closed.");
    }

}
