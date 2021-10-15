package core;

import common.Property;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Base class providing set of common selenium methods
 */

public class Screenshot {
    private static final Logger LOG = LogManager.getLogger(Screenshot.class);

    private Screenshot() { //Utility class
    }

    /**
     * capture displayed area or scrolling screenshot and return a file object.
     * to capture scrolling screenshot property scrollingScreenshot = true has to be set in runtime.properties file
     */
    public static File grabScreenshot(WebDriver driver) {

        String screenshotType = null;

        screenshotType = Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getString("scrollingScreenshot");
        if (driver != null) {
            if (screenshotType != null) {
                return (screenshotType.equalsIgnoreCase("true") ? grabScrollingScreenshot(driver) : grabDisplayedAreaScreenShot(driver));
            } else {
                return grabDisplayedAreaScreenShot(driver);
            }
        } else {
            LOG.warn("Driver is not launched. Skipping screenshot.");
            return null;
        }
    }

    /**
     * capture screenshot for the displayed area and return a file object
     */
    public static File grabDisplayedAreaScreenShot(WebDriver driver) {
        try {
            Thread.sleep(Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getInt("screenshotDelay", 0));
        } catch (InterruptedException | NumberFormatException e) {
            LOG.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * capture scrolling screenshot and return a file object
     */
    public static File grabScrollingScreenshot(WebDriver driver) {
        try {
            Thread.sleep(Property.getProperties(Constants.SELENIUMRUNTIMEPATH).getInt("screenshotDelay", 0));
        } catch (InterruptedException | NumberFormatException e) {
            LOG.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        ru.yandex.qatools.ashot.Screenshot screenshot;

        if (System.getProperties().get("os.name").toString().contains("Mac")) {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)).takeScreenshot(driver);
        } else {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        }

        File file = new File("image.png");

        try {
            ImageIO.write(screenshot.getImage(), "PNG", file);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return file;
    }

    /**
     * grab screenshot snippet
     */
    public static File snipScreenshot(File screenshot, By by, Dimension dim, Point point) {

        try {
            BufferedImage buffer = ImageIO.read(screenshot);
            // Crop the entire page screenshot to get only element screenshot
            BufferedImage snippet = buffer.getSubimage(0, point.getY(), point.getX() + dim.width, dim.height);
            ImageIO.write(snippet, "png", screenshot);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return screenshot;
    }

    /**
     * capture screenshot and save to specified location
     */
    public static File saveScreenshot(File screenshot, String filePath) {
        UUID uuid = UUID.randomUUID();
        File file = new File(filePath + uuid + ".png");
        try {
            FileUtils.moveFile(screenshot, file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return file;
    }

    /**
     * capture screenshot and save to specified location
     */
    public static File saveScreenshot(File screenshot, String filePath, String fileName) {
        File file = new File(filePath + fileName + ".png");
        try {
            FileUtils.moveFile(screenshot, file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return file;
    }

    public static Boolean compareScreenshot(File fileExpected, File fileActual) throws IOException {

        BufferedImage bufileActual = ImageIO.read(fileActual);
        BufferedImage bufileExpected = ImageIO.read(fileExpected);

        DataBuffer dafileActual = bufileActual.getData().getDataBuffer();
        DataBuffer dafileExpected = bufileExpected.getData().getDataBuffer();

        int sizefileActual = dafileActual.getSize();

        Boolean matchFlag = true;

        for (int j = 0; j < sizefileActual; j++) {
            if (dafileActual.getElem(j) != dafileExpected.getElem(j)) {
                matchFlag = false;
                break;
            }
        }

        return matchFlag;
    }



}
