package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submitBtn;

    @FindBy(css=".toast-error")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password) throws InterruptedException {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        //wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        Thread.sleep(10000);
        submitBtn.click();
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
