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

        WebElement textBox = getDriver().findElement(By.id("s"));
        textBox.sendKeys("Eastham");

//        Thread.sleep(1000);

        WebElement searchButton = getDriver().findElement(By.id("searchsubmit"));
        searchButton.click();

        WebElement title = getDriver().findElement(By.className("breadcrumb"));
        String value = title.getText();
        Assert.assertEquals(value, "You Are Here: YMCA Cape Cod > Search results for 'Eastham'");

    }

    @Test
    public void testMacsShack() {
        getDriver().get("https://macsseafood.com/restaurant/macs-shack-wellfleet/");
        WebElement orderOnline = getDriver().findElement(By.xpath("//*[text() = 'Order Online']"));
        orderOnline.click();
        WebElement macsFishHouse = getDriver().findElement(By.xpath("//main//a[text() = 'Mac’s Fish House Provincetown']"));
        Assert.assertTrue(macsFishHouse.isDisplayed());
    }

    @Test
    public void testCheckMenu() {
        getDriver().get("https://macsseafood.com/restaurant/macs-shack-wellfleet/");

        WebElement viewMenu = getDriver().findElement(By.id("menu-link"));
        viewMenu.click();

        WebElement aboutThisLocationButton = getDriver().findElement(By.xpath("//span[text() = 'About This Location']"));
        Assert.assertTrue(aboutThisLocationButton.isDisplayed());
    }
}
