package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ui_automation.pages.HerokuApplication;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

public class HerokuAppSteps {

    public static final Logger oLog = LogManager.getLogger(HerokuAppSteps.class);

    HerokuApplication herokuApplication = new HerokuApplication();
    WebDriver driver = Driver.getInstance().getDriver();
    Actions act = new Actions(driver);
    Keys key = null;


    @Given("user navigates to heroku application")
    public void user_navigates_to_heroku_application() throws InterruptedException {

        String url = ConfigurationReader.getProperty("ui-config.properties", "herokuApplication.url");
        driver.get(url);

    }

    @Then("user switch to {string} page")
    public void user_switch_to_page(String page) throws InterruptedException {
        switch (page){
            case "keyPresses" : {
                herokuApplication.keyPressesPage.click();
                System.out.println("111111111111111111111");
                break;
            }
            case "fileUpload" : {
                herokuApplication.fileUploadPage.click();
                System.out.println("22222222222222222222222222");
                break;
            }
            case "download" : {
                herokuApplication.fileDownloadPage.click();
                System.out.println("3333333333333333333333333333");
                break;
            }
        }
    }

    @Then("user interacts with keyboard {string} actions")
    public void user_interacts_with_keyboard_actions(String keys) {
        oLog.info("this is INFO"+keys);

        switch (keys) {
            case "ENTER":
                key = Keys.ENTER;
                System.out.println("111111111111111111111");
                break;
            case "TAB":
                key = Keys.TAB;
                System.out.println("111111111111111111111");
                break;
            case "SPACE":
                key = Keys.SPACE;
                break;
            case "BACK_SPACE":
                key = Keys.BACK_SPACE;
                break;
        }

        act.moveToElement(herokuApplication.target).sendKeys(key).perform();

    }

    @Then("user is able to see the message {string}")
    public void user_is_able_to_see_the_message(String message) {

        String expectedMessage = "You entered: " + message;

        String actualText = herokuApplication.result.getText();

        Assert.assertEquals("Result verification failed", expectedMessage, actualText);
    }

    //TODO need to implement tooltip verification

    @Then("user able to upload a file from local machine")
    public void user_able_to_upload_a_file_from_local_machine() throws InterruptedException {

        System.getProperty("user.dir");

        String path =  System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\person.png";

        oLog.info("The path to the folder is " + path);
        oLog.warn("The path to the folder is " + path);
        System.out.println("The path to the folder is " + path);

        herokuApplication.chooseFileButton.sendKeys(path);
        herokuApplication.uploadButton.click();
        Thread.sleep(5000);
    }

    @Then("user able to download a file to local machine")
    public void user_able_to_download_a_file_to_local_machine() throws InterruptedException {
        herokuApplication.downloadFiles.get(0).click();
        Thread.sleep(5000);
    }

}
