package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.JenkinsUtils;
import school.redrover.runner.ProjectUtils;

import static school.redrover.runner.ProjectUtils.*;


public class TopNewsTest extends BaseTest {

    private static final String BASEURL = "https://topnews.ru";
    private static final String LOGINJENKINS = "Admin";
    private static final String PASSWORDJENKINS = "Admin";

    @Test(description = "Сравнение контента заголовков в первом фрейме и в первом блоке боковой панели ")
    public void testContent1() {
        getDriver().get(BASEURL);
        String mainTitle = getDriver().findElement(By.xpath("//div[@class = 'first-news-title']")).getText();
        getDriver().findElement(By.xpath("//div[@class = 'first-news-title']")).click();
        String sideTitle = getDriver().findElement(By.xpath(" //div[@class ='top-news-item'][1]")).getText();

        Assert.assertEquals(mainTitle, sideTitle, "Заголовки не совпадают");
    }


    @Test(description = "Сравнение главного заголовка в разделе о проекте ")
    public void testContent2() {
        String TITLE_ABOUT = "TOPNEWS — этот рейтинг делаешь только ты!";
        getDriver().get(BASEURL);
        getDriver().findElement(By.id("menu-item-73200")).click();
        getDriver().findElement(By.id("menu-item-73201")).click();
        String getTitleAboutProject = getDriver().findElement(By.className("about-title")).getText();

        Assert.assertEquals(getTitleAboutProject, TITLE_ABOUT, "Заголовки не совпадают");

    }

    void AuthorizationInJenkins() {
        ProjectUtils.get(getDriver());
        getDriver().findElement(By.id("j_username")).sendKeys(LOGINJENKINS);
        getDriver().findElement(By.id("j_password")).sendKeys(PASSWORDJENKINS);
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test(description = "Проверка Заголовка приветствия")
    public void testJenkinsAuthorization() {
        AuthorizationInJenkins();
        String actualInfo = getDriver().findElement(By.xpath("//h2[@class ='h4'][contains(text(), 'Start')]")).getText();

        Assert.assertEquals(actualInfo, "Start building your software project", "Заголовок не совпадает");
    }

    @Test(description = "Проверка адреса URL страницы новой Job")
    public void testJenkins() {
        AuthorizationInJenkins();
        getDriver().findElement(By.xpath("//span[contains(text(),'Create a job')]")).click();
        String actualURL = getDriver().getCurrentUrl();

        Assert.assertEquals(actualURL, "http://localhost:8080/newJob", "URL не совпадает");
    }

}
