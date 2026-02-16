package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutBtn;

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
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}
