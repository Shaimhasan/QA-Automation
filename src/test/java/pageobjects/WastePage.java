package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class WastePage extends BasePO {
    private By addBtn = By.id("btn_BO_IN_WST_AddNew");
    private By table = By.id("tbl_BO_List_IN_WST");
    private By editBtn = By.id("btn_BO_IN_WST_Edit");
    private By editBtnDisable = By.xpath("//button[@id='btn_BO_IN_WST_Edit' and @class='button-disabled ui-state-disabled']");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_IN_WST_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By wasteTxt = By.xpath("//div[text()='Waste']");

    public Element editBtnDisable() throws IOException, InterruptedException {
        return $(editBtnDisable);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element delete() throws IOException, InterruptedException {
        return $(delete);
    }

    public Element wasteTxt() throws IOException, InterruptedException {
        return $(wasteTxt);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }
}
