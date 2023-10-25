package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {
    private void creationNewOrganizationFolder(String folderName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//span[text()='Organization Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
    }

    @Test
    public void testCreatedNewOrganizationFolder() {
        final String folderName = "Organization_Folder";

        creationNewOrganizationFolder(folderName);
        getDriver().findElement(By.linkText("Dashboard")).click();

        Assert.assertTrue(getDriver()
                .findElement(By.xpath("//tr[@id='job_" + folderName + "']")).isDisplayed());

    }

}


