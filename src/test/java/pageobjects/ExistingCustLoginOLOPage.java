package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ExistingCustLoginOLOPage extends BasePO {

    private By existingCustLogin = By.xpath("//div[text()='Existing Customer Login']");
    private By firstName = By.id("txt_CHKOUT_name");
    private By lastName = By.id("txt_CHKOUT_lastname");
    private By email = By.id("eml_CHKOUT_email1");
    private By reEnterEmail = By.id("eml_CHKOUT_email2");
    private By phoneNo = By.id("tel_CHKOUT_phone1");
    private By reEnterPhoneNo = By.id("tel_CHKOUT_phone2");
    private By payInStore = By.id("rdo_CHKOUT_Store");
    private By cash = By.id("rdo_CHKOUT_Cash");
    private By termsAndCondition = By.id("chk_CHKOUT_PrivTerm");
    private By placeYourOrder = By.id("btn_CHKOUT_Submit");
    private By creditCard = By.id("rdo_CHKOUT_Credit");
    private By cardNumber = By.xpath("//input[@name='cardNumber']");
    private By cardExpiration = By.xpath("//input[@name='cardExpiration']");
    private By cardCvv = By.name("cardCvv");
    private By zipCode = By.id("txt_CHKOUT_CCZip");
    private By billingAddress = By.id("txt_CHKOUT_CCAddr");
    private By customerInfo = By.xpath("//div[text()='Contact Information']");

    public Element customerInfo() throws IOException, InterruptedException {
        return $(customerInfo);
    }

    public Element cash() throws IOException, InterruptedException {
        return $(cash);
    }

    public Element cardCvv() throws IOException, InterruptedException {
        return $(cardCvv);
    }

    public Element billingAddress() throws IOException, InterruptedException {
        return $(billingAddress);
    }

    public Element zipCode() throws IOException, InterruptedException {
        return $(zipCode);
    }

    public Element cardExpiration() throws IOException, InterruptedException {
        return $(cardExpiration);
    }

    public Element cardNumber() throws IOException, InterruptedException {
        return $(cardNumber);
    }

    public Element creditCard() throws IOException, InterruptedException {
        return $(creditCard);
    }

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