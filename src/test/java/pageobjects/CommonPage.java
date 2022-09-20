package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CommonPage extends BasePO {

    private By printerOkBtn = By.xpath("//p[contains(text(),'No printer has been defined on this station.')]/..//following-sibling::div//button[text()='OK']");

    public Element printerOkBtn() throws IOException, InterruptedException {
        return $(printerOkBtn);
    }


}
