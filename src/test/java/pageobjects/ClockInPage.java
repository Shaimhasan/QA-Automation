package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ClockInPage extends BasePO {

    private By twoDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol2']");
    private By oneDigit = By.xpath("//div[@class='gridCols3']//button[@class='gridCol1']");
    private By zeroDigit = By.xpath("//div[@class='gridCols2X1X']//button[@class='gridCol1']");
    private By enter = By.xpath("//button[text()='Enter']");
    private By timeCardRecordSuccessMsg = By.xpath("//div[text()='Your time card recorded successfully.']");
    private By OKBtn = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='OK']");

    public Element oneDigit() throws IOException, InterruptedException {
        return $(oneDigit);
    }

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
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
