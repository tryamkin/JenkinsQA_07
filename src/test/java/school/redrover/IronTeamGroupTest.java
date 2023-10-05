package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IronTeamGroupTest {
    @Test
    public void w3schoolTest() throws InterruptedException {
        // Check Title of site
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.w3schools.com/");
            WebElement title = driver.findElement(By.cssSelector("h1.learntocodeh1"));
            String value = title.getText();
            Assert.assertEquals(value, "Learn to Code");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void javaPageTest() throws InterruptedException {
        // Check Java page of site
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.w3schools.com/");
            WebElement textBox = driver.findElement(By.id("search2"));
            textBox.sendKeys("Java Tutorial");
            WebElement searchButton = driver.findElement(By.id("learntocode_searchbtn"));
            Thread.sleep(500);
            searchButton.click();
           WebElement title = driver.findElement(By.cssSelector("h1"));
            String value = title.getText();
           Assert.assertEquals(value, "Java Tutorial");
        } finally {
            driver.quit();
        }
    }
}
