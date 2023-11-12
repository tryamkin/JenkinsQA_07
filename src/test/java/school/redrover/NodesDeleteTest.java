package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class NodesDeleteTest extends BaseTest {
    private static final String NODE_NAME = "NodeNew";

    private void createNode() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        getDriver().findElement(By.xpath("//a[@href='new']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NODE_NAME);
        getDriver().findElement(By.cssSelector(".jenkins-radio__label")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.name("Submit")).click();
    }

    private void goToNodesPage() {
        getDriver().findElement(By.linkText("Build Executor Status")).click();
    }


    private boolean elementIsNotPresent(String xpath){
        return getDriver().findElements(By.xpath(xpath)).isEmpty();
    }

    @Test
    public void testCheckAlertMessageInDeleteNewNode() {
        createNode();

        Actions a = new Actions(getDriver());
        WebElement dropDownMenu = getDriver().findElement(By.xpath("//tr[@id='node_"+ NODE_NAME +"']//a//button"));
        a.moveToElement(dropDownMenu).build().perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(dropDownMenu)).click();

        getDriver().findElement(By.xpath("//button[@href='/computer/"+ NODE_NAME +"/doDelete']")).click();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Delete Agent: are you sure?");
    }

    @Test
    public void testCancelToDeleteNewNodeFromDropDownMenu() {
        createNode();

        Actions a = new Actions(getDriver());
        WebElement dropDownMenu = getDriver().findElement(By.xpath("//tr[@id='node_"+ NODE_NAME +"']//a//button"));
        a.moveToElement(dropDownMenu).build().perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(dropDownMenu)).click();

        getDriver().findElement(By.xpath("//button[@href='/computer/"+ NODE_NAME +"/doDelete']")).click();

        getDriver().switchTo().alert().dismiss();

        Assert.assertFalse(elementIsNotPresent("//tr[@id='node_"+ NODE_NAME +"']//a//button"));
    }

    @Test(dependsOnMethods = "testCancelToDeleteNewNodeFromDropDownMenu")
    public void testDeleteNewNodeFromDropDownMenu() {
        goToNodesPage();

        Actions a = new Actions(getDriver());
        WebElement dropDownMenu = getDriver().findElement(By.xpath("//tr[@id='node_"+ NODE_NAME +"']//a//button"));
        a.moveToElement(dropDownMenu).build().perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(dropDownMenu)).click();

        getDriver().findElement(By.xpath("//button[@href='/computer/"+ NODE_NAME +"/doDelete']")).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(elementIsNotPresent("//tr[@id='node_"+ NODE_NAME +"']//a//button"));
    }

    @Test
    public void testDeleteNewNodeFromAgentPage() {
        createNode();

        getDriver().findElement(By.xpath("//a[@href='/computer/"+ NODE_NAME +"/']")).click();

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[2]/span/a")).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(elementIsNotPresent("//tr[@id='node_"+ NODE_NAME +"']//a"));
    }
}
