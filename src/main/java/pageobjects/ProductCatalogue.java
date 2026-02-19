package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    public ProductCatalogue(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3")
    private List<WebElement> products;

    private By productsSelector = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".card-body button:last-of-type");
    private By productName = By.cssSelector("div h5");
    private By toastMessage = By.id("toast-container");
    private By animation = By.cssSelector(".ng-animating");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsSelector);
        return products;
    }

    public WebElement getProductByName(String name){
        return getProductList().stream().filter(product -> product.
                findElement(productName)
                .getText().equals(name)).findFirst().orElse(null);
    }

    public void addProductToCart(String name){
        WebElement product = getProductByName(name);
        product.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(animation);
    }

}
