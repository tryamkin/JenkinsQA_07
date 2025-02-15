package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolder5Test extends BaseTest {

    private static final String WELCOME_MESSAGE = "Welcome to Jenkins!";
    private static final String ORGANIZATION_FOLDER_VALID_NAME = "Organization Folder";
    private static final String ORGANIZATION_FOLDER_NEW_NAME = "Organization Folder New";
    private static final String MESSAGE_FOLDER_DISABLED_EXPECTED = "This Organization Folder is currently disabled";

    @Test
    public void testVerifyWarningMessageEmptyName() {
        final String WARNING_MESSAGE_TEXT_EXPECTED = "» This field cannot be empty, please enter a valid name";
        final String CSS_COLOR_WARNING_MESSAGE_EXPECTED = "rgba(255, 0, 0, 1)";

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();

        String warningMessageTextActual = getDriver().findElement(By.xpath("//div[@id='itemname-required']")).getText();
        String cssColorWarningMessageActual = getDriver().findElement(By.xpath("//div[@id='itemname-required']")).getCssValue("color");

        Assert.assertEquals(cssColorWarningMessageActual, CSS_COLOR_WARNING_MESSAGE_EXPECTED);
        Assert.assertEquals(warningMessageTextActual, WARNING_MESSAGE_TEXT_EXPECTED);
    }

    @Test
    public void testVerifyWarningMessageWithDotName() {
        final String WARNING_MESSAGE_TEXT_EXPECTED = "» A name cannot end with ‘.’";
        final String CSS_COLOR_WARNING_MESSAGE_EXPECTED = "rgba(255, 0, 0, 1)";
        final String ORGANIZATION_FOLDER_WITH_DOT_NAME = "Organization Folder.";

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(ORGANIZATION_FOLDER_WITH_DOT_NAME);
        getDriver().findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();

        String warningMessageTextActual = getDriver().findElement(By.cssSelector("#itemname-invalid")).getText();
        String cssColorWarningMessageActual = getDriver().findElement(By.cssSelector("#itemname-invalid")).getCssValue("color");

        Assert.assertEquals(cssColorWarningMessageActual, CSS_COLOR_WARNING_MESSAGE_EXPECTED);
        Assert.assertEquals(warningMessageTextActual, WARNING_MESSAGE_TEXT_EXPECTED);
    }

    private void returnHomeJenkins() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    @Test
    public void testCreateOrganizationFolderWithValidName() {

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(ORGANIZATION_FOLDER_VALID_NAME);
        getDriver().findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        returnHomeJenkins();

        boolean isDisplayedOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + ORGANIZATION_FOLDER_VALID_NAME + "']/td/a/span")).isDisplayed();
        Assert.assertTrue(isDisplayedOnDashboard);
    }

    private void createOrganizationFolder(String organizationFolderName) {

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(organizationFolderName);
        getDriver().findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        returnHomeJenkins();
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderWithValidName")
    public void testRenameOrganizationFolderNameUsingSideBar() {

        getDriver().findElement(By.xpath("//tr[@id='job_" + ORGANIZATION_FOLDER_VALID_NAME + "']/td/a/span")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'confirm-rename')]")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).clear();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ORGANIZATION_FOLDER_NEW_NAME);
        getDriver().findElement(By.name("Submit")).click();

        String organizationFolderNameNewActual = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(organizationFolderNameNewActual, ORGANIZATION_FOLDER_NEW_NAME);

        returnHomeJenkins();
        boolean isDisplayedOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + ORGANIZATION_FOLDER_NEW_NAME + "']/td/a/span")).isDisplayed();
        Assert.assertTrue(isDisplayedOnDashboard);
    }

    @Test
    public void testVerifyErrorMessageRenameWithSameName() {
        String organizationFolderName = "Organization Folder";
        final String ERROR_MESSAGE_RENAME_WITH_SAME_NAME_EXPECTED = "The new name is the same as the current name.";
        final String ERROR_EXPECTED = "Error";
        createOrganizationFolder(organizationFolderName);

        getDriver().findElement(By.xpath("//tr[@id='job_" + organizationFolderName + "']/td/a/span")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'confirm-rename')]")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).clear();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(organizationFolderName);
        getDriver().findElement(By.name("Submit")).click();

        String errorActual = getDriver().findElement(By.cssSelector("#main-panel h1")).getText();
        String errorMessageRenameWithSameNameActual = getDriver().findElement(By.cssSelector("#main-panel p")).getText();

        Assert.assertEquals(errorMessageRenameWithSameNameActual, ERROR_MESSAGE_RENAME_WITH_SAME_NAME_EXPECTED);
        Assert.assertEquals(errorActual, ERROR_EXPECTED);
    }

    @Test
    public void testDisableOrganizationFolder() {
        String cssColorMessageFolderDisabledExpected = "rgba(254, 130, 10, 1)";

        createOrganizationFolder(ORGANIZATION_FOLDER_VALID_NAME);

        getDriver().findElement(By.xpath("//tr[@id='job_" + ORGANIZATION_FOLDER_VALID_NAME + "']/td/a/span")).click();
        getDriver().findElement(By.xpath("//button[@name=\"Submit\"]")).click();

        String messageFolderDisabledActual = getDriver().findElement(By.cssSelector("form#enable-project")).getText();
        String cssColorMessageFolderDisabledActual = getDriver().findElement(By.id("enable-project")).getCssValue("color");

        Assert.assertTrue(messageFolderDisabledActual.contains(MESSAGE_FOLDER_DISABLED_EXPECTED));
        Assert.assertEquals(cssColorMessageFolderDisabledActual, cssColorMessageFolderDisabledExpected);
    }

    @Test(dependsOnMethods = "testDisableOrganizationFolder")
    public void testEnableOrganizationFolder() {
        String disableButtonText = "Disable Organization Folder";

        getDriver().findElement(By.xpath("//tr[@id='job_" + ORGANIZATION_FOLDER_VALID_NAME + "']/td/a/span")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        boolean isMessageDisplayed = getDriver().findElement(By.id("main-panel")).getText().contains(MESSAGE_FOLDER_DISABLED_EXPECTED);

        Assert.assertFalse(isMessageDisplayed);
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[@name='Submit']")).getText().contains(disableButtonText));
    }

    @Test
    public void testMessageBeforeDeleting() {
        String organizationFolderName = "Organization Folder";
        final String CONFIRMING_MESSAGE_EXPECTED = "Delete the Organization Folder ‘Organization Folder’?";
        final String CONFIRMING_BUTTON_TEXT_EXPECTED = "Yes";

        createOrganizationFolder(organizationFolderName);
        getDriver().findElement(By.xpath("//tr[@id='job_" + organizationFolderName + "']/td/a/span")).click();
        getDriver().findElement(By.cssSelector("#tasks a[href*=delete]")).click();

        String confirmingMessageActual = getDriver().findElement(By.xpath("//form[@action=\"doDelete\"]")).getText();
        String confirmingButtonActual = getDriver().findElement(By.cssSelector("button[name='Submit']")).getText();

        Assert.assertTrue(confirmingMessageActual.contains(CONFIRMING_MESSAGE_EXPECTED));
        Assert.assertTrue(confirmingButtonActual.contains(CONFIRMING_BUTTON_TEXT_EXPECTED));
    }

    @Ignore
    @Test
    public void testDeleteOrganizationFolder() {
        String organizationFolderName = "Organization Folder";

        createOrganizationFolder(organizationFolderName);
        getDriver().findElement(By.xpath("//tr[@id='job_" + organizationFolderName + "']/td/a/span")).click();
        getDriver().findElement(By.cssSelector("#tasks a[href*=delete]")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        boolean isJobPresentOnDashboard = getDriver().findElement(By.id("main-panel")).getText().contains(organizationFolderName);
        boolean isWelcomeMessageDisplayed = getDriver().findElement(By.xpath("//h1")).getText().matches(WELCOME_MESSAGE);

        Assert.assertFalse(isJobPresentOnDashboard);
        Assert.assertTrue(isWelcomeMessageDisplayed);
    }

    @Test
    public void testCloneNotExistingJob() {
        String organizationFolderName = "Organization Folder";
        String organizationFolderWrongName = "Organization Folder Wrong";
        String organizationFolderCloneName = "Organization Folder Clone";
        String errorTitle = "Error";
        String errorMessage = "No such job:";

        createOrganizationFolder(organizationFolderName);
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(organizationFolderCloneName);
        getDriver().findElement(By.xpath("//input[@id='from']")).sendKeys(organizationFolderWrongName);
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), errorTitle);
        Assert.assertTrue(getDriver().findElement(By.xpath("//p")).getText().contains(errorMessage));
    }

    @Test
    public void testCloneOrganizationFolder() {
        String organizationFolderName = "Organization Folder Parent";
        String organizationFolderCloneName = "Organization Folder Clone";
        Actions actions = new Actions(getDriver());

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(organizationFolderName);
        getDriver().findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebElement checkBox = getDriver().findElement(By.xpath("//section[2]//label[contains(text(), 'Periodically if not otherwise run')]"));
        actions.moveToElement(checkBox).click().build().perform();
        getDriver().findElement(By.name("Submit")).click();
        returnHomeJenkins();

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(organizationFolderCloneName);
        getDriver().findElement(By.xpath("//input[@id='from']")).sendKeys(organizationFolderName);
        getDriver().findElement(By.id("ok-button")).click();
        boolean isCheckBoxChecked = getDriver().findElement(By.xpath("//section[2]//input[@id=\"cb2\"]")).isSelected();
        Assert.assertTrue(isCheckBoxChecked);
        getDriver().findElement(By.name("Submit")).click();
        returnHomeJenkins();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr[@id='job_" + organizationFolderName + "']//td//span")).getText(), organizationFolderName);
        Assert.assertEquals(getDriver().findElement(By.xpath("//tr[@id='job_" + organizationFolderCloneName + "']//td//span")).getText(), organizationFolderCloneName);
    }


}
