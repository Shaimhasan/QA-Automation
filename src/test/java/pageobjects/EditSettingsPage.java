package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class EditSettingsPage extends BasePO {

    private By edit = By.id("btn_BO_ST_SET_Edit");
    private By settingPopupTxt = By.xpath("//div[@id='div_BO_ST_SET_EDT_DIALOG_modal_view']//span[text()='Setting']");
    private By drpDwn = By.xpath("//select[@id='ddl_BO_ST_SET_EDT']");
    private By save = By.xpath("//div[@id='div_BO_ST_SET_EDT_DIALOG_modal_view']//button[text()='Save']");

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element drpDwn() throws IOException, InterruptedException {
        return $(drpDwn);
    }

    public Element settingPopupTxt() throws IOException, InterruptedException {
        return $(settingPopupTxt);
    }

    public Element edit() throws IOException, InterruptedException {
        return $(edit);
    }

}
