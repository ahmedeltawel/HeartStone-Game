package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreditsView extends JFrame{
	private JLabel Anas;
	private JLabel Amera;
	private JLabel Ahmed;
	private JLabel Slim;
	private JLabel image;
	private JPanel im;
	private JButton back;
	
	
	public CreditsView() {
		this.setBounds(0, 0, 1300, 845);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		im = new JPanel();
		JLabel image = new JLabel(new ImageIcon("images//Main.jpg"));
		im.setLayout(new GridLayout(0,1));
		im.add(image);
		Anas = new JLabel();
		Amera = new JLabel();
		Ahmed = new JLabel();
		Slim = new JLabel();
		back = new JButton();
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
		back.setText("Back");
		back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.setFont(gamefont);
        back.setIcon(imageIcon);
        back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setBounds(0,0,120,120);
		this.add(back);
//Anas
		 Anas.setBounds(240, 275, 200, 300);
		 	BufferedImage imgan = null;
		   	try {
		   	    imgan = ImageIO.read(new File("images//Anasgame.png"));
		   	} catch (IOException e) {
		   	    e.printStackTrace();
		   	}
		   	
		   	Image Imgan = imgan.getScaledInstance( (int) 200, (int) 300,
		   	        Image.SCALE_SMOOTH);
		   	ImageIcon imageIconan= new ImageIcon(Imgan);
		   	Anas.setIcon(imageIconan);
		   	this.add(Anas);
//Amera
		   	Amera.setBounds(840, 275, 200, 300);
		 	BufferedImage imgam = null;
		   	try {
		   	    imgam = ImageIO.read(new File("images//Ameragame.png"));
		   	} catch (IOException e) {
		   	    e.printStackTrace();
		   	}
		   	
		   	Image Imgam = imgam.getScaledInstance( (int) 200, (int) 300,
		   	        Image.SCALE_SMOOTH);
		   	ImageIcon imageIconam = new ImageIcon(Imgam);
		   	Amera.setIcon(imageIconam);
		   	this.add(Amera);
//Ahmed
		   	Ahmed.setBounds(550, 480, 200, 300);
		 	BufferedImage imgah = null;
		   	try {
		   	    imgah = ImageIO.read(new File("images//Ahmedgame.png"));
		   	} catch (IOException e) {
		   	    e.printStackTrace();
		   	}
		   	
		   	Image Imgah = imgah.getScaledInstance( (int) 200, (int) 300,
		   	        Image.SCALE_SMOOTH);
		   	ImageIcon imageIconah = new ImageIcon(Imgah);
		   	Ahmed.setIcon(imageIconah);
		   	this.add(Ahmed);
//Slim
		   	Slim.setBounds(525, 0, 250, 350);
		 	BufferedImage imgsm = null;
		   	try {
		   	    imgsm = ImageIO.read(new File("images//Slimgame.png"));
		   	} catch (IOException e) {
		   	    e.printStackTrace();
		   	}
		   	
		   	Image Imgsm = imgsm.getScaledInstance( (int) 250, (int) 350,
		   	        Image.SCALE_SMOOTH);
		   	ImageIcon imageIconsm = new ImageIcon(Imgsm);
		   	Slim.setIcon(imageIconsm);
		   	this.add(Slim);
		
		
		
		
		
		
		
		
		
		this.setBounds(0, 0, 1300, 845);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(im);
		//this.setVisible(true);
		this.revalidate();
		this.repaint();
		
		
		
		
		
		
		
	}
public static void main(String[] args) {
	new CreditsView();
}
public JButton getBack() {
	return back;
}
}
