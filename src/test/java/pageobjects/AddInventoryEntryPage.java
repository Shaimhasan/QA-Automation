package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddInventoryEntryPage extends BasePO {

    private By receivingUnit = By.id("txt_BO_IN_ENT_ADDEDT_VQTY_0_0");
    private By weekly = By.xpath("//ul[@id='ul_BO_IN_ENT_AddNew_Frequencies']//div[text()='Weekly']");
    private By date = By.id("txt_BO_IN_ENT_ADDEDT_Date");
    private By inventoryUnit = By.id("txt_BO_IN_ENT_ADDEDT_IQTY_0_0");
    private By usageUnit = By.id("txt_BO_IN_ENT_ADDEDT_UQTY_0_0");
    private By inventoryEntryTxtPopup = By.xpath("//div[@id='div_BO_IN_ENT_ADDEDT_DIALOG_modal_view']//span[text()='Inventory Entry']");
    private By save = By.xpath("//div[@id='div_BO_IN_ENT_ADDEDT_DIALOG']//button[text()='Save']");

    public Element usageUnit() throws IOException, InterruptedException {
        return $(usageUnit);
    }

    public Element weekly() throws IOException, InterruptedException {
        return $(weekly);
    }

    public Element inventoryEntryTxtPopup() throws IOException, InterruptedException {
        return $(inventoryEntryTxtPopup);
    }

    public Element inventoryUnit() throws IOException, InterruptedException {
        return $(inventoryUnit);
    }

    public Element receivingUnit() throws IOException, InterruptedException {
        return $(receivingUnit);
    }

    public Element date() throws IOException, InterruptedException {
        return $(date);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

}
