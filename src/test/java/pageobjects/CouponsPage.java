package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class CouponsPage extends BasePO {

    private By addBtn = By.xpath("//button[normalize-space(@id)='btn_BO_CP_CPN_AddNew']");
    private By editBtn = By.id("btn_BO_CP_CPN_Edit");
    private By editBtnDisable = By.xpath("//button[@id='btn_BO_CP_CPN_Edit' and @class='button-disabled ui-state-disabled']");
    private By table = By.id("tbl_BO_List_CP_CPN");
    private By history = By.id("btn_BO_HISTORY");
    private By actAndDeact = By.id("btn_BO_CP_CPN_AVAL");
    private By cloneBtn = By.id("btn_BO_CP_CPN_Clone");
    private By cloneConfirmationBtn = By.xpath("//p[contains(text(),'Are you sure you want to clone')]/..//following-sibling::div//button[text()='Clone']");
    private By cloneWarningTxt = By.xpath("//div[text()='Warning']");
    private By delete = By.id("btn_BO_CP_CPN_Del");
    private By deleteOnWarning = By.xpath("//div[text()='Warning']/..//button[text()='Delete']");
    private By selectPricePointDialogBox = By.id("div_BO_CP_CPN_SELECT_DIALOG");
    private By deletePricePoint = By.xpath("(//div[@class='center-child']//button)[1]");

    public List<Element> deletePricePoint() throws IOException, InterruptedException {
        return $$(deletePricePoint);
    }

    public Element selectPricePointDialogBox() throws IOException, InterruptedException {
        return $(selectPricePointDialogBox);
    }

    public Element editBtnDisable() throws IOException, InterruptedException {
        return $(editBtnDisable);
    }

    public Element deleteOnWarning() throws IOException, InterruptedException {
        return $(deleteOnWarning);
    }

    public Element delete() throws IOException, InterruptedException {
        return $(delete);
    }

    public Element cloneWarningTxt() throws IOException, InterruptedException {
        return $(cloneWarningTxt);
    }

    public Element cloneConfirmationBtn() throws IOException, InterruptedException {
        return $(cloneConfirmationBtn);
    }

    public Element cloneBtn() throws IOException, InterruptedException {
        return $(cloneBtn);
    }

    public Element actAndDeact() throws IOException, InterruptedException {
        return $(actAndDeact);
    }

    public Element history() throws IOException, InterruptedException {
        return $(history);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }

}
