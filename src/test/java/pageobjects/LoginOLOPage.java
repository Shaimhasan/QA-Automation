package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class LoginOLOPage extends BasePO {

    private By continueAsGuest = By.id("btnLogin_Guest");

    public Element continueAsGuest() throws IOException, InterruptedException {
        return $(continueAsGuest);
    }

}