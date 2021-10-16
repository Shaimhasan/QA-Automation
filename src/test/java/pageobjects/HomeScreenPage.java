package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomeScreenPage extends BasePO {

    private By orderEntry = By.xpath("//div[text()='Order Entry']");

    public Element orderEntry() throws IOException, InterruptedException {
        return $(orderEntry);
    }
}
