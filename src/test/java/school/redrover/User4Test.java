package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class User4Test extends BaseTest {

    @Test
    public void testUserCreation() {
        final String username = "testUser";
        final String password = "1";
        final String email = "test@test.com";

        getDriver().findElement(By.xpath("//a[@href = '/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'username']")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name = 'password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name = 'password2']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name = 'email']")).sendKeys(email);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        List<WebElement> listOfUserID = getDriver().findElements(By.xpath(
                "//td/a[@class = 'jenkins-table__link model-link inside']"));

        Assert.assertFalse(listOfUserID.isEmpty());

        boolean isNewUserIDShown = false;
        for (WebElement webElement : listOfUserID) {
            if (webElement.getText().contains(username)) {
                isNewUserIDShown = true;
                break;
            }
        }

        Assert.assertTrue(isNewUserIDShown);
    }

    @Test
    public void testShowingValidationMessages() {
        getDriver().findElement(By.xpath("//a[@href = '/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        List<WebElement> listOfValidationMessages = getDriver().findElements(By.xpath(
                "//div[@class = 'error jenkins-!-margin-bottom-2']"));

        Assert.assertFalse(listOfValidationMessages.isEmpty());
    }

    @Test
    public void testUnableToCreateUserWithExistedUsername() {
        final String existedUsername = "admin";
        final String password = "1";
        final String email = "test@test.com";
        final String validationMessage = "User name is already taken";

        getDriver().findElement(By.xpath("//a[@href = '/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'username']")).sendKeys(existedUsername);
        getDriver().findElement(By.xpath("//input[@name = 'password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name = 'password2']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name = 'email']")).sendKeys(email);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class = 'error jenkins-!-margin-bottom-2']")).getText(),
                validationMessage);
    }

    @Test
    public void testPasswordAndConfirmPasswordArentTheSame() {
        final String existedUsername = "testUser";
        final String password = "1";
        final String confirmPassword = "2";
        final String email = "test@test.com";
        final String validationMessage = "Password didn't match";

        getDriver().findElement(By.xpath("//a[@href = '/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'username']")).sendKeys(existedUsername);
        getDriver().findElement(By.xpath("//input[@name = 'password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name = 'password2']")).sendKeys(confirmPassword);
        getDriver().findElement(By.xpath("//input[@name = 'email']")).sendKeys(email);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        List<WebElement> listOfValidationMessages = getDriver().findElements(By.xpath(
                "//div[@class = 'error jenkins-!-margin-bottom-2']"));

        Assert.assertFalse(listOfValidationMessages.isEmpty());

        boolean isValidationMessageEqual = false;

        for (WebElement listOfValidationMessage : listOfValidationMessages) {
            if (listOfValidationMessage.getText().equals(validationMessage)) {
                isValidationMessageEqual = true;
            } else {
                isValidationMessageEqual = false;
                break;
            }
        }

        Assert.assertTrue(isValidationMessageEqual);
    }

    @Test(dependsOnMethods = "testUserCreation")
    public void testDeleteLoggedInUser() {
        getDriver().findElement(By.xpath("//a[@href = '/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();

        String logUsername = getDriver().findElement(By.xpath("(//span[@class='hidden-xs hidden-sm'])[1]"))
                .getText();

        boolean doDelete = true;

        try {
            getDriver().findElement(By.xpath("//tr[.//td[contains(text(), '" + logUsername + "')]]/td[last()]/*"));
        } catch (Exception e) {
            doDelete = false;
        }

        Assert.assertFalse(doDelete);
    }
}
