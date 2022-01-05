package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ClockOutPage extends BasePO {

    private By twoDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol2']");
    private By oneDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol1']");
    private By zeroDigit = By.xpath("//div[@class='gridCols2X1X']//button[@class='gridCol1']");
    private By enter = By.xpath("//button[text()='Enter']");
    private By timeCardRecordSuccessMsg = By.xpath("//div[text()='Your time card recorded successfully.']");
    private By OKBtn = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='OK']");
    private By gratuityAmt = By.id("txtClockOutGratuity");
    private By clockOut = By.xpath("//button[text()='Clock Out']");
    private By clockOutSuccessMsg = By.xpath("//div[text()='You have clocked out successfully.']");

    public Element oneDigit() throws IOException, InterruptedException {
        return $(oneDigit);
    }

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
    }

    public Element clockOutSuccessMsg() throws IOException, InterruptedException {
        return $(clockOutSuccessMsg);
    }

    public Element clockOut() throws IOException, InterruptedException {
        return $(clockOut);
    }

    public Element gratuityAmt() throws IOException, InterruptedException {
        return $(gratuityAmt);
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
