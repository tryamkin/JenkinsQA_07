package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupJavaAutomationTest {

    @Test
    public void herokuappHomePageTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        assertEquals(title, "The Internet");
        driver.quit();
    }

    @Test
    public void herokuappABTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        WebElement buttonABTesting = driver.findElement(By.xpath("//a[@href='/abtest']"));
        buttonABTesting.click();
        WebElement textABTest = driver.findElement(By.xpath("//h3[text()='A/B Test Control']"));
        String abTestTitle = textABTest.getText();
        assertEquals(abTestTitle, "A/B Test Control");
        driver.quit();
    }

    @Test
    public void herokuappCheckBoxTest() {
        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/");
        WebElement buttonCheckBox = driver.findElement(By.xpath("//a[@href='/checkboxes']"));
        buttonCheckBox.click();
        WebElement textCheckBox = driver.findElement(By.xpath("//h3[text()='Checkboxes']"));
        String titleCheckBox = textCheckBox.getText();
        softAssert.assertEquals(titleCheckBox, "Checkboxes");
        List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        softAssert.assertEquals(listCheckbox.size(), 2);
        softAssert.assertAll();
        driver.quit();
    }

}

