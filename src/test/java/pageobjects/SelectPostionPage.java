package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SelectPostionPage extends BasePO {

    private By driver = By.id("//button[text()='Driver']");


    public Element driver() throws IOException, InterruptedException {
        return $(driver);
    }
}
