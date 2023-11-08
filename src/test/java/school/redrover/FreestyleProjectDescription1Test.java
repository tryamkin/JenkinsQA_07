package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectDescription1Test extends BaseTest {

    private final String PROJECT_NAME = "First Project";
    private final String DESCRIPTION = "Project Description";

    public void createFirstProject(String projectName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
    }

    public void addDescription(String DESCRIPTION) {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION);
        getDriver().findElement(By.name("Submit")).click();
    }

    public void editDescription(String NEW_DESCRIPTION) {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(NEW_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();
    }

    public void deleteDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();
    }


    @Test
    public void testAddDescription() {
        createFirstProject(PROJECT_NAME);
        addDescription(DESCRIPTION);
        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(),DESCRIPTION);
    }


    @Test
    public void testEditDescription() {
        final String newDescription = "New Description";

        createFirstProject(PROJECT_NAME);
        addDescription(DESCRIPTION);
        editDescription(newDescription);
        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(), newDescription);
    }


    @Test
    public void testDeleteDescription() {

        createFirstProject(PROJECT_NAME);
        deleteDescription();
        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(),"");

    }

}
