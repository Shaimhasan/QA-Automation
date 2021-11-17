package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PricesRegularPage extends BasePO {

    private By selectSmallSize = By.xpath("//select[@id='ddl_BO_MN_PRC_EDT_947_235_Options']");
    private By selectMediumSize = By.xpath("//select[@id='ddl_BO_MN_PRC_EDT_947_236_Options']");
    private By selectLargeSize = By.xpath("//select[@id='ddl_BO_MN_PRC_EDT_947_237_Options']");
    private By saveBtn = By.xpath("//button[@onclick='BO_MN_PRC_EDT_Save();']");
    private By cancelBtn = By.xpath("//button[@onclick='BO_MN_PRC_EDT_Close();']");
    private By table = By.id("tbl_BO_MN_PRC_EDT_252_947");
    private By priceForSmall = By.id("txt_BO_MN_PRC_EDT_947_235_0_Price");
    private By priceForMedium = By.id("txt_BO_MN_PRC_EDT_947_236_0_Price");
    private By priceForLarge = By.id("txt_BO_MN_PRC_EDT_947_237_0_Price");

    public Element priceForSmall() throws IOException, InterruptedException {
        return $(priceForSmall);
    }

    public Element priceForMedium() throws IOException, InterruptedException {
        return $(priceForMedium);
    }

    public Element priceForLarge() throws IOException, InterruptedException {
        return $(priceForLarge);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

    public Element cancelBtn() throws IOException, InterruptedException {
        return $(cancelBtn);
    }

    public Element saveBtn() throws IOException, InterruptedException {
        return $(saveBtn);
    }

    public Element selectSmallSize() throws IOException, InterruptedException {
        return $(selectSmallSize);
    }

    public Element selectMediumSize() throws IOException, InterruptedException {
        return $(selectMediumSize);
    }

    public Element selectLargeSize() throws IOException, InterruptedException {
        return $(selectLargeSize);
    }
}
