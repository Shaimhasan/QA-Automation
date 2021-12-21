package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CopyToInventoryEntryPage extends BasePO {

    private By newWeekly = By.xpath("//div[@id='div_BO_IN_ENT_CopyTo_Frequencies']//div[text()='New Weekly']");
    private By date = By.id("txt_INPUT");
    private By save = By.xpath("//div[@data-title='Copy To']//button[text()='Save']");
    private By copyToTxt = By.xpath("//div[@class='modal-view' and starts-with(@id,'div_Input_Box')]//span[text()='Copy To']");

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element copyToTxt() throws IOException, InterruptedException {
        return $(copyToTxt);
    }

    public Element newWeekly() throws IOException, InterruptedException {
        return $(newWeekly);
    }

    public Element date() throws IOException, InterruptedException {
        return $(date);
    }

}
