package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class Delete2UserTest extends BaseTest {

    private void goToUsersTab() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
    }

    private static final String USER_VALID_NAME = "USER_NAME_Test";

    @Test
    public void testCreateUserValidData() {
        goToUsersTab();

        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();

        getDriver().findElement(By.id("username")).sendKeys(USER_VALID_NAME);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys("123456_Test");
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys("123456_Test");
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys("TestName");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("Test@mail.ru");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//a[@href='user/user_name_test/']")).getText(), "USER_NAME_Test");
    }

    @Test
    public void testCreateUserDifferentPassword() {
        goToUsersTab();

        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();

        getDriver().findElement(By.id("username")).sendKeys("USER_NAME_Test");
        getDriver().findElement(By.name("password1")).sendKeys("123456_Test");
        getDriver().findElement(By.name("password2")).sendKeys("123456");
        getDriver().findElement(By.name("fullname")).sendKeys("TestName");
        getDriver().findElement(By.name("email")).sendKeys("Test@mail.ru");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/form/div[1]/div[3]")).getText(), "Password didn't match");
        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/form/div[1]/div[5]")).getText(), "Password didn't match");
    }

    @Test (dependsOnMethods = "testCreateUserValidData")
    public void testDeleteUser() {
        goToUsersTab();

        WebElement dropDownArrow = getDriver().findElement(By.xpath("//a[@href='user/user_name_test/']"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(dropDownArrow).perform();
        WebElement arrow = getDriver().findElement(By.xpath("//a[@href='user/" + USER_VALID_NAME.toLowerCase() + "/']/button"));

        actions.moveToElement(arrow).perform();

        arrow.click();

        WebElement deleteDropDown =getDriver().findElement(By.xpath("//button[@href='/user/" + USER_VALID_NAME.toLowerCase() + "/doDelete']"));

        actions.moveToElement(deleteDropDown).click().perform();

        getDriver().switchTo().alert().accept();

        goToUsersTab();

        List<WebElement> usersList = getDriver().findElements(By.xpath("//a[@href='user/" + USER_VALID_NAME.toLowerCase() + "/']"));

        Assert.assertEquals(usersList.size(), 0);
    }
}
