package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GroupUnicornsTest {

    @Test
    public void usPsPageOpenTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.usps.com/");
        String title = driver.getTitle();
        assertEquals("Welcome | USPS", title);
        driver.quit();
    }

    @Test
    public void usPsSendMailPackageTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.usps.com/");
        WebElement send = driver.findElement(By.xpath("//a[@id='mail-ship-width']"));
        send.click();
        String sendTitle = driver.getTitle();
        assertEquals("Send Mail & Packages | USPS", sendTitle);
        driver.quit();
    }

    @Test
    public void testSuccessfulLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();
        String actual = driver.findElement(By.id("flash")).getText();
        assertTrue(actual.contains("You logged into a secure area!"));
        driver.quit();
    }

    @Test
    public void testLoginAttemptWithInvalidUsername() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        String username = "tomsmith123";
        String password = "SuperSecretPassword!";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();
        String actual = driver.findElement(By.id("flash-messages")).getText();
        assertTrue(actual.contains("Your username is invalid!"));
        driver.quit();
    }

    @Test
    public void testGeico() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.geico.com/");

            WebElement title = driver.findElement(By.xpath("//div/h1[@id ='section1heading']"));
            title.isDisplayed();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

            WebElement zipCode = driver.findElement(By.xpath("//div/input[@id ='ssp-service-zip']"));
            zipCode.sendKeys("11111");

            WebElement submit = driver.findElement(By.xpath("//input[@class ='btn btn--secondary']"));
            submit.click();
        } finally {
            driver.quit();
        }
    }
}