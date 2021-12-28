package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class EditVendorDeliveryDaysPage extends BasePO {

    private By vendorDeliveryDaysTxt = By.xpath("//div[@id='div_BO_IN_VDD_EDT_DIALOG_modal_view']//span[text()='Vendor Delivery Days']");
    private By save = By.xpath("//div[@id='div_BO_IN_VDD_EDT_DIALOG_modal_view']//button[text()='Save']");
    private By checkBox = By.xpath("//td[text()='Automation Testing1730']/..//input[contains(@id,'chk_BO_IN_VDD_EDT')]");
    private By cancel = By.xpath("//div[@id='div_BO_IN_VDD_EDT_DIALOG_modal_view']//button[text()='Cancel']");

    public List<Element> checkBox() throws IOException, InterruptedException {
        return $$(checkBox);
    }

    public Element vendorDeliveryDaysTxt() throws IOException, InterruptedException {
        return $(vendorDeliveryDaysTxt);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }


}
