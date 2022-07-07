package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ClockInPage extends BasePO {

    private By twoDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol2']");
    private By oneDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol1']");
    private By oneDigitLatest = By.xpath("(//button[text()='1'])[2]");
    private By zeroDigit = By.xpath("//div[@class='gridCols2X1X']//button[@class='gridCol1']");
    private By zeroDigitLatest = By.xpath("(//button[text()='0'])[2]");
    private By enter = By.xpath("//button[text()='Enter']");
    private By enterLatest = By.xpath("(//button[text()='Enter'])[2]");
    private By timeCardRecordSuccessMsg = By.xpath("//div[text()='Your time card recorded successfully.']");
    private By OKBtn = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='OK']");
    private By clockInTxtPopup = By.xpath("//div[text()='Please Enter Employee No.:']");
    private By employeeNo = By.xpath("//div[text()='1000']");
    private By clockInPopup = By.xpath("//span[text()='Clock In']");

    public Element enterLatest() throws IOException, InterruptedException {
        return $(enterLatest);
    }

    public Element clockInPopup() throws IOException, InterruptedException {
        return $(clockInPopup);
    }

    public Element employeeNo() throws IOException, InterruptedException {
        return $(employeeNo);
    }

    public Element clockInTxtPopup() throws IOException, InterruptedException {
        return $(clockInTxtPopup);
    }

    public Element oneDigitLatest() throws IOException, InterruptedException {
        return $(oneDigitLatest);
    }

    public Element oneDigit() throws IOException, InterruptedException {
        return $(oneDigit);
    }

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
    }

    public Element zeroDigitLatest() throws IOException, InterruptedException {
        return $(zeroDigitLatest);
    }

    public Element zeroDigit() throws IOException, InterruptedException {
        return $(zeroDigit);
    }

    public Element twoDigit() throws IOException, InterruptedException {
        return $(twoDigit);
    }

    public Element timeCardRecordSuccessMsg() throws IOException, InterruptedException {
        return $(timeCardRecordSuccessMsg);
    }

    public Element enter() throws IOException, InterruptedException {
        return $(enter);
    }
}
