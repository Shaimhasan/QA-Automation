package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class AddMenuItemsPage extends BasePO {

    private By nameUS = By.id("txt_BO_MN_ITM_ADDEDT_Name_en-us");
    private By webNameUS = By.id("txt_BO_MN_ITM_ADDEDT_WebName_en-us");
    private By descriptionUS = By.id("txt_BO_MN_ITM_ADDEDT_Desc_en-us");
    private By webDescriptionUS = By.id("txt_BO_MN_ITM_ADDEDT_WebDesc_en-us");
    private By nameSpanish = By.id("txt_BO_MN_ITM_ADDEDT_Name_sp-mx");
    private By webNameSpanish = By.id("txt_BO_MN_ITM_ADDEDT_WebName_sp-mx");
    private By descriptionSpanish = By.id("txt_BO_MN_ITM_ADDEDT_Desc_sp-mx");
    private By webDescriptionSpanish = By.id("txt_BO_MN_ITM_ADDEDT_WebDesc_sp-mx");
    private By itemNumber = By.id("txt_BO_MN_ITM_ADDEDT_ItemNumber");
    private By dineInChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_ITM_ADDEDT_DineIn']");
    private By takeOutChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_ITM_ADDEDT_TakeOut']");
    private By deliveryChkBx = By.xpath("//input[normalize-space(@id)='chk_BO_MN_ITM_ADDEDT_Delivery']");
    private By dineInSizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_DineIn_235']");
    private By takeOutSizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_TakeOut_235']");
    private By deliverySizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_Delivery_235']");
    private By dineInMediumSizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_DineIn_236']");
    private By takeOutMediumSizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_TakeOut_236']");
    private By deliveryMediumSizeChkBx = By.xpath("//input[@id='chk_BO_MN_ITM_ADDEDT_Sizes_Delivery_236']");
    private By defualtMedium = By.xpath("//input[@id='rdo_BO_MN_ITM_ADDEDT_Sizes_Default_236']");
    private By defualt = By.xpath("//input[@id='rdo_BO_MN_ITM_ADDEDT_Sizes_Default_235']");
    private By save = By.xpath("//button[@onclick='BO_MN_ITM_ADDEDT_Save();']");

    public Element save() throws IOException, InterruptedException {
        return $(save);
    }

    public Element dineInMediumSizeChkBx() throws IOException, InterruptedException {
        return $(dineInMediumSizeChkBx);
    }

    public Element takeOutMediumSizeChkBx() throws IOException, InterruptedException {
        return $(takeOutMediumSizeChkBx);
    }

    public Element deliveryMediumSizeChkBx() throws IOException, InterruptedException {
        return $(deliveryMediumSizeChkBx);
    }

    public Element defualtMedium() throws IOException, InterruptedException {
        return $(defualtMedium);
    }

    public Element defualt() throws IOException, InterruptedException {
        return $(defualt);
    }

    public Element deliverySizeChkBx() throws IOException, InterruptedException {
        return $(deliverySizeChkBx);
    }

    public Element takeOutSizeChkBx() throws IOException, InterruptedException {
        return $(takeOutSizeChkBx);
    }

    public Element dineInSizeChkBx() throws IOException, InterruptedException {
        return $(dineInSizeChkBx);
    }

    public Element deliveryChkBx() throws IOException, InterruptedException {
        return $(deliveryChkBx);
    }

    public Element takeOutChkBx() throws IOException, InterruptedException {
        return $(takeOutChkBx);
    }

    public Element dineInChkBx() throws IOException, InterruptedException {
        return $(dineInChkBx);
    }

    public Element itemNumber() throws IOException, InterruptedException {
        return $(itemNumber);
    }

    public Element nameUS() throws IOException, InterruptedException {
        return $(nameUS);
    }

    public Element webNameUS() throws IOException, InterruptedException {
        return $(webNameUS);
    }

    public Element descriptionUS() throws IOException, InterruptedException {
        return $(descriptionUS);
    }

    public Element webDescriptionUS() throws IOException, InterruptedException {
        return $(webDescriptionUS);
    }

    public Element nameSpanish() throws IOException, InterruptedException {
        return $(nameSpanish);
    }

    public Element webNameSpanish() throws IOException, InterruptedException {
        return $(webNameSpanish);
    }

    public Element descriptionSpanish() throws IOException, InterruptedException {
        return $(descriptionSpanish);
    }

    public Element webDescriptionSpanish() throws IOException, InterruptedException {
        return $(webDescriptionSpanish);
    }


}
