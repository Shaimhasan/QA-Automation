package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class LoginPage extends BasePO {

    private By storeKey = By.id("txtStoreKey");
    private By employee_Id = By.id("txtEmployeeID");
    private By password = By.id("txtEmployeePassword");
    private By stationKey = By.id("txtStationKey");
    private By connect = By.xpath("//button[@type='submit']");

    public Element storeKey() throws IOException, InterruptedException {
        return $(storeKey);
    }

    public Element stationKey() throws IOException, InterruptedException {
        return $(stationKey);
    }

    public Element connect() throws IOException, InterruptedException {
        return $(connect);
    }

    public Element employee_Id() throws IOException, InterruptedException {
        return $(employee_Id);
    }

    public Element password() throws IOException, InterruptedException {
        return $(password);
    }
}
