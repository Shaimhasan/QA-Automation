package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class HistoryPage extends BasePO {

    private By table = By.id("tbl_BO_HIDDEN");
    private By close = By.xpath("//button[@onclick='BO_HISTORY_DETAILS_Close();']");
    private By tableDetails = By.id("tbl_BO_HIDDEN_DETAILS");

    public Element tableDetails() throws IOException, InterruptedException {
        return $(tableDetails);
    }

    public Element close() throws IOException, InterruptedException {
        return $(close);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }


}
