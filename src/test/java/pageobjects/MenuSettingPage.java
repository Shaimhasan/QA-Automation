package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class MenuSettingPage extends BasePO {

    private By menuItems = By.xpath("//li[text()='Menu Items']");
    private By prices = By.xpath("//li[text()='Prices']");

    public Element menuItems() throws IOException, InterruptedException {
        return $(menuItems);
    }

    public Element prices() throws IOException, InterruptedException {
        return $(prices);
    }

}
