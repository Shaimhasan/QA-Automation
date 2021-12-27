package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class InventoryEntryPage extends BasePO {

    private By addBtn = By.id("btn_BO_IN_ENT_AddNew");
    private By editBtn = By.id("btn_BO_IN_ENT_Edit");
    private By editBtnDisable = By.xpath("//button[@id='btn_BO_IN_ENT_Edit' and @class='button-disabled ui-state-disabled']");
    private By table = By.id("tbl_BO_List_IN_ENT");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_IN_ENT_Del");
    private By post = By.id("btn_BO_IN_ENT_Post");
    private By unPost = By.id("btn_BO_IN_ENT_UnPost");
    private By copyTo = By.id("btn_BO_IN_ENT_CopyTo");
    private By deleteOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Delete']");
    private By inventoryEntryTxt = By.xpath("//div[text()='Inventory Entry' and @id='div_Page_Title']");

    public Element inventoryEntryTxt() throws IOException, InterruptedException {
        return $(inventoryEntryTxt);
    }

    public Element copyTo() throws IOException, InterruptedException {
        return $(copyTo);
    }

    public Element editBtnDisable() throws IOException, InterruptedException {
        return $(editBtnDisable);
    }

    public Element unPost() throws IOException, InterruptedException {
        return $(unPost);
    }

    public Element post() throws IOException, InterruptedException {
        return $(post);
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
