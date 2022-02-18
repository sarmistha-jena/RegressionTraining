package epambstack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LocalTest extends TestBase {

    @Test
    public void test() throws Exception {
        driver.get("http://bs-local.com:45691/check");

        Assert.assertTrue(driver.getPageSource().contains("Up and running"));
    }
}
