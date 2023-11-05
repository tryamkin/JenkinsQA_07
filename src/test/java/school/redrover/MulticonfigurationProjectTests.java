package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import org.openqa.selenium.interactions.Actions;

public class MulticonfigurationProjectTests extends BaseTest {

    @Test
    public void testCreateMCProjectClickingNewItemDashboard(){

        getDriver().findElement(By.xpath("//span[normalize-space()='Create a job']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Test");
        getDriver().findElement(By.cssSelector(".hudson_matrix_MatrixProject")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#jenkins-name-icon")).click();

        WebElement el = getDriver().findElement(By.xpath("//td/a[@href = 'job/Test/']"));

        new Actions(getDriver()).click(el).perform();


        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("#main-panel > h1")).getText(),
                "Project Test");
    }

    @Test
    public void testCreateMCProjectClickingNewItemLeftPanel() {
        /*
1.Click the “+ New Item” link shown under “Start building your software project”.
2.Type name “Test 1” of the new Multiconfiguration project in the input field "Enter an item name".
3.Select “Multi-configuration project”.
4.Click “OK” button.
5.In the “General” view click “Save”
6. Click “Jenkins” icon to go back to the Dashboard.
7. Click the project

Expected:
The Project view should be loaded.

The “Multiconfiguration project“ is displayed in the Project View.
     */
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Test");
        getDriver().findElement(By.cssSelector(".hudson_matrix_MatrixProject")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#jenkins-name-icon")).click();

        WebElement el = getDriver().findElement(By.xpath("//td/a[@href = 'job/Test/']"));

        new Actions(getDriver()).click(el).perform();


        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("#main-panel > h1")).getText(),
                "Project Test");


    }

}
