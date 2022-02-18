package epambstack.suite;

import epambstack.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuiteTest05 extends TestBase {

    @Test
    public void test_05() throws Exception {
        driver.get("https://www.google.com/ncr");
        Thread.sleep(7000);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 05");
        element.submit();
        Thread.sleep(7000);

        Assert.assertEquals("BrowserStack Test 05 - Google Search", driver.getTitle());
    }
}
