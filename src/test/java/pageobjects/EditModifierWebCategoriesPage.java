package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditModifierWebCategoriesPage extends BasePO {

    private By nameUS = By.id("txt_BO_MN_MWC_ADDEDT_Name_en-us");
    private By descriptionUS = By.id("txt_BO_MN_MWC_ADDEDT_Desc_en-us");
    private By nameSpanish = By.id("txt_BO_MN_MWC_ADDEDT_Name_sp-mx");
    private By descriptionSpanish = By.id("txt_BO_MN_MWC_ADDEDT_Desc_sp-mx");
    private By save = By.xpath("//button[@onclick='BO_MN_MWC_ADDEDT_Save();']");
    private By modifierWebCatTextPopUp = By.xpath("//div[@id='div_BO_MN_MWC_ADDEDT_DIALOG_modal_view']//span[text()='Modifier Web Category']");
    private By cancelBtn = By.xpath("//button[@onclick='BO_MN_MWC_ADDEDT_Save();']//preceding-sibling::button[text()='Cancel']");

    public Element modifierWebCatTextPopUp() throws IOException, InterruptedException {
        return $(modifierWebCatTextPopUp);
    }

    public Element cancelBtn() throws IOException, InterruptedException {
        return $(cancelBtn);
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
}
