package Pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CoinDeskAPIPage extends BasePage{
    WebDriver driver;
    JsonPath js;
    public CoinDeskAPIPage(WebDriver driver) throws IOException {
        super(driver);
        this.driver=driver;
    }
    public void setBaseURL(){
        RestAssured.baseURI=getProperty("apiBaseURL");
    }
    public Response getRequest(){
        return given().log().all().when().log().all().get();
    }
    public void validateBPICurrencyValues(Response response){
        try {
            js = new JsonPath(response.asString());
            int bpiCount = js.getInt("bpi.size()");
            Assert.assertEquals(bpiCount, 3);
            Map<String, Object> bpi = response.jsonPath().getMap("bpi");

            Assert.assertTrue(bpi.containsKey("USD"));
            Assert.assertTrue(bpi.containsKey("GBP"));
            Assert.assertTrue(bpi.containsKey("EUR"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void validateGBPCurrencyDescription(Response response){
        try {
            js = new JsonPath(response.asString());
            String gbpDescription = js.get("bpi.GBP.description");
            Assert.assertEquals(gbpDescription, "British Pound Sterling");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
