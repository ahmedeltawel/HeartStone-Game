package view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class EndView extends JFrame implements ActionListener{
	private JLabel image;
	private JPanel im;
	
	private JLabel Victoryplayer;
	private JLabel Victoryhero;
	private JLabel Victory;
	private JLabel Defeatplayer;	
	private JLabel Defeathero;
	private JLabel Defeat;
	
	
	private JButton again;
	private JButton quit;
	private Container cont ;
	private CardLayout c ;
	private JFrame l ;
	
	
	

	
public EndView( Icon Winner ,String s, Container cont , CardLayout c , JFrame l  ) {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Font gamefont = null;

	try {
		gamefont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(23f);
	} catch (FontFormatException | IOException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	try {
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
	} catch (FontFormatException | IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
//	this.setVisible(true);
	this.l = l ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0,1300, 845);
		//this.setLayout(null);
		
		
//picture for buttons
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images//Button.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance( (int) 180, (int) 175,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
//picture for player labels
	  BufferedImage imgp = null;
		try {
	      imgp = ImageIO.read(new File("images//Button.png"));
		} catch (IOException e) {
		  e.printStackTrace();
		}
				
		Image dimgp = imgp.getScaledInstance( (int) 300, (int) 80,
				   Image.SCALE_SMOOTH);
				
		ImageIcon imageIconp = new ImageIcon(dimg);
		
//Background
		im = new JPanel();
		image = new JLabel();
		image = new JLabel(new ImageIcon("images//Endbackground.jpg"));
	    im.setLayout(new GridLayout(0,1));
		im.add(image);
	
		
//Victory player	
		Victoryplayer= new JLabel();
		Victoryplayer.setBounds(545,120,180,120);
		Victoryplayer.setIcon(imageIconp);
		Victoryplayer.setText(s);
		Victoryplayer.setHorizontalTextPosition(SwingConstants.CENTER);
		Victoryplayer.setFont(gamefont);
		this.add(Victoryplayer);

//Victory animation
		Victory = new JLabel();
		Victory.setBounds(700,230,300,300);
		File ftestv = new File("images//Victory.gif");
	  	URL imgv = null;
	  	try {
	  		imgv = ftestv.toURL();
	  	} catch (MalformedURLException e1) {
	  		e1.printStackTrace();
	  	}
	  	ImageIcon iconv = new ImageIcon(imgv);
	  	iconv.setImage(iconv.getImage().getScaledInstance(350, 350,Image.SCALE_DEFAULT));
	  	Victory.setIcon(iconv);

    this.add(Victory);

//Play Again button
		again = new JButton();
		again.setBounds(545, 520, 180, 175);
		again.setText("Play Again");
		again.setHorizontalTextPosition(SwingConstants.CENTER);
		again.setFont(gamefont);
		again.setIcon(imageIcon);
		again.setOpaque(false);
		again.setContentAreaFilled(false);
		again.setBorderPainted(false);
		this.add(again);
		again.addActionListener(this);

		
//Victory Hero
	Victoryhero=new JLabel();
	Victoryhero.setBounds(275,190,300,400);
	Victoryhero.setIcon(Winner);
	this.add(Victoryhero);

	
	
		this.cont = cont ;
		this.c = c ;
		this.add(im);
		this.revalidate();
		this.repaint();
		

}
public static void main(String[] args) {
//	new EndView();
}
@Override
public void actionPerformed(ActionEvent e) {
	 
//	System.out.print(true);
//	if(e.getActionCommand().equals("Quit")) {
	//	l.dispose();
//	}else
		if(e.getActionCommand().equals("Play Again")) {
			
		
				PlayAgain a;
		//	StartView a ;
				try {
					a = new PlayAgain(l);
				//	a = new StartView();
			//		a.setVisible(false);
					cont.add(a.getContentPane());
					c.next(cont);
				  // l.setContentPane(a.getContentPane());
					l.repaint();
					l.revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
		}
	
}}