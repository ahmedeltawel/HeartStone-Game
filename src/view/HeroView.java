package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeroView extends JFrame {
	private JPanel player1;
	private JPanel player2;
	private JPanel name;
	private JLabel image;
	private JPanel im;
	private JLabel playername1;
	private JLabel playername2;
	private JButton next;
	private JLabel vs;
	private JLabel pic1;
	private JLabel pic2;
	private GameView Game;
	private JButton Mage1;
	private JButton Hunter1;
	private JButton Paladin1;
	private JButton Warlock1;
	private JButton Priest1;
	private JButton Mage2;
	private JButton Hunter2;
	private JButton Paladin2;
	private JButton Warlock2;
	private JButton Priest2;
	
	public HeroView(){
		Font backgroundfont = null;

		try {
		backgroundfont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(25f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
		}
		catch(IOException | FontFormatException e) {

		}	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//	this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		image = new JLabel();
		im = new JPanel();
		JLabel image = new JLabel(new ImageIcon("images//HeroBackground.jpg"));
		im.setLayout(new GridLayout(0,1));
		im.add(image);
		player1 = new JPanel();
		player2 = new JPanel();
		playername1 = new JLabel();
		playername2 = new JLabel();
		pic1 = new JLabel();
		pic2 = new JLabel();
		vs = new JLabel();
		next = new JButton();
	//	Game = new GameView();
		playername1.setBounds(300,230,180,120);
		playername2.setBounds(800,230,180,120);
		vs.setBounds(550,350,180,120);
		next.setBounds(540,600,180,120);
		pic1.setBounds(275,285,300,400);
		pic2.setBounds(775,285,300,400);
		this.add(pic1);
		this.add(pic2);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images//Button.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance( (int) 180, (int) 120,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
	//	///////
		
		BufferedImage imgvs = null;
		try {
		    imgvs = ImageIO.read(new File("images//VS.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimgvs = imgvs.getScaledInstance( (int) 180, (int) 200,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIconvs = new ImageIcon(dimgvs);
		
		////////////
		
		BufferedImage imgnext = null;
		try {
		    imgnext = ImageIO.read(new File("images//Arrow.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimgnext = imgnext.getScaledInstance( (int) 130, (int) 120,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIconnext = new ImageIcon(dimgnext);
		
		//////////
		
		
		
		
		vs.setIcon(imageIconvs);
		this.add(vs);
		next.setIcon(imageIconnext);
		next.setOpaque(false);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		this.add(next);
		
		playername1.setIcon(imageIcon);
		playername2.setIcon(imageIcon);
		playername1.setText("Player 1");
		playername1.setHorizontalTextPosition(SwingConstants.CENTER);
        playername1.setFont(backgroundfont);
        playername2.setText("Player 2");
		playername2.setHorizontalTextPosition(SwingConstants.CENTER);
        playername2.setFont(backgroundfont);
		this.add(playername1);
		this.add(playername2);
		
		name = new JPanel();
		int heightplayer = (int) (0.45*this.getHeight());
		int heightname = (int) (0.1*this.getHeight());
		player1.setPreferredSize(new Dimension(this.getWidth(),heightplayer));
		player2.setPreferredSize(new Dimension(this.getWidth(),heightplayer));
		name.setPreferredSize(new Dimension(this.getWidth(),heightname));
		player1.setOpaque(false);
		player2.setOpaque(false);
		//player1.setLayout(new GridLayout(5,1));
		player1.setBounds(0,0,170,790);
		player2.setBounds(1110,0,170,790);
		
		 Mage1 = new JButton();
		 Hunter1 = new JButton();
		 Paladin1 = new JButton();
		 Warlock1 = new JButton();
		 Priest1 = new JButton();
		
		 Mage2 = new JButton();
		 Hunter2 = new JButton();
		 Paladin2 = new JButton();
		 Warlock2 = new JButton();
		 Priest2 = new JButton();
		
		this.add(player1);
		this.add(player2);
		player1.setLayout(new GridLayout(5,1));
		player2.setLayout(new GridLayout(5,1));
	
/////////////		

		  Image image1 = Toolkit.getDefaultToolkit().createImage("images/Mage.gif");
		
		
		Mage1.setIcon(new ImageIcon(image1));
		Mage2.setIcon(new ImageIcon(image1));
		Mage1.setOpaque(false);
		Mage1.setContentAreaFilled(false);
		Mage1.setBorderPainted(false);
		Mage2.setOpaque(false);
		Mage2.setContentAreaFilled(false);
		Mage2.setBorderPainted(false);
		Mage1.setBackground(Color.blue);
		player1.add(Mage1);
		player2.add(Mage2);
		
	///////////	
		
		
		Image image2 = Toolkit.getDefaultToolkit().createImage("images/Hunter.gif");
		
		
		Hunter1.setIcon(new ImageIcon(image2));
		Hunter2.setIcon(new ImageIcon(image2));
		Hunter1.setOpaque(false);
		Hunter1.setContentAreaFilled(false);
		Hunter1.setBorderPainted(false);
		Hunter2.setOpaque(false);
		Hunter2.setContentAreaFilled(false);
		Hunter2.setBorderPainted(false);
		
		player1.add(Hunter1);
		player2.add(Hunter2);
		
		
///////////	
		
		
		Image image3 = Toolkit.getDefaultToolkit().createImage("images/Paladin.gif");

		
		
		Paladin1.setIcon(new ImageIcon(image3));
		Paladin2.setIcon(new ImageIcon(image3));
		Paladin1.setOpaque(false);
		Paladin1.setContentAreaFilled(false);
		Paladin1.setBorderPainted(false);
		Paladin2.setOpaque(false);
		Paladin2.setContentAreaFilled(false);
		Paladin2.setBorderPainted(false);
		
		player1.add(Paladin1);
		player2.add(Paladin2);
		
///////////	
		
	
		Image image4 = Toolkit.getDefaultToolkit().createImage("images/Priest.gif");
       
		
		Priest1.setIcon(new ImageIcon(image4));
		Priest2.setIcon(new ImageIcon(image4));
		Priest1.setOpaque(false);
		Priest1.setContentAreaFilled(false);
		Priest1.setBorderPainted(false);
		Priest2.setOpaque(false);
		Priest2.setContentAreaFilled(false);
		Priest2.setBorderPainted(false);
		
		
		player1.add(Priest1);
		player2.add(Priest2);
//		
		///////////	
		
		
	
		Image image5 = Toolkit.getDefaultToolkit().createImage("images/Warlock.gif");

		
		Warlock1.setIcon(new ImageIcon(image5));
		Warlock2.setIcon(new ImageIcon(image5));
		Warlock1.setOpaque(false);
		Warlock1.setContentAreaFilled(false);
		Warlock1.setBorderPainted(false);
		Warlock2.setOpaque(false);
		Warlock2.setContentAreaFilled(false);
		Warlock2.setBorderPainted(false);
		
		player1.add(Warlock1);
		player2.add(Warlock2);

		this.add(im);
		
		
		
		
		this.revalidate();
		this.repaint();
		
	}
	

	
	public JButton getNext() {
		return next;
	}


	public JLabel getPic1() {
		return pic1;
	}



	public JLabel getPic2() {
		return pic2;
	}



	public void setPic1(JLabel pic1) {
		this.pic1 = pic1;
	}


	public void setPic2(JLabel pic2) {
		this.pic2 = pic2;
	}


	public JButton getMage1() {
		return Mage1;
	}



	public JButton getHunter1() {
		return Hunter1;
	}



	public JButton getPaladin1() {
		return Paladin1;
	}



	public JButton getWarlock1() {
		return Warlock1;
	}



	public JButton getPriest1() {
		return Priest1;
	}



	public JButton getMage2() {
		return Mage2;
	}



	public JButton getHunter2() {
		return Hunter2;
	}



	public JButton getPaladin2() {
		return Paladin2;
	}



	public JButton getWarlock2() {
		return Warlock2;
	}


	public JButton getPriest2() {
		return Priest2;
	}



	public static void main(String[] args) {
		new HeroView();
	}
	
	

	
	

	
}
