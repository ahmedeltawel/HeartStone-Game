package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RulesView extends JFrame{

	private JLabel Rules;
	private JLabel image;
	private JPanel im;
	private JButton backr;
	
	
	public RulesView() {
	  //this.setVisible(true);
		this.setBounds(0, 0, 1300, 845);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		im = new JPanel();
		JLabel image = new JLabel(new ImageIcon("images//Rulesbackground.jpg"));
		im.setLayout(new GridLayout(0,1));
		im.add(image);
		Rules = new JLabel();
		backr = new JButton();
		Font gamefont = null;

		try {
		gamefont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(30f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
		}
		catch(IOException | FontFormatException e) {

		}
//back button
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images//Button.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance( (int) 120, (int) 120,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		backr.setText("Back");
		backr.setHorizontalTextPosition(SwingConstants.CENTER);
		backr.setActionCommand("Backr");
        backr.setFont(gamefont);
        backr.setIcon(imageIcon);
        backr.setOpaque(false);
		backr.setContentAreaFilled(false);
		backr.setBorderPainted(false);
		backr.setBounds(10,-30,120,120);
		this.add(backr);
		this.setBounds(0, 0, 1300, 845);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(im);
		this.revalidate();
		this.repaint();
		
		
		
		
		
		
		
	}
public static void main(String[] args) {
	new RulesView();
}
public JButton getBackr() {
	return backr;
}
}
