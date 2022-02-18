package epambstack;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase1 extends TestBase {

    @Test
    public void test1() throws Exception {
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("EPAM");
        element.submit();
        Thread.sleep(5000);

        Assert.assertTrue(driver.getTitle().matches("(?i)EPAM - Google Search"));
    }
    @Test
    public void test2() throws Exception {
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Hello");
        element.submit();
        Thread.sleep(5000);

        Assert.assertTrue(driver.getTitle().matches("(?i)EPAM - Google Search"));
    }
    @Test
    public void verifyAlert(){
        driver.navigate().to("http://demo.automationtesting.in/Alerts.html");
        SoftAssert softAssert=new SoftAssert();
        softAssert.fail("failing");
        driver.findElement(By.xpath("//a[@href='#CancelTab']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'btn btn-primary')]")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.dismiss();
    }
}
