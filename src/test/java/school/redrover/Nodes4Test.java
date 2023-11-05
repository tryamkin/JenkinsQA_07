package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nodes4Test extends BaseTest {

    @Test
    public void testSortNodesInReverseOrder() {

        Actions actions = new Actions(getDriver());
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        actions.scrollToElement(getDriver().findElement(By.xpath("//a[@href='computer']")))
                .perform();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        for (int i = 1; i <= 3; i++) {
            String NODE_NAME = "Agent" + i;
            getDriver().findElement(By.xpath("//a[@href='new']")).click();
            getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(NODE_NAME);
            getDriver().findElement(By.className("jenkins-radio__label")).click();
            getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
            getDriver().findElement(By.xpath("//input[@name='_.remoteFS']")).sendKeys("C:\\Users\\galin\\Documents\\QA_courses\\redroverclasses\\Nile_agent");
            actions.scrollToElement(getDriver().findElement(By.xpath("//input[@name='_.labelString']")))
                    .perform();
            getDriver().findElement(By.xpath("//input[@name='_.labelString']")).sendKeys("Label1 Label2");
            getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        }

        List<String> ORIGINAL_TEXT_LIST = new ArrayList<>();
        ORIGINAL_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent1/']")).getText());
        ORIGINAL_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent2/']")).getText());
        ORIGINAL_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent3/']")).getText());
        ORIGINAL_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/(built-in)/']")).getText());

        List<String> EXPECTED_SORTED_TEXT_LIST = new ArrayList<>(ORIGINAL_TEXT_LIST);
        Collections.reverse(EXPECTED_SORTED_TEXT_LIST);

        getDriver().findElement(By.xpath("//th[@initialsortdir='down']")).click();

        List<String> ACTUAL_SORTED_TEXT_LIST = new ArrayList<>();
        ACTUAL_SORTED_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/(built-in)/']")).getText());
        ACTUAL_SORTED_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent3/']")).getText());
        ACTUAL_SORTED_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent2/']")).getText());
        ACTUAL_SORTED_TEXT_LIST.add(getDriver().findElement(By.xpath("//a[@href = '../computer/Agent1/']")).getText());

        Assert.assertEquals(ACTUAL_SORTED_TEXT_LIST, EXPECTED_SORTED_TEXT_LIST);

    }
}
