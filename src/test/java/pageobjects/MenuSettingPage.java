package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class MenuSettingPage extends BasePO {

    private By menuItems = By.xpath("//li[text()='Menu Items']");
    private By prices = By.xpath("//li[text()='Prices']");
    private By posCategories = By.xpath("//li[text()='POS Categories']");
    private By webCategories = By.xpath("//li[text()='Web Categories']");
    private By subCategories = By.xpath("//li[text()='Sub Categories']");

    public Element subCategories() throws IOException, InterruptedException {
        return $(subCategories);
    }

    public Element webCategories() throws IOException, InterruptedException {
        return $(webCategories);
    }

    public Element posCategories() throws IOException, InterruptedException {
        return $(posCategories);
    }

    public Element menuItems() throws IOException, InterruptedException {
        return $(menuItems);
    }

    public Element prices() throws IOException, InterruptedException {
        return $(prices);
    }

}
