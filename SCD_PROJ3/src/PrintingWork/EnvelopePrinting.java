package PrintingWork;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import PrintingWork.TestPrinting01.Page;

public class EnvelopePrinting {
	 private String Name;
	 private String Address ;
	 
	public EnvelopePrinting(String name, String address) {
		
		Name = name;
		Address = address;
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
	            pBook.append(new Page1(this), pf);
	            pj.setPageable(pBook);

	            try {
	                pj.print();
	            } catch (PrinterException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	  public static class Page1 implements Printable {

		  EnvelopePrinting Test;

	        public Page1(EnvelopePrinting envelopePrinting) {
	            this.Test = envelopePrinting;
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

	           
	            int NameY = fm.getAscent();
	            int AddressY = fm.getAscent() + 200;
	          

	            g2d.drawString("Name: " + Test.Name, 10, NameY);
	            g2d.drawString("Address: " + Test.Address, AddressY, NameY);
	           


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
