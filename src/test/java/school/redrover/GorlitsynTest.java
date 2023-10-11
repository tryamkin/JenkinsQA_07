package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GorlitsynTest extends BaseTest {


    @Test
    public void petStoreTitleTest(){


        getDriver().get("https://petstore.octoperf.com/");
        String title = getDriver().getTitle();
        Assert.assertEquals(title, "JPetStore Demo");

    }

    @Test
    public void openPetStoreCatalog(){
        String expectedFooter = "www.mybatis.org";
        getDriver().get("https://petstore.octoperf.com/");
        WebElement enterTheStoreButton = getDriver().findElement(By.linkText("Enter the Store"));
        enterTheStoreButton.click();

        String footer = getDriver().findElement(By.xpath("//a[@href = 'http://www.mybatis.org']")).getText();
        Assert.assertEquals(footer,expectedFooter);
    }

}

