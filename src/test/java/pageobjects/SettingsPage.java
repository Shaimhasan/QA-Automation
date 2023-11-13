package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SettingsPage extends BasePO {

    private By settings = By.xpath("//div[@id='divVerticalMenu7']");
    private By settingsChange = By.xpath("//li[text()='Settings']");
    private By taxDefinitions = By.xpath("//li[text()='Tax Definitions']");
    private By taxRates = By.xpath("//li[text()='Tax Rates']");
    private By pricingGroups = By.xpath("//li[text()='Pricing Groups']");
    private By paymentTypes = By.xpath("//li[text()='Payment Types']");
    private By orderTypes = By.xpath("//li[text()='Order Types']");

    public Element settings() throws IOException, InterruptedException {
        return $(settings);
    }

    public Element settingsChange() throws IOException, InterruptedException {
        return $(settingsChange);
    }
    public Element taxDefinitions() throws IOException, InterruptedException {
        return $(taxDefinitions);
    }
    public Element taxRates() throws IOException, InterruptedException {
        return $(taxRates);
    }
    public Element pricingGroups() throws IOException, InterruptedException {
        return $(pricingGroups);
    }
    public Element paymentTypes() throws IOException, InterruptedException {
        return $(paymentTypes);
    }
    public Element orderTypes() throws IOException, InterruptedException {
        return $(orderTypes);
    }
}
