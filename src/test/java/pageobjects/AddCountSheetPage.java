package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddCountSheetPage extends BasePO {

    private By frequency = By.xpath("//select[@id='ddl_BO_IN_CNT_ADDEDT_Freq']");
    private By location = By.xpath("//select[@id='ddl_BO_IN_CNT_ADDEDT_Loc']");
    private By allItems = By.xpath("//input[normalize-space(@id)='chk_BO_IN_CNT_ADDEDT_Items_Active']");
    private By items = By.xpath("//input[normalize-space(@id)='chk_BO_IN_CNT_ADDEDT_CountSheet_342']");
    private By countSheetTxtPopup = By.xpath("//div[@id='div_BO_IN_CNT_ADDEDT_DIALOG_modal_view']//span[text()='Count Sheet']");
    private By save = By.xpath("//button[@onclick='BO_IN_CNT_ADDEDT_Save();']");


    public Element items() throws IOException, InterruptedException {
        return $(items);
    }

    public Element frequency() throws IOException, InterruptedException {
        return $(frequency);
    }

    public Element countSheetTxtPopup() throws IOException, InterruptedException {
        return $(countSheetTxtPopup);
    }

    public Element location() throws IOException, InterruptedException {
        return $(location);
    }

    public Element allItems() throws IOException, InterruptedException {
        return $(allItems);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

}
