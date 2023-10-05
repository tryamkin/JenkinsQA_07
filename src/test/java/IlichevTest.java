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

            //Comment!
            WebDriver driver = new ChromeDriver();
            driver.get("https://allegro.pl/");

            String title = driver.getTitle();
            Assert.assertEquals("Allegro - atrakcyjne ceny - Strona Główna", title);

            WebElement button = driver.findElement(By.name("Ok, zgadzam się"));

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.name("allegro-metrumheader"));
            WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/header/div[1]/div/div/div/form/button"));

            textBox.sendKeys("Ball");
            submitButton.click();

            // Check if the search results page contains the text "Ball"
            WebElement searchResults = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[3]/div/div/div[1]/div/div/div[3]/div/div/div[1]/div/div/div[1]/div/div/div/div/div/div[1]/div/h3"));
            String searchResultsText = searchResults.getText();
            Assert.assertTrue(searchResultsText.contains("Ball"));

            driver.quit();


        }
    }

