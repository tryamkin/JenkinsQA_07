package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class AikaTest extends BaseTest {

    @Test
    public void testRelativeLocationToLeftOf() {
        String categoriesOpt = "Categories";
        JenkinsUtils.login(getDriver());
        
        getDriver().get("https://mvnrepository.com/");
        WebElement popularOpt = getDriver().findElement(By.xpath("//a[text()= 'Popular']"));

        Assert.assertEquals(getDriver().findElement(with(By.tagName("a"))
                .toLeftOf(popularOpt)).getText(), categoriesOpt);
    }

    @Test
    public void testRelativeLocationBelow() {
        getDriver().get("https://mvnrepository.com/");
        WebElement siteDeveloped = getDriver().findElement(By.xpath("//span[contains(text(), 'developed')]"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(siteDeveloped).perform();

        Assert.assertEquals(getDriver().findElement(with(By.tagName("a")).below(siteDeveloped))
                .getText(), "Contact Us");
    }

    @Test
    public void testRelativeLocationAbove() {
        getDriver().get("https://mvnrepository.com/");
        WebElement whatsNew = getDriver().findElement(By.tagName("h1"));
        WebElement inputField = getDriver().findElement(with(By.id("query")).above(whatsNew));

        Assert.assertTrue(inputField.isDisplayed());
    }
}
