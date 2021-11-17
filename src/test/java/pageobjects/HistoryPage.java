package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class HistoryPage extends BasePO {

    private By table = By.id("tbl_BO_HIDDEN");
    private By close = By.xpath("//button[@onclick='BO_HISTORY_DETAILS_Close();']");
    private By closeHistoryBtn = By.xpath("//button[@onclick='BO_HISTORY_Close();']");
    private By tableDetails = By.id("tbl_BO_HIDDEN_DETAILS");
    private By newCloneIdNumber = By.xpath("(//td[text()='Cloned']//following-sibling::td)[3]");
    private By detailsText = By.xpath("//span[text()='Details']");
    private By detailsBtn = By.xpath("(//button[text()='Details'])[1]");

    public Element detailsBtn() throws IOException, InterruptedException {
        return $(detailsBtn);
    }

    public Element detailsText() throws IOException, InterruptedException {
        return $(detailsText);
    }

    public Element closeHistoryBtn() throws IOException, InterruptedException {
        return $(closeHistoryBtn);
    }

    public Element newCloneIdNumber() throws IOException, InterruptedException {
        return $(newCloneIdNumber);
    }

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
