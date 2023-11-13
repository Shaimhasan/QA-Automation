package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class TaxRatesPage extends BasePO {

    private By editBtn = By.id("btn_BO_ST_TXR_Edit");
    private By history = By.id("btn_BO_HISTORY");
    private By table = By.id("tbl_BO_List_ST_TXR");


    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }
}
