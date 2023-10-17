package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PavTomakTest extends BaseTest {

    @Test
    public void testTypeIn() throws InterruptedException {

        getDriver().get("https://ymcacapecod.org/");

        WebElement textBox = getDriver().findElement(By.className("field"));
        textBox.sendKeys("Eastham");

        Thread.sleep(1000);

        WebElement searchButton = getDriver().findElement(By.className("submit"));
        searchButton.click();


        WebElement title = getDriver().findElement(By.className("breadcrumb"));
        String value = title.getText();
        Assert.assertEquals(value, "You Are Here: YMCA Cape Cod > Search results for 'Eastham'");

    }

    @Test
    public void testMacsShack() {


        getDriver().get("https://macsseafood.com/restaurant/macs-shack-wellfleet/");

        WebElement viewMenu = getDriver().findElement(By.xpath("//*[@id='menu-link']/span[2]"));
        viewMenu.click();

        WebElement orderOnline = getDriver().findElement(By.xpath("//*[text() = 'Order Online']"));
        orderOnline.click();

        WebElement macsFishHouse = getDriver().findElement(By.xpath("//main//a[text() = 'Mac’s Fish House Provincetown']"));
        Assert.assertTrue(macsFishHouse.isDisplayed());


    }
}
