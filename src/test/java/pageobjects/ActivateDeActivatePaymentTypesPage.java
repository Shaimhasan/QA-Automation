package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ActivateDeActivatePaymentTypesPage extends BasePO {

    private By smallAvailableChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_ST_PTP_AVAL_Aval']");
    private By paymentTypeTxt = By.xpath("//div[@id='div_BO_ST_PTP_AVAL_DIALOG_modal_view']//span[text()='Payment Type']");
    private By sortingIndex = By.id("txt_BO_ST_PTP_AVAL_SortingIndex");
    private By save = By.xpath("//button[@id='btn_BO_ST_PTP_AVAL_Save']");
    private By cancel = By.xpath("//button[@id='btn_BO_ST_PTP_AVAL_Cancel']");

    public Element sortingIndex() throws IOException, InterruptedException {
        return $(sortingIndex);
    }
    public Element paymentTypeTxt() throws IOException, InterruptedException {
        return $(paymentTypeTxt);
    }
    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element smallAvailableChkBx() throws IOException, InterruptedException {
        return $(smallAvailableChkBx);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


}
