package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PaymentPage extends BasePO {

    private By cash = By.id("btn2CASHPAYMENT_1_");
    private By credit = By.xpath("//label[text()='Credit Card']");
    private By send = By.xpath("//span[text()='Send']");
    private By finish = By.xpath("//span[text()='Finish']");
    private By half = By.xpath("//span[text()='1/2']");
    private By fiftyDollar = By.id("btn2CASHBILL_1_50");

    public Element finish() throws IOException, InterruptedException {
        return $(finish);
    }
    public Element fiftyDollar() throws IOException, InterruptedException {
        return $(fiftyDollar);
    }

    public Element half() throws IOException, InterruptedException {
        return $(half);
    }

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
