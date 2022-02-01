package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderTypeOLOPage extends BasePO {

    private By orderType = By.id("divOrderType_Title");
    private By dineIn = By.id("spanOrderType_1_Name");


    public Element dineIn() throws IOException, InterruptedException {
        return $(dineIn);
    }

    public Element orderType() throws IOException, InterruptedException {
        return $(orderType);
    }

}