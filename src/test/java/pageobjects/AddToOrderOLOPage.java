package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddToOrderOLOPage extends BasePO {

    private By addToOrder = By.id("btn_customize_item_add_to_order");
    private By size = By.xpath("//select[@id='ddl_customize_item_item1_size']");

    public Element size() throws IOException, InterruptedException {
        return $(size);
    }

    public Element addToOrder() throws IOException, InterruptedException {
        return $(addToOrder);
    }

}