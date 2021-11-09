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
    private By clockIn = By.xpath("//div[text()='Clock In']");
    private By dispatch = By.xpath("//div[text()='Dispatch']");
    private By clockOut = By.xpath("//div[text()='Clock Out']");

    public Element orderEntry() throws IOException, InterruptedException {
        return $(orderEntry);
    }

    public Element clockOut() throws IOException, InterruptedException {
        return $(clockOut);
    }

    public Element dispatch() throws IOException, InterruptedException {
        return $(dispatch);
    }

    public Element clockIn() throws IOException, InterruptedException {
        return $(clockIn);
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
