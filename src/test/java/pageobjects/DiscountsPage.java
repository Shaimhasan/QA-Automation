package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class DiscountsPage extends BasePO {

    private By discount = By.xpath("//div[@id='divVerticalMenu2']");
    private By coupons = By.xpath("//li[text()='Coupons']");
    private By advertisement = By.xpath("//li[text()='Advertisements']");
    private By singleUseCoupons = By.xpath("//li[text()='Single Use Coupons']");

    public Element singleUseCoupons() throws IOException, InterruptedException {
        return $(singleUseCoupons);
    }

    public Element advertisement() throws IOException, InterruptedException {
        return $(advertisement);
    }

    public Element discount() throws IOException, InterruptedException {
        return $(discount);
    }

    public Element coupons() throws IOException, InterruptedException {
        return $(coupons);
    }

}
