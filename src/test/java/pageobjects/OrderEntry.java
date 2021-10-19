package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderEntry extends BasePO {

    private By dinInColor = By.id("divOrder");
    private By suprimePizza = By.xpath("//label[text()='Supreme Pizza']");
    private By paperroniPizza = By.xpath("//label[text()='Pepperoni Pizza']");
    private By suprimePizzaSelected = By.xpath("//div[text()='1) Supreme Pizza']");
    private By paperroniPizzaSelected = By.xpath("//div[text()='3) Pepperoni Pizza']");
    private By veggiePizza = By.xpath("//label[text()='Veggie Pizza']");
    private By veggiePizzaSelected = By.xpath("//div[text()='2) Veggie Pizza']");
    private By finishBtn = By.xpath("//label[text()='Finish']");
    private By tableNoPopUpMenu = By.xpath("//span[text()='Table No. / Customer Name']");
    private By tableNo = By.id("txtTableNo");
    private By OK = By.id("btnTableNo");
    private By headerPopUpChangeDue = By.xpath("//span[text()='Change Due']");
    private By close = By.id("btnClosePrintReceipt");
    private By amount = By.xpath("//td[text()='Amount Due']//following-sibling::td");
    private By transactionNum = By.id("lblEndOfOrderID");
    private By orderNum = By.id("lblEndOfOrderNo");
    private By adoraHeaderSVG = By.xpath("//div[@class='AdoraTopArrow']//*[local-name()='svg' and @id='Layer_1']");
    private By makeLine = By.xpath("//div[text()='Make Line']");


    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }

    public Element orderNum() throws IOException, InterruptedException {
        return $(orderNum);
    }

    public Element adoraHeaderSVG() throws IOException, InterruptedException {
        return $(adoraHeaderSVG);
    }

    public Element transactionNum() throws IOException, InterruptedException {
        return $(transactionNum);
    }

    public Element amount() throws IOException, InterruptedException {
        return $(amount);
    }

    public Element paperroniPizza() throws IOException, InterruptedException {
        return $(paperroniPizza);
    }

    public Element paperroniPizzaSelected() throws IOException, InterruptedException {
        return $(paperroniPizzaSelected);
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
