package oneBeforeMethod;

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

public class testTwo extends hooksClass{

    /*
        Working with one before method
     */



    @Test
    public void searchcarAccesories(){

//        search in the input
        String searh = "carAccesories";
        WebElement inputSearch = driver.findElement(By.id("global-enhancements-search-query"));
        inputSearch.sendKeys(searh);

//        click on search button
        WebElement buttonSearch = driver.findElement(By.xpath("//button[@value='Search']"));
        buttonSearch.click();

        WebElement textH1 = driver.findElement(By.xpath("//h1[@class='display-inline strong wt-text-caption']"));

        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            wait.until(ExpectedConditions.visibilityOf(textH1));
        }catch(Exception e){

            System.out.println("Element is not visible");
        }


//        get the url
        String myUrl = driver.getCurrentUrl();

//        verify url contains the search
        Assert.assertTrue(myUrl.contains(searh));



    }

}
