package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderTypeOLOPage extends BasePO {

    private By orderType = By.id("divOrderType_Title");
    private By dineIn = By.id("spanOrderType_1_Name");
    private By takeOut = By.id("spanOrderType_2_Name");
    private By delivery = By.id("spanOrderType_3_Name");
    private By address = By.id("txtOrderType_Address");
    private By yesProceedOrder = By.id("btnMsgBoxYesNo_Yes");
    private By continueBtn = By.xpath("//button[@id='btnOrderType_Continue']");
    private By unit = By.id("txtOrderType_Apt");
    private By addressDisplay = By.xpath("//option[@value='1234 Elm St Roseville CA 95678']");
    private By orderTypeValidate = By.xpath("//div[text()='Order Type']");
    private By asap = By.xpath("//label[@id='lblOrderType_FD_Now']");

    public Element orderTypeValidate() throws IOException, InterruptedException {
        return $(orderTypeValidate);
    }

    public Element asap() throws IOException, InterruptedException {
        return $(asap);
    }

    public Element addressDisplay() throws IOException, InterruptedException {
        return $(addressDisplay);
    }

    public Element unit() throws IOException, InterruptedException {
        return $(unit);
    }

    public Element address() throws IOException, InterruptedException {
        return $(address);
    }

    public Element delivery() throws IOException, InterruptedException {
        return $(delivery);
    }

    public Element takeOut() throws IOException, InterruptedException {
        return $(takeOut);
    }

    public Element continueBtn() throws IOException, InterruptedException {
        return $(continueBtn);
    }

    public Element yesProceedOrder() throws IOException, InterruptedException {
        return $(yesProceedOrder);
    }

    public Element dineIn() throws IOException, InterruptedException {
        return $(dineIn);
    }

    public Element orderType() throws IOException, InterruptedException {
        return $(orderType);
    }

}