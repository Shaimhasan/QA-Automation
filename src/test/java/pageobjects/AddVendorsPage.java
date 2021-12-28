package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddVendorsPage extends BasePO {

    private By vendorTxt = By.xpath("//span[@class='modal-header-text']");
    private By name = By.id("txt_BO_IN_VDN_ADDEDT_Name");
    private By phoneNo = By.id("txt_BO_IN_VDN_ADDEDT_Phone");
    private By email = By.id("txt_BO_IN_VDN_ADDEDT_Email");
    private By address = By.id("txt_BO_IN_VDN_ADDEDT_Addr1");
    private By city = By.id("txt_BO_IN_VDN_ADDEDT_City");
    private By zipCode = By.id("txt_BO_IN_VDN_ADDEDT_Zip");
    private By state = By.xpath("//select[@id='ddl_BO_IN_VDN_ADDEDT_State']");
    private By save = By.xpath("//div[@id='div_BO_IN_VND_ADDEDT_DIALOG_modal_view']//button[text()='Save']");

    public Element vendorTxt() throws IOException, InterruptedException {
        return $(vendorTxt);
    }

    public Element email() throws IOException, InterruptedException {
        return $(email);
    }

    public Element name() throws IOException, InterruptedException {
        return $(name);
    }

    public Element phoneNo() throws IOException, InterruptedException {
        return $(phoneNo);
    }

    public Element address() throws IOException, InterruptedException {
        return $(address);
    }

    public Element city() throws IOException, InterruptedException {
        return $(city);
    }

    public Element zipCode() throws IOException, InterruptedException {
        return $(zipCode);
    }

    public Element state() throws IOException, InterruptedException {
        return $(state);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


}
