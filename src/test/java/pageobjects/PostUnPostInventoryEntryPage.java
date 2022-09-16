package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class PostUnPostInventoryEntryPage extends BasePO {

    private By post = By.xpath("//div[@data-title='Warning']//button[text()='Post']");
    private By unPost = By.xpath("//div[@data-title='Warning']//button[text()='Un-Post']");
    private By warningTxt = By.xpath("//div[contains(@id,'div_Message')]//span[text()='Warning']");
    private By validatePost = By.xpath("//i[@class='icon-check']");

    public Element validatePost() throws IOException, InterruptedException {
        return $(validatePost);
    }

    public Element warningTxt() throws IOException, InterruptedException {
        return $(warningTxt);
    }

    public Element post() throws IOException, InterruptedException {
        return $(post);
    }

    public Element unPost() throws IOException, InterruptedException {
        return $(unPost);
    }

}
