package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ActDeactMajorMenusPage extends BasePO {

    private By availabelChkBox = By.xpath("//input[normalize-space(@id)='chk_BO_MN_MNU_AVAL_Aval']");
    private By save = By.id("btn_BO_MN_MNU_AVAL_Save");
    private By cancel = By.id("btn_BO_MN_MNU_AVAL_Cancel");
    private By availabelChkBoxIsSelected = By.xpath("//label[@for='chk_BO_MN_MNU_AVAL_Aval']");
    private By majorMenusTextVal = By.xpath("//div[@id='div_BO_MN_MNU_AVAL_DIALOG_modal_view']//span[text()='Major Menus']");

    public Element majorMenusTextVal() throws IOException, InterruptedException {
        return $(majorMenusTextVal);
    }

    public Element cancel() throws IOException, InterruptedException {
        return $(cancel);
    }

    public Element availabelChkBox() throws IOException, InterruptedException {
        return $(availabelChkBox);
    }

    public Element availabelChkBoxIsSelected() throws IOException, InterruptedException {
        return $(availabelChkBoxIsSelected);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }


}
