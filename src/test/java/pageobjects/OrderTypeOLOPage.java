package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderTypeOLOPage extends BasePO {

    private By orderType = By.xpath("//h2[text()='Order Type']");
    private By dineIn = By.id("span_order_type_1_name");
    private By takeOut = By.id("span_order_type_2_name");
    private By delivery = By.id("span_order_type_3_name");
    private By address = By.id("txt_order_type_address");
    private By yesProceedOrder = By.id("btnMsgBoxYesNo_Yes");
    private By continueBtn = By.xpath("//button[@id='btn_order_type_continue']");
    private By unit = By.id("txt_order_type_apt");
    private By addressDisplay = By.xpath("//option[@value='1234 Elm St Roseville CA 95678']");
    private By orderTypeValidate = By.xpath("//h2[text()='Order Type']");
    private By asap = By.xpath("//label[@id='lbl_order_type_time_now']");
    private By existingAddressSelect = By.xpath("//input[@id='rdoOrderType_Address_36165']");

    public Element existingAddressSelect() throws IOException, InterruptedException {
        return $(existingAddressSelect);
    }

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