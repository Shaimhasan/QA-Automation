package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CreditCardPage extends BasePO {

    private By cardNum = By.name("cardNumber");
    private By expiration = By.name("cardExpiration");
    private By cvv = By.name("cardCvv");
    private By chargeBtn = By.id("btnCreditCardCharge");
    private By zipCode = By.id("txtCCZip");

    public Element cardNum() throws IOException, InterruptedException {
        return $(cardNum);
    }

    public Element zipCode() throws IOException, InterruptedException {
        return $(zipCode);
    }

    public Element chargeBtn() throws IOException, InterruptedException {
        return $(chargeBtn);
    }

    public Element expiration() throws IOException, InterruptedException {
        return $(expiration);
    }

    public Element cvv() throws IOException, InterruptedException {
        return $(cvv);
    }
}
