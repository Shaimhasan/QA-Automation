package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class MakeLinePage extends BasePO {

    private By order = By.xpath("(//div[@name='mldisplayitem'])[1]");
    private By order1 = By.xpath("(//div[@class='ml-item-bottom'])[1]");

    public List<Element> order() throws IOException, InterruptedException {
        return $$(order);
    }

    public Element order1() throws IOException, InterruptedException {
        return $(order1);
    }

}
