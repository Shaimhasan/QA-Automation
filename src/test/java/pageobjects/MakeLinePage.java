package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PrepStationPage extends BasePO {

    private By order = By.xpath("//div[@class='ps-item-main ps-item-expanding gridCol1']");

    public Element order() throws IOException, InterruptedException {
        return $(order);
    }

}
