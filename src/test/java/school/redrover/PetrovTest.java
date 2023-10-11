package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class PetrovTest extends BaseTest {

    @Test
    public void testTextBoxForm() {

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
    }

    @Test
    public void testPracticeForm() {

        getDriver().get("https://demoqa.com/automation-practice-form");

        WebElement firstName = getDriver().findElement(By.xpath("//*[@id=\"firstName\"]"));
        WebElement lastName = getDriver().findElement(By.cssSelector("#lastName"));
        WebElement email = getDriver().findElement(By.cssSelector("#userEmail"));
        WebElement gender = getDriver().findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label"));
        WebElement number = getDriver().findElement(By.xpath("//*[@id=\"userNumber\"]"));

        firstName.sendKeys("TestFirstName");
        lastName.sendKeys("TestLastName");
        email.sendKeys("testemail@gamail.com");

        gender.click();

        number.sendKeys("1111111111");

        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.click();

        WebElement afterSubmittingForm = getDriver().findElement(By.cssSelector("#example-modal-sizes-title-lg"));
        String title = afterSubmittingForm.getText();

        Assert.assertEquals(title, "Thanks for submitting the form");

        WebElement genderAfterSubmitting = getDriver().findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(3) > td:nth-child(2)"));
        Assert.assertEquals(genderAfterSubmitting.getText(), "Male");
    }

}
