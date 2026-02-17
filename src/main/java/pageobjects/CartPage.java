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

public class CartPage extends AbstractComponent {

    By checkout = By.xpath("//button[text()='Checkout']");

    @FindBy(css = ".cartSection h3")
    WebElement selectedProduct;

    public CartPage(WebDriver driver){
        super(driver);
    }

    public boolean verifyDisplayedProduct(String name){
        String product = selectedProduct.getText();
        return product.equals(name);
    }

    public CheckoutPage goToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement freshCheckoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(checkout));

        try {
            freshCheckoutBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", freshCheckoutBtn);
        }
        return new CheckoutPage(driver);
    }
}
