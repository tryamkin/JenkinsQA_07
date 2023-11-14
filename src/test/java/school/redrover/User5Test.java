package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class User5Test extends BaseTest {

    private static final String USERNAME = "newUser1";
    private static final String PASSWORD = "12345";
    private static final String CONFIRM_PASSWORD = "12345";
    private static final String FULL_NAME = "John Wick";
    private static final String E_MAIL_ADDRESS = "abc@example.com";

    private void goToCreateUsersPage() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
    }

    private void createUser() {
        goToCreateUsersPage();

        getDriver().findElement(By.id("username")).sendKeys(USERNAME);
        getDriver().findElement(By.name("password1")).sendKeys(PASSWORD);
        getDriver().findElement(By.name("password2")).sendKeys(CONFIRM_PASSWORD);
        getDriver().findElement(By.name("fullname")).sendKeys(FULL_NAME);
        getDriver().findElement(By.name("email")).sendKeys(E_MAIL_ADDRESS);
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testCreateUser() {
        createUser();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href = 'user/newuser1/']")).getText(),
                USERNAME);
    }

    @Test
    public void testNewUserRequiredFields() {
        goToCreateUsersPage();

        List<String> requiredFieldNames = List.of("Username", "Password", "Confirm password", "Full name", "E-mail address");
        List<WebElement> namesFromFields = getDriver().findElements(
                By.xpath("//div[@class = 'jenkins-form-item tr ']/div[@class = 'jenkins-form-label help-sibling']"));

        Assert.assertTrue(namesFromFields.size() > 0);

        List<String> actualFieldNames = new ArrayList<>();
        for(WebElement element: namesFromFields) {
            actualFieldNames.add(element.getText());
        }

        Assert.assertEquals(actualFieldNames, requiredFieldNames);
    }
}
