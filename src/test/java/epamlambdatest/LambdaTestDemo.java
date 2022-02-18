package epamlambdatest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LambdaTestDemo {
    String username = "sarmistha.jena";
    String accesskey = "";
    static RemoteWebDriver driver = null;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    private static String Status="failed";
    public static void main(String[] args) {
        new LambdaTestDemo().test();
    }
    public void test() {
        // To Setup driver
        setUp();

        ArrayList<String> exceptionCapture = new ArrayList<>();
        try {
            driver.get("https://www.lambdatest.com");

            //String ExpectedTitle = "Most Powerful Cross Browser Testing Tool Online | LambdaT";
            String ExpectedTitle = "Most Powerful Cross Browser Testing Tool Online | LambdaTest";

            String TitleValue = driver.getTitle();
            if (TitleValue.equals(ExpectedTitle)) {
                Status = "passed";
            }
            Assert.assertEquals(TitleValue, ExpectedTitle);
        } catch (AssertionError e) {
            Status = "failed";
            exceptionCapture.add(e.getMessage());
            ((JavascriptExecutor) driver).executeScript("lambda-exceptions", exceptionCapture);
        }
        finally {
            tearDown();
        }
    }
    private void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("name", "Test1");
        //capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("platform", "MacOS Catalina"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "LambdaTestSarmistha");
        capabilities.setCapability("name", "LambdaTestSarmisthaTest");
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + Status);
            driver.quit(); //really important statement for preventing your test execution from a timeout.
        }
    }
 }
