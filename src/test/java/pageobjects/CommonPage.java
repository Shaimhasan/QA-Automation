package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class CommonPage extends BasePO {

    private By printerOkBtn = By.xpath("//p[contains(text(),'No printer has been defined on this station.')]/..//following-sibling::div//button[text()='OK']");
    private By OKBtn = By.xpath("//button[@class='btn_dialog_box']");

    public Element printerOkBtn() throws IOException, InterruptedException {
        return $(printerOkBtn);
    }

    public Element OKBtn() throws IOException, InterruptedException {
        return $(OKBtn);
    }


}
