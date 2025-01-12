package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EBayPage extends BasePage {
    WebDriver driver;
    public EBayPage(WebDriver driver) throws IOException {
        super(driver);
        this.driver=driver;
    }
    public void launchTheBrowser(){
        driver.get(getProperty("appURL"));
        driver.manage().window().maximize();
    }
    public void searchBookAndClickOnFirstRecord() throws InterruptedException {
        try {
            enterText(getProperty("searchTextBox"), "Book");
            waitTillElementIsVisible(getProperty("firstRecord"));
            clickOnElement(getProperty("firstRecord"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clickOnAddToCart(){
        try {
            waitTillElementIsVisible(getProperty("btn_addToCart"));
            clickOnElement(getProperty("btn_addToCart"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void verifyCartHasBeenAddedAndShowingCorrectNumber(int expectedCartNumber){
        try {
            waitTillElementIsVisible(getProperty("txt_numberOfCart"));
            String numberOfItemsInCart = getText(getProperty("txt_numberOfCart"));
            Assert.assertEquals(Integer.parseInt(numberOfItemsInCart), expectedCartNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
