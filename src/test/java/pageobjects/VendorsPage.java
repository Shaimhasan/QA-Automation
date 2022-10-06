package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class VendorsPage extends BasePO {
    private By addBtn = By.id("btn_BO_IN_VND_AddNew");
    private By table = By.id("tbl_BO_List_IN_VND");
    private By editBtn = By.id("btn_BO_IN_VND_Edit");
    private By history = By.id("btn_BO_HISTORY");
    private By actAndDeact = By.id("btn_BO_IN_VND_AVAL");
    private By delete = By.id("btn_BO_IN_VND_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By vendorsTxt = By.xpath("//div[text()='Vendors']");

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

    public Element actAndDeact() throws IOException, InterruptedException {
        return $(actAndDeact);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element vendorsTxt() throws IOException, InterruptedException {
        return $(vendorsTxt);
    }


}
