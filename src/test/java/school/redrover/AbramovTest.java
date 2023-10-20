package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AbramovTest extends BaseTest {

    @Test
    public void testNewFirstJenkinsFreestyleProject() {
        WebElement newJob = getDriver().findElement(By.cssSelector("a[href='newJob']"));
        newJob.click();

        WebElement inputJobName = getDriver().findElement(By.cssSelector(".jenkins-input"));
        inputJobName.sendKeys("NewTestJob01");

        WebElement freestyleJobOption = getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject"));
        freestyleJobOption.click();

        WebElement okNewJobButton = getDriver().findElement(By.cssSelector("#ok-button"));
        okNewJobButton.click();

        WebElement newJobLinkName = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//li[3]/a"));
        String linkText = newJobLinkName.getText();

        Assert.assertEquals(linkText, "NewTestJob01");
    }


}
