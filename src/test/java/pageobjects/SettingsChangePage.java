package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class SettingsChangePage extends BasePO {

    private By table = By.id("tbl_BO_List_ST_SET");
    private By settingTxt = By.xpath("//div[@id='div_BO_MAIN_Container_Lists']//div[@id='div_Page_Title']");
    private By makeLineConfig = By.xpath("//td[text()='Make Line Configuration']");
    private By prepStationConfig = By.xpath("//td[text()='Prep station configuration']");
    private By cutAndWrapConfig = By.xpath("//td[text()='Cut and Wrap Configuration']");

    public Element cutAndWrapConfig() throws IOException, InterruptedException {
        return $(cutAndWrapConfig);
    }

    public Element prepStationConfig() throws IOException, InterruptedException {
        return $(prepStationConfig);
    }

    public Element makeLineConfig() throws IOException, InterruptedException {
        return $(makeLineConfig);
    }

    public Element settingTxt() throws IOException, InterruptedException {
        return $(settingTxt);
    }

    public Element table() throws IOException, InterruptedException {
        return $(table);
    }

}
