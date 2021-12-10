package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddPOSCategoriesPage extends BasePO {

    private By nameUS = By.id("txt_BO_MN_CAT_ADDEDT_Name_en-us");
    private By descriptionUS = By.id("txt_BO_MN_CAT_ADDEDT_Desc_en-us");
    private By nameSpanish = By.id("txt_BO_MN_CAT_ADDEDT_Name_sp-mx");
    private By descriptionSpanish = By.id("txt_BO_MN_CAT_ADDEDT_Desc_sp-mx");
    private By selectMenuDrpDwn = By.xpath("//select[@id='ddl_BO_MN_CAT_ADDEDT_Menus']");
    private By selectMergeCatDrpDwn = By.xpath("//select[@id='ddl_BO_MN_CAT_ADDEDT_SharedCategory']");
    private By activeChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_CAT_ADDEDT_Active']");
    private By save = By.xpath("//button[@onclick='BO_MN_CAT_ADDEDT_Save();']");
    private By posCatTextPopUp = By.xpath("//div[@id='div_BO_MN_CAT_ADDEDT_DIALOG_modal_view']//span[text()='POS Category']");

    public Element selectMenuDrpDwn() throws IOException, InterruptedException {
        return $(selectMenuDrpDwn);
    }

    public Element posCatTextPopUp() throws IOException, InterruptedException {
        return $(posCatTextPopUp);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element activeChkBx() throws IOException, InterruptedException {
        return $(activeChkBx);
    }

    public Element selectMergeCatDrpDwn() throws IOException, InterruptedException {
        return $(selectMergeCatDrpDwn);
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


}
