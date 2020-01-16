import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestNGClassOfficeHour2 {

    WebDriver driver ;
    WebDriverWait wait;

    @BeforeMethod
    @Parameters({"username", "password", "path"})
    public void beforeMethod(String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();


        //write the username in the input
        WebElement inputUserName= driver.findElement(By.cssSelector("[formcontrolname=\"username\"]"));
        inputUserName.sendKeys(username);

        //write the password in the input
        WebElement inputPassword =  driver.findElement(By.cssSelector("[formcontrolname=\"password\"]"));
        inputPassword.sendKeys(password);

        //click on login button
        WebElement buttonLogin = driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]"));
        buttonLogin.click();

        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }


    @Test
    public void test(){
        //click on setup
        WebElement buttonSetup = driver.findElement(By.xpath("//span[text()='Setup']"));

        buttonSetup.click();

        //click on parameters
        WebElement buttonParamters = driver.findElement(By.xpath("//span[text()='Parameters']"));

        buttonParamters.click();

        //click on the nationalities
        WebElement buttonNationalities = driver.findElement(By.xpath("//span[text()='Citizenships']"));

        buttonNationalities.click();


        String myUrl = driver.getCurrentUrl();

        Assert.assertEquals(myUrl ,"https://test-basqar.mersys.io/nationality/list" );

        By myel = By.cssSelector("div[aria-label=\" School successfully updated\"]") ;


    }
}
