package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class DispatchPage extends BasePO {
    private By table = By.xpath("//div[@class='divHeader']/table");
    private By bobTheDriver = By.xpath("//div[text()='Bob TheDriver']");

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element bobTheDriver() throws IOException, InterruptedException {
        return $(bobTheDriver);
    }
}
