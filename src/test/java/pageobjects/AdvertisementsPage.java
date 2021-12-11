package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AdvertisementsPage extends BasePO {

    private By addBtn = By.xpath("//button[normalize-space(@id)='btn_BO_CP_ADV_AddNew']");
    private By editBtn = By.id("btn_BO_CP_ADV_Edit");
    private By table = By.id("tbl_BO_List_CP_ADV");
    private By history = By.id("btn_BO_HISTORY");

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
