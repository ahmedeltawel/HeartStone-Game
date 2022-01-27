package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*from   ww w .  j a  v a  2s . c  o  m*/
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;



class MyCanvas extends JComponent {
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    String s = "java2s.com";
    
  

    //s.setFont(customFont);  
    Font font = new Font("fonts//BelweBdBTBold.ttf", Font.BOLD, 40);
    g2d.translate(20, 200);

    FontRenderContext frc = g2d.getFontRenderContext();

    GlyphVector gv = font.createGlyphVector(frc, s);
    int length = gv.getNumGlyphs();
    System.out.println(length);
    g2d.draw(gv.getOutline());
  }
}

public class font {
	JTextArea test;
	
	
  public static void main(String[] a) {
	  String fName = "fonts//BelweBdBTBold.ttf";
	    InputStream is = font.class.getResourceAsStream(fName);
	    try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  

	  
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setBounds(30, 30, 450, 450);
    window.getContentPane().add(new MyCanvas());
    window.setVisible(true);
  }
}

