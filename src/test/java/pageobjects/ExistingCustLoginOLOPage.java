package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ExistingCustLoginOLOPage extends BasePO {

    private By existingCustLogin = By.id("lbl_CHKOUT_Existing_Cust_Login");
    private By firstName = By.id("txt_CHKOUT_name");
    private By lastName = By.id("txt_CHKOUT_lastname");
    private By email = By.id("eml_CHKOUT_email1");
    private By reEnterEmail = By.id("eml_CHKOUT_email2");
    private By phoneNo = By.id("tel_CHKOUT_phone1");
    private By reEnterPhoneNo = By.id("tel_CHKOUT_phone2");
    private By payInStore = By.id("rdo_CHKOUT_Store");
    private By termsAndCondition = By.id("chk_CHKOUT_PrivTerm");
    private By placeYourOrder = By.id("btn_CHKOUT_Submit");

    public Element placeYourOrder() throws IOException, InterruptedException {
        return $(placeYourOrder);
    }

    public Element termsAndCondition() throws IOException, InterruptedException {
        return $(termsAndCondition);
    }

    public Element payInStore() throws IOException, InterruptedException {
        return $(payInStore);
    }

    public Element reEnterPhoneNo() throws IOException, InterruptedException {
        return $(reEnterPhoneNo);
    }

    public Element phoneNo() throws IOException, InterruptedException {
        return $(phoneNo);
    }

    public Element reEnterEmail() throws IOException, InterruptedException {
        return $(reEnterEmail);
    }

    public Element email() throws IOException, InterruptedException {
        return $(email);
    }

    public Element lastName() throws IOException, InterruptedException {
        return $(lastName);
    }

    public Element firstName() throws IOException, InterruptedException {
        return $(firstName);
    }

    public Element existingCustLogin() throws IOException, InterruptedException {
        return $(existingCustLogin);
    }

}