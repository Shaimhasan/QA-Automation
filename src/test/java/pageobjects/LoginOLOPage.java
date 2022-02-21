package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class LoginOLOPage extends BasePO {

    private By continueAsGuest = By.id("btnLogin_Guest");
    private By loginEmail = By.xpath("//input[@id='emlLogin_Email']");
    private By loginPassword = By.xpath("//input[@id='pasLogin_Password']");
    private By loginBtn = By.xpath("//button[@id='btnLogin_Login']");

    public Element loginBtn() throws IOException, InterruptedException {
        return $(loginBtn);
    }

    public Element loginPassword() throws IOException, InterruptedException {
        return $(loginPassword);
    }

    public Element loginEmail() throws IOException, InterruptedException {
        return $(loginEmail);
    }

    public Element continueAsGuest() throws IOException, InterruptedException {
        return $(continueAsGuest);
    }

}