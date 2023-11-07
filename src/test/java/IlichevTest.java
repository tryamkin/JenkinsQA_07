import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IlichevTest {

        @Test
        public void testSearch() {

            WebDriver driver = new ChromeDriver();

            driver.get("https://toolsqa.com/");

            driver.getTitle();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.xpath("//a[@href = '/'][text() = 'Home']"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));

            submitButton.click();

            driver.quit();


        }
    }

