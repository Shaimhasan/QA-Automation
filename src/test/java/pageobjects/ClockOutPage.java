package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ClockOutPage extends BasePO {

    private By empoloyeeNumber = By.id("//div[@id='divNumericKeyPadValue']");
    private By enter = By.id("//button[text()='Enter']");
    private By timeCardRecordSuccessMsg = By.xpath("//div[text()='You have clocked out successfully.']");
    private By OKBtn = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='OK']");

    public Element empoloyeeNumber() throws IOException, InterruptedException {
        return $(empoloyeeNumber);
    }

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
    }

    public Element timeCardRecordSuccessMsg() throws IOException, InterruptedException {
        return $(timeCardRecordSuccessMsg);
    }

    public Element enter() throws IOException, InterruptedException {
        return $(enter);
    }
}
