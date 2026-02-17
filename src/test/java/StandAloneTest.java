import TestComponents.BaseTest;
import data.DataReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

import java.util.HashMap;


public class StandAloneTest extends BaseTest {


        @Test(dataProvider = "getData", dataProviderClass = DataReader.class)
        public void purchaseOrderTest(HashMap<String, String> input) throws InterruptedException {
            ProductCatalogue productCatalogue = landingPage
                                                 .loginApplication(input.get("email"),
                                                         input.get("password"));

            productCatalogue.addProductToCart(input.get("product"));
            CartPage cartPage = productCatalogue.goToCartPage();

            boolean match = cartPage.verifyDisplayedProduct(input.get("product"));
            Assert.assertTrue(match);
            CheckoutPage checkoutPage = cartPage.goToCheckout();
            checkoutPage.selectCountry("Ind");
            SucessPage sucessPage = checkoutPage.clickSubmit();
            sucessPage.printMessage();
    }
}
