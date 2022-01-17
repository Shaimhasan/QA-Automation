package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SettingsPage extends BasePO {

    private By settings = By.xpath("//div[@id='divVerticalMenu7']");
    private By settingsChange = By.xpath("//li[text()='Settings']");

    public Element settings() throws IOException, InterruptedException {
        return $(settings);
    }

    public Element settingsChange() throws IOException, InterruptedException {
        return $(settingsChange);
    }

}
