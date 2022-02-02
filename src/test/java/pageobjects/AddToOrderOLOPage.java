package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddToOrderOLOPage extends BasePO {

    private By addToOrder = By.id("btn_CI_Add_to_Order");

    public Element addToOrder() throws IOException, InterruptedException {
        return $(addToOrder);
    }

}