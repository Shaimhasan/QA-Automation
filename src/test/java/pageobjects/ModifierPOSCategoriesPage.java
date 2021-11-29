package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ModifierPOSCategoriesPage extends BasePO {

    private By addBtn = By.id("btn_BO_MN_MCT_AddNew");
    private By editBtn = By.id("btn_BO_MN_MCT_Edit");
    private By search = By.xpath("//input[@id='txtSearch']");
    private By table = By.id("tbl_BO_List_MN_MCT");
    private By history = By.id("btn_BO_HISTORY");
    private By delete = By.id("btn_BO_MN_MCT_Del");
    private By deleteOnWarning = By.xpath("//div[@data-title='Warning']//button[text()='Delete']");
    private By modifierPOSCatText = By.xpath("//div[text()='Modifier POS Categories']");

    public Element modifierPOSCatText() throws IOException, InterruptedException {
        return $(modifierPOSCatText);
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

    public Element search() throws IOException, InterruptedException {
        return $(search);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }

}
