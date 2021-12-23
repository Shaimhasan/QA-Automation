package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddInventoryItemsPage extends BasePO {

    private By name = By.id("txt_BO_IN_ITM_ADDEDT_Name");
    private By conversionFactor = By.id("txt_BO_IN_ITM_ADDEDT_Conversion_Factor");
    private By InvUnitConFactor = By.id("txt_BO_IN_ITM_ADDEDT_Cost");
    private By save = By.xpath("//div[@id='div_BO_IN_ITM_ADDEDT_DIALOG_modal_view']//button[text()='Save']");
    private By inventoryItemText = By.xpath("//div[@id='div_BO_IN_ITM_ADDEDT_DIALOG_modal_view']//span[text()='Inventory Item']");

    public Element InvUnitConFactor() throws IOException, InterruptedException {
        return $(InvUnitConFactor);
    }

    public Element inventoryItemText() throws IOException, InterruptedException {
        return $(inventoryItemText);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element name() throws IOException, InterruptedException {
        return $(name);
    }

    public Element conversionFactor() throws IOException, InterruptedException {
        return $(conversionFactor);
    }
}
