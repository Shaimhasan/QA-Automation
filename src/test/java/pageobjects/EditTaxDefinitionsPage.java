package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditTaxDefinitionsPage extends BasePO {

    private By nameUS = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_Name_en-us']");
    private By descriptionUS = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_Desc_en-us']");
    private By nameSpanish = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_Name_sp-mx']");
    private By descriptionSpanish = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_Desc_sp-mx']");
    private By taxRate = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_TaxPercent']");
    private By fixedAmt = By.xpath("//input[@id='txt_BO_ST_TAX_ADDEDT_FixAmount']");
    private By save = By.xpath("//button[@id='btn_BO_ST_TAX_ADDEDT_Save']");
    private By cancel = By.xpath("//button[@id='btn_BO_ST_TAX_ADDEDT_Cancel']");
    private By taxDefinitionsTxtPopup = By.xpath("//div[@id='div_BO_ST_TAX_ADDEDT_DIALOG_modal']//span[text()='Tax Definition']");




    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element fixedAmt() throws IOException, InterruptedException {
        return $(fixedAmt);
    }

    public Element taxRate() throws IOException, InterruptedException {
        return $(taxRate);
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

    public Element taxDefinitionsTxtPopup() throws IOException, InterruptedException {
        return $(taxDefinitionsTxtPopup);
    }
}
