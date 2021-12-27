package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ActDeactVendorsPage extends BasePO {


    private By activeChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_IN_VND_AVAL_Active']");
    private By save = By.xpath("//div[@id='div_BO_IN_VND_AVAL_DIALOG_modal_view']//button[text()='Save']");
    private By cancel = By.xpath("//div[@id='div_BO_IN_VND_AVAL_DIALOG_modal_view']//button[text()='Cancel']");
    private By actDeactTextVal = By.xpath("//div[@id='div_BO_IN_VND_AVAL_DIALOG_modal_view']//span[text()='Activate / Deactivate']");

    public Element activeChkBx() throws IOException, InterruptedException {
        return $(activeChkBx);
    }

    public Element actDeactTextVal() throws IOException, InterruptedException {
        return $(actDeactTextVal);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


}
