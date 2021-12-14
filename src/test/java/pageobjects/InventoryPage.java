package pageobjects;

import core.BasePO;
import core.Element;
import org.openqa.selenium.By;

import java.io.IOException;

public class InventoryPage extends BasePO {

    private By inventory = By.xpath("//div[@id='divVerticalMenu3']");
    private By receiving = By.xpath("//li[text()='Receiving']");
    private By mixturesPrep = By.xpath("//li[text()='Mixtures Prep']");
    private By countSheet = By.xpath("//li[text()='Count Sheet']");
    private By invetoryEntry = By.xpath("//li[text()='Inventory Entry']");
    private By receivingItems = By.xpath("//li[text()='Receiving Items']");
    private By inventoryItems = By.xpath("//li[text()='Inventory Items']");
    private By mixtureRecipe = By.xpath("//li[text()='Mixture Recipe']");
    private By vendors = By.xpath("//li[text()='Vendors']");
    private By vendorsDeliveryDays = By.xpath("//li[text()='Vendor Delivery Days']");

    public Element vendorsDeliveryDays() throws IOException, InterruptedException {
        return $(vendorsDeliveryDays);
    }

    public Element vendors() throws IOException, InterruptedException {
        return $(vendors);
    }

    public Element mixtureRecipe() throws IOException, InterruptedException {
        return $(mixtureRecipe);
    }

    public Element inventoryItems() throws IOException, InterruptedException {
        return $(inventoryItems);
    }

    public Element receivingItems() throws IOException, InterruptedException {
        return $(receivingItems);
    }

    public Element invetoryEntry() throws IOException, InterruptedException {
        return $(invetoryEntry);
    }

    public Element countSheet() throws IOException, InterruptedException {
        return $(countSheet);
    }

    public Element mixturesPrep() throws IOException, InterruptedException {
        return $(mixturesPrep);
    }

    public Element inventory() throws IOException, InterruptedException {
        return $(inventory);
    }

    public Element receiving() throws IOException, InterruptedException {
        return $(receiving);
    }

}
