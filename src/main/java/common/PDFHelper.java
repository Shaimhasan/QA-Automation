package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PDFHelper {
    private static final String ERROR_EXTRACT_MSG = "error: extract not posible";

    private PDFHelper() {
        //Utility class
    }

    private static Logger getLogger() {
        return LogManager.getLogger(PDFHelper.class);
    }

    public static PDDocument getDoc(String path) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(path));
        } catch (IOException e) {
            getLogger().error(e);
        }
        return doc;
    }

    public static PDDocument getDoc(byte[] contents) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(contents);
        } catch (IOException e) {
            getLogger().error(e);
        }
        return doc;
    }

    public static PDPage getPDFPage(PDDocument doc, int num) {
        return doc.getPage(num);
    }

    public static PDPageTree getPDFPages(PDDocument doc) {
        return doc.getPages();
    }

    public static String getPDFText(PDDocument doc) {
        String extractText = null;
        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            extractText = pdfStripper.getText(doc);
        } catch (IOException e) {
            getLogger().error(e);
            extractText = ERROR_EXTRACT_MSG;
        }

        return extractText;
    }

    public static 	String getPDFText(PDDocument doc, int startPage, int...endPage) {
        String extractText = null;
        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(startPage);
            if (endPage.length>0) {
                pdfStripper.setEndPage(endPage[0]);
            }else {
                pdfStripper.setEndPage(startPage);
            }
            extractText = pdfStripper.getText(doc);
        } catch (IOException e) {
            getLogger().error(e);
            extractText = ERROR_EXTRACT_MSG;
        }

        return extractText;
    }

    public static String[] getPDFText(PDDocument doc, String token, int startPage, int... endPage) {
        String extractText = null;

        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setSortByPosition( true );
            pdfStripper.setStartPage(startPage);
            if (endPage.length > 0) {
                pdfStripper.setEndPage(endPage[0]);
            } else {
                pdfStripper.setEndPage(startPage);
            }

            extractText = pdfStripper.getText(doc);
        } catch (IOException var6) {
            getLogger().error(var6);
            extractText = ERROR_EXTRACT_MSG;
        }

        return extractText.split(token);
    }

    public static String getPDFText(PDDocument doc, int pageNum, Rectangle rectangle) {
        String extractText = null;
        try {
            PDPage page = doc.getPage(pageNum-1);
            PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();
            pdfStripper.setSortByPosition( true );
            pdfStripper.addRegion( "area", rectangle );
            pdfStripper.extractRegions( page );
            extractText = pdfStripper.getTextForRegion("area");
        } catch (IOException e) {
            getLogger().error(e);
            extractText = ERROR_EXTRACT_MSG;
        }

        return extractText;
    }

    public static String scanFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString().trim();
        } finally {
            scanner.close();
        }
    }

    public static void closePDFDoc(PDDocument document) {
        try {
            document.close();
        } catch (IOException ioex) {
            getLogger().error(ioex);
        }
    }

    public static List<File> takeScreenshotOfFullDoc(PDDocument document, String filePath) {
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        File file = null;
        List<File> listOfScreenshots = new ArrayList<>();

        for (int pageNum = 0; pageNum < document.getNumberOfPages(); pageNum++) {
            try {
                file = new File(String.format("%s%s-page%s%s", filePath,
                                              StringHelper.removeSpecialChars(getPDFFileName(document)),
                                              pageNum+1,
                                              ".png"));
                file.getParentFile().mkdirs();
                ImageIO.write(pdfRenderer.renderImage(pageNum), "PNG", file);
                listOfScreenshots.add(file);
            } catch (IOException e) {
                getLogger().error(e);
            }
        }

        return listOfScreenshots;
    }

    public static File takeScreenshotOfPage(PDDocument document, String filePath, int pageNum) {
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        File file = null;
        pageNum = pageNum > 0 ? pageNum : 1;

        try {
            file = new File(String.format("%s%s-page%s%s", filePath,
                                          StringHelper.removeSpecialChars(getPDFFileName(document)),
                                          pageNum,
                                          ".png"));
            file.getParentFile().mkdirs();
            ImageIO.write(pdfRenderer.renderImage(pageNum-1), "PNG", file);
        } catch (IOException | NullPointerException e) {
            getLogger().error(e);
        }

        return file;
    }

    private static String getPDFFileName(PDDocument document) {
        String title = document.getDocumentInformation().getTitle();
        if(title == null || title.isEmpty()) {
            title = "PDF";
        }

        return String.format("%s%s", title, UUID.randomUUID());
    }

}
