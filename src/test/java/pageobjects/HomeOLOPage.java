package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomeOLOPage extends BasePO {

    private By automationPizzaPMC = By.xpath("//button[@aria-label='Automation Pizza (P-M-C)']");
    private By chickenBaconPizzaNone = By.xpath("//button[@aria-label='Chicken Bacon Ranch (None)']");
    private By hawaiianPizzaP = By.xpath("//button[@aria-label='Hawaiian Pizza (P)']");
    private By cheesePizzaPMC = By.xpath("//button[@aria-label='Cheese Pizza (P-M-C)']");
    private By checkOut = By.id("btn_OB_CheckOut");

    public Element hawaiianPizzaP() throws IOException, InterruptedException {
        return $(hawaiianPizzaP);
    }

    public Element chickenBaconPizzaNone() throws IOException, InterruptedException {
        return $(chickenBaconPizzaNone);
    }

    public Element checkOut() throws IOException, InterruptedException {
        return $(checkOut);
    }

    public Element cheesePizzaPMC() throws IOException, InterruptedException {
        return $(cheesePizzaPMC);
    }

    public Element automationPizzaPMC() throws IOException, InterruptedException {
        return $(automationPizzaPMC);
    }

}