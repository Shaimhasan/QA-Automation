package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderListPage extends BasePO {

    private By tableOrderList = By.id("tblOrdersListOrders");
    private By orderNum = By.id("txtSearch");
    private By creditCard = By.xpath("(//tr[@style='display: table-row;']//following-sibling::td)[11]//div[text()='Credit Card']");
    private By cash = By.xpath("(//tr[@style='display: table-row;']//following-sibling::td)[11]//div[text()='Cash']");
    private By table = By.xpath("//table[@id='tblOrdersListOrders']");
    private By orderListVisible = By.xpath("//span[text()='Order List']");

    public Element orderListVisible() throws IOException, InterruptedException {
        return $(orderListVisible);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element tableOrderList() throws IOException, InterruptedException {
        return $(tableOrderList);
    }

    public Element cash() throws IOException, InterruptedException {
        return $(cash);
    }

    public Element creditCard() throws IOException, InterruptedException {
        return $(creditCard);
    }

    public Element orderNum() throws IOException, InterruptedException {
        return $(orderNum);
    }

}
