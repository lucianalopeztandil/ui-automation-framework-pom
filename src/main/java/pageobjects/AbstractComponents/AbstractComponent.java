package pageobjects.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement freshCartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(cartBtn));

        try {
            freshCartBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", freshCartBtn);
        }
        return new CartPage(driver);
    }

    public void clickOption(By submitAction){
        WebElement freshSubmitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(submitAction));
        try {
            freshSubmitBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", freshSubmitBtn);
        }
    }

}
