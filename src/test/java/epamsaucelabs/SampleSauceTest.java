package epamsaucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;

public class SampleSauceTest {

    public static final String USERNAME = "SarmisthaJena";
    public static final String ACCESS_KEY = "ea90bd3e-c363-499e-8d72-9dc87ea65be6";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        caps.setCapability("name", "Test1");
        caps.setCapability("extendedDebugging", "true");
        caps.setCapability("buildNumber", "3.0");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("EPAM");
        element.submit();
        Thread.sleep(1000);

        Assert.assertTrue(driver.getTitle().matches("(?i)EPAM - Google Search"));

        driver.quit();
    }
}