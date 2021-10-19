package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PaymentPage extends BasePO {

    private By cash = By.id("btn2CASHPAYMENT_1_");
    private By credit = By.xpath("//label[text()='Credit Card']");
    private By send = By.xpath("//span[text()='Send']");

    public Element cash() throws IOException, InterruptedException {
        return $(cash);
    }

    public Element send() throws IOException, InterruptedException {
        return $(send);
    }

    public Element credit() throws IOException, InterruptedException {
        return $(credit);
    }
}
