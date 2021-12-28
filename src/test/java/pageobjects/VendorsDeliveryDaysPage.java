package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class VendorsDeliveryDaysPage extends BasePO {
    private By table = By.id("tbl_BO_List_IN_VDD");
    private By editBtn = By.id("btn_BO_IN_VDD_Edit");
    private By history = By.id("btn_BO_HISTORY");
    private By vendorsDeliveryDaysTxt = By.xpath("//div[text()='Vendor Delivery Days']");

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element vendorsDeliveryDaysTxt() throws IOException, InterruptedException {
        return $(vendorsDeliveryDaysTxt);
    }


}
