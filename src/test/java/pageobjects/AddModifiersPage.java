package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddModifiersPage extends BasePO {

    private By nameUS = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Name']//div[text()='English - United States']//following-sibling::input[@data-cult='en-us']");
    private By webNameUS = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Web_Name']//div[text()='English - United States']//following-sibling::input[@data-cult='en-us']");
    private By descriptionUS = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Desc']//div[text()='English - United States']//following-sibling::input[@data-cult='en-us']");
    private By webDescriptionUS = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Web_Desc']//div[text()='English - United States']//following-sibling::input[@data-cult='en-us']");
    private By nameSpanish = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Name']//div[text()='Spanish - Mexico']//following-sibling::input[@data-cult='sp-mx']");
    private By webNameSpanish = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Web_Name']//div[text()='Spanish - Mexico']//following-sibling::input[@data-cult='sp-mx']");
    private By descriptionSpanish = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Desc']//div[text()='Spanish - Mexico']//following-sibling::input[@data-cult='sp-mx']");
    private By webDescriptionSpanish = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_Web_Desc']//div[text()='Spanish - Mexico']//following-sibling::input[@data-cult='sp-mx']");
    private By selectModifierWebCatDrpDwn = By.xpath("//select[@id='ddl_BO_MN_MOD_ADDEDT_Web_Cats']");
    private By activeChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_MOD_ADDEDT_Active']");
    private By save = By.xpath("//button[@onclick='BO_MN_MOD_ADDEDT_Save();']");
    private By modifiersCode = By.id("txt_BO_MN_MOD_ADDEDT_Code");
    private By addModifiersText = By.xpath("//div[@id='div_BO_MN_MOD_ADDEDT_DIALOG_modal_view']//span[text()='Modifier']");

    public Element modifiersCode() throws IOException, InterruptedException {
        return $(modifiersCode);
    }

    public Element addModifiersText() throws IOException, InterruptedException {
        return $(addModifiersText);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element selectModifierWebCatDrpDwn() throws IOException, InterruptedException {
        return $(selectModifierWebCatDrpDwn);
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
