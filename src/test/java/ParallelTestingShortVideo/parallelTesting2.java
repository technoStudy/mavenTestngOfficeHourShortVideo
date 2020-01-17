package ParallelTestingShortVideo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class parallelTesting2 {

    WebDriver driver ;
    WebDriverWait wait;

    @BeforeMethod
    @Parameters({"username", "password", "path"})
    public void beforeMethod(String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        // login info
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

    @Test(priority = 2)
    public void URLNationality(){

        //click on setup
        WebElement buttonSetup = driver.findElement(By.xpath("//span[text()='Setup']"));

        buttonSetup.click();

        //click on parameters
        WebElement buttonParamters = driver.findElement(By.xpath("//span[text()='Parameters']"));

        buttonParamters.click();

        //click on the nationalities
        WebElement buttonNationalities = driver.findElement(By.xpath("//span[text()='Nationalities']"));

        buttonNationalities.click();

        WebElement buttonPlus = driver.findElement(By.xpath("//ms-add-button//button"));
        try{

            wait.until(ExpectedConditions.elementToBeClickable(buttonPlus));
        }catch(Exception e){
            System.out.println("plus icon is not clickable ");
        }

        String myUrl = driver.getCurrentUrl();

        Assert.assertEquals(myUrl ,"https://test-basqar.mersys.io/nationality/list" );

    }


    @Test(priority = 1)
    public void URLCitizenship(){

        //click on setup
        WebElement buttonSetup = driver.findElement(By.xpath("//span[text()='Setup']"));

        buttonSetup.click();

        //click on parameters
        WebElement buttonParamters = driver.findElement(By.xpath("//span[text()='Parameters']"));

        buttonParamters.click();

        //click on the nationalities
        WebElement buttonCitizenship = driver.findElement(By.xpath("//span[text()='Citizenships']"));

        buttonCitizenship.click();

        WebElement buttonPlus = driver.findElement(By.xpath("//ms-add-button//button"));
        try{

            wait.until(ExpectedConditions.elementToBeClickable(buttonPlus));
        }catch(Exception e){
            System.out.println("plus icon is not clickable ");
        }

        String myUrl = driver.getCurrentUrl();

        Assert.assertEquals(myUrl ,"https://test-basqar.mersys.io/citizenships/list" );

    }


}