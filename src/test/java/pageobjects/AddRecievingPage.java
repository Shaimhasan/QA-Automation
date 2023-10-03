package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddRecievingPage extends BasePO {

    private By invoiceNum = By.id("txt_BO_IN_REC_ADDEDT_InvoiceNo");
    private By automation = By.xpath("//div[text()='Automation Testing1031']");
    private By date = By.id("txt_BO_IN_REC_ADDEDT_RecDate");
    private By tax = By.id("txt_BO_IN_REC_ADDEDT_Tax");
    private By freight = By.id("txt_BO_IN_REC_ADDEDT_Freight");
    private By others = By.id("txt_BO_IN_REC_ADDEDT_OtherCharges");
    private By recievingTxtPopup = By.xpath("//span[@id='div_BO_IN_REC_ADDEDT_DIALOG_Header_Title']");
    private By save = By.id("btn_BO_IN_VIT_ADDEDT_Save");

    public Element others() throws IOException, InterruptedException {
        return $(others);
    }

    public Element freight() throws IOException, InterruptedException {
        return $(freight);
    }

    public Element automation() throws IOException, InterruptedException {
        return $(automation);
    }

    public Element recievingTxtPopup() throws IOException, InterruptedException {
        return $(recievingTxtPopup);
    }

    public Element tax() throws IOException, InterruptedException {
        return $(tax);
    }

    public Element invoiceNum() throws IOException, InterruptedException {
        return $(invoiceNum);
    }

    public Element date() throws IOException, InterruptedException {
        return $(date);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

}
