package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddReceivingItemsPage extends BasePO {

    private By name = By.id("txt_BO_IN_VIT_ADDEDT_Name");
    private By yieldFactor = By.id("txt_BO_IN_VIT_ADDEDT_YieldFactor");
    private By InvUnitConFactor = By.id("txt_BO_IN_VIT_ADDEDT_InvUnitConFactor");
    private By save = By.xpath("//div[@id='div_BO_IN_VIT_ADDEDT_DIALOG']//button[text()='Save']");
    private By receivingItemText = By.xpath("//div[@id='div_BO_IN_VIT_ADDEDT_DIALOG_modal_view']//span[text()='Receiving Item']");

    public Element InvUnitConFactor() throws IOException, InterruptedException {
        return $(InvUnitConFactor);
    }

    public Element receivingItemText() throws IOException, InterruptedException {
        return $(receivingItemText);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element name() throws IOException, InterruptedException {
        return $(name);
    }

    public Element yieldFactor() throws IOException, InterruptedException {
        return $(yieldFactor);
    }
}
