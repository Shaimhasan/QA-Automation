package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddAdvertisementsPage extends BasePO {

    private By name = By.id("txt_BO_CP_AVD_ADDEDT_Name");
    private By expirationDate = By.id("txt_BO_CP_AVD_ADDEDT_ExpDate");
    private By cost = By.id("txt_BO_CP_AVD_ADDEDT_Cost");
    private By quantity = By.id("txt_BO_CP_AVD_ADDEDT_QTY");
    private By advertisementsTxtPopup = By.xpath("//div[@id='div_BO_CP_ADV_ADDEDT_DIALOG_modal_view']//span[text()='Advertisement']");
    private By save = By.xpath("//button[@onclick='BO_CP_ADV_ADDEDT_Save();']");

    public Element advertisementsTxtPopup() throws IOException, InterruptedException {
        return $(advertisementsTxtPopup);
    }

    public Element expirationDate() throws IOException, InterruptedException {
        return $(expirationDate);
    }

    public Element cost() throws IOException, InterruptedException {
        return $(cost);
    }

    public Element quantity() throws IOException, InterruptedException {
        return $(quantity);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element name() throws IOException, InterruptedException {
        return $(name);
    }

}
