package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class VendorsPage extends BasePO {
    private By addBtn = By.id("btn_BO_IN_VND_AddNew");

    public Element addBtn() throws IOException, InterruptedException {
        return $(addBtn);
    }


}
