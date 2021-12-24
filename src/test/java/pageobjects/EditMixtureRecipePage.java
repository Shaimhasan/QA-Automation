package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditMixtureRecipePage extends BasePO {

    private By name = By.id("txt_BO_IN_MIX_ADDEDT_Name");
    private By conversionFactor = By.id("txt_BO_IN_MIX_ADDEDT_ConversionFactor");
    private By save = By.xpath("//div[@id='div_BO_IN_MIX_ADDEDT_DIALOG_modal_view']//button[text()='Save']");
    private By mixtureRecipeText = By.xpath("//div[@id='div_BO_IN_MIX_ADDEDT_DIALOG_modal_view']//span[text()='Mixture']");
    private By cancel = By.xpath("//div[@id='div_BO_IN_MIX_ADDEDT_DIALOG_modal_view']//button[text()='Cancel']");
    private By toMake = By.id("txt_BO_IN_MIX_ADDEDT_MakeAmount");
    private By item1ChkBox = By.xpath("//input[normalize-space(@id)='chk_BO_IN_MIX_ADDEDT_InvItm_363']");
    private By item2ChkBox = By.xpath("//input[normalize-space(@id)='chk_BO_IN_MIX_ADDEDT_InvItm_364']");
    private By iteventoryUnit1TxtBx = By.xpath("//input[normalize-space(@id)='txt_BO_IN_MIX_ADDEDT_INVITMS_IU_363']");
    private By iteventoryUnit2TxtBx = By.xpath("//input[normalize-space(@id)='txt_BO_IN_MIX_ADDEDT_INVITMS_IU_364']");
    private By usageUnit1TxtBx = By.xpath("//input[normalize-space(@id)='txt_BO_IN_MIX_ADDEDT_INVITMS_UU_363']");
    private By usageUnit2TxtBx = By.xpath("//input[normalize-space(@id)='txt_BO_IN_MIX_ADDEDT_INVITMS_UU_364']");


    public Element iteventoryUnit2TxtBx() throws IOException, InterruptedException {
        return $(iteventoryUnit2TxtBx);
    }

    public Element iteventoryUnit1TxtBx() throws IOException, InterruptedException {
        return $(iteventoryUnit1TxtBx);
    }

    public Element usageUnit2TxtBx() throws IOException, InterruptedException {
        return $(usageUnit2TxtBx);
    }

    public Element usageUnit1TxtBx() throws IOException, InterruptedException {
        return $(usageUnit1TxtBx);
    }


    public Element item2ChkBox() throws IOException, InterruptedException {
        return $(item2ChkBox);
    }

    public Element item1ChkBox() throws IOException, InterruptedException {
        return $(item1ChkBox);
    }

    public Element toMake() throws IOException, InterruptedException {
        return $(toMake);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element mixtureRecipeText() throws IOException, InterruptedException {
        return $(mixtureRecipeText);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element name() throws IOException, InterruptedException {
        return $(name);
    }

    public Element conversionFactor() throws IOException, InterruptedException {
        return $(conversionFactor);
    }
}
