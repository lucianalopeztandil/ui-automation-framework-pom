package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.AbstractComponents.AbstractComponent;

public class SucessPage extends AbstractComponent {

    @FindBy(css = ".hero-primary")
    WebElement msg;

    By sucessMessage = By.cssSelector(".hero-primary");

    public SucessPage(WebDriver driver){
        super(driver);
    }

    public void printMessage(){
        waitForElementToAppear(sucessMessage);
        System.out.println(msg.getText());

    }
}
