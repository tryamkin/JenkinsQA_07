package school.redrover;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class GroupBrainBuildersTest extends BaseTest {

    @Test
    public void testJenkinsAdminStatus() {

        getDriver().findElement(By.cssSelector("#tasks > div:nth-child(2) > span > a")).click();
        // From the list of users I would like to get name of the particular user and click on it
        WebElement recordInTheList = getDriver().findElement(By.className("jenkins-table__link"));
        String userName = recordInTheList.getText();
        recordInTheList.click();
        // And to verify that on the next page userID match with the name
        Assert.assertTrue(getDriver().getPageSource().contains(userName));
    }

    @Test
    public void testJenkinsCredentialsTooltip() {

        WebElement adminMenu = getDriver().findElement(By.xpath("//a[@href='/user/admin']"));
        adminMenu.click();

        WebElement credentialsItem = getDriver().findElement(By.xpath("//a[@href='/user/admin/credentials']"));
        credentialsItem.click();

        WebElement systemTableItem = getDriver().findElement(By.xpath("//a[@href='/manage/credentials/store/system']"));
        systemTableItem.click();

        WebElement imageSystemTable = getDriver().findElement(By.xpath("//img[@class='icon-credentials-domain icon-lg']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(imageSystemTable).perform();

        WebElement tooltip = getDriver().findElement(By.xpath("//img[@aria-describedby = 'tippy-10']"));
        Assert.assertTrue(tooltip.isDisplayed());
    }


    private void createNewItemFreestyle(String freestyleName) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.className("jenkins-input")).sendKeys(freestyleName);

        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//*[@class='jenkins-button jenkins-button--primary ']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText(), "Project " + freestyleName);
    }

    private void deleteItemFreestyle(String freestyleName) {

        getDriver().findElement(By.xpath("//a[@href='job/" + freestyleName + "/']")).click();

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[6]/span/a/span[1]")).click();

        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    @Test
    public void changeFreestyleName() throws InterruptedException {

        final String freestyleProjectName = "Brains";
        final String freestyleChangedProjectName = "NEW_Brains";

        createNewItemFreestyle(freestyleProjectName);

        getDriver().findElement(By.xpath("//a[@href='/']")).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + freestyleProjectName + "/']")).click();

        getDriver().findElement(By.xpath("//a[@href='/job/" + freestyleProjectName + "/confirm-rename']")).click();

        WebElement newNameField = getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[2]/input"));
        newNameField.clear();
        newNameField.sendKeys(freestyleChangedProjectName);
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button")).click();

        getDriver().findElement(By.xpath("//a[@href='/']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='job/" + freestyleChangedProjectName + "/']")).getText(), freestyleChangedProjectName);

        deleteItemFreestyle(freestyleChangedProjectName);
    }

    @Test
    public void addFreestyleDescription() throws InterruptedException {

        final String freeStyleProjectName = "New_brains";

        createNewItemFreestyle(freeStyleProjectName);

        getDriver().findElement(By.xpath("//a[@href='/']")).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + freeStyleProjectName + "/']")).click();

        getDriver().findElement(By.xpath("//a[@href='editDescription']")).click();

        WebElement descriptionField = getDriver().findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"));
        descriptionField.clear();
        descriptionField.sendKeys("my_new_project");
        getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(), "my_new_project");
    }
}
