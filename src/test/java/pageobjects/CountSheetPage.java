package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CountSheetPage extends BasePO {

    private By addBtn = By.id("btn_BO_IN_CNT_AddNew");
    private By editBtn = By.id("btn_BO_IN_CNT_Edit");
    private By editBtnDisable = By.xpath("//button[@id='btn_BO_IN_CNT_Edit' and @class='button-disabled ui-state-disabled']");
    private By table = By.id("tbl_BO_List_IN_CNT");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_IN_CNT_Del");
    private By deleteOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Delete']");

    public Element editBtnDisable() throws IOException, InterruptedException {
        return $(editBtnDisable);
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
