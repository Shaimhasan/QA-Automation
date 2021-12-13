package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SingleUseCouponsPage extends BasePO {

    private By addBtn = By.xpath("//button[normalize-space(@id)='btn_BO_CP_SUC_AddNew']");
    private By editBtn = By.id("btn_BO_CP_SUC_Edit");
    private By delete = By.id("btn_BO_CP_SUC_Delete");
    private By deleteOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Delete']");
    private By export = By.id("btn_BO_CP_SUC_Export");
    private By table = By.id("tbl_BO_List_CP_SUC");
    private By history = By.id("btn_BO_HISTORY");

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element export() throws IOException, InterruptedException {
        return $(export);
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
