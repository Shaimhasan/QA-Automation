package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderEntry extends BasePO {

    private By dinInColor = By.id("divOrder");
    private By automationPizzaPMC = By.xpath("//label[text()='Automation Pizza (P-M-C)']");
    private By cheesePizzaPMC = By.xpath("//label[text()='Cheese Pizza (P-M-C)']");
    private By hawaiianPizzaM = By.xpath("//label[text()='Hawaiian Pizza (P)']");
    private By mediumSize = By.xpath("//label[text()='Medium']");
    private By chicagoSylPizzaM = By.xpath("//label[text()='Chicago Style Pizza (M)']");
    private By pepperoniPizzaMC = By.xpath("//label[text()='Pepperoni Pizza (M-C)']");
    private By pepperoniPizzaMCIsSelected = By.xpath("//div[text()='1) Pepperoni Pizza (M-C)']");
    private By automationPizzaPMCSelected = By.xpath("//div[text()='1) Automation Pizza (P-M-C)']");
    private By chicagoSylPizzaMSelected = By.xpath("//div[text()='2) Chicago Style Pizza (M)']");
    private By cheesePizzaPMCSelected = By.xpath("//div[text()='2) Cheese Pizza (P-M-C)']");
    private By hawaiianPizzaMSelected = By.xpath("//div[text()='1) Hawaiian Pizza (P)']");
    private By finishBtn = By.xpath("//label[text()='Finish']");
    private By tableNoPopUpMenu = By.xpath("//span[text()='Table No. / Customer Name']");
    private By tableNo = By.id("txtTableNo");
    private By OK = By.id("btnTableNo");
    private By headerPopUpChangeDue = By.xpath("//span[text()='Change Due']");
    private By close = By.id("btnClosePrintReceipt");
    private By closeForDelivery = By.id("btnOrderEndOK");
    private By closeForPhoneTakeOut = By.id("btnOrderEndOK");
    private By amount = By.xpath("//td[text()='Amount Due']//following-sibling::td");
    private By transactionNum = By.id("lblEndOfOrderID");
    private By orderNum = By.id("lblEndOfOrderNo");
    private By adoraHeaderSVG = By.xpath("//div[normalize-space(@class)='AdoraTopArrow']");
    private By makeLine = By.xpath("//div[text()='Make Line']");
    private By delivery = By.xpath("//label[text()='Delivery']");
    private By panCrust = By.xpath("//label[text()='Pan Crust']");
    private By whiteSauce = By.xpath("//label[text()='White Sauce']");
    private By toppingChicken = By.xpath("//label[text()='Chicken']");
    private By takeOut = By.xpath("//button[@data-text='Take Out']");
    private By phoneTakeOut = By.xpath("//button[@data-text='Phone Take-Out']");
    private By takeOutColor = By.id("divOrder");
    private By phoneTakeOutColor = By.id("divOrder");
    private By changeDueAmt = By.xpath("//p[text()='Change Due']//following-sibling::div");


    public Element hawaiianPizzaMSelected() throws IOException, InterruptedException {
        return $(hawaiianPizzaMSelected);
    }

    public Element hawaiianPizzaM() throws IOException, InterruptedException {
        return $(hawaiianPizzaM);
    }

    public Element pepperoniPizzaMCIsSelected() throws IOException, InterruptedException {
        return $(pepperoniPizzaMCIsSelected);
    }

    public Element pepperoniPizzaMC() throws IOException, InterruptedException {
        return $(pepperoniPizzaMC);
    }

    public Element cheesePizzaPMCSelected() throws IOException, InterruptedException {
        return $(cheesePizzaPMCSelected);
    }

    public Element cheesePizzaPMC() throws IOException, InterruptedException {
        return $(cheesePizzaPMC);
    }

    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }

    public Element mediumSize() throws IOException, InterruptedException {
        return $(mediumSize);
    }

    public Element closeForDelivery() throws IOException, InterruptedException {
        return $(closeForDelivery);
    }

    public Element changeDueAmt() throws IOException, InterruptedException {
        return $(changeDueAmt);
    }

    public Element closeForPhoneTakeOut() throws IOException, InterruptedException {
        return $(closeForPhoneTakeOut);
    }

    public Element phoneTakeOut() throws IOException, InterruptedException {
        return $(phoneTakeOut);
    }

    public Element phoneTakeOutColor() throws IOException, InterruptedException {
        return $(phoneTakeOutColor);
    }

    public Element takeOut() throws IOException, InterruptedException {
        return $(takeOut);
    }

    public Element takeOutColor() throws IOException, InterruptedException {
        return $(takeOutColor);
    }

    public Element chicagoSylPizzaMSelected() throws IOException, InterruptedException {
        return $(chicagoSylPizzaMSelected);
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

    public Element chicagoSylPizzaM() throws IOException, InterruptedException {
        return $(chicagoSylPizzaM);
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

    public Element automationPizzaPMC() throws IOException, InterruptedException {
        return $(automationPizzaPMC);
    }

    public Element automationPizzaPMCSelected() throws IOException, InterruptedException {
        return $(automationPizzaPMCSelected);
    }
}
