package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class DriverStatusPage extends BasePO {

    private By removeDriver = By.xpath("//button[text()='Remove Driver']");

    public Element removeDriver() throws IOException, InterruptedException {
        return $(removeDriver);
    }
}
