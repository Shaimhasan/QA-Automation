package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class ActivateDeActicateMenuItemsPage extends BasePO {

    private By smallActiveChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_ITM_AVAL_Active_235']");
    private By activateTxt = By.xpath("//div[@id='div_BO_MN_ITM_AVAL_ADDEDT_DIALOG_modal_view']//span[text()='Availability']");
    private By save = By.xpath("//button[@onclick='BO_MN_ITM_AVAL_ADDEDT_Save();']");
    private By cancel = By.xpath("//button[@onclick='BO_MN_ITM_AVAL_ADDEDT_Save();']");

    public Element activateTxt() throws IOException, InterruptedException {
        return $(activateTxt);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element smallActiveChkBx() throws IOException, InterruptedException {
        return $(smallActiveChkBx);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


}
