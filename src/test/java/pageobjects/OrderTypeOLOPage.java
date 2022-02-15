package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderTypeOLOPage extends BasePO {

    private By orderType = By.id("divOrderType_Title");
    private By dineIn = By.id("spanOrderType_1_Name");
    private By takeOut = By.id("spanOrderType_2_Name");
    private By yesProceedOrder = By.id("btnMsgBoxYesNo_Yes");
    private By continueBtn = By.id("btnOrderType_Continue");

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