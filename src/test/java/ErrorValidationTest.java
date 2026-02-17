import TestComponents.BaseTest;
import data.DataReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ProductCatalogue;
import pageobjects.SucessPage;

import java.io.IOException;
import java.util.HashMap;


public class ErrorValidationTest extends BaseTest {


    @Test(dataProvider = "getData", dataProviderClass = DataReader.class)
        public void loginTest(HashMap<String, String> input) throws IOException {
            landingPage.loginApplication(input.get("email"),
                    input.get("password"));
            Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
        }

    @Test(dataProvider = "getData", dataProviderClass = DataReader.class)
    public void validationTest(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = landingPage
                .loginApplication(input.get("email"),
                        input.get("password"));

        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyDisplayedProduct(input.get("wrong_product"));
        Assert.assertFalse(match);
        }
}
