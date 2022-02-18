package epambstack.suite;

import epambstack.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuiteTest03 extends TestBase {

    @Test
    public void test_03() throws Exception {
        driver.get("https://www.google.com/ncr");
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 03");
        Thread.sleep(5000);
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("BrowserStack Test 03 - Google Search", driver.getTitle());
    }
}
