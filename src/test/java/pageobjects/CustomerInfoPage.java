package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CustomerInfoPage extends BasePO {

    private By close = By.id("btnCustomerInfoClose");

    public Element close() throws IOException, InterruptedException {
        return $(close);
    }
}
