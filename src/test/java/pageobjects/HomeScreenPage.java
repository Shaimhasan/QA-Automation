package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomeScreenPage extends BasePO {

    private By orderEntry = By.xpath("//div[text()='Order Entry']");
    private By prepStation = By.xpath("//div[text()='Prep Station']");
    private By makeLine = By.xpath("//div[text()='Make Line']/..");

    public Element orderEntry() throws IOException, InterruptedException {
        return $(orderEntry);
    }
    public Element prepStation() throws IOException, InterruptedException {
        return $(prepStation);
    }
    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }
}
