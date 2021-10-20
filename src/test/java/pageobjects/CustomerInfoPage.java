package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CustomerInfoPage extends BasePO {

    private By close = By.id("btnCustomerInfoClose");
    private By textPhone = By.id("txtPhone");
    private By OK = By.xpath("//button[text()='OK']");
    private By address = By.xpath("//div[text()='55 1st St']");

    public Element close() throws IOException, InterruptedException {
        return $(close);
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
