package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class WebCategoriesPage extends BasePO {

    private By addBtn = By.id("btn_BO_MN_WCT_AddNew");
    private By editBtn = By.id("btn_BO_MN_WCT_Edit");
    private By search = By.xpath("//input[@id='txtSearch']");
    private By table = By.id("tbl_BO_List_MN_WCT");
    private By history = By.id("btn_BO_HISTORY");
    private By actAndDeact = By.id("btn_BO_MN_WCT_AVAL");
    private By delete = By.id("btn_BO_MN_WCT_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By webCatTxt = By.xpath("//div[@id='div_BO_MN_WCT_ADDEDT_DIALOG_modal_view']//span[text()='Web Category']");

    public Element webCatTxt() throws IOException, InterruptedException {
        return $(webCatTxt);
    }


    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element delete() throws IOException, InterruptedException {
        return $(delete);
    }


    public Element actAndDeact() throws IOException, InterruptedException {
        return $(actAndDeact);
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

    public Element search() throws IOException, InterruptedException {
        return $(search);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }

}
