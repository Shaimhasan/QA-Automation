package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class OrderTrack extends BasePO {

    private By OK = By.xpath("//button[text()='OK']");
    private By trackYourOrderTxt = By.xpath("//h3[text()='Track Your Order']");
    private By trackerActiveMaking = By.xpath("//li[@id='tr_status_0' and @class='tracker-active']//img[@alt='Notification Image for Making']");
    private By trackerActiveCooking = By.xpath("//li[@id='tr_status_1' and @class='tracker-active']//img[@alt='Notification Image for Cooking']");
    private By trackerActiveReady = By.xpath("//li[@id='tr_status_2' and @class='tracker-active']//img[@alt='Notification Image for Ready']");
    private By trackerUncomplete = By.xpath("//li[@class='tracker-uncompleted']");

    public List<Element> trackerUncomplete() throws IOException, InterruptedException {
        return $$(trackerUncomplete);
    }

    public Element trackerActiveCooking() throws IOException, InterruptedException {
        return $(trackerActiveCooking);
    }

    public Element trackerActiveReady() throws IOException, InterruptedException {
        return $(trackerActiveReady);
    }

    public Element trackerActiveMaking() throws IOException, InterruptedException {
        return $(trackerActiveMaking);
    }

    public Element trackYourOrderTxt() throws IOException, InterruptedException {
        return $(trackYourOrderTxt);
    }

    public Element OK() throws IOException, InterruptedException {
        return $(OK);
    }

}
