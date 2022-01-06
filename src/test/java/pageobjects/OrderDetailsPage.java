package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class OrderDetailsPage extends BasePO {

    private By orderDetailTxt = By.xpath("//span[text()='Order Detail']");
    private By amount = By.xpath("//div[contains(text(),'Total ')]//following-sibling::div");
    private By transactionNum = By.xpath("//div[@id='divTabOrderDetailInfo']//td[text()='Transaction No.']//following-sibling::td//div");
    private By orderNum = By.xpath("//div[@id='divTabOrderDetailInfo']//td[contains(text(),'Order no')]//following-sibling::td");
    private By close = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='Close']");

    public Element close() throws IOException, InterruptedException {
        return $(close);
    }

    public Element orderDetailTxt() throws IOException, InterruptedException {
        return $(orderDetailTxt);
    }

    public Element orderNum() throws IOException, InterruptedException {
        return $(orderNum);
    }

    public Element transactionNum() throws IOException, InterruptedException {
        return $(transactionNum);
    }

    public Element amount() throws IOException, InterruptedException {
        return $(amount);
    }

}
