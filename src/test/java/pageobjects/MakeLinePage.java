package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class MakeLinePage extends BasePO {

    private By order = By.xpath("//div[@class='ml-selected ml-item-expanding']");

    public Element order() throws IOException, InterruptedException {
        return $(order);
    }

}
