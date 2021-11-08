package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ClockInPage extends BasePO {

    private By empoloyeeNumber = By.id("//div[@id='divNumericKeyPadValue']");
    private By enter = By.id("//button[text()='Enter']");
    private By timeCardRecordSuccessMsg = By.xpath("//div[text()='Your time card recorded successfully.']");
    private By OKBtn = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='OK']");

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
    }

    public Element empoloyeeNumber() throws IOException, InterruptedException {
        return $(empoloyeeNumber);
    }

    public Element timeCardRecordSuccessMsg() throws IOException, InterruptedException {
        return $(timeCardRecordSuccessMsg);
    }

    public Element enter() throws IOException, InterruptedException {
        return $(enter);
    }
}
