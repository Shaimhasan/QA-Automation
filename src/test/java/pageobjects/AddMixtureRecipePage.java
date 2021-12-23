package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddMixtureRecipePage extends BasePO {

    private By name = By.id("txt_BO_IN_MIX_ADDEDT_Name");
    private By conversionFactor = By.id("txt_BO_IN_MIX_ADDEDT_ConversionFactor");
    private By save = By.xpath("//div[@id='div_BO_IN_MIX_ADDEDT_DIALOG_modal_view']//button[text()='Save']");
    private By mixtureRecipeText = By.xpath("//div[@id='div_BO_IN_MIX_ADDEDT_DIALOG_modal_view']//span[text()='Mixture']");
    private By toMake = By.id("txt_BO_IN_MIX_ADDEDT_MakeAmount");
    private By item1ChkBox = By.xpath("//label[text()='Amarretto']/../input[contains(normalize-space(@id),'chk_BO_IN_MIX_ADDEDT_InvItm')]");
    private By item2ChkBox = By.xpath("//label[text()='Artichoke']/../input[contains(normalize-space(@id),'chk_BO_IN_MIX_ADDEDT_InvItm')]");
    private By iteventoryUnit1 = By.xpath("//span[text()='Bottle']/../input[contains(@id,'txt_BO_IN_MIX_ADDEDT_INVITMS_IU')]");
    private By iteventoryUnit2 = By.xpath("//span[text()='Each']/../input[contains(@id,'txt_BO_IN_MIX_ADDEDT_INVITMS_IU')]");
    private By usageUnit1 = By.xpath("//span[text()='Fluid Ounce']/../input[contains(@id,'txt_BO_IN_MIX_ADDEDT_INVITMS_UU')]");
    private By usageUnit2 = By.xpath("//tr[@id='364']//input[@id='txt_BO_IN_MIX_ADDEDT_INVITMS_UU_364']");


    public Element iteventoryUnit2() throws IOException, InterruptedException {
        return $(iteventoryUnit2);
    }

    public Element iteventoryUnit1() throws IOException, InterruptedException {
        return $(iteventoryUnit1);
    }

    public Element usageUnit2() throws IOException, InterruptedException {
        return $(usageUnit2);
    }

    public Element usageUnit1() throws IOException, InterruptedException {
        return $(usageUnit1);
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
