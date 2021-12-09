package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CouponsPage extends BasePO {

    private By addBtn = By.id("btn_BO_CP_CPN_AddNew");
    private By editBtn = By.id("btn_BO_CP_CPN_Edit");
    private By table = By.id("tbl_BO_List_CP_CPN");
    private By history = By.id("btn_BO_HISTORY");
    private By actAndDeact = By.id("btn_BO_CP_CPN_AVAL");
    private By cloneBtn = By.id("btn_BO_CP_CPN_Clone");
    private By cloneConfirmationBtn = By.xpath("//div[contains(text(),'Are you sure you want to clone')]/..//following-sibling::div//button[text()='Clone']");
    private By cloneWarningTxt = By.xpath("//span[text()='Warning']");

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
