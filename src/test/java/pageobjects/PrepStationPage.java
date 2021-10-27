package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PrepStationPage extends BasePO {

    private By order = By.xpath("(//div[@id='div_PS_Item_Column_0']/div)[1]");

    public Element order() throws IOException, InterruptedException {
        return $(order);
    }

}
