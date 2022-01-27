package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class PreHeroView extends JFrame {
	private JLabel effect ;	
	private JPanel test;
	private HeroView Hero;
	
	
	
	
	public PreHeroView() throws IOException {
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Hero = new HeroView();
		this.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			effect = new JLabel();
			test=new JPanel();
			test.setLayout(null);
			effect.setBounds(0,0,(int)screenSize.getWidth(),(int) screenSize.getHeight());
			Image image3 = Toolkit.getDefaultToolkit().createImage("images/PreHero.gif");
			effect.setIcon(new ImageIcon(image3));
			effect.setBounds(0,0,1440,800);
			test.add(effect);
			this.add(test);

			this.revalidate();
			this.repaint();
			
		
			
	}
	
	

	public void setTimer2() {
		
	}




	public static void main(String[] args) throws IOException {
		new PreHeroView();
		
	}


	
	
}
