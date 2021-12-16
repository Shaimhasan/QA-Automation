package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CreateMixMixturesPrepPage extends BasePO {

    private By making = By.id("txt_BO_IN_MXP_ADDEDT_MakeAmount");
    private By mixtureDrpDwn = By.xpath("//select[@id='ddl_BO_IN_MXP_ADDEDT_Mixtures']");
    private By date = By.id("txt_BO_IN_MXP_ADDEDT_Date");
    private By mixturesPrepTxtPopup = By.xpath("//div[@id='div_BO_IN_MXP_ADDEDT_DIALOG_modal_view']//span[text()='Mixtures Prep']");
    private By save = By.xpath("//button[@onclick='BO_IN_MXP_ADDEDT_Save();']");


    public Element mixtureDrpDwn() throws IOException, InterruptedException {
        return $(mixtureDrpDwn);
    }

    public Element mixturesPrepTxtPopup() throws IOException, InterruptedException {
        return $(mixturesPrepTxtPopup);
    }

    public Element making() throws IOException, InterruptedException {
        return $(making);
    }

    public Element date() throws IOException, InterruptedException {
        return $(date);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

}
