package pageobjects.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;

import java.time.Duration;

public class AbstractComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartHeader;

    By cartBtn = By.cssSelector("button[routerlink*='cart']");

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(By findBy){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForElementToBeClickable(By findBy){
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public CartPage goToCartPage() throws InterruptedException {
        waitForElementToBeClickable(cartBtn);
        Thread.sleep(4000);
        cartHeader.click();
        return new CartPage(driver);
    }
}
