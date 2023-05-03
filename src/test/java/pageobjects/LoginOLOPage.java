package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class LoginOLOPage extends BasePO {

    private By continueAsGuest = By.id("btn_signin_guest");
    private By loginEmail = By.xpath("//input[@id='emlLogin_Email']");
    private By loginPassword = By.xpath("//input[@id='pasLogin_Password']");
    private By loginBtn = By.xpath("//button[@id='btnLogin_Login']");
    private By errorLocationMsg = By.xpath("//p[contains(text(),'You will be prompted to let the store know your location.')]");
    private By OK = By.xpath("//button[text()='Ok']");

    public Element OK() throws IOException, InterruptedException {
        return $(OK);
    }

    public Element errorLocationMsg() throws IOException, InterruptedException {
        return $(errorLocationMsg);
    }

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