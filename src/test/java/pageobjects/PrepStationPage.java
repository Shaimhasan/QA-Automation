package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PrepStationPage extends BasePO {

    private By tableOrderList = By.id("tblOrdersListOrders");

    public Element tableOrderList() throws IOException, InterruptedException {
        return $(tableOrderList);
    }

}
