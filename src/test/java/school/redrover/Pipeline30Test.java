package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline30Test extends BaseTest {
    private final String PIPELINE_NAME = "BrandNewPipelineName";

    public void createBrandNewPipeline() {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
    }

    public void goToDash() {
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
    }

    @Test
    public void testBrandNewPipelineCreated() {
        createBrandNewPipeline();
        goToDash();

        Assert.assertEquals
                (getDriver().findElement(By.xpath("//*[@href='job/" + PIPELINE_NAME + "/']")).getText(),
                        PIPELINE_NAME);
    }
}

