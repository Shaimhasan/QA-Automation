package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class EditModifiersPage extends BasePO {

    private By nameUS = By.id("txt_BO_MN_MOD_ADDEDT_Name_en-us");
    private By webNameUS = By.id("txt_BO_MN_MOD_ADDEDT_Web_Name_en-us");
    private By descriptionUS = By.id("txt_BO_MN_MOD_ADDEDT_Desc_en-us");
    private By webDescriptionUS = By.id("txt_BO_MN_MOD_ADDEDT_Web_Desc_en-us");
    private By nameSpanish = By.id("txt_BO_MN_MOD_ADDEDT_Name_sp-mx");
    private By webNameSpanish = By.id("txt_BO_MN_MOD_ADDEDT_Web_Name_sp-mx");
    private By descriptionSpanish = By.id("txt_BO_MN_MOD_ADDEDT_Desc_sp-mx");
    private By webDescriptionSpanish = By.id("txt_BO_MN_MOD_ADDEDT_Web_Desc_sp-mx");
    private By activeChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_MOD_ADDEDT_Active']");
    private By save = By.xpath("//button[@onclick='BO_MN_MOD_ADDEDT_Save();']");
    private By activeCheckBoxIsSelected = By.xpath("//label[@for='chk_BO_MN_MOD_ADDEDT_Active']");
    private By activeCheckBoxIsSelectedValidate = By.xpath("//label[@for='chk_BO_MN_WCT_ADDEDT_Active']");
    private By cancelBtn = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_DIALOG_modal']//button[@id='btn_BO_MN_MOD_ADDEDT_Cancel']");
    private By modifiersCode = By.id("txt_BO_MN_MOD_ADDEDT_Code");
    private By addModifiersText = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_DIALOG_modal_view']//span[text()='Modifier']");

    public Element addModifiersText() throws IOException, InterruptedException {
        return $(addModifiersText);
    }

    public Element modifiersCode() throws IOException, InterruptedException {
        return $(modifiersCode);
    }

    public Element activeCheckBoxIsSelectedValidate() throws IOException, InterruptedException {
        return $(activeCheckBoxIsSelectedValidate);
    }

    public Element cancelBtn() throws IOException, InterruptedException {
        return $(cancelBtn);
    }

    public List<Element> activeCheckBoxIsSelected() throws IOException, InterruptedException {
        return $$(activeCheckBoxIsSelected);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


    public Element activeChkBx() throws IOException, InterruptedException {
        return $(activeChkBx);
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

    public Element webNameUS() throws IOException, InterruptedException {
        return $(webNameUS);
    }


    public Element webDescriptionUS() throws IOException, InterruptedException {
        return $(webDescriptionUS);
    }


    public Element webNameSpanish() throws IOException, InterruptedException {
        return $(webNameSpanish);
    }


    public Element webDescriptionSpanish() throws IOException, InterruptedException {
        return $(webDescriptionSpanish);
    }
}
