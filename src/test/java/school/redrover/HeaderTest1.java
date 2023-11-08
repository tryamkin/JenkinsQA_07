package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class HeaderTest1 extends BaseTest {

    @Test
    public void testReturnToMainPage(){
        getDriver().findElement(By.xpath("//*[@href = '/asynchPeople/']")).click();
        getDriver().findElement(By.xpath("//*[@id = 'jenkins-name-icon']")).click();
        Assert.assertEquals(getDriver().getTitle(),"Dashboard [Jenkins]");
    }
}
