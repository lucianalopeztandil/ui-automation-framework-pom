package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.AbstractComponents.AbstractComponent;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends AbstractComponent {

    @FindBy(xpath = "//input[@placeholder = 'Select Country']")
    WebElement inputCountry;

    @FindBy(css = ".ta-item")
    List<WebElement> countries;

    By submitAction = By.cssSelector(".action__submit");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void selectCountry(String countryName){
        inputCountry.sendKeys(countryName);

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
        wait.until(ExpectedConditions.visibilityOfAllElements(countries));

        WebElement target = countries.stream()
                .filter(s -> s.getText().equalsIgnoreCase("India"))
                .findFirst()
                .orElse(null);

        if (target != null) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(target)).click();
            } catch (Exception e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", target);
            }
        }
    }

    public SucessPage clickSubmit(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement freshSubmitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(submitAction));
        try {
            freshSubmitBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", freshSubmitBtn);
        }
        return new SucessPage(driver);
    }
}
