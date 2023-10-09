package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PendingOrdersPage extends BasePO {

    private By pendingOrdersTxt = By.xpath("//span[text()='Pending Orders']");
    private By dineInBtn = By.id("btnReviewOrdersOrderType1");
    private By takeOutBtn = By.id("btnReviewOrdersOrderType2");
    private By deliveryBtn = By.id("btnReviewOrdersOrderType3");
    private By pendingOrderTable = By.id("tblReviewOrdersOrders");

    public Element pendingOrderTable() throws IOException, InterruptedException {
        return $(pendingOrderTable);
    }

    public Element deliveryBtn() throws IOException, InterruptedException {
        return $(deliveryBtn);
    }

    public Element takeOutBtn() throws IOException, InterruptedException {
        return $(takeOutBtn);
    }

    public Element dineInBtn() throws IOException, InterruptedException {
        return $(dineInBtn);
    }

    public Element pendingOrdersTxt() throws IOException, InterruptedException {
        return $(pendingOrdersTxt);
    }
}
