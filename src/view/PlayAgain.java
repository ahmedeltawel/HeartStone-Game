package view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import engine.Controller;
import exceptions.FullHandException;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class PlayAgain extends JFrame implements ActionListener{
	private Clip clip2;
	private JFrame f;
	private CardLayout c;
	private Container cont;
	private HeroView Hero;
	private MainView Sound;
	private Controller Game;
	private Hero setHero1;
	private Hero setHero2;
	//private JButton Skip;
	private JFrame l ;
	private EndView endview ;
	public PlayAgain(JFrame l) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		 ////Game Music
//		File soundFile2 = new File("images//Mainaudio.wav");
//		AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
//		clip2 = AudioSystem.getClip();
//		clip2.open(audioIn2);
//		clip2.loop(Clip.LOOP_CONTINUOUSLY);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.l= l ;
		
		Hero = new HeroView();
		c = new CardLayout();
		cont = this.getContentPane();
		cont.setLayout(c);

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
			cont.add(Hero.getContentPane(),"Hero");
		//	cont.add(endview,"endview");
			
			
			
			
			

			
			
		
		//	this.setVisible(true);
			
			
			
		this.revalidate();
			this.repaint();
		
	}
	

	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
	//	new PlayAgain();
		
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
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
			//	this.revalidate();
			//	this.repaint();
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




}

