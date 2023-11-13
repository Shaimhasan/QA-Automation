package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditTaxRatesPage extends BasePO {

    private By name = By.id("txt_BO_ST_TXR_EDT_Name");
    private By localTaxRate = By.id("txt_BO_ST_TXR_EDT_TaxRate");
    private By fixedAmt = By.id("txt_BO_ST_TXR_EDT_FixAmount");
    private By activeCheckbox = By.id("chk_BO_ST_TXR_EDT_IsActive");
    private By excludeDineInCheckbox = By.id("chk_BO_ST_TXR_EDT_ExcludeOT_1");
    private By excludeTakeOutCheckbox = By.id("chk_BO_ST_TXR_EDT_ExcludeOT_2");
    private By excludeDeliveryCheckbox = By.id("chk_BO_ST_TXR_EDT_ExcludeOT_3");
    private By save = By.xpath("//button[@id='btn_BO_ST_TXR_EDT_Save']");
    private By cancel = By.xpath("//button[@id='btn_BO_ST_TXR_EDT_Cancel']");
    private By taxRatesTxtPopup = By.xpath("//div[@id='div_BO_ST_TXR_EDT_DIALOG_modal']//span[text()='Tax Rate']");




    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element fixedAmt() throws IOException, InterruptedException {
        return $(fixedAmt);
    }


    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element localTaxRate() throws IOException, InterruptedException {
        return $(localTaxRate);
    }


    public Element activeCheckbox() throws IOException, InterruptedException {
        return $(activeCheckbox);
    }


    public Element excludeDineInCheckbox() throws IOException, InterruptedException {
        return $(excludeDineInCheckbox);
    }


    public Element excludeTakeOutCheckbox() throws IOException, InterruptedException {
        return $(excludeTakeOutCheckbox);
    }

    public Element excludeDeliveryCheckbox() throws IOException, InterruptedException {
        return $(excludeDeliveryCheckbox);
    }

    public Element taxRatesTxtPopup() throws IOException, InterruptedException {
        return $(taxRatesTxtPopup);
    }
}
