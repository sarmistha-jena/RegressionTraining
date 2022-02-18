package epambstack.suite;

import epambstack.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuiteTest01 extends TestBase {

    @Test
    public void test_01() throws Exception {
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack Test 01");
        element.submit();
        Thread.sleep(5000);

        Assert.assertEquals("BrowserStack Test 01 - Google Search", driver.getTitle());
    }
}
