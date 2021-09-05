package stepdefinitions;

import global.Facade;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ValueWrapper;
import org.testng.Assert;


public class DefinitionSteps {

    private Facade domManipulatorFacade;
    private ValueWrapper<String> productTitleFromProduct;
    private ValueWrapper<String> productTitleFromCart;
    private ValueWrapper<Integer> cartTotalPrice;

    @Before
    public void setupScenario () {
        domManipulatorFacade = new Facade();
        productTitleFromProduct = new ValueWrapper<>();
        productTitleFromCart = new ValueWrapper<>();
        cartTotalPrice = new ValueWrapper<>();
    }

    @Given("User opens {string} home page")
    public void openHomePage(final String url) {
        domManipulatorFacade.open(url);
    }

    @When("User filters products by category by keyword {string}")
    public void findProductsByCategory(final String category) throws InterruptedException {
        domManipulatorFacade.filterProductsByCategory(category);
    }

    @And("User filters products by brand by keyword {string}")
    public void findProductsByBrand(final String brand) {
        domManipulatorFacade.filterProductsByBrand(brand);
    }

    @When("User sorts products from expensive to cheap")
    public void sortProductsFromExpensiveToCheap() {
        domManipulatorFacade.sortProductsFromExpensive();
    }

    @And("User selects the most expensive product")
    public void chooseTheMostExpensiveProduct() {
        domManipulatorFacade.chooseFirstProduct();
    }

    @Then("User gets product title from product")
    public void getProductNameFromProduct() {
        domManipulatorFacade.getProductTitleFromProduct(productTitleFromProduct);
    }

    @When("User adds product to cart")
    public void addProductToTheCart() {
        domManipulatorFacade.addProductToCart();
    }

    @Then("User gets product title from cart")
    public void getProductNameFromCart() {
        domManipulatorFacade.getProductTitleFromCart(productTitleFromCart);
    }

    @Then("User gets cart total price")
    public void getTotalPriceFromCart() {
        domManipulatorFacade.getCartTotalPrice(cartTotalPrice);
    }

    @Then("User checks product title validation")
    public void checkProductTitle() {
        Assert.assertEquals(productTitleFromCart.value, productTitleFromProduct.value);
    }

    @Then("User checks that the total price of the shopping cart is less than {int}")
    public void checkCartTotalPrice(final Integer maxPrice) {
        System.out.printf("Actual price: %s, max expected price: %s\n", cartTotalPrice.value, maxPrice);
        Assert.assertTrue(cartTotalPrice.value < maxPrice);
    }

    @After
    public void tearDownScenario() {
        domManipulatorFacade.close();
    }
}
