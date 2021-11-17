package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PricesPage extends BasePO {

    private By editBtn = By.id("btn_BO_MN_PRC_Edit");
    private By table = By.id("tbl_BO_List_MN_PRC");
    private By priceTxt = By.xpath("//div[text()='Prices']");
    private By regularTextClick = By.xpath("//td[text()='Regular']");

    public Element regularTextClick() throws IOException, InterruptedException {
        return $(regularTextClick);
    }

    public Element priceTxt() throws IOException, InterruptedException {
        return $(priceTxt);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element editBtn() throws IOException, InterruptedException {
        return $(editBtn);
    }
}
