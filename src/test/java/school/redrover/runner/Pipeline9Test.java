package school.redrover.runner;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Pipeline9Test extends BaseTest {

    @Test
    public void testCreatePipeline() {
        final String pipelineName = "Test Pipeline";

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@class = 'jenkins-input']")).sendKeys(pipelineName);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        getDriver().findElement(By.id("jenkins-home-link")).click();
        getDriver().findElement(By.xpath("//td//a[@href = 'job/Test%20Pipeline/']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Pipeline " + pipelineName);
    }

}
