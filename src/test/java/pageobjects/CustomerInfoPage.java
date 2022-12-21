package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CustomerInfoPage extends BasePO {

    private By close = By.id("btnCustomerInfoClose");
    private By textPhone = By.id("txtPhone");
    private By OK = By.xpath("//button[@id='btnCUSTINFOOK__']");
    private By address = By.id("divAddressInfo");
    private By addressText = By.xpath("//div[text()='1328 Blue Oaks Blvd']");
    private By customerName = By.id("txtName");
    private By custInfoId = By.xpath("//span[text()='Customer Info']");

    public Element addressText() throws IOException, InterruptedException {
        return $(addressText);
    }

    public Element close() throws IOException, InterruptedException {
        return $(close);
    }

    public Element custInfoId() throws IOException, InterruptedException {
        return $(custInfoId);
    }

    public Element customerName() throws IOException, InterruptedException {
        return $(customerName);
    }

    public Element address() throws IOException, InterruptedException {
        return $(address);
    }

    public Element OK() throws IOException, InterruptedException {
        return $(OK);
    }

    public Element textPhone() throws IOException, InterruptedException {
        return $(textPhone);
    }
}
