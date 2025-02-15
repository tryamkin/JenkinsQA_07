package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class User5Test extends BaseTest {

    @Test
    public void testVerifyRequiredFields() {
        List<String> expectedLabelNames = List.of("Username", "Password", "Confirm password", "Full name", "E-mail address");
        List<String> actualLabelNames = new ArrayList<>();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href = '/manage']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='securityRealm/']"))).click();
        getDriver().findElement(By.cssSelector("a[href='addUser']")).click();

        for (String labelName : expectedLabelNames) {
            String labelText = getDriver().findElement(By.xpath("//div[text() = '" + labelName + "']")).getText();
            actualLabelNames.add(labelText);
            WebElement input = getDriver().findElement
                    (By.xpath("//div[@class='jenkins-form-label help-sibling'][text() = '" + labelName + "']" +
                            "/following-sibling::div/input"));

            Assert.assertNotNull(input);
        }

        Assert.assertEquals(expectedLabelNames, actualLabelNames);
    }
}
