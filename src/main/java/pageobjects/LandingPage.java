package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.AbstractComponents.AbstractComponent;

import java.time.Duration;

public class LandingPage extends AbstractComponent {

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(css=".toast-error")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);

        By loginButton = By.id("login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement freshLoginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));

        try {
            freshLoginBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", freshLoginBtn);
        }

        return new ProductCatalogue(driver);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

}
