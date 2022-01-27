package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import engine.*;
import exceptions.FullHandException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import model.heroes.*;
import model.heroes.Paladin;



public class StartView extends JFrame implements ActionListener, MouseListener{
	private JLabel effect ;	
	private JPanel Startanimation;
	private Timer timer;
	private Timer timer2;
	private Clip clip;
	private Clip clip2;
	private JButton Sound;
	private JFrame f;
	private CardLayout c;
	private Container cont;
	private RulesView Rules;
	private MainView Main;
	private CreditsView Credits;
	private PreHeroView PreHero;
	private HeroView Hero;
	private Controller Game;
	private Hero setHero1;
	private Hero setHero2;
	private EndView endview ;
	
	

	
	public StartView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
	////Dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, 1300, 845);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		

		
		
   ////Intro Music
		File soundFile = new File("images//Startviewaudio.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start(); 
   ////Game Music
		File soundFile2 = new File("images//Mainaudio.wav");
		AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
		clip2 = AudioSystem.getClip();
		clip2.open(audioIn2);
		
		
		
		
		
		
	///initialization
		f = new JFrame();
		Main = new MainView();
		PreHero = new PreHeroView();
		Hero = new HeroView();
		Credits = new CreditsView();
		Rules=new RulesView();
		c = new CardLayout();
		cont = this.getContentPane();
		cont.setLayout(c);
		effect = new JLabel();
		Startanimation=new JPanel();
		Startanimation.setLayout(null);

		this.setTitle("Hearthstone");

		
		
		////gif
			effect.setBounds(0,0,(int)screenSize.getWidth(),(int) screenSize.getHeight());
			Image image3 = Toolkit.getDefaultToolkit().createImage("images/Start.gif");
			effect.setIcon(new ImageIcon(image3));
			effect.setBounds(-80,0,1440,800);
			Startanimation.add(effect);
			f.add(Startanimation);
			
			
	
			
		////All Buttons of the game
			Main.getStart().addActionListener(this);
			Main.getCredits().addActionListener(this);
			Main.getExit().addActionListener(this);
			Main.getRules().addActionListener(this);
			Main.getSound().addActionListener(this);
			
			Credits.getBack().addActionListener(this);
			Rules.getBackr().addActionListener(this);
			

			Hero.getNext().setActionCommand("next");
			Hero.getMage1().setActionCommand("Mage1");
			Hero.getMage2().setActionCommand("Mage2");
			Hero.getHunter1().setActionCommand("Hunter1");
			Hero.getHunter2().setActionCommand("Hunter2");
			Hero.getPriest1().setActionCommand("Priest1");
			Hero.getPriest2().setActionCommand("Priest2");
			Hero.getPaladin1().setActionCommand("Paladin1");
			Hero.getPaladin2().setActionCommand("Paladin2");
			Hero.getWarlock1().setActionCommand("Warlock1");
			Hero.getWarlock2().setActionCommand("Warlock2");
			
			
			Hero.getNext().addActionListener(this);
			Hero.getMage1().addActionListener(this);
			Hero.getMage2().addActionListener(this);
			Hero.getHunter1().addActionListener(this);
			Hero.getHunter2().addActionListener(this);
			Hero.getPriest1().addActionListener(this);
			Hero.getPriest2().addActionListener(this);
			Hero.getPaladin1().addActionListener(this);
			Hero.getPaladin2().addActionListener(this);
			Hero.getWarlock1().addActionListener(this);
			Hero.getWarlock2().addActionListener(this);
			
			
		
			////cont	
			cont.add(f.getContentPane(),"Start");
			cont.add(Main.getContentPane(),"Main");
			cont.add(Rules.getContentPane(),"Rules");
			cont.add(Credits.getContentPane(),"Credits");
			cont.add(PreHero.getContentPane(),"PreHero");
			cont.add(Hero.getContentPane(),"Hero");
		//	cont.add(endview,"endview");
			
			f.getContentPane().addMouseListener(this);
			PreHero.getContentPane().addMouseListener(this);
			
			
			

			
			
		////timer for start gif	 //16000
			timer = new Timer(16000, new ActionListener(){ 
	            public void actionPerformed(ActionEvent e) {
	            	c.next(cont);
	            	timer.setRepeats(false);
	            	timer.stop();
	            } 
	        });
	
			timer.start();
		
			//11000
			timer2 = new Timer(11500, new ActionListener(){ 
	            public void actionPerformed(ActionEvent e) {
	            	c.next(cont);
	            	timer2.setRepeats(false);
	            	timer2.stop();}
	        });
			
	
			
			
			
		this.revalidate();
			this.repaint();
		
	}
	

	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		new StartView();
		
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start Game")){
			c.next(cont);
			c.next(cont);
			c.next(cont);
			clip.stop();
			if(Main.getSound().getActionCommand().equals("Sound")) {
			clip2.loop(Clip.LOOP_CONTINUOUSLY);
			clip2.start();}
			timer2.start();
		}
		else if(e.getActionCommand().equals("Sound")) {
			
			BufferedImage imgs = null;
		try {
		    imgs = ImageIO.read(new File("images//Mute.png"));
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		
		Image dimgs = imgs.getScaledInstance( (int) 40, (int) 40,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcons = new ImageIcon(dimgs);
		Main.getSound().setIcon(imageIcons);
		clip.stop();
		Main.getSound().setActionCommand("Mute");
		}
		
		
		
		else if(e.getActionCommand().equals("Mute")) {
			
			BufferedImage imgs = null;
		try {
		    imgs = ImageIO.read(new File("images//Sound.png"));
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		
		Image dimgs = imgs.getScaledInstance( (int) 40, (int) 40,
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcons = new ImageIcon(dimgs);
		Main.getSound().setIcon(imageIcons);
		clip.start();
		Main.getSound().setActionCommand("Sound");
		}
		
        else if(e.getActionCommand().equals("Game Board")) {
        	c.next(cont);
			
		}
		else if(e.getActionCommand().equals("Credits")) {
			c.next(cont);
			c.next(cont);
		}
		
		else if(e.getActionCommand().equals("Back")) {
			c.previous(cont);
			c.previous(cont);
		}
		else if(e.getActionCommand().equals("Backr")) {
			c.previous(cont);
		}
		else if(e.getActionCommand().equals("End Game")) {
			this.dispose();
		}
		if(e.getActionCommand().equals("next")){
			if(Hero.getPic1().getIcon()!=null && Hero.getPic2().getIcon()!=null) {
				if(Hero.getPic1().getIcon().toString().contains("Paladin")) {
					try {
						setHero1 = new Paladin();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				if(Hero.getPic2().getIcon().toString().contains("Paladin")) {
					try {
						setHero2 = new Paladin();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic1().getIcon().toString().contains("Hunter")) {
					try {
						setHero1 = new Hunter();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic2().getIcon().toString().contains("Hunter")) {
					try {
						setHero2 = new Hunter();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic1().getIcon().toString().contains("Mage")) {
					try {
						setHero1 = new Mage();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic2().getIcon().toString().contains("Mage")) {
					try {
						setHero2 = new Mage();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic1().getIcon().toString().contains("Priest")) {
					try {
						setHero1 = new Priest();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic2().getIcon().toString().contains("Priest")) {
					try {
						setHero2 = new Priest();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic1().getIcon().toString().contains("Warlock")) {
					try {
						setHero1 = new Warlock();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				if(Hero.getPic2().getIcon().toString().contains("Warlock")) {
					try {
						setHero2 = new Warlock();
					 
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {

						e1.printStackTrace();
					} 
				}
				
				
					 try {
						Game  = new Controller(setHero1,setHero2,Hero.getPic1().getIcon().toString(),Hero.getPic2().getIcon().toString(),cont,c,this);
					} catch (FullHandException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    cont.add(Game.getView().getContentPane());
			    c.next(cont);
			//	this.setContentPane(Game.getView().getContentPane());
				this.revalidate();
				this.repaint();
			}
				
		}
		if(e.getActionCommand().equals("Mage1")) {
			if(Hero.getPic1().getIcon()==null)
				Hero.getPic1().setIcon(new ImageIcon("images//MageB.gif"));
			else if(!Hero.getPic1().getIcon().toString().equals(("images//MageB.gif")))
				Hero.getPic1().setIcon(new ImageIcon("images//MageB.gif"));
			else
				Hero.getPic1().setIcon(null);
		}
		
		if(e.getActionCommand().equals("Mage2")) {
			if(Hero.getPic2().getIcon()==null)
				Hero.getPic2().setIcon(new ImageIcon("images//MageB.gif"));
			else if(!Hero.getPic2().getIcon().toString().equals(("images//MageB.gif")))
				Hero.getPic2().setIcon(new ImageIcon("images//MageB.gif"));
			else
				Hero.getPic2().setIcon(null);
		}
		if(e.getActionCommand().equals("Hunter1")) {
			if(Hero.getPic1().getIcon()==null)
				Hero.getPic1().setIcon(new ImageIcon("images//HunterB.gif"));
			else if(!Hero.getPic1().getIcon().toString().equals(("images//HunterB.gif")))
				Hero.getPic1().setIcon(new ImageIcon("images//HunterB.gif"));
			else
				Hero.getPic1().setIcon(null);
		}
		if(e.getActionCommand().equals("Hunter2")) {
			if(Hero.getPic2().getIcon()==null)
				Hero.getPic2().setIcon(new ImageIcon("images//HunterB.gif"));
			else if(!Hero.getPic2().getIcon().toString().equals(("images//HunterB.gif")))
				Hero.getPic2().setIcon(new ImageIcon("images//HunterB.gif"));
			else
				Hero.getPic2().setIcon(null);
		}
		if(e.getActionCommand().equals("Priest1")) {
			if(Hero.getPic1().getIcon()==null)
				Hero.getPic1().setIcon(new ImageIcon("images//PriestB.gif"));
			else if(!Hero.getPic1().getIcon().toString().equals(("images//PriestB.gif")))
				Hero.getPic1().setIcon(new ImageIcon("images//PriestB.gif"));
			else
				Hero.getPic1().setIcon(null);
		}
		if(e.getActionCommand().equals("Priest2")) {
			if(Hero.getPic2().getIcon()==null)
				Hero.getPic2().setIcon(new ImageIcon("images//PriestB.gif"));
			else if(!Hero.getPic2().getIcon().toString().equals(("images//PriestB.gif")))
				Hero.getPic2().setIcon(new ImageIcon("images//PriestB.gif"));
			else
				Hero.getPic2().setIcon(null);
		}
		if(e.getActionCommand().equals("Paladin1")) {
			if(Hero.getPic1().getIcon()==null)
				Hero.getPic1().setIcon(new ImageIcon("images//PaladinB.gif"));
			else if(!Hero.getPic1().getIcon().toString().equals(("images//PaladinB.gif")))
				Hero.getPic1().setIcon(new ImageIcon("images//PaladinB.gif"));
			else
				Hero.getPic1().setIcon(null);
		}
		if(e.getActionCommand().equals("Paladin2")) {
			if(Hero.getPic2().getIcon()==null)
				Hero.getPic2().setIcon(new ImageIcon("images//PaladinB.gif"));
			else if(!Hero.getPic2().getIcon().toString().equals(("images//PaladinB.gif")))
				Hero.getPic2().setIcon(new ImageIcon("images//PaladinB.gif"));
			else
				Hero.getPic2().setIcon(null);
		}
		if(e.getActionCommand().equals("Warlock1")) {
			if(Hero.getPic1().getIcon()==null)
				Hero.getPic1().setIcon(new ImageIcon("images//WarlockB.gif"));
			else if(!Hero.getPic1().getIcon().toString().equals(("images//WarlockB.gif")))
				Hero.getPic1().setIcon(new ImageIcon("images//WarlockB.gif"));
			else
				Hero.getPic1().setIcon(null);
		}
		if(e.getActionCommand().equals("Warlock2")) {
			if(Hero.getPic2().getIcon()==null)
				Hero.getPic2().setIcon(new ImageIcon("images//WarlockB.gif"));
			else if(!Hero.getPic2().getIcon().toString().equals(("images//WarlockB.gif")))
				Hero.getPic2().setIcon(new ImageIcon("images//WarlockB.gif"));
			else
				Hero.getPic2().setIcon(null);
		}
		
		
		
			
		
		
	}
	

	
	
	
	

	public HeroView getHero() {
		return Hero;
	}



	public Hero getSetHero1() {
		System.out.println(setHero1);
		return setHero1;
	}



	public Hero getSetHero2() {
		return setHero2;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		c.next(cont);
		timer.stop();
		timer2.stop();
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}
