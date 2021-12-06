package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddCommentsPage extends BasePO {

    private By enterComment = By.id("txt_BO_MN_MOD_ADDEDT_comment");
    private By save = By.xpath("//button[@onclick='BO_MN_COM_ADDEDT_Save();']");
    private By commentsTextPopUp = By.xpath("//div[@id='div_BO_MN_COM_ADDEDT_DIALOG_modal_view']//span[text()='Comment']");

    public Element commentsTextPopUp() throws IOException, InterruptedException {
        return $(commentsTextPopUp);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element enterComment() throws IOException, InterruptedException {
        return $(enterComment);
    }
}
