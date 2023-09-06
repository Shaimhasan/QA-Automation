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
    private By trackerUncompletedMakeline = By.xpath("//li[@id='tr_status_0' and @class='tracker-uncompleted']");
    private By trackerUncompletedCooking = By.xpath("//li[@id='tr_status_1' and @class='tracker-uncompleted']");
    private By trackerUncompletedReady = By.xpath("//li[@id='tr_status_2' and @class='tracker-uncompleted']");
    private By trackerUncompletedBeingDelivered = By.xpath("//li[@id='tr_status_3' and @class='tracker-uncompleted']");
    private By trackerUncompletedOrderArriving = By.xpath("//li[@id='tr_status_4' and @class='tracker-uncompleted']");
    private By trackerUncompletedDelivered = By.xpath("//li[@id='tr_status_5' and @class='tracker-uncompleted']");

    public Element trackerUncompletedBeingDelivered() throws IOException, InterruptedException {
        return $(trackerUncompletedBeingDelivered);
    }

    public Element trackerUncompletedOrderArriving() throws IOException, InterruptedException {
        return $(trackerUncompletedOrderArriving);
    }

    public Element trackerUncompletedDelivered() throws IOException, InterruptedException {
        return $(trackerUncompletedDelivered);
    }

    public Element trackerUncompletedReady() throws IOException, InterruptedException {
        return $(trackerUncompletedReady);
    }

    public Element trackerUncompletedCooking() throws IOException, InterruptedException {
        return $(trackerUncompletedCooking);
    }

    public Element trackerUncompletedMakeline() throws IOException, InterruptedException {
        return $(trackerUncompletedMakeline);
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
