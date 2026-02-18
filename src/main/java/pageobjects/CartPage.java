package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.AbstractComponents.AbstractComponent;

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
        clickOption(checkout);
        return new CheckoutPage(driver);
    }
}
