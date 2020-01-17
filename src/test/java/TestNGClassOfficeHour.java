import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestNGClassOfficeHour {


    /*
    *       Pom.xml vs testng.xml
    *
    *           Pom.xml: comes from the maven. Helps you to organize your jar files basically your dependencies.
    *
    *           testng.xml: comes from the TestNG.
    *                       it helps me  to run the multiple class in the same time
    *                       I can use a parameters so if I need to change my parameters easily
    *                       It helps us to run the code parallel it save a time for us
    *
    *       What is Testng
    *               Is a tool to create a framework.    (Maven, cucumeber, SQL , API , Jenkins )
    *               TestNG has multiple annotations like :
        *               @BeforeSuite.
                        @BeforeTest.
                        @BeforeClass.
                        @BeforeMethod.
                        @Test.
                        @AfterMethod.
                        @AfterClass.
                        @AfterTest.
                        @AfterSuite

                    While we are working with a main method we were writing the login in every class. But with a TestNG we need
                       to type it once so we will not duplicate our code (we are not going to write the same code again and again )


    *
    *       Why use testng with selenium
    *               If we want to run the code multiple times then we can use testng with a 'invocation count'
    *               Annotations help us to not duplicate the code
    *               I can use a parameters so if I need to change my parameters easily
    *               TestNG is easily integrated with maven and jenkins
    *
    *
    *       Advantages of TestNG over JUnit (TesNG vs Junit)
    *               Testng has more annotations then junit
    *               Junit doesnt have the .xml file
    *               Parallel testing is possible in testng and not in junit (if you want to achive parallel testing in junit
    *           you need a selenium grid)
    *               Group test is available in the testng but not in the junit
    *
    *
     */

//@BeforeSuite run the code before every suite in the .xml file

//@BeforeTest runs the code before every test in the .xml file

//@BeforeMethod runs the code before every @Test in the java class

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
    @Test
    public void test(){

        //click on setup
        WebElement buttonSetup = driver.findElement(By.xpath("//span[text()='Setup']"));

        buttonSetup.click();

        //click on parameters
        WebElement buttonParamters = driver.findElement(By.xpath("//span[text()='Parameters']"));

        buttonParamters.click();

        //click on the nationalities
        WebElement buttonNationalities = driver.findElement(By.xpath("//span[text()='Nationalities']"));

        buttonNationalities.click();

        // element for plus icon
        WebElement buttonPlus = driver.findElement(By.xpath("//ms-add-button//button"));

        //wait until plus icon is clickable
        try{
            wait.until(ExpectedConditions.elementToBeClickable(buttonPlus));
        }catch(Exception e){
            System.out.println("plus icon is not clickable ");
        }

        //get the url
        String myUrl = driver.getCurrentUrl();

        // verify url is correct
        Assert.assertEquals(myUrl ,"https://test-basqar.mersys.io/nationality/list" );

    }
}