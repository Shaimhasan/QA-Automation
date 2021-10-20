package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CreditCardPage extends BasePO {

    private By cardNum = By.id("txtCC");
    private By expirationMonth = By.id("txtCCExp1");
    private By expirationYear = By.id("txtCCExp2");
    private By cvv = By.id("txtCCCVV");
    private By chargeBtn = By.xpath("//button[text()='Charge']");

    public Element cardNum() throws IOException, InterruptedException {
        return $(cardNum);
    }

    public Element chargeBtn() throws IOException, InterruptedException {
        return $(chargeBtn);
    }

    public Element expirationMonth() throws IOException, InterruptedException {
        return $(expirationMonth);
    }

    public Element expirationYear() throws IOException, InterruptedException {
        return $(expirationYear);
    }

    public Element cvv() throws IOException, InterruptedException {
        return $(cvv);
    }
}
