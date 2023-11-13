package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditPricingGroupsPage extends BasePO {

    private By nameUS = By.xpath("//input[@id='txt_BO_ST_PGP_ADDEDT_Name_en-us']");
    private By descriptionUS = By.xpath("//input[@id='txt_BO_ST_PGP_ADDEDT_Desc_en-us']");
    private By nameSpanish = By.xpath("//input[@id='txt_BO_ST_PGP_ADDEDT_Name_sp-mx']");
    private By descriptionSpanish = By.xpath("//input[@id='txt_BO_ST_PGP_ADDEDT_Desc_sp-mx']");
    private By thirdPartyCheckbox = By.id("chk_BO_ST_PGP_ADDEDT_3rdParty");
    private By thirdPartyCheckboxIsSelected = By.xpath("//label[@for='chk_BO_ST_PGP_ADDEDT_3rdParty']");
    private By save = By.xpath("//button[@id='btn_BO_ST_PGP_ADDEDT_Save']");
    private By cancel = By.xpath("//button[@id='btn_BO_ST_PGP_ADDEDT_Cancel']");
    private By priceGroupTxtPopup = By.xpath("//div[@id='div_BO_ST_PGP_ADDEDT_DIALOG_modal']//span[text()='Price Group']");
    private By continueOnWarning = By.xpath("//div[text()='3rd Party Menu']/..//button[text()='Continue']");



    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element thirdPartyCheckbox() throws IOException, InterruptedException {
        return $(thirdPartyCheckbox);
    }
    public Element thirdPartyCheckboxIsSelected() throws IOException, InterruptedException {
        return $(thirdPartyCheckboxIsSelected);
    }
    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element nameUS() throws IOException, InterruptedException {
        return $(nameUS);
    }


    public Element descriptionUS() throws IOException, InterruptedException {
        return $(descriptionUS);
    }


    public Element nameSpanish() throws IOException, InterruptedException {
        return $(nameSpanish);
    }


    public Element descriptionSpanish() throws IOException, InterruptedException {
        return $(descriptionSpanish);
    }

    public Element priceGroupTxtPopup() throws IOException, InterruptedException {
        return $(priceGroupTxtPopup);
    }
    public Element continueOnWarning() throws IOException, InterruptedException {
        return $(continueOnWarning);
    }
}
