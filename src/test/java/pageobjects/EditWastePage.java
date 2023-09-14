package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditWastePage extends BasePO {

    private By wasteDate = By.id("txt_BO_IN_WST_ADDEDT_Date");
    private By addWasteTxt = By.xpath("//span[text()='Waste']");
    private By item = By.id("txt_BO_IN_WST_ADDEDT_Item");
    private By quantity1 = By.id("txt_BO_IN_WST_ADDEDT_InventoryUnit");
    private By quantity2 = By.id("txt_BO_IN_WST_ADDEDT_UsageUnit");
    private By itemSelect = By.xpath("//strong[text()='Automation Inventory Item']");
    private By totalWaste = By.id("txt_BO_IN_WST_ADDEDT_Total");
    private By save = By.id("btn_BO_IN_WST_ADDEDT_Save");
    private By cancel = By.id("btn_BO_IN_WST_ADDEDT_Cancel");

    public Element totalWaste() throws IOException, InterruptedException {
        return $(totalWaste);
    }

    public Element itemSelect() throws IOException, InterruptedException {
        return $(itemSelect);
    }

    public Element addWasteTxt() throws IOException, InterruptedException {
        return $(addWasteTxt);
    }

    public Element wasteDate() throws IOException, InterruptedException {
        return $(wasteDate);
    }

    public Element quantity2() throws IOException, InterruptedException {
        return $(quantity2);
    }

    public Element item() throws IOException, InterruptedException {
        return $(item);
    }

    public Element quantity1() throws IOException, InterruptedException {
        return $(quantity1);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }


}
