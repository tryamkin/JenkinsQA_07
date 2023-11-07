package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class FirstTest {
    WebDriver driver;
    String baseUrl = "https://www.georgianholidays.com/ru/attractions/wineries";

    @AfterMethod
    public void after() {
        driver = new ChromeDriver();
        System.out.println("running @AfterMethod.............");
        driver.quit();
    }

    @BeforeMethod
    public void before() {
        System.out.println("running @BeforeMethod.............");
        driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    public void MainPageTitle() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Georgian Holidays-Individual and Group Tours");
        driver.quit();
    }

    @Test
    public void MainPageDescription() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebElement descriptionElement = driver.findElement(By.name("description"));
        String description = descriptionElement.getAttribute("content");
        Assert.assertEquals(description, "Исторически Грузия была винодельческой культурой с древних времен. Откройте для себя многочисленные винодельни и сорта вин в разных уголках страны.");
        driver.quit();
    }

    @Test
    public void MainPageLogo() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebElement descriptionElement = driver.findElement(By.cssSelector(".logo"));
        driver.quit();
    }

    @Test
    public void MainPageContact() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebElement linkElement = driver.findElement(By.cssSelector("a[href='https://www.georgianholidays.com/ru/contact']"));
        driver.quit();
    }
}

