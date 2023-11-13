package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;



  public class CreateNevViewTest extends BaseTest {
      private static final String PROJECT_NAME = "MyProject";

      @Ignore
    @Test
    public void testCreateFreestPro() {
        getDriver().findElement(By.xpath("//a [@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//img[@class='icon-freestyle-project icon-xlg']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.id("jenkins-home-link")).click();

        String newFreestPro = getDriver().findElement(By.xpath(
                "//tr[@id=\"job_MyProject\"]/td[3]/a/span")).getText();
        Assert.assertEquals(newFreestPro,"MyProject");
    }

      @Ignore
    @Test (dependsOnMethods = "testCreateFreestPro")
    public void testCreateView() {
        getDriver().findElement(By.xpath("//a[@aria-label='New View']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("My view");
        getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ListView']")).click();
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        getDriver().findElement(By.name("Submit")).click();

        String nameView = getDriver().findElement(By.xpath(
                "//div[@id='main-panel']/div[2]/div[1]/div[2]/a")).getText();
        Assert.assertEquals(nameView, "My view");
    }

      @Ignore
      @Test (dependsOnMethods = "testCreateView")
      public void testRenameView() {
          getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]/a")).click();
          getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a")).click();
          getDriver().findElement(By.name("name")).clear();
          getDriver().findElement(By.name("name")).sendKeys("Job view");
          getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

          Assert.assertEquals(getDriver().findElement(
                  By.xpath("//div[@id=\"main-panel\"]/div[2]/div[1]/div[2]/a")).getText(),
                  "Job view");
      }

      @Ignore
      @Test (dependsOnMethods = "testRenameView")
       public  void testAddDescription() {
          getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]/a")).click();
          getDriver().findElement(By.xpath("//a[@href = 'editDescription']")).click();
          getDriver().findElement(By.name("description")).sendKeys("Help folder");
          getDriver().findElement(By.name("Submit")).click();

          final String textDescription = getDriver().findElement(By.xpath(
                  "//div[@id = 'main-panel']/div[1]/div[2]/div")).getText();
          Assert.assertEquals(textDescription, "Help folder");
      }

      @Ignore
      @Test (dependsOnMethods = "testCreateFreestPro")
      public void testAddJobView() {
          getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]/a")).click();
          getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a")).click();
          getDriver().findElement(By.xpath("//label[@title = 'MyProject']")).click();
          getDriver().findElement(By.name("Submit")).click();

          final String jobAdd = getDriver().findElement(By.xpath(
                  "//span [contains(text(), 'MyProject')]")).getText();
          Assert.assertEquals(jobAdd,"MyProject");
      }
  }
