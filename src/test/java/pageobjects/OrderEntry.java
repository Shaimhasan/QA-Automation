package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderEntry extends BasePO {

    private By dinInColor = By.id("divOrderBody");
    private By suprimePizza = By.xpath("//label[text()='Supreme Pizza']");
    private By suprimePizzaSelected = By.xpath("//div[text()='1) Supreme Pizza']");
    private By veggiePizza = By.xpath("//label[text()='Veggie Pizza']");
    private By veggiePizzaSelected = By.xpath("//div[text()='2) Veggie Pizza']");
    private By finishBtn = By.xpath("//label[text()='Finish']");
    private By tableNoPopUpMenu = By.xpath("//span[text()='Table No. / Customer Name']");
    private By tableNo = By.id("txtTableNo");
    private By OK = By.id("btnTableNo");
    private By cash = By.id("btn2CASHPAYMENT_1_");
    private By headerPopUpChangeDue = By.xpath("//span[text()='Change Due']");
    private By close = By.id("btnClosePrintReceipt");

    public Element cash() throws IOException, InterruptedException {
        return $(cash);
    }

    public Element headerPopUpChangeDue() throws IOException, InterruptedException {
        return $(headerPopUpChangeDue);
    }

    public Element close() throws IOException, InterruptedException {
        return $(close);
    }

    public Element OK() throws IOException, InterruptedException {
        return $(OK);
    }

    public Element tableNo() throws IOException, InterruptedException {
        return $(tableNo);
    }

    public Element tableNoPopUpMenu() throws IOException, InterruptedException {
        return $(tableNoPopUpMenu);
    }

    public Element finishBtn() throws IOException, InterruptedException {
        return $(finishBtn);
    }

    public Element dinInColor() throws IOException, InterruptedException {
        return $(dinInColor);
    }

    public Element suprimePizza() throws IOException, InterruptedException {
        return $(suprimePizza);
    }

    public Element suprimePizzaSelected() throws IOException, InterruptedException {
        return $(suprimePizzaSelected);
    }

    public Element veggiePizza() throws IOException, InterruptedException {
        return $(veggiePizza);
    }

    public Element veggiePizzaSelected() throws IOException, InterruptedException {
        return $(veggiePizzaSelected);
    }
}
