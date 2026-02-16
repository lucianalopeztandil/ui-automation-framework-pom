import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ProductCatalogue;
import pageobjects.SucessPage;

import java.io.IOException;


public class ErrorValidationTest extends BaseTest {


        @Test
        public void validationLoginErrorTest() throws IOException {
            landingPage.loginApplication("your_email_here",
                                                         "your_password_here");
            Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
        }

    @Test
    public void productErrorValidationTest() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage
                .loginApplication("your_email_here",
                        "your_password_here");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyDisplayedProduct("Adidas runners");
        Assert.assertFalse(match);
        }
}
