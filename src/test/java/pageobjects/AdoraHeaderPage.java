package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AdoraHeaderPage extends BasePO {

    private By makeLine = By.xpath("//div[text()='Make Line']");
    private By orderList = By.xpath("//div[text()='Order List']");

    public Element makeLine() throws IOException, InterruptedException {
        return $(makeLine);
    }

    public Element orderList() throws IOException, InterruptedException {
        return $(orderList);
    }


}
