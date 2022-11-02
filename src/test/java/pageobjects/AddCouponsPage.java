package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddCouponsPage extends BasePO {

    private By nameUS = By.xpath("//div[text()='English - United States: ']//following-sibling::input[@name='CouponName']");
    private By descriptionUS = By.xpath("//div[text()='English - United States:']//following-sibling::input[@name='CouponDescription']");
    private By nameSpanish = By.xpath("//div[text()='Spanish - Mexico: ']//following-sibling::input[@name='CouponName']");
    private By descriptionSpanish = By.xpath("//div[text()='Spanish - Mexico:']//following-sibling::input[@name='CouponDescription']");
    private By couponNumber = By.id("txtCouponNo");
    private By backGroundColor = By.id("txtCouponBackgroundColor");
    private By discount = By.id("txtCouponDiscount");
    private By couponsTxtPopup = By.xpath("//span[@id='div_BO_CP_CPN_ADDEDT_DIALOG_Header_Title']");
    private By save = By.xpath("//div[@class='ui-dialog-buttonset']//button[text()='Save']");

    public Element couponsTxtPopup() throws IOException, InterruptedException {
        return $(couponsTxtPopup);
    }

    public Element discount() throws IOException, InterruptedException {
        return $(discount);
    }

    public Element backGroundColor() throws IOException, InterruptedException {
        return $(backGroundColor);
    }

    public Element couponNumber() throws IOException, InterruptedException {
        return $(couponNumber);
    }

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element nameUS() throws IOException, InterruptedException {
        return $(nameUS);
    }


    public Element descriptionUS() throws IOException, InterruptedException {
        return $(descriptionUS);
    }


    public Element nameSpanish() throws IOException, InterruptedException {
        return $(nameSpanish);
    }


    public Element descriptionSpanish() throws IOException, InterruptedException {
        return $(descriptionSpanish);
    }


}
