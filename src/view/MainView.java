package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainView extends JFrame{
	private JPanel Button;
	private JLabel image;
	private JPanel im;
	private HeroView Hero;
	private JButton start;
	private JButton Sound;
	private JButton credits;
	private JButton rules;
	private JButton exit;
	
public MainView() throws IOException{
    // this.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		
		
		
		

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		Button = new JPanel();
		image = new JLabel();
		im = new JPanel();
		Hero = new HeroView();
	    start = new JButton();
	    rules=new JButton();
	    credits = new JButton();
	    exit = new JButton();
		JLabel image = new JLabel(new ImageIcon("images//Main.jpg"));
		im.setLayout(new GridLayout(0,1));
		im.add(image);
	    Button.setLayout(new GridLayout(4,1,10,10));
	    Button.setOpaque(false);
	    Button.setBounds(500,350,260, 400);
		this.add(Button);
	    ///Sound mute
			Sound = new JButton();
			Sound.setBounds(1200, 20, 40, 40);	

			BufferedImage imgs = null;
			try {
			    imgs = ImageIO.read(new File("images//Sound.png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			Image dimgs = imgs.getScaledInstance( (int) 40, (int) 40,
			        Image.SCALE_SMOOTH);
			
			ImageIcon imageIcons = new ImageIcon(dimgs);
			Sound.setIcon(imageIcons);
			Sound.setOpaque(false);
			Sound.setBorderPainted(false);
			Sound.setContentAreaFilled(false);
			Sound.setActionCommand("Sound");
			this.add(Sound);
			
		
		Font gamefont = null;

		try {
		gamefont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(35f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
		}
		catch(IOException | FontFormatException e) {

		}

		
		

		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images//Button.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance( (int) 260, (int) 260,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		start.setText("Start Game");
		start.setHorizontalTextPosition(SwingConstants.CENTER);
        start.setFont(gamefont);
        start.setIcon(imageIcon);
        start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		rules.setText("Game Board");
		rules.setHorizontalTextPosition(SwingConstants.CENTER);
		rules.setFont(gamefont);
		rules.setIcon(imageIcon);
		rules.setOpaque(false);
		rules.setContentAreaFilled(false);
		rules.setBorderPainted(false);
		credits.setText("Credits");
		credits.setHorizontalTextPosition(SwingConstants.CENTER);
        credits.setFont(gamefont);
        credits.setIcon(imageIcon);
        credits.setOpaque(false);
		credits.setContentAreaFilled(false);
		credits.setBorderPainted(false);
		exit.setText("End Game");
		exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setFont(gamefont);
        exit.setIcon(imageIcon);
        exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		Button.add(start);
		Button.add(rules);
		Button.add(credits);
		Button.add(exit);
		this.add(im);
		this.revalidate();
		this.repaint();
		
		
	}
	

	




	public JButton getSound() {
	return Sound;
}







	public JButton getStart() {
	return start;
}

public JButton getRules() {
		return rules;
	}



public JButton getCredits() {
	return credits;
}




public JButton getExit() {
	return exit;
}


	public static void main(String[] args) throws IOException{
		new MainView(); 
		

		
	}
	public void actionPerformed(ActionEvent e)
	{

	}






	
}
