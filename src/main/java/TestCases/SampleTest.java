package TestCases;

import Pages.CoinDeskAPIPage;
import Pages.EBayPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;

import java.io.IOException;

public class SampleTest {

    WebDriver driver;
    EBayPage eBayPage;
    CoinDeskAPIPage coinDeskAPIPage;

    public SampleTest() throws IOException {
    }

    @BeforeClass
    public void setupClass() throws IOException {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void test2(){
        driver = new ChromeDriver();
    }

    @Test
    public void verifyItemCanBeAddedToCart() throws InterruptedException, IOException {
        eBayPage = new EBayPage(driver);
        eBayPage.launchTheBrowser();
        String mainWindowHandle = driver.getWindowHandle();
        eBayPage.searchBookAndClickOnFirstRecord();
        eBayPage.switchToChildWindow(mainWindowHandle);
        eBayPage.clickOnAddToCart();
        eBayPage.verifyCartHasBeenAddedAndShowingCorrectNumber(1);
    }

    @Test
    public void apiCoinDeskTest() throws IOException {
        coinDeskAPIPage = new CoinDeskAPIPage(driver);
        coinDeskAPIPage.setBaseURL();
        Response response = coinDeskAPIPage.getRequest();
        coinDeskAPIPage.validateBPICurrencyValues(response);
        coinDeskAPIPage.validateGBPCurrencyDescription(response);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
