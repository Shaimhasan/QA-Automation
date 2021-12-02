package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ActDeactModifiersPage extends BasePO {

    private By availabelChkBox = By.xpath("//input[normalize-space(@id)='chk_BO_MN_MOD_AVAL_Aval']");
    private By activeChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_MOD_AVAL_Active']");
    private By activeChkBxIsSelected = By.xpath("//label[@for='chk_BO_MN_MOD_AVAL_Active']");
    private By save = By.xpath("//button[@onclick='BO_MN_MOD_AVAL_Save();']");
    private By cancel = By.xpath("//button[@onclick='BO_MN_MOD_AVAL_Save();']//preceding-sibling::button[text()='Cancel']");
    private By availabelChkBoxIsSelected = By.xpath("//label[@for='chk_BO_MN_MOD_AVAL_Aval']");
    private By modifiersTextVal = By.xpath("//div[@id='div_BO_MN_MOD_AVAL_DIALOG_modal_view']//span[text()='Modifier']");

    public Element activeChkBxIsSelected() throws IOException, InterruptedException {
        return $(activeChkBxIsSelected);
    }

    public Element activeChkBx() throws IOException, InterruptedException {
        return $(activeChkBx);
    }

    public Element modifiersTextVal() throws IOException, InterruptedException {
        return $(modifiersTextVal);
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
