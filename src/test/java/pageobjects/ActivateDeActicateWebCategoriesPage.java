package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ActivateDeActicateWebCategoriesPage extends BasePO {

    private By availabelChkBox = By.xpath("//input[normalize-space(@id)='chk_BO_MN_WCT_AVAL_Aval']");
    private By save = By.xpath("//button[@onclick='BO_MN_WCT_AVAL_Save();']");
    private By cancel = By.xpath("//button[@onclick='BO_MN_WCT_AVAL_Save();']//preceding-sibling::button[text()='Cancel']");
    private By availabelChkBoxIsSelected = By.xpath("//label[@for='chk_BO_MN_WCT_AVAL_Aval']");
    private By webCatTextVal = By.xpath("//div[@id='div_BO_MN_WCT_AVAL_DIALOG_modal_view']//span[text()='Web Category']");

    public Element webCatTextVal() throws IOException, InterruptedException {
        return $(webCatTextVal);
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
