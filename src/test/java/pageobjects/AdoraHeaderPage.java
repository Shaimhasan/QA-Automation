package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AdoraHeaderPage extends BasePO {

    private By orderEntry = By.xpath("//div[text()='Order Entry']");
    private By makeLine = By.xpath("//div[text()='Make Line']/..");
    private By orderList = By.xpath("//div[text()='Order List']");
    private By prepStation = By.xpath("//div[text()='Prep Station']");
    private By cutAndWrap = By.xpath("//div[text()='Cut & Wrap']");

    public Element orderEntry() throws IOException, InterruptedException {
        return $(orderEntry);
    }

    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }

    public Element cutAndWrap() throws IOException, InterruptedException {
        return $(cutAndWrap);
    }

    public Element prepStation() throws IOException, InterruptedException {
        return $(prepStation);
    }

    public Element orderList() throws IOException, InterruptedException {
        return $(orderList);
    }


}
