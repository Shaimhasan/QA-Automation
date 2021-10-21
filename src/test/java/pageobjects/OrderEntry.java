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
    private By panCrustSelected = By.xpath("//div[text()='Pan Crust']");
    private By whiteSauceSelected = By.xpath("//div[text()='White Sauce']");
    private By toppingChickenSelected = By.xpath("//div[text()='Chicken']");
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
    private By delivery = By.xpath("//label[text()='Delivery']");
    private By panCrust = By.xpath("//label[text()='Pan Crust']");
    private By whiteSauce = By.xpath("//label[text()='White Sauce']");
    private By toppingChicken = By.xpath("//label[text()='Chicken']");
    private By takeOut = By.xpath("//button[@data-text='Take Out']");
    private By takeOutColor = By.id("divOrder");


    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }

    public Element takeOut() throws IOException, InterruptedException {
        return $(takeOut);
    }

    public Element takeOutColor() throws IOException, InterruptedException {
        return $(takeOutColor);
    }

    public Element panCrustSelected() throws IOException, InterruptedException {
        return $(panCrustSelected);
    }

    public Element whiteSauceSelected() throws IOException, InterruptedException {
        return $(whiteSauceSelected);
    }

    public Element toppingChickenSelected() throws IOException, InterruptedException {
        return $(toppingChickenSelected);
    }

    public Element toppingChicken() throws IOException, InterruptedException {
        return $(toppingChicken);
    }

    public Element whiteSauce() throws IOException, InterruptedException {
        return $(whiteSauce);
    }

    public Element panCrust() throws IOException, InterruptedException {
        return $(panCrust);
    }

    public Element delivery() throws IOException, InterruptedException {
        return $(delivery);
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
