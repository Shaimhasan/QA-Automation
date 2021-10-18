package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CreditCardPage extends BasePO {

    private By cardNum = By.xpath("//input[@name='cardNumber']");
    private By expiration = By.xpath("//input[@name='cardExpiration']");
    private By cvv = By.xpath("//input[@name='cardCvv']");
    private By chargeBtn = By.xpath("//button[text()='Charge']");

    public Element cardNum() throws IOException, InterruptedException {
        return $(cardNum);
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
