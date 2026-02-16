package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.AbstractComponents.AbstractComponent;

public class SucessPage extends AbstractComponent {

    @FindBy(css = ".hero-primary")
    WebElement msg;

    public SucessPage(WebDriver driver){
        super(driver);
    }

    public void printMessage(){
        System.out.println(msg.getText());

    }
}
