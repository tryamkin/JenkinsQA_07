package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BreadCrumbTest6 extends BaseTest {

   @Test
    public void testNavigateToDashboard () {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']"));
        getDriver().findElement(By.xpath("//div[@id = 'breadcrumbBar']//a[@href ='/']")).click();

        int count = getDriver().findElements(By.xpath("//ol[@id = 'breadcrumbs']//li")).size();

        Assert.assertEquals(count, 2);
    }

}
