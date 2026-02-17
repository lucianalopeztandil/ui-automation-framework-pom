package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.AbstractComponents.AbstractComponent;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    @FindBy(xpath = "//input[@placeholder = 'Select Country']")
    WebElement inputCountry;

    @FindBy(css = ".ta-item")
    List<WebElement> countries;

    @FindBy(css = ".action__submit")
    WebElement submitBtn;

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void selectCountry(String countryName){
        inputCountry.sendKeys(countryName);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
        countries.stream()
                .filter(country -> country.getText().equalsIgnoreCase("India"))
                .findFirst()
                .ifPresent(country -> {
                    wait.until(ExpectedConditions.elementToBeClickable(country)).click();
                });
    }

    public SucessPage clickSubmit(){
        submitBtn.click();
        return new SucessPage(driver);
    }
}
