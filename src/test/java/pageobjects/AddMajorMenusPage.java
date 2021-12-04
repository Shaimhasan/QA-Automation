package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddMajorMenusPage extends BasePO {

    private By menuName = By.id("txt_BO_MN_MNU_ADDEDT_Field");
    private By save = By.xpath("//button[@onclick='BO_MN_MNU_ADDEDT_Save();']");
    private By majorMenusTextPopUp = By.xpath("//div[@id='div_BO_MN_MNU_ADDEDT_DIALOG_modal']//span[text()='Major Menus']");

    public Element majorMenusTextPopUp() throws IOException, InterruptedException {
        return $(majorMenusTextPopUp);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element menuName() throws IOException, InterruptedException {
        return $(menuName);
    }
}
