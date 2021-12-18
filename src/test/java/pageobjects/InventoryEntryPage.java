package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class InventoryEntryPage extends BasePO {

    private By addBtn = By.id("btn_BO_IN_ENT_AddNew");
    private By editBtn = By.id("btn_BO_IN_ENT_Edit");
    private By table = By.id("tbl_BO_List_IN_ENT");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_IN_ENT_Del");
    private By post = By.id("btn_BO_IN_ENT_Post");
    private By unPost = By.id("btn_BO_IN_ENT_UnPost");
    private By deleteOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Delete']");

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
