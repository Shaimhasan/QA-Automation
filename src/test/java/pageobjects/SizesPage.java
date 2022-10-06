package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SizesPage extends BasePO {

    private By addBtn = By.id("btn_BO_MN_SIZ_AddNew");
    private By editBtn = By.id("btn_BO_MN_SIZ_Edit");
    private By table = By.id("tbl_BO_List_MN_SIZ");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_MN_SIZ_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By sizeTxt = By.xpath("//div[@id='div_BO_MN_SIZ_ADDEDT_DIALOG_modal_view']//span[text()='Size']");


    public Element sizeTxt() throws IOException, InterruptedException {
        return $(sizeTxt);
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
