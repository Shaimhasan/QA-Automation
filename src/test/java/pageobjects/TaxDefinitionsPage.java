package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class TaxDefinitionsPage extends BasePO {

    private By addBtn = By.xpath("//button[normalize-space(@id)='btn_BO_ST_TAX_AddNew']");
    private By editBtn = By.id("btn_BO_ST_TAX_Edit");
    private By history = By.id("btn_BO_HISTORY");
    private By cancelOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Cancel']");
    private By delete = By.id("btn_BO_ST_TAX_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By table = By.id("tbl_BO_List_ST_TAX");

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element delete() throws IOException, InterruptedException {
        return $(delete);
    }

    public Element cancelOnWarning() throws IOException, InterruptedException {
        return $(cancelOnWarning);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }
    public Element table() throws IOException, InterruptedException {
        return $(table);
    }
}
