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
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By inventoryEntryTxt = By.xpath("//div[text()='Inventory Entry' and @id='div_Page_Title']");
    private By validatePostPresent = By.xpath("//table[@id='tbl_BO_List_IN_ENT']//*[local-name()='svg' and @class='fixed_header_SelectedRow_CheckBox']/*[local-name()='path']");
    private By postOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Post']");
    private By unPostOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Un-Post']");
    private By warningTxt = By.xpath("//div[contains(@id,'div_Message')]//span[text()='Warning']");
    private By validatePost = By.xpath("//*[local-name()='svg' and @class='fixed_header_SelectedRow_CheckBox']/*[local-name()='path']");

    public Element validatePost() throws IOException, InterruptedException {
        return $(validatePost);
    }

    public Element warningTxt() throws IOException, InterruptedException {
        return $(warningTxt);
    }

    public Element postOnWarning() throws IOException, InterruptedException {
        return $(postOnWarning);
    }

    public Element unPostOnWarning() throws IOException, InterruptedException {
        return $(unPostOnWarning);
    }

    public Element validatePostPresent() throws IOException, InterruptedException {
        return $(validatePostPresent);
    }

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
