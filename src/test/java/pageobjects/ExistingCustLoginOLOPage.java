package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class ExistingCustLoginOLOPage extends BasePO {

    private By existingCustLogin = By.xpath("//div[text()='Existing customer login']");
    private By checkoutTxt = By.xpath("//h2[text()='Checkout']");
    private By firstName = By.id("txt_checkout_name");
    private By lastName = By.id("txt_checkout_last_name");
    private By email = By.id("txt_checkout_email");
    private By reEnterEmail = By.id("eml_CHKOUT_email2");
    private By phoneNo = By.id("txt_checkout_phone");
    private By reEnterPhoneNo = By.id("tel_CHKOUT_phone2");
    private By payInStore = By.id("rdo_checkout_in_store");
    private By cash = By.id("rdo_checkout_cash");
    private By termsAndCondition = By.id("chk_checkout_privacy_terms");
    private By placeYourOrder = By.id("btn_checkout_submit");
    private By creditCard = By.id("rdo_checkout_credit");
    private By cardNumber = By.xpath("//input[@name='cardNumber']");
    private By cardExpiration = By.xpath("//input[@name='cardExpiration']");
    private By cardCvv = By.name("cardCvv");
    private By zipCode = By.id("txt_checkout_credit_entry_billing_zip");
    private By billingAddress = By.id("txt_checkout_credit_entry_billing_address");
    private By customerInfo = By.xpath("//div[text()='Contact Information']");

    public Element checkoutTxt() throws IOException, InterruptedException {
        return $(checkoutTxt);
    }

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