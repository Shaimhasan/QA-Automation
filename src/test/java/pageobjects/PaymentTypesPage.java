package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PaymentTypesPage extends BasePO {

    private By activateDeactivateBtn = By.id("btn_BO_ST_PTP_AVAL");
    private By history = By.id("btn_BO_HISTORY");
    private By table = By.id("tbl_BO_List_ST_PTP");
    private By amazon = By.id("tblRowXST_PTP27");


    public Element history() throws IOException, InterruptedException {
        return $(history);
    }
    public Element activateDeactivateBtn() throws IOException, InterruptedException {
        return $(activateDeactivateBtn);
    }
    public Element table() throws IOException, InterruptedException {
        return $(table);
    }
    public Element amazon() throws IOException, InterruptedException {
        return $(amazon);
    }

}
