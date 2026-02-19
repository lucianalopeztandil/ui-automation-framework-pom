package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.AbstractComponents.AbstractComponent;
import java.util.List;

public class CheckoutPage extends AbstractComponent {

    @FindBy(xpath = "//input[@placeholder = 'Select Country']")
    private WebElement inputCountry;

    @FindBy(css = ".ta-item")
    private List<WebElement> countries;

    private By submitAction = By.cssSelector(".action__submit");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void selectCountry(String countryName){
        inputCountry.sendKeys(countryName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        By botonesSelector = By.cssSelector(".ta-results button");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(botonesSelector, 0));
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
        clickOption(submitAction);
        return new SucessPage(driver);
    }
}
