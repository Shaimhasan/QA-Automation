package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class InventoryItemsPage extends BasePO {

    private By addBtn = By.id("btn_BO_IN_ITM_AddNew");
    private By editBtn = By.id("btn_BO_IN_ITM_Edit");
    private By table = By.id("tbl_BO_List_IN_ITM");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_IN_ITM_Del");
    private By warningTxt = By.xpath("//div[contains(@id,'div_Message')]//span[text()='Warning']");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");

    public Element warningTxt() throws IOException, InterruptedException {
        return $(warningTxt);
    }

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element delete() throws IOException, InterruptedException {
        return $(delete);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }

}
