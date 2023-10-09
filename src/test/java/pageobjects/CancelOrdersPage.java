package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CancelOrdersPage extends BasePO {

    private By cancelTxt = By.id("txtCancel");
    private By yesCancel = By.xpath("//button[text()='Yes (Cancel)']");

    public Element cancelTxt() throws IOException, InterruptedException {
        return $(cancelTxt);
    }

    public Element yesCancel() throws IOException, InterruptedException {
        return $(yesCancel);
    }
}
