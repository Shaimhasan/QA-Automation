package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderListPage extends BasePO {

    private By tableOrderList = By.id("tblOrdersListOrders");
    private By orderNum = By.id("txtSearch");

    public Element tableOrderList() throws IOException, InterruptedException {
        return $(tableOrderList);
    }

    public Element orderNum() throws IOException, InterruptedException {
        return $(orderNum);
    }

}
