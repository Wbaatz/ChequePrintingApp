package PrintingWork;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class TestPrinting01 {
    private String payee;
    private String formattedDate;
    private String amount;
    private String wording;

    public TestPrinting01(String payee, String formattedDate, String amount, String wording) {
        this.payee = payee;
        this.formattedDate = formattedDate;
        this.amount = amount;
        this.wording = wording;
    }

    public void printCheque() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();
            double width = 6d * 72d;
            double height = 2d * 72d;
            double margin = 0d * 72d;
            paper.setSize(width, height);
            paper.setImageableArea(
                    margin,
                    margin,
                    width - (margin * 2),
                    height - (margin * 2));

            pf.setOrientation(PageFormat.PORTRAIT);
            pf.setPaper(paper);

            Book pBook = new Book();
            pBook.append(new Page(this), pf);
            pj.setPageable(pBook);

            try {
                pj.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class Page implements Printable {

        TestPrinting01 Test;

        public Page(TestPrinting01 Test) {
            this.Test = Test;
        }

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
            if (pageIndex >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            double width = pageFormat.getImageableWidth();
            double height = pageFormat.getImageableHeight();

            g2d.drawRect(0, 0, (int)width - 1, (int)height - 1);
            Font font = new Font("Arial", Font.BOLD, 11);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();

            int dateX = (int) width - 200;
            int payeeY = fm.getAscent();
            int amountY = fm.getAscent() + 40;
            int wordingY = fm.getAscent() + 60;

            g2d.drawString("Payee: " + Test.payee, 10, payeeY);
            g2d.drawString("Date: " + Test.formattedDate, dateX, payeeY);
            g2d.drawString("Amount: " + Test.amount, 10, amountY);

            int wordingWidth = (int) (width * 0.8);
            int wordingX = (int) width - wordingWidth - 10;
            drawStringWithWordWrap(g2d, "Wording: " + Test.wording, wordingX, wordingY, wordingWidth);

            return Printable.PAGE_EXISTS;
        }

        // Rest of the code...
        private void drawStringWithWordWrap(Graphics2D g2d, String text, int x, int y, int maxWidth) {
            FontMetrics fm = g2d.getFontMetrics();
            String[] words = text.split("\\s+");
            StringBuilder currentLine = new StringBuilder(words[0]);

            for (int i = 1; i < words.length; i++) {
                String testLine = currentLine.toString() + " " + words[i];
                if (fm.stringWidth(testLine) <= maxWidth) {
                    currentLine.append(" ").append(words[i]);
                } else {
                    g2d.drawString(currentLine.toString(), x, y);
                    y += fm.getAscent();
                    currentLine = new StringBuilder(words[i]);
                }
            }
            g2d.drawString(currentLine.toString(), x, y);
        }
    }
}
