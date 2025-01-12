package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BasePage {
    WebDriver driver;
    Properties properties;
    public BasePage(WebDriver driver) throws IOException {
        this.driver=driver;
        this.properties= new Properties();
        InputStream input= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//test.properties");
        this.properties.load(input);
    }
    public String getProperty(String key){
        return properties.get(key).toString();
    }
    public void enterText(String webElement, String value){
        driver.findElement(By.xpath(webElement)).sendKeys(value+ Keys.ENTER);
    }

    public void clickOnElement(String webElement){
        driver.findElement(By.xpath(webElement)).click();
    }

    public void switchToChildWindow(String mainWindowHandle){
        for (String childWindowHandle : driver.getWindowHandles()) {
            if(!childWindowHandle.equals(mainWindowHandle)){
                driver.switchTo().window(childWindowHandle);
            }
        }
    }

    public String getText(String webElement){
        return driver.findElement(By.xpath(webElement)).getText();
    }

   public void waitTillElementIsVisible(String webElement){
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)));
    }
}
