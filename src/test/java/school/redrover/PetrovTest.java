package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.JenkinsUtils;


public class PetrovTest extends BaseTest {

    @Test
    public void testTextBoxForm(){

        JenkinsUtils.login(getDriver());

        try {
            getDriver().get("https://demoqa.com/text-box");

            WebElement fullName = getDriver().findElement(By.xpath("//*[@id=\"userName\"]"));
            fullName.sendKeys("testName");

            WebElement email = getDriver().findElement(By.id("userEmail"));
            email.sendKeys("test@gmail.com");

            WebElement submitButton = getDriver().findElement(By.id("submit"));
            submitButton.click();

            WebElement nameS = getDriver().findElement(By.id("name"));
            WebElement emailS = getDriver().findElement(By.id("email"));

            Assert.assertEquals(nameS.getText(), "Name:testName");
            Assert.assertEquals(emailS.getText(), "Email:test@gmail.com");
            Assert.assertNotEquals(emailS.getText(), "");

        } finally {
            getDriver().quit();
        }
    }

    @Test
    public void testPracticeForm(){
        WebDriver driver=new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstName=driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        WebElement lastName=driver.findElement(By.cssSelector("#lastName"));
        WebElement email=driver.findElement(By.cssSelector("#userEmail"));
        WebElement gender=driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label"));
        WebElement number=driver.findElement(By.xpath("//*[@id=\"userNumber\"]"));

        firstName.sendKeys("TestFirstName");
        lastName.sendKeys("TestLastName");
        email.sendKeys("testemail@gamail.com");

        gender.click();

        number.sendKeys("1111111111");

        WebElement submitButton=driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.click();

        WebElement afterSubmittingForm=driver.findElement(By.cssSelector("#example-modal-sizes-title-lg"));
        String title=afterSubmittingForm.getText();

        Assert.assertEquals(title, "Thanks for submitting the form");

        WebElement genderAfterSubmitting=driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(3) > td:nth-child(2)"));
        Assert.assertEquals(genderAfterSubmitting.getText(), "Male");
        driver.quit();
    }


}
