package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolder8Test extends BaseTest {

    private void clickNewJobButton() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
    }

    private void clickOrganizationFolderButton() {
        getDriver().findElement(By.xpath("//span[contains(text(), 'Organization Folder')]")).click();
    }

    private void clickOkButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void clickSaveButton() {
        getDriver().findElement(By.xpath("//button[contains(text(), 'Save')]")).click();
    }

    private void setFolderName(String name) {
        getDriver().findElement(By.name("name")).sendKeys(name);
    }

    private void openDashboard() {
        getDriver().findElement(By.id("jenkins-name-icon")).click();
    }

    private void createOrganizationFolder(String folderName) {
        clickNewJobButton();
        setFolderName(folderName);
        clickOrganizationFolderButton();
        clickOkButton();
        clickSaveButton();
    }

    @Test
    public void testCreateOrganizationFolderWithValidName() {
        createOrganizationFolder("organization folder test");

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(),
                "organization folder test");
    }

    @Test
    public void testNewOrganizationFolderAppearedOnDashboard() {
        createOrganizationFolder("organization folder test");
        openDashboard();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(), 'organization folder test')]")).getText(),
                "organization folder test");
    }

}
