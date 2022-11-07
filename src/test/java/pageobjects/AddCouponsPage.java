package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddCouponsPage extends BasePO {

    private By nameUS = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_Name_en-us']");
    private By descriptionUS = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_Desc_en-us']");
    private By nameSpanish = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_Name_sp-mx']");
    private By descriptionSpanish = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_Desc_sp-mx']");
    private By couponNumber = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_CouponNumber']");
    private By backGroundColor = By.id("txtCouponBackgroundColor");
    private By discountAmt = By.xpath("//input[@id='inp_BO_CP_CPN_ADDEDT_PricePoint_Discount_0']");
    private By couponsTxtPopup = By.xpath("//span[@id='div_BO_CP_CPN_ADDEDT_DIALOG_Header_Title']");
    private By save = By.xpath("//button[@id='btn_BO_CP_CPN_ADDEDT_Save']");
    private By discountType = By.xpath("//select[@id='ddl_BO_CP_CPN_ADDEDT_DiscountType']");
    private By minOrderAmt = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_MinimumOrderAmount']");
    private By maxOrderAmt = By.xpath("//input[@id='txt_BO_CP_CPN_ADDEDT_MaximumDiscountAmount']");
    private By startDate = By.xpath("//input[@id='inp_BO_CP_CPN_ADDEDT_PricePoint_StartDate_0']");
    private By endDate = By.xpath("//input[@id='inp_BO_CP_CPN_ADDEDT_PricePoint_EndDate_0']");
    private By orderTypes = By.xpath("//input[@id='chk_BO_CP_CPN_ADDEDT_OSTs_1_2']");

    public Element orderTypes() throws IOException, InterruptedException {
        return $(orderTypes);
    }

    public Element endDate() throws IOException, InterruptedException {
        return $(endDate);
    }

    public Element startDate() throws IOException, InterruptedException {
        return $(startDate);
    }

    public Element maxOrderAmt() throws IOException, InterruptedException {
        return $(maxOrderAmt);
    }

    public Element minOrderAmt() throws IOException, InterruptedException {
        return $(minOrderAmt);
    }

    public Element discountType() throws IOException, InterruptedException {
        return $(discountType);
    }

    public Element couponsTxtPopup() throws IOException, InterruptedException {
        return $(couponsTxtPopup);
    }

    public Element discountAmt() throws IOException, InterruptedException {
        return $(discountAmt);
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
