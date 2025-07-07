package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class HerokuApplication {

    WebDriver driver  = Driver.getInstance().getDriver();

    public HerokuApplication(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "target")
    public WebElement target;

    @FindBy(id = "result")
    public WebElement result;

    @FindBy(xpath = "//*[text() = 'Key Presses']" )
    public WebElement keyPressesPage;

    @FindBy(xpath = "//*[text() = 'File Upload']")
    public WebElement fileUploadPage;

    @FindBy(id = "file-upload")
    public WebElement chooseFileButton;

    @FindBy(id = "file-submit")
    public WebElement uploadButton;

    @FindBy(xpath = "//*[text() ='File Download']")
    public WebElement fileDownloadPage;

    @FindBy(css = "#content a")
    public List<WebElement> downloadFiles;

}
