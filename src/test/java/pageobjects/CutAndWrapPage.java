package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CutAndWrapPage extends BasePO {

    private By table = By.id("tblItemsList");
    private By inOven = By.xpath("(//td[text()=' In Oven'])[1]");
    private By cutAndWrap = By.xpath("//td[text()='Cut & Wrapped']");
    private By customXpath = By.xpath("//td[contains(@id,'tdItem732')]");

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element customXpath() throws IOException, InterruptedException {
        return $(customXpath);
    }

    public Element inOven() throws IOException, InterruptedException {
        return $(inOven);
    }

    public Element cutAndWrap() throws IOException, InterruptedException {
        return $(cutAndWrap);
    }

}
