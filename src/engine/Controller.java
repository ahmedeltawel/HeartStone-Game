package engine;

import view.GameView;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import exceptions.*;
import model.cards.*;
import model.cards.minions.*;
import model.cards.spells.*;
import model.heroes.*;
import view.*;

public class Controller implements GameListener , ActionListener , MouseListener{
	private Game model ;
	private GameView view ;
	private StartView Heros;
	private ArrayList<JButton> opponents ;
	private ArrayList<JButton> currentheros ;
	private ArrayList<JButton> opponentsField ;
	private ArrayList<JButton> currentherosField ;
	private  JLabel Manacrystals ;
	private JButton SelectedMinion ;
	private JButton SelectedMinion1 ;
	private EndView endview ;
	private Container cont ;
	private CardLayout c ;
	private JFrame l ;
	private JButton Power ;
	
	public Controller(Hero setHero1 , Hero setHero2 , String H1 , String H2, Container cont , CardLayout c , JFrame l) throws FullHandException, CloneNotSupportedException  {
		
		opponentsField = new ArrayList<JButton>();
		currentherosField = new ArrayList<JButton>();
		this.cont = cont ;
		this.c = c ;
		this.l = l ;
		model = new Game(setHero1,setHero2);
		view = new GameView();
		setCurrent(H1,model.getCurrentHero());
		setCurrent(H2,model.getCurrentHero());
		setOpponent(H1,model.getOpponent());
		setOpponent(H2,model.getOpponent());
	//	setCurrent(H2,model.getCurrentHero());
		int a = model.getCurrentHero().getDeck().size();
		int b = model.getOpponent().getDeck().size();
		view.getEndturn().addActionListener(this);
		opponents = new ArrayList<JButton>();
		currentheros = new ArrayList<JButton>();
		view.getDeck1().setText(new Integer(model.getCurrentHero().getDeck().size()).toString());
		view.getDeck2().setText(new Integer(model.getOpponent().getDeck().size()).toString());
		model.setListener(this);
	   for(int i = 0 ; i <10 ; i ++) {
		  JButton Hand1card = new JButton();
		   Hand1card.setBounds(200,660,110, 140);
		   Hand1card.setOpaque(false);
		    Hand1card.setContentAreaFilled(false);
		    Hand1card.setBorderPainted(false);
		    view.getHero2hand().add(Hand1card);
		    Hand1card.addActionListener(this);
		    view.getHero2hand().add(Hand1card); 
		    opponents.add(Hand1card);
		    Hand1card.addMouseListener(this);
	   }
	   for(int i = 0 ; i <10 ; i ++) {
			  JButton Hand1card = new JButton();
			   Hand1card.setBounds(200,-43,100, 140);
			   Hand1card.setOpaque(false);
			    Hand1card.setContentAreaFilled(false);
			    Hand1card.setBorderPainted(false);
			    view.getHero1hand().add(Hand1card);
			    Hand1card.addActionListener(this);
			    view.getHero1hand().add(Hand1card);  
			    currentheros.add(Hand1card);
			    Hand1card.addMouseListener(this);
		   }
	   
	   
	   view.getField1card1().addActionListener(this);
	   view.getField1card2().addActionListener(this);
	   view.getField1card3().addActionListener(this);
	   view.getField1card4().addActionListener(this);
	   view.getField1card5().addActionListener(this);
	   view.getField1card6().addActionListener(this);
	   view.getField1card7().addActionListener(this);
	   currentherosField.add(view.getField1card1());
	   currentherosField.add(view.getField1card2());
	   currentherosField.add(view.getField1card3());
	   currentherosField.add(view.getField1card4());
	   currentherosField.add(view.getField1card5());
	   currentherosField.add(view.getField1card6());
	   currentherosField.add(view.getField1card7());
	   view.getField2card1().addActionListener(this);
	   view.getField2card2().addActionListener(this);
	   view.getField2card3().addActionListener(this);
	   view.getField2card4().addActionListener(this);
	   view.getField2card5().addActionListener(this);
	   view.getField2card6().addActionListener(this);
	   view.getField2card7().addActionListener(this);
	   opponentsField.add(view.getField2card1());
	   opponentsField.add(view.getField2card2());
	   opponentsField.add(view.getField2card3());
	   opponentsField.add(view.getField2card4());
	   opponentsField.add(view.getField2card5());
	   opponentsField.add(view.getField2card6());
	   opponentsField.add(view.getField2card7());
	   
	   // 
	  
	      generate(model.getCurrentHero().getHand() , currentheros) ;
	      generateOpponent(model.getOpponent().getHand(),opponents);
	   //   nuul();
	      if(model.getCurrentHero().equals(model.getFirstHero())) {
	    	  setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
	    	  view.setlocationMana1down();
	    	  view.setlocationMana2up();
	      }
	      if(model.getCurrentHero().equals(model.getSecondHero())) {
	    	  setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
	    	  view.setlocationMana2down();
	    	  view.setlocationMana1up();
	      }
	      
	 //     generate(model.getOpponent().getHand() , opponents) ;
	      view.getHero2power().addActionListener(this);
	      view.getHero1().addActionListener(this);
	      view.getHero2().addActionListener(this);
	      view.getHero1().setActionCommand("Opponent");
	      view.getHero2().setActionCommand("Current");
	      view.getHero1().addMouseListener(this);
	      view.getHero2().addActionListener(this);
	      
	      view.getDeck2cards().setText(new Integer(model.getCurrentHero().getDeck().size()).toString());
		  view.getDeck1cards().setText(new Integer(model.getOpponent().getDeck().size()).toString());
		 
		  
		  
		  view.setH2((new Integer(model.getCurrentHero().getCurrentHP())).toString());
	    	view.setH1((new Integer(model.getOpponent().getCurrentHP())).toString());
	    	setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
	    	PowerH();
	    	view.getHero2power().addMouseListener(this);
		
	}
	public  void setMana1(int Mana,int TotalMana) {
		view.setManabarcrystals1();
	    BufferedImage manacrystal = null;
	  	try {
	  	    manacrystal = ImageIO.read(new File("images//Mana.png"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image manacrystal1 = manacrystal.getScaledInstance( (int) 20, (int) 28,
	  	        Image.SCALE_SMOOTH);
	  	ImageIcon manacrystalicon = new ImageIcon(manacrystal1);
	  	
	  	 BufferedImage manacrystaldark = null;
		  	try {
		  	    manacrystaldark = ImageIO.read(new File("images//Manadark.png"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image manacrystal1dark = manacrystaldark.getScaledInstance( (int) 20, (int) 28,
		  	        Image.SCALE_SMOOTH);
		  	ImageIcon manacrystalicondark = new ImageIcon(manacrystal1dark);
		  	

	    for(int i =0;i<Mana;i++) {
	    	 Manacrystals = new JLabel();
	    	 Manacrystals.setIcon(manacrystalicon);
	    	 view.getManabarcrystals1().add(Manacrystals);
	    }
	    for(int i =0;i<TotalMana-Mana;i++) {
	    	 Manacrystals = new JLabel();
	    	 Manacrystals.setIcon(manacrystalicondark);
	    	 view.getManabarcrystals1().add(Manacrystals);
	    }
	    
	    for(int i =0;i<10-TotalMana;i++) {
	   	 Manacrystals = new JLabel();
	   	 Manacrystals.setIcon(null);
	   	view.getManabarcrystals1().add(Manacrystals);
	
	   }
	    view.repaint();
	    view.revalidate();
	    
	}
	
	
	public  void setMana2(int Mana,int TotalMana) {
		view.setManabarcrystals2();
	    BufferedImage manacrystal = null;
	  	try {
	  	    manacrystal = ImageIO.read(new File("images//Mana.png"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image manacrystal1 = manacrystal.getScaledInstance( (int) 20, (int) 28,
	  	        Image.SCALE_SMOOTH);
	  	ImageIcon manacrystalicon = new ImageIcon(manacrystal1);
	  	
	  	 BufferedImage manacrystaldark = null;
		  	try {
		  	    manacrystaldark = ImageIO.read(new File("images//Manadark.png"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image manacrystal1dark = manacrystaldark.getScaledInstance( (int) 20, (int) 28,
		  	        Image.SCALE_SMOOTH);
		  	ImageIcon manacrystalicondark = new ImageIcon(manacrystal1dark);
	  	
	  	
	  	int x = Mana;
	    for(int i =0;i<x;i++) {
	    	 Manacrystals = new JLabel();
	    	 Manacrystals.setIcon(manacrystalicon);
	    	 view.getManabarcrystals2().add(Manacrystals);
	    }
	    
	    int w = TotalMana;
	    for(int i =0;i<w-x;i++) {
	    	 Manacrystals = new JLabel();
	    	 Manacrystals.setIcon(manacrystalicondark);
	    	 view.getManabarcrystals2().add(Manacrystals);
	    }
	    
	    for(int i =0;i<10-TotalMana;i++) {
	   	 Manacrystals = new JLabel();
	   	 Manacrystals.setIcon(null);
	   	view.getManabarcrystals2().add(Manacrystals);
	
	   }
	}
	public void nuul() {
	    
	}
	public void  PowerH() {
		
		if(model.getCurrentHero().getName().equals("Rexxar")) {
			view.getHero2power().setToolTipText("Inflict two damage points to the opponent hero." );
		}
	else
		if(model.getCurrentHero().getName().equals("Jaina Proudmoore")){
				view.getHero2power().setToolTipText(" Inflict one damage point to a specific target (a hero or a minion).  " );
			}
	else
		if(model.getCurrentHero().getName().equals("Uther Lightbringer")) {
			view.getHero2power().setToolTipText("Create a new minion with the following attributes and add it to the field" );
			}
	else
		if(model.getCurrentHero().getName().equals("Anduin Wrynn")){
			view.getHero2power().setToolTipText(" Restore two health points to a specific target (a hero or a minion). " );
		}
	else
		if(model.getCurrentHero().getName().equals("Gul'dan")) {
			view.getHero2power().setToolTipText("Draw an extra card and inflict two damage points to the hero.  " ); 
		}
}
	public static void generateOpponent(ArrayList<Card> a , ArrayList<JButton> b) {
		if(a.size() ==0) {
			for(int i = 0 ; i < b.size() ; i ++) {
				b.get(i).setIcon(null);
			}
		}else { 
			int i ;
		for( i = 0 ; i < a.size() ; i ++) {
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//Cardback.gif"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 100, (int) 140,
		  	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		}
		for(int j = i ; j < b.size() ; j++) {
			b.get(j).setIcon(null);
		}
		}
	}
	public  void setCurrent(String a , Hero b) {
		if(b instanceof Paladin) {
		 if(a.contains("Paladin")) {
			File ftesth1 = new File(a);
			URL imgh1 = null;
			try {
				imgh1 = ftesth1.toURL();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			ImageIcon iconh1 = new ImageIcon(imgh1);
			iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
			if(view.getHero2().getIcon() == null)
			 view.getHero2().setIcon(iconh1);
			BufferedImage imghp1 = null;
			try {
			    imghp1= ImageIO.read(new File("images//PaladinPower.PNG"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
			        Image.SCALE_SMOOTH);
			
			ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
			
			view.getHero2power().setIcon(imageIconhp1);
		}
		}
		 if(b instanceof Priest) {
			 if(a.contains("Priest")) {
					File ftesth1 = new File(a);
					URL imgh1 = null;
					try {
						imgh1 = ftesth1.toURL();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					ImageIcon iconh1 = new ImageIcon(imgh1);
					iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
					if(view.getHero2().getIcon() == null)
						 view.getHero2().setIcon(iconh1);
					
					BufferedImage imghp1 = null;
					try {
					    imghp1= ImageIO.read(new File("images//PriestPower.PNG"));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					
					Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
					        Image.SCALE_SMOOTH);
					
					ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
					view.getHero2power().setIcon(imageIconhp1);
				}
		}
		 if(b instanceof Hunter) {
			 if(a.contains("Hunter")) {
					File ftesth1 = new File(a);
					URL imgh1 = null;
					try {
						imgh1 = ftesth1.toURL();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					ImageIcon iconh1 = new ImageIcon(imgh1);
					iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
					if(view.getHero2().getIcon() == null)
						 view.getHero2().setIcon(iconh1);
					
					BufferedImage imghp1 = null;
					try {
					    imghp1= ImageIO.read(new File("images//HunterPower.PNG"));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					
					Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
					        Image.SCALE_SMOOTH);
					
					ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
					view.getHero2power().setIcon(imageIconhp1);
				}
		}
		 if(b instanceof Warlock) {
			 if(a.contains("Warlock")) {
					File ftesth1 = new File(a);
					URL imgh1 = null;
					try {
						imgh1 = ftesth1.toURL();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					ImageIcon iconh1 = new ImageIcon(imgh1);
					iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
					if(view.getHero2().getIcon() == null)
						 view.getHero2().setIcon(iconh1);
					BufferedImage imghp1 = null;
					try {
					    imghp1= ImageIO.read(new File("images//WarlockPower.PNG"));
					} catch (IOException e) {
					    e.printStackTrace();
					}
					
					Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
					        Image.SCALE_SMOOTH);
					
					ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
					view.getHero2power().setIcon(imageIconhp1);
				}
		 }
			 if(b instanceof Mage) {
					 if(a.contains("Mage")) {
							File ftesth1 = new File(a);
							URL imgh1 = null;
							try {
								imgh1 = ftesth1.toURL();
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							}
							ImageIcon iconh1 = new ImageIcon(imgh1);
							iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
							if(view.getHero2().getIcon() == null)
								 view.getHero2().setIcon(iconh1);
							BufferedImage imghp1 = null;
							try {
							    imghp1= ImageIO.read(new File("images//MagePower.PNG"));
							} catch (IOException e) {
							    e.printStackTrace();
							}
							
							Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
							        Image.SCALE_SMOOTH);
							
							ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
							view.getHero2power().setIcon(imageIconhp1);
						}
		}
				
		
	}
	public  void setOpponent(String a , Hero b) {
		if(b instanceof Paladin) {
			 if(a.contains("Paladin")) {
				File ftesth1 = new File(a);
				URL imgh1 = null;
				try {
					imgh1 = ftesth1.toURL();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				ImageIcon iconh1 = new ImageIcon(imgh1);
				iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
				if(view.getHero1().getIcon() == null)
				 view.getHero1().setIcon(iconh1);
				BufferedImage imghp1 = null;
				try {
				    imghp1= ImageIO.read(new File("images//PaladinPower.PNG"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
				        Image.SCALE_SMOOTH);
				
				ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
				
				view.getHero1power().setIcon(imageIconhp1);
			}
			}
			 if(b instanceof Priest) {
				 if(a.contains("Priest")) {
						File ftesth1 = new File(a);
						URL imgh1 = null;
						try {
							imgh1 = ftesth1.toURL();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						ImageIcon iconh1 = new ImageIcon(imgh1);
						iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
						if(view.getHero1().getIcon() == null)
							 view.getHero1().setIcon(iconh1);
						
						BufferedImage imghp1 = null;
						try {
						    imghp1= ImageIO.read(new File("images//PriestPower.PNG"));
						} catch (IOException e) {
						    e.printStackTrace();
						}
						
						Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
						        Image.SCALE_SMOOTH);
						
						ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
						view.getHero1power().setIcon(imageIconhp1);
					}
			}
			 if(b instanceof Hunter) {
				 if(a.contains("Hunter")) {
						File ftesth1 = new File(a);
						URL imgh1 = null;
						try {
							imgh1 = ftesth1.toURL();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						ImageIcon iconh1 = new ImageIcon(imgh1);
						iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
						if(view.getHero1().getIcon() == null)
							 view.getHero1().setIcon(iconh1);
						
						BufferedImage imghp1 = null;
						try {
						    imghp1= ImageIO.read(new File("images//HunterPower.PNG"));
						} catch (IOException e) {
						    e.printStackTrace();
						}
						
						Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
						        Image.SCALE_SMOOTH);
						
						ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
						view.getHero1power().setIcon(imageIconhp1);
					}
			}
			 if(b instanceof Warlock) {
				 if(a.contains("Warlock")) {
						File ftesth1 = new File(a);
						URL imgh1 = null;
						try {
							imgh1 = ftesth1.toURL();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						ImageIcon iconh1 = new ImageIcon(imgh1);
						iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
						if(view.getHero1().getIcon() == null)
							 view.getHero1().setIcon(iconh1);
						BufferedImage imghp1 = null;
						try {
						    imghp1= ImageIO.read(new File("images//WarlockPower.PNG"));
						} catch (IOException e) {
						    e.printStackTrace();
						}
						
						Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
						        Image.SCALE_SMOOTH);
						
						ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
						view.getHero1power().setIcon(imageIconhp1);
					}
			 }
				 if(b instanceof Mage) {
						 if(a.contains("Mage")) {
								File ftesth1 = new File(a);
								URL imgh1 = null;
								try {
									imgh1 = ftesth1.toURL();
								} catch (MalformedURLException e1) {
									e1.printStackTrace();
								}
								ImageIcon iconh1 = new ImageIcon(imgh1);
								iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
								if(view.getHero1().getIcon() == null)
									 view.getHero1().setIcon(iconh1);
								BufferedImage imghp1 = null;
								try {
								    imghp1= ImageIO.read(new File("images//MagePower.PNG"));
								} catch (IOException e) {
								    e.printStackTrace();
								}
								
								Image Imghp1 = imghp1.getScaledInstance( (int) 80, (int) 80,
								        Image.SCALE_SMOOTH);
								
								ImageIcon imageIconhp1 = new ImageIcon(Imghp1);
								view.getHero1power().setIcon(imageIconhp1);
							}
			}
					
		}
	public  void generateField1(ArrayList<Minion> a , ArrayList<JButton> b) {


		if(a.size()==0) {
			for(int i = 0 ; i < b.size() ; i++) {
				b.get(i).setIcon(null);
				b.get(i).setText("");
				view.getShield11().setVisible(false);
				view.getShield12().setVisible(false);
				view.getShield13().setVisible(false);
				view.getShield14().setVisible(false);
				view.getShield15().setVisible(false);
				view.getShield16().setVisible(false);
				view.getShield17().setVisible(false);
				view.setvisibility11(false);
				view.setvisibility12(false);
				view.setvisibility13(false);
				view.setvisibility14(false);
				view.setvisibility15(false);
				view.setvisibility16(false);
				view.setvisibility17(false);
			}
		}
		else {
			int i ;
		for( i = 0 ; i < a.size() ; i++) {
		if(a.get(i).getName().equals("Sunwalker")) {
			if(!(a.get(i).isSleeping())) {
			//ImageIcon l = new ImageIcon("images//Sunwalker.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//SunwalkerF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			
			}else
			{
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//SunwalkerS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
		}
		if(a.get(i).getName().equals("Sheep")) {
			if(!(a.get(i).isSleeping())) {
			//ImageIcon l = new ImageIcon("images//Sunwalker.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//SheepF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			
			}else
			{
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//SheepF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
		}
		if(a.get(i).getName().equals("Silver Hand Recruit")) {
			if(!(a.get(i).isSleeping())) {
			//ImageIcon l = new ImageIcon("images//Sunwalker.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//SilverhandrecruitF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			
			}else
			{
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//SilverhandrecruitS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
		}
	else
	    if(a.get(i).getName().equals("Argent Commander")) {
	    	if(!(a.get(i).isSleeping())) {
		//	ImageIcon l = new ImageIcon("images//Argentcommander.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ArgentcommanderF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
		}
	    	else {
	    		BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//ArgentcommanderS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
	    	}
	    	if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
	    	if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
	    }
	  else
		 if(a.get(i).getName().equals("Colossus of the Moon")) {
			 if(!(a.get(i).isSleeping())){
		//    ImageIcon l = new ImageIcon("images//Colossusofthemoon.PNG");
		    BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ColossusofthemoonF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		    b.get(i).setText(a.get(i).getName());
		    System.out.print("Pass");
		}
			 else {
				 BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//ColossusofthemoonS.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				    b.get(i).setText(a.get(i).getName());
				    System.out.print("Pass");
			 }
			 if(i == 0 && a.get(i).isDivine()) {
					view.getShield11().setVisible(true);
				}
				else 
				if(i==1 && a.get(i).isDivine()){
					view.getShield12().setVisible(true);
				}
				else
					if(i==2 && a.get(i).isDivine())
						view.getShield13().setVisible(true);
			    else
					if(i==3 &&  a.get(i).isDivine())
						view.getShield14().setVisible(true);
				else
					if(i==4 && a.get(i).isDivine())
						view.getShield15().setVisible(true);
				else
					if(i==5 && a.get(i).isDivine())
						view.getShield16().setVisible(true);
				else
					if(i==6 && a.get(i).isDivine())
						view.getShield17().setVisible(true);
			 if(i == 0 && a.get(i).isDivine()==false ) {
					view.getShield11().setVisible(false);
				}
				else 
				if(i==1 && a.get(i).isDivine()==false){
					view.getShield12().setVisible(false);
				}
				else
					if(i==2 && a.get(i).isDivine() == false)
						view.getShield13().setVisible(false);
			    else
					if(i==3 &&  a.get(i).isDivine()==false)
						view.getShield14().setVisible(false);
				else
					if(i==4 && a.get(i).isDivine()==false)
						view.getShield15().setVisible(false);
				else
					if(i==5 && a.get(i).isDivine() == false)
						view.getShield16().setVisible(false);
				else
					if(i==6 && a.get(i).isDivine()==false)
						view.getShield17().setVisible(false);
		}
	else
		if(a.get(i).getName().equals("Icehowl")) {
			if(!(a.get(i).isSleeping())){
			//  ImageIcon l = new ImageIcon("images//Icehowl.PNG");
			  BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//IcehowlF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			else {
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//IcehowlS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
		}	
else
if(a.get(i).getName().equals("The LichKing")) {
	if(!(a.get(i).isSleeping())) {
	//	 ImageIcon l = new ImageIcon("images//Thelichking.PNG");
		 BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ThelichkingF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		 b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ThelichkingS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	 b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield11().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield12().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield13().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield14().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield15().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield16().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield17().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield11().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield12().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield13().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield14().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield15().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield16().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield17().setVisible(false);
}
else
if(a.get(i).getName().equals("Chromaggus")) {
	if(!(a.get(i).isSleeping())) {
	//    ImageIcon l = new ImageIcon("images//Chromaggus.PNG");
	    BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ChromaggusF.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	    b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ChromaggusS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	    b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield11().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield12().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield13().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield14().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield15().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield16().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield17().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield11().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield12().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield13().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield14().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield15().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield16().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield17().setVisible(false);
}
else
if(a.get(i).getName().equals("Core Hound")) {
	if(!(a.get(i).isSleeping())) {
	//	ImageIcon l = new ImageIcon("images//Corehound.PNG");
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//CorehoundF.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
		b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//CorehoundS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
		b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield11().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield12().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield13().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield14().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield15().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield16().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield17().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield11().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield12().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield13().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield14().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield15().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield16().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield17().setVisible(false);
}
else
	if(a.get(i).getName().equals("Boulderfist Ogre")) {
		if(!(a.get(i).isSleeping())) {
	//	  ImageIcon l = new ImageIcon("images//Boulderfistogre.PNG");
		  BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//BoulderfistogreF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		  b.get(i).setText(a.get(i).getName());
		}else {
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//BoulderfistogreS.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		}
		if(i == 0 && a.get(i).isDivine()) {
			view.getShield11().setVisible(true);
		}
		else 
		if(i==1 && a.get(i).isDivine()){
			view.getShield12().setVisible(true);
		}
		else
			if(i==2 && a.get(i).isDivine())
				view.getShield13().setVisible(true);
	    else
			if(i==3 &&  a.get(i).isDivine())
				view.getShield14().setVisible(true);
		else
			if(i==4 && a.get(i).isDivine())
				view.getShield15().setVisible(true);
		else
			if(i==5 && a.get(i).isDivine())
				view.getShield16().setVisible(true);
		else
			if(i==6 && a.get(i).isDivine())
				view.getShield17().setVisible(true);
		if(i == 0 && a.get(i).isDivine()==false ) {
			view.getShield11().setVisible(false);
		}
		else 
		if(i==1 && a.get(i).isDivine()==false){
			view.getShield12().setVisible(false);
		}
		else
			if(i==2 && a.get(i).isDivine() == false)
				view.getShield13().setVisible(false);
	    else
			if(i==3 &&  a.get(i).isDivine()==false)
				view.getShield14().setVisible(false);
		else
			if(i==4 && a.get(i).isDivine()==false)
				view.getShield15().setVisible(false);
		else
			if(i==5 && a.get(i).isDivine() == false)
				view.getShield16().setVisible(false);
		else
			if(i==6 && a.get(i).isDivine()==false)
				view.getShield17().setVisible(false);
	}
else
	if(a.get(i).getName().equals("Chilwind Yeti")) {
		if(!(a.get(i).isSleeping())) {
	//		ImageIcon l = new ImageIcon("images//Chillwindyeti.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ChillwindyetiF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			}
		else {
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ChillwindyetiS.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
		}
		if(i == 0 && a.get(i).isDivine()) {
			view.getShield11().setVisible(true);
		}
		else 
		if(i==1 && a.get(i).isDivine()){
			view.getShield12().setVisible(true);
		}
		else
			if(i==2 && a.get(i).isDivine())
				view.getShield13().setVisible(true);
	    else
			if(i==3 &&  a.get(i).isDivine())
				view.getShield14().setVisible(true);
		else
			if(i==4 && a.get(i).isDivine())
				view.getShield15().setVisible(true);
		else
			if(i==5 && a.get(i).isDivine())
				view.getShield16().setVisible(true);
		else
			if(i==6 && a.get(i).isDivine())
				view.getShield17().setVisible(true);
		if(i == 0 && a.get(i).isDivine()==false ) {
			view.getShield11().setVisible(false);
		}
		else 
		if(i==1 && a.get(i).isDivine()==false){
			view.getShield12().setVisible(false);
		}
		else
			if(i==2 && a.get(i).isDivine() == false)
				view.getShield13().setVisible(false);
	    else
			if(i==3 &&  a.get(i).isDivine()==false)
				view.getShield14().setVisible(false);
		else
			if(i==4 && a.get(i).isDivine()==false)
				view.getShield15().setVisible(false);
		else
			if(i==5 && a.get(i).isDivine() == false)
				view.getShield16().setVisible(false);
		else
			if(i==6 && a.get(i).isDivine()==false)
				view.getShield17().setVisible(false);
	}
	else
		if(a.get(i).getName().equals("Wolfrider")) {
			if(!(a.get(i).isSleeping())) {
		//	  ImageIcon l = new ImageIcon("images//Wolfrider.PNG");
			  BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//WolfriderF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			else {
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//WolfriderS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield11().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield12().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield13().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield14().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield15().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield16().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield17().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield12().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield13().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield14().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield15().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield16().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield17().setVisible(false);
		}
		else
			if(a.get(i).getName().equals("Frostwolf Grunt")) {
				if(!(a.get(i).isSleeping())) {
			//	  ImageIcon l = new ImageIcon("images//Frostwolfgrunt.PNG");
				  BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//FrostwolfgruntF.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				  b.get(i).setText(a.get(i).getName());
				}else {
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//FrostwolfgruntS.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				  b.get(i).setText(a.get(i).getName());
				}
				if(i == 0 && a.get(i).isDivine()) {
					view.getShield11().setVisible(true);
				}
				else 
				if(i==1 && a.get(i).isDivine()){
					view.getShield12().setVisible(true);
				}
				else
					if(i==2 && a.get(i).isDivine())
						view.getShield13().setVisible(true);
			    else
					if(i==3 &&  a.get(i).isDivine())
						view.getShield14().setVisible(true);
				else
					if(i==4 && a.get(i).isDivine())
						view.getShield15().setVisible(true);
				else
					if(i==5 && a.get(i).isDivine())
						view.getShield16().setVisible(true);
				else
					if(i==6 && a.get(i).isDivine())
						view.getShield17().setVisible(true);
				if(i == 0 && a.get(i).isDivine()==false ) {
					view.getShield11().setVisible(false);
				}
				else 
				if(i==1 && a.get(i).isDivine()==false){
					view.getShield12().setVisible(false);
				}
				else
					if(i==2 && a.get(i).isDivine() == false)
						view.getShield13().setVisible(false);
			    else
					if(i==3 &&  a.get(i).isDivine()==false)
						view.getShield14().setVisible(false);
				else
					if(i==4 && a.get(i).isDivine()==false)
						view.getShield15().setVisible(false);
				else
					if(i==5 && a.get(i).isDivine() == false)
						view.getShield16().setVisible(false);
				else
					if(i==6 && a.get(i).isDivine()==false)
						view.getShield17().setVisible(false);
			}
			else
				if(a.get(i).getName().equals("Bloodfen Raptor")) {
					if(!(a.get(i).isSleeping())) {
				//	  ImageIcon l = new ImageIcon("images//Bloodfenraptor.PNG");
					  BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//BloodfenraptorF.PNG"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
				    	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					  b.get(i).setText(a.get(i).getName());
					}
					else {
						BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//BloodfenraptorS.PNG"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
				    	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					  b.get(i).setText(a.get(i).getName());
					}
					if(i == 0 && a.get(i).isDivine()) {
						view.getShield11().setVisible(true);
					}
					else 
					if(i==1 && a.get(i).isDivine()){
						view.getShield12().setVisible(true);
					}
					else
						if(i==2 && a.get(i).isDivine())
							view.getShield13().setVisible(true);
				    else
						if(i==3 &&  a.get(i).isDivine())
							view.getShield14().setVisible(true);
					else
						if(i==4 && a.get(i).isDivine())
							view.getShield15().setVisible(true);
					else
						if(i==5 && a.get(i).isDivine())
							view.getShield16().setVisible(true);
					else
						if(i==6 && a.get(i).isDivine())
							view.getShield17().setVisible(true);
					if(i == 0 && a.get(i).isDivine()==false ) {
						view.getShield11().setVisible(false);
					}
					else 
					if(i==1 && a.get(i).isDivine()==false){
						view.getShield12().setVisible(false);
					}
					else
						if(i==2 && a.get(i).isDivine() == false)
							view.getShield13().setVisible(false);
				    else
						if(i==3 &&  a.get(i).isDivine()==false)
							view.getShield14().setVisible(false);
					else
						if(i==4 && a.get(i).isDivine()==false)
							view.getShield15().setVisible(false);
					else
						if(i==5 && a.get(i).isDivine() == false)
							view.getShield16().setVisible(false);
					else
						if(i==6 && a.get(i).isDivine()==false)
							view.getShield17().setVisible(false);
				}
				else
					if(a.get(i).getName().equals("Stonetusk Boar")) {
						if(!(a.get(i).isSleeping())) {
						//  ImageIcon l = new ImageIcon("images//Stonetuskboar.PNG");
						  BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//StonetuskboarF.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}else {
							BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//StonetuskboarS.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}
						if(i == 0 && a.get(i).isDivine()) {
							view.getShield11().setVisible(true);
						}
						else 
						if(i==1 && a.get(i).isDivine()){
							view.getShield12().setVisible(true);
						}
						else
							if(i==2 && a.get(i).isDivine())
								view.getShield13().setVisible(true);
					    else
							if(i==3 &&  a.get(i).isDivine())
								view.getShield14().setVisible(true);
						else
							if(i==4 && a.get(i).isDivine())
								view.getShield15().setVisible(true);
						else
							if(i==5 && a.get(i).isDivine())
								view.getShield16().setVisible(true);
						else
							if(i==6 && a.get(i).isDivine())
								view.getShield17().setVisible(true);
						if(i == 0 && a.get(i).isDivine()==false ) {
							view.getShield11().setVisible(false);
						}
						else 
						if(i==1 && a.get(i).isDivine()==false){
							view.getShield12().setVisible(false);
						}
						else
							if(i==2 && a.get(i).isDivine() == false)
								view.getShield13().setVisible(false);
					    else
							if(i==3 &&  a.get(i).isDivine()==false)
								view.getShield14().setVisible(false);
						else
							if(i==4 && a.get(i).isDivine()==false)
								view.getShield15().setVisible(false);
						else
							if(i==5 && a.get(i).isDivine() == false)
								view.getShield16().setVisible(false);
						else
							if(i==6 && a.get(i).isDivine()==false)
								view.getShield17().setVisible(false);
					}else
								if(a.get(i).getName().equals("Goldshire Footman")) {
									if(!(a.get(i).isSleeping())) {
								//	  ImageIcon l = new ImageIcon("images//Goldshirefootman.PNG");
									  BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//GoldshirefootmanF.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
						}else {
							BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//GoldshirefootmanS.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}
									if(i == 0 && a.get(i).isDivine()) {
										view.getShield11().setVisible(true);
									}
									else 
									if(i==1 && a.get(i).isDivine()){
										view.getShield12().setVisible(true);
									}
									else
										if(i==2 && a.get(i).isDivine())
											view.getShield13().setVisible(true);
								    else
										if(i==3 &&  a.get(i).isDivine())
											view.getShield14().setVisible(true);
									else
										if(i==4 && a.get(i).isDivine())
											view.getShield15().setVisible(true);
									else
										if(i==5 && a.get(i).isDivine())
											view.getShield16().setVisible(true);
									else
										if(i==6 && a.get(i).isDivine())
											view.getShield17().setVisible(true);
									if(i == 0 && a.get(i).isDivine()==false ) {
										view.getShield11().setVisible(false);
									}
									else 
									if(i==1 && a.get(i).isDivine()==false){
										view.getShield12().setVisible(false);
									}
									else
										if(i==2 && a.get(i).isDivine() == false)
											view.getShield13().setVisible(false);
								    else
										if(i==3 &&  a.get(i).isDivine()==false)
											view.getShield14().setVisible(false);
									else
										if(i==4 && a.get(i).isDivine()==false)
											view.getShield15().setVisible(false);
									else
										if(i==5 && a.get(i).isDivine() == false)
											view.getShield16().setVisible(false);
									else
										if(i==6 && a.get(i).isDivine()==false)
											view.getShield17().setVisible(false);
								}
								else
									if(a.get(i).getName().equals("King Krush")) {
										if(!(a.get(i).isSleeping())) {
									//	  ImageIcon l = new ImageIcon("images//Kingkrush.PNG");
										  BufferedImage img2 = null;
										  	try {
										  	    img2 = ImageIO.read(new File("images//KingkrushF.PNG"));
										  	} catch (IOException e) {
										  	    e.printStackTrace();
										  	}
										  	
										  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
									    	        Image.SCALE_SMOOTH);
										  	ImageIcon imageIcon2 = new ImageIcon(Img2);
											b.get(i).setIcon(imageIcon2);
										  b.get(i).setText(a.get(i).getName());
							}
										else{BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//KingkrushS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
											
										}
										if(i == 0 && a.get(i).isDivine()) {
											view.getShield11().setVisible(true);
										}
										else 
										if(i==1 && a.get(i).isDivine()){
											view.getShield12().setVisible(true);
										}
										else
											if(i==2 && a.get(i).isDivine())
												view.getShield13().setVisible(true);
									    else
											if(i==3 &&  a.get(i).isDivine())
												view.getShield14().setVisible(true);
										else
											if(i==4 && a.get(i).isDivine())
												view.getShield15().setVisible(true);
										else
											if(i==5 && a.get(i).isDivine())
												view.getShield16().setVisible(true);
										else
											if(i==6 && a.get(i).isDivine())
												view.getShield17().setVisible(true);
										if(i == 0 && a.get(i).isDivine()==false ) {
											view.getShield11().setVisible(false);
										}
										else 
										if(i==1 && a.get(i).isDivine()==false){
											view.getShield12().setVisible(false);
										}
										else
											if(i==2 && a.get(i).isDivine() == false)
												view.getShield13().setVisible(false);
									    else
											if(i==3 &&  a.get(i).isDivine()==false)
												view.getShield14().setVisible(false);
										else
											if(i==4 && a.get(i).isDivine()==false)
												view.getShield15().setVisible(false);
										else
											if(i==5 && a.get(i).isDivine() == false)
												view.getShield16().setVisible(false);
										else
											if(i==6 && a.get(i).isDivine()==false)
												view.getShield17().setVisible(false);}
									else
										if(a.get(i).getName().equals("Kalycgos")) {
											if(!(a.get(i).isSleeping())) {
											//  ImageIcon l = new ImageIcon("images//Kalycgos.PNG");
											  BufferedImage img2 = null;
											  	try {
											  	    img2 = ImageIO.read(new File("images//KalecgosF.PNG"));
											  	} catch (IOException e) {
											  	    e.printStackTrace();
											  	}
											  	
											  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
										    	        Image.SCALE_SMOOTH);
											  	ImageIcon imageIcon2 = new ImageIcon(Img2);
												b.get(i).setIcon(imageIcon2);
											  b.get(i).setText(a.get(i).getName());
								}else {
									 BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//KalecgosS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
								}
											if(i == 0 && a.get(i).isDivine()) {
												view.getShield11().setVisible(true);
											}
											else 
											if(i==1 && a.get(i).isDivine()){
												view.getShield12().setVisible(true);
											}
											else
												if(i==2 && a.get(i).isDivine())
													view.getShield13().setVisible(true);
										    else
												if(i==3 &&  a.get(i).isDivine())
													view.getShield14().setVisible(true);
											else
												if(i==4 && a.get(i).isDivine())
													view.getShield15().setVisible(true);
											else
												if(i==5 && a.get(i).isDivine())
													view.getShield16().setVisible(true);
											else
												if(i==6 && a.get(i).isDivine())
													view.getShield17().setVisible(true);
											if(i == 0 && a.get(i).isDivine()==false ) {
												view.getShield11().setVisible(false);
											}
											else 
											if(i==1 && a.get(i).isDivine()==false){
												view.getShield12().setVisible(false);
											}
											else
												if(i==2 && a.get(i).isDivine() == false)
													view.getShield13().setVisible(false);
										    else
												if(i==3 &&  a.get(i).isDivine()==false)
													view.getShield14().setVisible(false);
											else
												if(i==4 && a.get(i).isDivine()==false)
													view.getShield15().setVisible(false);
											else
												if(i==5 && a.get(i).isDivine() == false)
													view.getShield16().setVisible(false);
											else
												if(i==6 && a.get(i).isDivine()==false)
													view.getShield17().setVisible(false);
										}
										else
											if(a.get(i).getName().equals("Tirion Fordring")) {
												if(!(a.get(i).isSleeping())) {
												//  ImageIcon l = new ImageIcon("images//Tirionfordring.PNG");
												  BufferedImage img2 = null;
												  	try {
												  	    img2 = ImageIO.read(new File("images//TirionfordringF.PNG"));
												  	} catch (IOException e) {
												  	    e.printStackTrace();
												  	}
												  	
												  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
											    	        Image.SCALE_SMOOTH);
												  	ImageIcon imageIcon2 = new ImageIcon(Img2);
													b.get(i).setIcon(imageIcon2);
												  b.get(i).setText(a.get(i).getName());
									}else {
										BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//TirionfordringS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
									}
												if(i == 0 && a.get(i).isDivine()) {
													view.getShield11().setVisible(true);
												}
												else 
												if(i==1 && a.get(i).isDivine()){
													view.getShield12().setVisible(true);
												}
												else
													if(i==2 && a.get(i).isDivine())
														view.getShield13().setVisible(true);
											    else
													if(i==3 &&  a.get(i).isDivine())
														view.getShield14().setVisible(true);
												else
													if(i==4 && a.get(i).isDivine())
														view.getShield15().setVisible(true);
												else
													if(i==5 && a.get(i).isDivine())
														view.getShield16().setVisible(true);
												else
													if(i==6 && a.get(i).isDivine())
														view.getShield17().setVisible(true);
												if(i == 0 && a.get(i).isDivine()==false ) {
													view.getShield11().setVisible(false);
												}
												else 
												if(i==1 && a.get(i).isDivine()==false){
													view.getShield12().setVisible(false);
												}
												else
													if(i==2 && a.get(i).isDivine() == false)
														view.getShield13().setVisible(false);
											    else
													if(i==3 &&  a.get(i).isDivine()==false)
														view.getShield14().setVisible(false);
												else
													if(i==4 && a.get(i).isDivine()==false)
														view.getShield15().setVisible(false);
												else
													if(i==5 && a.get(i).isDivine() == false)
														view.getShield16().setVisible(false);
												else
													if(i==6 && a.get(i).isDivine()==false)
														view.getShield17().setVisible(false);
											}
											else
												if(a.get(i).getName().equals("Prophet Velen")) {
													if(!(a.get(i).isSleeping())) {
												//	  ImageIcon l = new ImageIcon("images//Prophetvelen.PNG");
													  BufferedImage img2 = null;
													  	try {
													  	    img2 = ImageIO.read(new File("images//ProphetvelenF.PNG"));
													  	} catch (IOException e) {
													  	    e.printStackTrace();
													  	}
													  	
													  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
												    	        Image.SCALE_SMOOTH);
													  	ImageIcon imageIcon2 = new ImageIcon(Img2);
														b.get(i).setIcon(imageIcon2);
													  b.get(i).setText(a.get(i).getName());
										}else {
											BufferedImage img2 = null;
										  	try {
										  	    img2 = ImageIO.read(new File("images//ProphetvelenS.PNG"));
										  	} catch (IOException e) {
										  	    e.printStackTrace();
										  	}
										  	
										  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
									    	        Image.SCALE_SMOOTH);
										  	ImageIcon imageIcon2 = new ImageIcon(Img2);
											b.get(i).setIcon(imageIcon2);
										  b.get(i).setText(a.get(i).getName());
										}if(i == 0 && a.get(i).isDivine()) {
											view.getShield11().setVisible(true);
										}
										else 
										if(i==1 && a.get(i).isDivine()){
											view.getShield12().setVisible(true);
										}
										else
											if(i==2 && a.get(i).isDivine())
												view.getShield13().setVisible(true);
									    else
											if(i==3 &&  a.get(i).isDivine())
												view.getShield14().setVisible(true);
										else
											if(i==4 && a.get(i).isDivine())
												view.getShield15().setVisible(true);
										else
											if(i==5 && a.get(i).isDivine())
												view.getShield16().setVisible(true);
										else
											if(i==6 && a.get(i).isDivine())
												view.getShield17().setVisible(true);
										if(i == 0 && a.get(i).isDivine()==false ) {
											view.getShield11().setVisible(false);
										}
										else 
										if(i==1 && a.get(i).isDivine()==false){
											view.getShield12().setVisible(false);
										}
										else
											if(i==2 && a.get(i).isDivine() == false)
												view.getShield13().setVisible(false);
									    else
											if(i==3 &&  a.get(i).isDivine()==false)
												view.getShield14().setVisible(false);
										else
											if(i==4 && a.get(i).isDivine()==false)
												view.getShield15().setVisible(false);
										else
											if(i==5 && a.get(i).isDivine() == false)
												view.getShield16().setVisible(false);
										else
											if(i==6 && a.get(i).isDivine()==false)
												view.getShield17().setVisible(false);
												}
												else
													if(a.get(i).getName().equals("Wilfred Fizzlebang")) {
														if(!(a.get(i).isSleeping())) {
														//  ImageIcon l = new ImageIcon("images//Wilfredfizzlebang.PNG");
														  BufferedImage img2 = null;
														  	try {
														  	    img2 = ImageIO.read(new File("images//WilfredfizzlebangF.PNG"));
														  	} catch (IOException e) {
														  	    e.printStackTrace();
														  	}
														  	
														  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
													    	        Image.SCALE_SMOOTH);
														  	ImageIcon imageIcon2 = new ImageIcon(Img2);
															b.get(i).setIcon(imageIcon2);
														  b.get(i).setText(a.get(i).getName());
											}else {
												BufferedImage img2 = null;
											  	try {
											  	    img2 = ImageIO.read(new File("images//WilfredfizzlebangS.PNG"));
											  	} catch (IOException e) {
											  	    e.printStackTrace();
											  	}
											  	
											  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
										    	        Image.SCALE_SMOOTH);
											  	ImageIcon imageIcon2 = new ImageIcon(Img2);
												b.get(i).setIcon(imageIcon2);
											  b.get(i).setText(a.get(i).getName());
											}
														if(i == 0 && a.get(i).isDivine()) {
															view.getShield11().setVisible(true);
														}
														else 
														if(i==1 && a.get(i).isDivine()){
															view.getShield12().setVisible(true);
														}
														else
															if(i==2 && a.get(i).isDivine())
																view.getShield13().setVisible(true);
													    else
															if(i==3 &&  a.get(i).isDivine())
																view.getShield14().setVisible(true);
														else
															if(i==4 && a.get(i).isDivine())
																view.getShield15().setVisible(true);
														else
															if(i==5 && a.get(i).isDivine())
																view.getShield16().setVisible(true);
														else
															if(i==6 && a.get(i).isDivine())
																view.getShield17().setVisible(true);
														if(i == 0 && a.get(i).isDivine()==false ) {
															view.getShield11().setVisible(false);
														}
														else 
														if(i==1 && a.get(i).isDivine()==false){
															view.getShield12().setVisible(false);
														}
														else
															if(i==2 && a.get(i).isDivine() == false)
																view.getShield13().setVisible(false);
													    else
															if(i==3 &&  a.get(i).isDivine()==false)
																view.getShield14().setVisible(false);
														else
															if(i==4 && a.get(i).isDivine()==false)
																view.getShield15().setVisible(false);
														else
															if(i==5 && a.get(i).isDivine() == false)
																view.getShield16().setVisible(false);
														else
															if(i==6 && a.get(i).isDivine()==false)
																view.getShield17().setVisible(false);
													}


	}
		for(int j = i ; j < b.size();j++) {
			b.get(j).setIcon(null);
			b.get(j).setText("");
			if(j == 0  ) {
				view.getShield11().setVisible(false);
			}
			else 
			if(j==1 ){
				view.getShield12().setVisible(false);
			}
			else
				if(j==2 )
					view.getShield13().setVisible(false);
		    else
				if(j==3 )
					view.getShield14().setVisible(false);
			else
				if(j==4 )
					view.getShield15().setVisible(false);
			else
				if(j==5 )
					view.getShield16().setVisible(false);
			else
				if(j==6 )
					view.getShield17().setVisible(false);
		
		}
		}
set1();
set2();
		
	}
	public  void generateField2(ArrayList<Minion> a , ArrayList<JButton> b) {

		if(a.size()==0) {
			for(int i = 0 ; i < b.size() ; i++) {
				b.get(i).setIcon(null);
				b.get(i).setText("");
				view.getShield21().setVisible(false);
				view.getShield22().setVisible(false);
				view.getShield23().setVisible(false);
				view.getShield24().setVisible(false);
				view.getShield25().setVisible(false);
				view.getShield26().setVisible(false);
				view.getShield27().setVisible(false);
				
			}
		}
		else {
			int i ;
		for( i = 0 ; i < a.size() ; i++) {
		if(a.get(i).getName().equals("Sunwalker")) {
			if(!(a.get(i).isSleeping())) {
			//ImageIcon l = new ImageIcon("images//Sunwalker.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//SunwalkerF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			
			}else
			{
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//SunwalkerS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield21().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield22().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield23().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield24().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield25().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield26().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield27().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield21().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield22().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield23().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield24().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield25().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield26().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield27().setVisible(false);
		}
	else
	    if(a.get(i).getName().equals("Argent Commander")) {
	    	if(!(a.get(i).isSleeping())) {
		//	ImageIcon l = new ImageIcon("images//Argentcommander.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ArgentcommanderF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
		}
	    	else {
	    		BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//ArgentcommanderS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
	    	}
	    	if(i == 0 && a.get(i).isDivine()) {
				view.getShield21().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield22().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield23().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield24().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield25().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield26().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield27().setVisible(true);
	    	if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield21().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield22().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield23().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield24().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield25().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield26().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield27().setVisible(false);
	    }
	    else
		    if(a.get(i).getName().equals("Silver Hand Recruit")) {
		    	if(!(a.get(i).isSleeping())) {
			//	ImageIcon l = new ImageIcon("images//Argentcommander.PNG");
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//SilverhandrecruitF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
			}
		    	else {
		    		BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//SilverhandrecruitS.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
		    	}
		    	if(i == 0 && a.get(i).isDivine()) {
					view.getShield21().setVisible(true);
				}
				else 
				if(i==1 && a.get(i).isDivine()){
					view.getShield22().setVisible(true);
				}
				else
					if(i==2 && a.get(i).isDivine())
						view.getShield23().setVisible(true);
			    else
					if(i==3 &&  a.get(i).isDivine())
						view.getShield24().setVisible(true);
				else
					if(i==4 && a.get(i).isDivine())
						view.getShield25().setVisible(true);
				else
					if(i==5 && a.get(i).isDivine())
						view.getShield26().setVisible(true);
				else
					if(i==6 && a.get(i).isDivine())
						view.getShield27().setVisible(true);
		    	if(i == 0 && a.get(i).isDivine()==false ) {
					view.getShield21().setVisible(false);
				}
				else 
				if(i==1 && a.get(i).isDivine()==false){
					view.getShield22().setVisible(false);
				}
				else
					if(i==2 && a.get(i).isDivine() == false)
						view.getShield23().setVisible(false);
			    else
					if(i==3 &&  a.get(i).isDivine()==false)
						view.getShield24().setVisible(false);
				else
					if(i==4 && a.get(i).isDivine()==false)
						view.getShield25().setVisible(false);
				else
					if(i==5 && a.get(i).isDivine() == false)
						view.getShield26().setVisible(false);
				else
					if(i==6 && a.get(i).isDivine()==false)
						view.getShield27().setVisible(false);
		    }
		    else
			    if(a.get(i).getName().equals("Sheep")) {
			    	if(!(a.get(i).isSleeping())) {
				//	ImageIcon l = new ImageIcon("images//Argentcommander.PNG");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//SheepF.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
				}
			    	else {
			    		BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//SheepF.PNG"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
				    	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
						b.get(i).setText(a.get(i).getName());
			    	}
			    	if(i == 0 && a.get(i).isDivine()) {
						view.getShield21().setVisible(true);
					}
					else 
					if(i==1 && a.get(i).isDivine()){
						view.getShield22().setVisible(true);
					}
					else
						if(i==2 && a.get(i).isDivine())
							view.getShield23().setVisible(true);
				    else
						if(i==3 &&  a.get(i).isDivine())
							view.getShield24().setVisible(true);
					else
						if(i==4 && a.get(i).isDivine())
							view.getShield25().setVisible(true);
					else
						if(i==5 && a.get(i).isDivine())
							view.getShield26().setVisible(true);
					else
						if(i==6 && a.get(i).isDivine())
							view.getShield27().setVisible(true);
			    	if(i == 0 && a.get(i).isDivine()==false ) {
						view.getShield21().setVisible(false);
					}
					else 
					if(i==1 && a.get(i).isDivine()==false){
						view.getShield22().setVisible(false);
					}
					else
						if(i==2 && a.get(i).isDivine() == false)
							view.getShield23().setVisible(false);
				    else
						if(i==3 &&  a.get(i).isDivine()==false)
							view.getShield24().setVisible(false);
					else
						if(i==4 && a.get(i).isDivine()==false)
							view.getShield25().setVisible(false);
					else
						if(i==5 && a.get(i).isDivine() == false)
							view.getShield26().setVisible(false);
					else
						if(i==6 && a.get(i).isDivine()==false)
							view.getShield27().setVisible(false);
			    }
	  else
		 if(a.get(i).getName().equals("Colossus of the Moon")) {
			 if(!(a.get(i).isSleeping())){
		//    ImageIcon l = new ImageIcon("images//Colossusofthemoon.PNG");
		    BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ColossusofthemoonF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		    b.get(i).setText(a.get(i).getName());
		    System.out.print("Pass");
		}
			 else {
				 BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//ColossusofthemoonS.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				    b.get(i).setText(a.get(i).getName());
				    System.out.print("Pass");
			 }
			 if(i == 0 && a.get(i).isDivine()) {
					view.getShield21().setVisible(true);
				}
				else 
				if(i==1 && a.get(i).isDivine()){
					view.getShield22().setVisible(true);
				}
				else
					if(i==2 && a.get(i).isDivine())
						view.getShield23().setVisible(true);
			    else
					if(i==3 &&  a.get(i).isDivine())
						view.getShield24().setVisible(true);
				else
					if(i==4 && a.get(i).isDivine())
						view.getShield25().setVisible(true);
				else
					if(i==5 && a.get(i).isDivine())
						view.getShield26().setVisible(true);
				else
					if(i==6 && a.get(i).isDivine())
						view.getShield27().setVisible(true);
			 if(i == 0 && a.get(i).isDivine()==false ) {
					view.getShield21().setVisible(false);
				}
				else 
				if(i==1 && a.get(i).isDivine()==false){
					view.getShield22().setVisible(false);
				}
				else
					if(i==2 && a.get(i).isDivine() == false)
						view.getShield23().setVisible(false);
			    else
					if(i==3 &&  a.get(i).isDivine()==false)
						view.getShield24().setVisible(false);
				else
					if(i==4 && a.get(i).isDivine()==false)
						view.getShield25().setVisible(false);
				else
					if(i==5 && a.get(i).isDivine() == false)
						view.getShield26().setVisible(false);
				else
					if(i==6 && a.get(i).isDivine()==false)
						view.getShield27().setVisible(false);
		}
	else
		if(a.get(i).getName().equals("Icehowl")) {
			if(!(a.get(i).isSleeping())){
			//  ImageIcon l = new ImageIcon("images//Icehowl.PNG");
			  BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//IcehowlF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			else {
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//IcehowlS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield21().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield22().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield23().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield24().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield25().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield26().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield27().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield21().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield22().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield23().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield24().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield25().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield26().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield27().setVisible(false);
		}	
else
if(a.get(i).getName().equals("The LichKing")) {
	if(!(a.get(i).isSleeping())) {
	//	 ImageIcon l = new ImageIcon("images//Thelichking.PNG");
		 BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ThelichkingF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		 b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ThelichkingS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	 b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield21().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield22().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield23().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield24().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield25().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield26().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield27().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield21().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield22().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield23().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield24().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield25().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield26().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield27().setVisible(false);
}
else
if(a.get(i).getName().equals("Chromaggus")) {
	if(!(a.get(i).isSleeping())) {
	//    ImageIcon l = new ImageIcon("images//Chromaggus.PNG");
	    BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ChromaggusF.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	    b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//ChromaggusS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
	    b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield21().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield22().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield23().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield24().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield25().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield26().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield27().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield21().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield22().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield23().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield24().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield25().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield26().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield27().setVisible(false);
}
else
if(a.get(i).getName().equals("Core Hound")) {
	if(!(a.get(i).isSleeping())) {
	//	ImageIcon l = new ImageIcon("images//Corehound.PNG");
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//CorehoundF.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
		b.get(i).setText(a.get(i).getName());
	}
	else {
		BufferedImage img2 = null;
	  	try {
	  	    img2 = ImageIO.read(new File("images//CorehoundS.PNG"));
	  	} catch (IOException e) {
	  	    e.printStackTrace();
	  	}
	  	
	  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
    	        Image.SCALE_SMOOTH);
	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
		b.get(i).setIcon(imageIcon2);
		b.get(i).setText(a.get(i).getName());
	}
	if(i == 0 && a.get(i).isDivine()) {
		view.getShield21().setVisible(true);
	}
	else 
	if(i==1 && a.get(i).isDivine()){
		view.getShield22().setVisible(true);
	}
	else
		if(i==2 && a.get(i).isDivine())
			view.getShield23().setVisible(true);
    else
		if(i==3 &&  a.get(i).isDivine())
			view.getShield24().setVisible(true);
	else
		if(i==4 && a.get(i).isDivine())
			view.getShield25().setVisible(true);
	else
		if(i==5 && a.get(i).isDivine())
			view.getShield26().setVisible(true);
	else
		if(i==6 && a.get(i).isDivine())
			view.getShield27().setVisible(true);
	if(i == 0 && a.get(i).isDivine()==false ) {
		view.getShield21().setVisible(false);
	}
	else 
	if(i==1 && a.get(i).isDivine()==false){
		view.getShield22().setVisible(false);
	}
	else
		if(i==2 && a.get(i).isDivine() == false)
			view.getShield23().setVisible(false);
    else
		if(i==3 &&  a.get(i).isDivine()==false)
			view.getShield24().setVisible(false);
	else
		if(i==4 && a.get(i).isDivine()==false)
			view.getShield25().setVisible(false);
	else
		if(i==5 && a.get(i).isDivine() == false)
			view.getShield26().setVisible(false);
	else
		if(i==6 && a.get(i).isDivine()==false)
			view.getShield27().setVisible(false);
}
else
	if(a.get(i).getName().equals("Boulderfist Ogre")) {
		if(!(a.get(i).isSleeping())) {
	//	  ImageIcon l = new ImageIcon("images//Boulderfistogre.PNG");
		  BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//BoulderfistogreF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		  b.get(i).setText(a.get(i).getName());
		}else {
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//BoulderfistogreS.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
		}
		if(i == 0 && a.get(i).isDivine()) {
			view.getShield21().setVisible(true);
		}
		else 
		if(i==1 && a.get(i).isDivine()){
			view.getShield22().setVisible(true);
		}
		else
			if(i==2 && a.get(i).isDivine())
				view.getShield23().setVisible(true);
	    else
			if(i==3 &&  a.get(i).isDivine())
				view.getShield24().setVisible(true);
		else
			if(i==4 && a.get(i).isDivine())
				view.getShield25().setVisible(true);
		else
			if(i==5 && a.get(i).isDivine())
				view.getShield26().setVisible(true);
		else
			if(i==6 && a.get(i).isDivine())
				view.getShield27().setVisible(true);
		if(i == 0 && a.get(i).isDivine()==false ) {
			view.getShield21().setVisible(false);
		}
		else 
		if(i==1 && a.get(i).isDivine()==false){
			view.getShield22().setVisible(false);
		}
		else
			if(i==2 && a.get(i).isDivine() == false)
				view.getShield23().setVisible(false);
	    else
			if(i==3 &&  a.get(i).isDivine()==false)
				view.getShield24().setVisible(false);
		else
			if(i==4 && a.get(i).isDivine()==false)
				view.getShield25().setVisible(false);
		else
			if(i==5 && a.get(i).isDivine() == false)
				view.getShield26().setVisible(false);
		else
			if(i==6 && a.get(i).isDivine()==false)
				view.getShield27().setVisible(false);
	}
else
	if(a.get(i).getName().equals("Chilwind Yeti")) {
		if(!(a.get(i).isSleeping())) {
	//		ImageIcon l = new ImageIcon("images//Chillwindyeti.PNG");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ChillwindyetiF.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			}
		else {
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//ChillwindyetiS.PNG"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
	    	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
		}
		if(i == 0 && a.get(i).isDivine()) {
			view.getShield21().setVisible(true);
		}
		else 
		if(i==1 && a.get(i).isDivine()){
			view.getShield22().setVisible(true);
		}
		else
			if(i==2 && a.get(i).isDivine())
				view.getShield23().setVisible(true);
	    else
			if(i==3 &&  a.get(i).isDivine())
				view.getShield24().setVisible(true);
		else
			if(i==4 && a.get(i).isDivine())
				view.getShield25().setVisible(true);
		else
			if(i==5 && a.get(i).isDivine())
				view.getShield26().setVisible(true);
		else
			if(i==6 && a.get(i).isDivine())
				view.getShield27().setVisible(true);
		if(i == 0 && a.get(i).isDivine()==false ) {
			view.getShield21().setVisible(false);
		}
		else 
		if(i==1 && a.get(i).isDivine()==false){
			view.getShield22().setVisible(false);
		}
		else
			if(i==2 && a.get(i).isDivine() == false)
				view.getShield23().setVisible(false);
	    else
			if(i==3 &&  a.get(i).isDivine()==false)
				view.getShield24().setVisible(false);
		else
			if(i==4 && a.get(i).isDivine()==false)
				view.getShield25().setVisible(false);
		else
			if(i==5 && a.get(i).isDivine() == false)
				view.getShield26().setVisible(false);
		else
			if(i==6 && a.get(i).isDivine()==false)
				view.getShield27().setVisible(false);
	}
	else
		if(a.get(i).getName().equals("Wolfrider")) {
			if(!(a.get(i).isSleeping())) {
		//	  ImageIcon l = new ImageIcon("images//Wolfrider.PNG");
			  BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//WolfriderF.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			else {
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//WolfriderS.PNG"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
		    	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
			  b.get(i).setText(a.get(i).getName());
			}
			if(i == 0 && a.get(i).isDivine()) {
				view.getShield21().setVisible(true);
			}
			else 
			if(i==1 && a.get(i).isDivine()){
				view.getShield22().setVisible(true);
			}
			else
				if(i==2 && a.get(i).isDivine())
					view.getShield23().setVisible(true);
		    else
				if(i==3 &&  a.get(i).isDivine())
					view.getShield24().setVisible(true);
			else
				if(i==4 && a.get(i).isDivine())
					view.getShield25().setVisible(true);
			else
				if(i==5 && a.get(i).isDivine())
					view.getShield26().setVisible(true);
			else
				if(i==6 && a.get(i).isDivine())
					view.getShield27().setVisible(true);
			if(i == 0 && a.get(i).isDivine()==false ) {
				view.getShield21().setVisible(false);
			}
			else 
			if(i==1 && a.get(i).isDivine()==false){
				view.getShield22().setVisible(false);
			}
			else
				if(i==2 && a.get(i).isDivine() == false)
					view.getShield23().setVisible(false);
		    else
				if(i==3 &&  a.get(i).isDivine()==false)
					view.getShield24().setVisible(false);
			else
				if(i==4 && a.get(i).isDivine()==false)
					view.getShield25().setVisible(false);
			else
				if(i==5 && a.get(i).isDivine() == false)
					view.getShield26().setVisible(false);
			else
				if(i==6 && a.get(i).isDivine()==false)
					view.getShield27().setVisible(false);
		}
		else
			if(a.get(i).getName().equals("Frostwolf Grunt")) {
				if(!(a.get(i).isSleeping())) {
			//	  ImageIcon l = new ImageIcon("images//Frostwolfgrunt.PNG");
				  BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//FrostwolfgruntF.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				  b.get(i).setText(a.get(i).getName());
				}else {
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//FrostwolfgruntS.PNG"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
			    	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				  b.get(i).setText(a.get(i).getName());
				}
				if(i == 0 && a.get(i).isDivine()) {
					view.getShield21().setVisible(true);
				}
				else 
				if(i==1 && a.get(i).isDivine()){
					view.getShield22().setVisible(true);
				}
				else
					if(i==2 && a.get(i).isDivine())
						view.getShield23().setVisible(true);
			    else
					if(i==3 &&  a.get(i).isDivine())
						view.getShield24().setVisible(true);
				else
					if(i==4 && a.get(i).isDivine())
						view.getShield25().setVisible(true);
				else
					if(i==5 && a.get(i).isDivine())
						view.getShield26().setVisible(true);
				else
					if(i==6 && a.get(i).isDivine())
						view.getShield27().setVisible(true);
				if(i == 0 && a.get(i).isDivine()==false ) {
					view.getShield21().setVisible(false);
				}
				else 
				if(i==1 && a.get(i).isDivine()==false){
					view.getShield22().setVisible(false);
				}
				else
					if(i==2 && a.get(i).isDivine() == false)
						view.getShield23().setVisible(false);
			    else
					if(i==3 &&  a.get(i).isDivine()==false)
						view.getShield24().setVisible(false);
				else
					if(i==4 && a.get(i).isDivine()==false)
						view.getShield25().setVisible(false);
				else
					if(i==5 && a.get(i).isDivine() == false)
						view.getShield26().setVisible(false);
				else
					if(i==6 && a.get(i).isDivine()==false)
						view.getShield27().setVisible(false);
			}
			else
				if(a.get(i).getName().equals("Bloodfen Raptor")) {
					if(!(a.get(i).isSleeping())) {
				//	  ImageIcon l = new ImageIcon("images//Bloodfenraptor.PNG");
					  BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//BloodfenraptorF.PNG"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
				    	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					  b.get(i).setText(a.get(i).getName());
					}
					else {
						BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//BloodfenraptorS.PNG"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
				    	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					  b.get(i).setText(a.get(i).getName());
					}
					if(i == 0 && a.get(i).isDivine()) {
						view.getShield21().setVisible(true);
					}
					else 
					if(i==1 && a.get(i).isDivine()){
						view.getShield22().setVisible(true);
					}
					else
						if(i==2 && a.get(i).isDivine())
							view.getShield23().setVisible(true);
				    else
						if(i==3 &&  a.get(i).isDivine())
							view.getShield24().setVisible(true);
					else
						if(i==4 && a.get(i).isDivine())
							view.getShield25().setVisible(true);
					else
						if(i==5 && a.get(i).isDivine())
							view.getShield26().setVisible(true);
					else
						if(i==6 && a.get(i).isDivine())
							view.getShield27().setVisible(true);
					if(i == 0 && a.get(i).isDivine()==false ) {
						view.getShield21().setVisible(false);
					}
					else 
					if(i==1 && a.get(i).isDivine()==false){
						view.getShield22().setVisible(false);
					}
					else
						if(i==2 && a.get(i).isDivine() == false)
							view.getShield23().setVisible(false);
				    else
						if(i==3 &&  a.get(i).isDivine()==false)
							view.getShield24().setVisible(false);
					else
						if(i==4 && a.get(i).isDivine()==false)
							view.getShield25().setVisible(false);
					else
						if(i==5 && a.get(i).isDivine() == false)
							view.getShield26().setVisible(false);
					else
						if(i==6 && a.get(i).isDivine()==false)
							view.getShield27().setVisible(false);
				}
				else
					if(a.get(i).getName().equals("Stonetusk Boar")) {
						if(!(a.get(i).isSleeping())) {
						//  ImageIcon l = new ImageIcon("images//Stonetuskboar.PNG");
						  BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//StonetuskboarF.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}else {
							BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//StonetuskboarS.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}
						if(i == 0 && a.get(i).isDivine()) {
							view.getShield21().setVisible(true);
						}
						else 
						if(i==1 && a.get(i).isDivine()){
							view.getShield22().setVisible(true);
						}
						else
							if(i==2 && a.get(i).isDivine())
								view.getShield23().setVisible(true);
					    else
							if(i==3 &&  a.get(i).isDivine())
								view.getShield24().setVisible(true);
						else
							if(i==4 && a.get(i).isDivine())
								view.getShield25().setVisible(true);
						else
							if(i==5 && a.get(i).isDivine())
								view.getShield26().setVisible(true);
						else
							if(i==6 && a.get(i).isDivine())
								view.getShield27().setVisible(true);
						if(i == 0 && a.get(i).isDivine()==false ) {
							view.getShield21().setVisible(false);
						}
						else 
						if(i==1 && a.get(i).isDivine()==false){
							view.getShield22().setVisible(false);
						}
						else
							if(i==2 && a.get(i).isDivine() == false)
								view.getShield23().setVisible(false);
					    else
							if(i==3 &&  a.get(i).isDivine()==false)
								view.getShield24().setVisible(false);
						else
							if(i==4 && a.get(i).isDivine()==false)
								view.getShield25().setVisible(false);
						else
							if(i==5 && a.get(i).isDivine() == false)
								view.getShield26().setVisible(false);
						else
							if(i==6 && a.get(i).isDivine()==false)
								view.getShield27().setVisible(false);
					}else
								if(a.get(i).getName().equals("Goldshire Footman")) {
									if(!(a.get(i).isSleeping())) {
								//	  ImageIcon l = new ImageIcon("images//Goldshirefootman.PNG");
									  BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//GoldshirefootmanF.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
						}else {
							BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//GoldshirefootmanS.PNG"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
					    	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}if(i == 0 && a.get(i).isDivine()) {
							view.getShield21().setVisible(true);
						}
						else 
						if(i==1 && a.get(i).isDivine()){
							view.getShield22().setVisible(true);
						}
						else
							if(i==2 && a.get(i).isDivine())
								view.getShield23().setVisible(true);
					    else
							if(i==3 &&  a.get(i).isDivine())
								view.getShield24().setVisible(true);
						else
							if(i==4 && a.get(i).isDivine())
								view.getShield25().setVisible(true);
						else
							if(i==5 && a.get(i).isDivine())
								view.getShield26().setVisible(true);
						else
							if(i==6 && a.get(i).isDivine())
								view.getShield27().setVisible(true);
						if(i == 0 && a.get(i).isDivine()==false ) {
							view.getShield21().setVisible(false);
						}
						else 
						if(i==1 && a.get(i).isDivine()==false){
							view.getShield22().setVisible(false);
						}
						else
							if(i==2 && a.get(i).isDivine() == false)
								view.getShield23().setVisible(false);
					    else
							if(i==3 &&  a.get(i).isDivine()==false)
								view.getShield24().setVisible(false);
						else
							if(i==4 && a.get(i).isDivine()==false)
								view.getShield25().setVisible(false);
						else
							if(i==5 && a.get(i).isDivine() == false)
								view.getShield26().setVisible(false);
						else
							if(i==6 && a.get(i).isDivine()==false)
								view.getShield27().setVisible(false);
								}
								else
									if(a.get(i).getName().equals("King Krush")) {
										if(!(a.get(i).isSleeping())) {
									//	  ImageIcon l = new ImageIcon("images//Kingkrush.PNG");
										  BufferedImage img2 = null;
										  	try {
										  	    img2 = ImageIO.read(new File("images//KingkrushF.PNG"));
										  	} catch (IOException e) {
										  	    e.printStackTrace();
										  	}
										  	
										  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
									    	        Image.SCALE_SMOOTH);
										  	ImageIcon imageIcon2 = new ImageIcon(Img2);
											b.get(i).setIcon(imageIcon2);
										  b.get(i).setText(a.get(i).getName());
							}
										else{BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//KingkrushS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
											
										}if(i == 0 && a.get(i).isDivine()) {
											view.getShield21().setVisible(true);
										}
										else 
										if(i==1 && a.get(i).isDivine()){
											view.getShield22().setVisible(true);
										}
										else
											if(i==2 && a.get(i).isDivine())
												view.getShield23().setVisible(true);
									    else
											if(i==3 &&  a.get(i).isDivine())
												view.getShield24().setVisible(true);
										else
											if(i==4 && a.get(i).isDivine())
												view.getShield25().setVisible(true);
										else
											if(i==5 && a.get(i).isDivine())
												view.getShield26().setVisible(true);
										else
											if(i==6 && a.get(i).isDivine())
												view.getShield27().setVisible(true);
										if(i == 0 && a.get(i).isDivine()==false ) {
											view.getShield21().setVisible(false);
										}
										else 
										if(i==1 && a.get(i).isDivine()==false){
											view.getShield22().setVisible(false);
										}
										else
											if(i==2 && a.get(i).isDivine() == false)
												view.getShield23().setVisible(false);
									    else
											if(i==3 &&  a.get(i).isDivine()==false)
												view.getShield24().setVisible(false);
										else
											if(i==4 && a.get(i).isDivine()==false)
												view.getShield25().setVisible(false);
										else
											if(i==5 && a.get(i).isDivine() == false)
												view.getShield26().setVisible(false);
										else
											if(i==6 && a.get(i).isDivine()==false)
												view.getShield27().setVisible(false);}
									else
										if(a.get(i).getName().equals("Kalycgos")) {
											if(!(a.get(i).isSleeping())) {
											//  ImageIcon l = new ImageIcon("images//Kalycgos.PNG");
											  BufferedImage img2 = null;
											  	try {
											  	    img2 = ImageIO.read(new File("images//KalecgosF.PNG"));
											  	} catch (IOException e) {
											  	    e.printStackTrace();
											  	}
											  	
											  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
										    	        Image.SCALE_SMOOTH);
											  	ImageIcon imageIcon2 = new ImageIcon(Img2);
												b.get(i).setIcon(imageIcon2);
											  b.get(i).setText(a.get(i).getName());
								}else {
									 BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//KalecgosS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
								}if(i == 0 && a.get(i).isDivine()) {
									view.getShield21().setVisible(true);
								}
								else 
								if(i==1 && a.get(i).isDivine()){
									view.getShield22().setVisible(true);
								}
								else
									if(i==2 && a.get(i).isDivine())
										view.getShield23().setVisible(true);
							    else
									if(i==3 &&  a.get(i).isDivine())
										view.getShield24().setVisible(true);
								else
									if(i==4 && a.get(i).isDivine())
										view.getShield25().setVisible(true);
								else
									if(i==5 && a.get(i).isDivine())
										view.getShield26().setVisible(true);
								else
									if(i==6 && a.get(i).isDivine())
										view.getShield27().setVisible(true);
								if(i == 0 && a.get(i).isDivine()==false ) {
									view.getShield21().setVisible(false);
								}
								else 
								if(i==1 && a.get(i).isDivine()==false){
									view.getShield22().setVisible(false);
								}
								else
									if(i==2 && a.get(i).isDivine() == false)
										view.getShield23().setVisible(false);
							    else
									if(i==3 &&  a.get(i).isDivine()==false)
										view.getShield24().setVisible(false);
								else
									if(i==4 && a.get(i).isDivine()==false)
										view.getShield25().setVisible(false);
								else
									if(i==5 && a.get(i).isDivine() == false)
										view.getShield26().setVisible(false);
								else
									if(i==6 && a.get(i).isDivine()==false)
										view.getShield27().setVisible(false);
										}
										else
											if(a.get(i).getName().equals("Tirion Fordring")) {
												if(!(a.get(i).isSleeping())) {
												//  ImageIcon l = new ImageIcon("images//Tirionfordring.PNG");
												  BufferedImage img2 = null;
												  	try {
												  	    img2 = ImageIO.read(new File("images//TirionfordringF.PNG"));
												  	} catch (IOException e) {
												  	    e.printStackTrace();
												  	}
												  	
												  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
											    	        Image.SCALE_SMOOTH);
												  	ImageIcon imageIcon2 = new ImageIcon(Img2);
													b.get(i).setIcon(imageIcon2);
												  b.get(i).setText(a.get(i).getName());
									}else {
										BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//TirionfordringS.PNG"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
								    	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
									}if(i == 0 && a.get(i).isDivine()) {
										view.getShield21().setVisible(true);
									}
									else 
									if(i==1 && a.get(i).isDivine()){
										view.getShield22().setVisible(true);
									}
									else
										if(i==2 && a.get(i).isDivine())
											view.getShield23().setVisible(true);
								    else
										if(i==3 &&  a.get(i).isDivine())
											view.getShield24().setVisible(true);
									else
										if(i==4 && a.get(i).isDivine())
											view.getShield25().setVisible(true);
									else
										if(i==5 && a.get(i).isDivine())
											view.getShield26().setVisible(true);
									else
										if(i==6 && a.get(i).isDivine())
											view.getShield27().setVisible(true);
									if(i == 0 && a.get(i).isDivine()==false ) {
										view.getShield21().setVisible(false);
									}
									else 
									if(i==1 && a.get(i).isDivine()==false){
										view.getShield22().setVisible(false);
									}
									else
										if(i==2 && a.get(i).isDivine() == false)
											view.getShield23().setVisible(false);
								    else
										if(i==3 &&  a.get(i).isDivine()==false)
											view.getShield24().setVisible(false);
									else
										if(i==4 && a.get(i).isDivine()==false)
											view.getShield25().setVisible(false);
									else
										if(i==5 && a.get(i).isDivine() == false)
											view.getShield26().setVisible(false);
									else
										if(i==6 && a.get(i).isDivine()==false)
											view.getShield27().setVisible(false);
											}
											else
												if(a.get(i).getName().equals("Prophet Velen")) {
													if(!(a.get(i).isSleeping())) {
												//	  ImageIcon l = new ImageIcon("images//Prophetvelen.PNG");
													  BufferedImage img2 = null;
													  	try {
													  	    img2 = ImageIO.read(new File("images//ProphetvelenF.PNG"));
													  	} catch (IOException e) {
													  	    e.printStackTrace();
													  	}
													  	
													  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
												    	        Image.SCALE_SMOOTH);
													  	ImageIcon imageIcon2 = new ImageIcon(Img2);
														b.get(i).setIcon(imageIcon2);
													  b.get(i).setText(a.get(i).getName());
										}else {
											BufferedImage img2 = null;
										  	try {
										  	    img2 = ImageIO.read(new File("images//ProphetvelenS.PNG"));
										  	} catch (IOException e) {
										  	    e.printStackTrace();
										  	}
										  	
										  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
									    	        Image.SCALE_SMOOTH);
										  	ImageIcon imageIcon2 = new ImageIcon(Img2);
											b.get(i).setIcon(imageIcon2);
										  b.get(i).setText(a.get(i).getName());
										}if(i == 0 && a.get(i).isDivine()) {
											view.getShield21().setVisible(true);
										}
										else 
										if(i==1 && a.get(i).isDivine()){
											view.getShield22().setVisible(true);
										}
										else
											if(i==2 && a.get(i).isDivine())
												view.getShield23().setVisible(true);
									    else
											if(i==3 &&  a.get(i).isDivine())
												view.getShield24().setVisible(true);
										else
											if(i==4 && a.get(i).isDivine())
												view.getShield25().setVisible(true);
										else
											if(i==5 && a.get(i).isDivine())
												view.getShield26().setVisible(true);
										else
											if(i==6 && a.get(i).isDivine())
												view.getShield27().setVisible(true);
										if(i == 0 && a.get(i).isDivine()==false ) {
											view.getShield21().setVisible(false);
										}
										else 
										if(i==1 && a.get(i).isDivine()==false){
											view.getShield22().setVisible(false);
										}
										else
											if(i==2 && a.get(i).isDivine() == false)
												view.getShield23().setVisible(false);
									    else
											if(i==3 &&  a.get(i).isDivine()==false)
												view.getShield24().setVisible(false);
										else
											if(i==4 && a.get(i).isDivine()==false)
												view.getShield25().setVisible(false);
										else
											if(i==5 && a.get(i).isDivine() == false)
												view.getShield26().setVisible(false);
										else
											if(i==6 && a.get(i).isDivine()==false)
												view.getShield27().setVisible(false);
												}
												else
													if(a.get(i).getName().equals("Wilfred Fizzlebang")) {
														if(!(a.get(i).isSleeping())) {
														//  ImageIcon l = new ImageIcon("images//Wilfredfizzlebang.PNG");
														  BufferedImage img2 = null;
														  	try {
														  	    img2 = ImageIO.read(new File("images//WilfredfizzlebangF.PNG"));
														  	} catch (IOException e) {
														  	    e.printStackTrace();
														  	}
														  	
														  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
													    	        Image.SCALE_SMOOTH);
														  	ImageIcon imageIcon2 = new ImageIcon(Img2);
															b.get(i).setIcon(imageIcon2);
														  b.get(i).setText(a.get(i).getName());
											}else {
												BufferedImage img2 = null;
											  	try {
											  	    img2 = ImageIO.read(new File("images//WilfredfizzlebangS.PNG"));
											  	} catch (IOException e) {
											  	    e.printStackTrace();
											  	}
											  	
											  	Image Img2 = img2.getScaledInstance( (int) 140, (int) 200,
										    	        Image.SCALE_SMOOTH);
											  	ImageIcon imageIcon2 = new ImageIcon(Img2);
												b.get(i).setIcon(imageIcon2);
											  b.get(i).setText(a.get(i).getName());
											}if(i == 0 && a.get(i).isDivine()) {
												view.getShield21().setVisible(true);
											}
											else 
											if(i==1 && a.get(i).isDivine()){
												view.getShield22().setVisible(true);
											}
											else
												if(i==2 && a.get(i).isDivine())
													view.getShield23().setVisible(true);
										    else
												if(i==3 &&  a.get(i).isDivine())
													view.getShield24().setVisible(true);
											else
												if(i==4 && a.get(i).isDivine())
													view.getShield25().setVisible(true);
											else
												if(i==5 && a.get(i).isDivine())
													view.getShield26().setVisible(true);
											else
												if(i==6 && a.get(i).isDivine())
													view.getShield27().setVisible(true);
											if(i == 0 && a.get(i).isDivine()==false ) {
												view.getShield21().setVisible(false);
											}
											else 
											if(i==1 && a.get(i).isDivine()==false){
												view.getShield22().setVisible(false);
											}
											else
												if(i==2 && a.get(i).isDivine() == false)
													view.getShield23().setVisible(false);
										    else
												if(i==3 &&  a.get(i).isDivine()==false)
													view.getShield24().setVisible(false);
											else
												if(i==4 && a.get(i).isDivine()==false)
													view.getShield25().setVisible(false);
											else
												if(i==5 && a.get(i).isDivine() == false)
													view.getShield26().setVisible(false);
											else
												if(i==6 && a.get(i).isDivine()==false)
													view.getShield27().setVisible(false);
													}
	

	}
		for(int j = i ; j < b.size();j++) {
			b.get(j).setIcon(null);
			b.get(j).setText("");
			if(j == 0  ) {
				view.getShield21().setVisible(false);
			}
			else 
			if(j==1 ){
				view.getShield22().setVisible(false);
			}
			else
				if(j==2 )
					view.getShield23().setVisible(false);
		    else
				if(j==3 )
					view.getShield24().setVisible(false);
			else
				if(j==4)
					view.getShield25().setVisible(false);
			else
				if(j==5 )
					view.getShield26().setVisible(false);
			else
				if(j==6)
					view.getShield27().setVisible(false);
		}
		}
set1();
set2();
	}
	public static void generate (ArrayList<Card> a , ArrayList<JButton> b ) {
		int i ;
		for( i = 0 ; i < a.size() ; i++) {
			if(a.get(i) instanceof Spell) {
				if(a.get(i) instanceof CurseOfWeakness) {
				//	ImageIcon l = new ImageIcon("images//Curseofweakness.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Curseofweakness.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 140,
				  			
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
		}
				else 
		   if(a.get(i) instanceof DivineSpirit) {
				 //   ImageIcon l = new ImageIcon("images//Divinespirit.png");
				    BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Divinespirit.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				    b.get(i).setText(a.get(i).getName());
			}else
			if(a.get(i) instanceof Flamestrike) {
				//	ImageIcon l = new ImageIcon("images//Flamestrike.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Flamestrike.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
			}else
			if(a.get(i) instanceof HolyNova) {
				//	ImageIcon l = new ImageIcon("images//Holynova.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Holynova.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
			}else
			if(a.get(i) instanceof KillCommand) {
				//	ImageIcon l = new ImageIcon("images//Killcommand.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Killcommand.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
			}else 
			if(a.get(i) instanceof LevelUp) {
				//	ImageIcon l = new ImageIcon("images//Levelup.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Levelup.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
			}else
			if(a.get(i) instanceof MultiShot) {
				//	ImageIcon l = new ImageIcon("images//Multishot.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Multishot.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
			}else
			if(a.get(i) instanceof Polymorph) {
				//	ImageIcon l = new ImageIcon("images//Polymorph.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Polymorph.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
					
			}else
			if(a.get(i) instanceof Pyroblast) {
				//	ImageIcon l = new ImageIcon("images//Pyroblast.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Pyroblast.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 83, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());	
				
		}else
			if(a.get(i) instanceof SealOfChampions) {
			//	ImageIcon l = new ImageIcon("images//Sealofchampions.png");
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//Sealofchampions.png"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
			  	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
			}		
		else
			if(a.get(i) instanceof ShadowWordDeath) {
			//	ImageIcon l = new ImageIcon("images//Shadowwordeath.png");
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//Shadowworddeath.png"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
			  	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				}
		else
			if(a.get(i) instanceof SiphonSoul) {
			//	ImageIcon l = new ImageIcon("images//Siphonsoul.png");
				BufferedImage img2 = null;
			  	try {
			  	    img2 = ImageIO.read(new File("images//Siphonsoul.png"));
			  	} catch (IOException e) {
			  	    e.printStackTrace();
			  	}
			  	
			  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
			  	        Image.SCALE_SMOOTH);
			  	ImageIcon imageIcon2 = new ImageIcon(Img2);
				b.get(i).setIcon(imageIcon2);
				b.get(i).setText(a.get(i).getName());
				}
	else
		if(a.get(i) instanceof TwistingNether) {
		//	ImageIcon l = new ImageIcon("images//Twistingnether.png");
			BufferedImage img2 = null;
		  	try {
		  	    img2 = ImageIO.read(new File("images//Twistingnether.png"));
		  	} catch (IOException e) {
		  	    e.printStackTrace();
		  	}
		  	
		  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
		  	        Image.SCALE_SMOOTH);
		  	ImageIcon imageIcon2 = new ImageIcon(Img2);
			b.get(i).setIcon(imageIcon2);
			b.get(i).setText(a.get(i).getName());
			}
		
			}
			else
				if(a.get(i) instanceof Minion) {
					if(a.get(i).getName().equals("Sunwalker")) {
						//ImageIcon l = new ImageIcon("images//Sunwalker.png");
						BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Sunwalker.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
						b.get(i).setText(a.get(i).getName());
						}
				else
				    if(a.get(i).getName().equals("Argent Commander")) {
					//	ImageIcon l = new ImageIcon("images//Argentcommander.png");
						BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Argentcommander.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
						b.get(i).setText(a.get(i).getName());
					}
				  else
					 if(a.get(i).getName().equals("Colossus of the Moon")) {
					//    ImageIcon l = new ImageIcon("images//Colossusofthemoon.png");
					    BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Colossusofthemoon.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					    b.get(i).setText(a.get(i).getName());
					}	
				else
					if(a.get(i).getName().equals("Icehowl")) {
						//  ImageIcon l = new ImageIcon("images//Icehowl.png");
						  BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//Icehowl.png"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
						  	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}	
			else
			if(a.get(i).getName().equals("The LichKing")) {
				//	 ImageIcon l = new ImageIcon("images//Thelichking.png");
					 BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Thelichking.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					 b.get(i).setText(a.get(i).getName());
				}
			else
			if(a.get(i).getName().equals("Chromaggus")) {
				//    ImageIcon l = new ImageIcon("images//Chromaggus.png");
				    BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Chromaggus.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
				    b.get(i).setText(a.get(i).getName());
				}
			else
			if(a.get(i).getName().equals("Core Hound")) {
				//	ImageIcon l = new ImageIcon("images//Corehound.png");
					BufferedImage img2 = null;
				  	try {
				  	    img2 = ImageIO.read(new File("images//Corehound.png"));
				  	} catch (IOException e) {
				  	    e.printStackTrace();
				  	}
				  	
				  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
				  	        Image.SCALE_SMOOTH);
				  	ImageIcon imageIcon2 = new ImageIcon(Img2);
					b.get(i).setIcon(imageIcon2);
					b.get(i).setText(a.get(i).getName());
				}
			else
				if(a.get(i).getName().equals("Boulderfist Ogre")) {
				//	  ImageIcon l = new ImageIcon("images//Boulderfistogre.png");
					  BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Boulderfistogre.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
					  b.get(i).setText(a.get(i).getName());
					}
			else
				if(a.get(i).getName().equals("Chilwind Yeti")) {
				//		ImageIcon l = new ImageIcon("images//Chillwindyeti.png");
						BufferedImage img2 = null;
					  	try {
					  	    img2 = ImageIO.read(new File("images//Chillwindyeti.png"));
					  	} catch (IOException e) {
					  	    e.printStackTrace();
					  	}
					  	
					  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
					  	        Image.SCALE_SMOOTH);
					  	ImageIcon imageIcon2 = new ImageIcon(Img2);
						b.get(i).setIcon(imageIcon2);
						b.get(i).setText(a.get(i).getName());
						}						
				else
					if(a.get(i).getName().equals("Wolfrider")) {
					//	  ImageIcon l = new ImageIcon("images//Wolfrider.png");
						  BufferedImage img2 = null;
						  	try {
						  	    img2 = ImageIO.read(new File("images//Wolfrider.png"));
						  	} catch (IOException e) {
						  	    e.printStackTrace();
						  	}
						  	
						  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
						  	        Image.SCALE_SMOOTH);
						  	ImageIcon imageIcon2 = new ImageIcon(Img2);
							b.get(i).setIcon(imageIcon2);
						  b.get(i).setText(a.get(i).getName());
						}
					else
						if(a.get(i).getName().equals("Frostwolf Grunt")) {
						//	  ImageIcon l = new ImageIcon("images//Frostwolfgrunt.png");
							  BufferedImage img2 = null;
							  	try {
							  	    img2 = ImageIO.read(new File("images//Frostwolfgrunt.png"));
							  	} catch (IOException e) {
							  	    e.printStackTrace();
							  	}
							  	
							  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
							  	        Image.SCALE_SMOOTH);
							  	ImageIcon imageIcon2 = new ImageIcon(Img2);
								b.get(i).setIcon(imageIcon2);
							  b.get(i).setText(a.get(i).getName());
							}
						else
							if(a.get(i).getName().equals("Bloodfen Raptor")) {
							//	  ImageIcon l = new ImageIcon("images//Bloodfenraptor.png");
								  BufferedImage img2 = null;
								  	try {
								  	    img2 = ImageIO.read(new File("images//Bloodfenraptor.png"));
								  	} catch (IOException e) {
								  	    e.printStackTrace();
								  	}
								  	
								  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
								  	        Image.SCALE_SMOOTH);
								  	ImageIcon imageIcon2 = new ImageIcon(Img2);
									b.get(i).setIcon(imageIcon2);
								  b.get(i).setText(a.get(i).getName());
								}
							else
								if(a.get(i).getName().equals("Stonetusk Boar")) {
									//  ImageIcon l = new ImageIcon("images//Stonetuskboar.png");
									  BufferedImage img2 = null;
									  	try {
									  	    img2 = ImageIO.read(new File("images//Stonetuskboar.png"));
									  	} catch (IOException e) {
									  	    e.printStackTrace();
									  	}
									  	
									  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
									  	        Image.SCALE_SMOOTH);
									  	ImageIcon imageIcon2 = new ImageIcon(Img2);
										b.get(i).setIcon(imageIcon2);
									  b.get(i).setText(a.get(i).getName());
									}else
											if(a.get(i).getName().equals("Goldshire Footman")) {
											//	  ImageIcon l = new ImageIcon("images//Goldshirefootman.png");
												  BufferedImage img2 = null;
												  	try {
												  	    img2 = ImageIO.read(new File("images//Goldshirefootman.png"));
												  	} catch (IOException e) {
												  	    e.printStackTrace();
												  	}
												  	
												  	Image Img2 = img2.getScaledInstance( (int) 81, (int) 130,
												  	        Image.SCALE_SMOOTH);
												  	ImageIcon imageIcon2 = new ImageIcon(Img2);
													b.get(i).setIcon(imageIcon2);
												  b.get(i).setText(a.get(i).getName());
									}
											else
												if(a.get(i).getName().equals("King Krush")) {
												//	  ImageIcon l = new ImageIcon("images//Kingkrush.png");
													  BufferedImage img2 = null;
													  	try {
													  	    img2 = ImageIO.read(new File("images//Kingkrush.png"));
													  	} catch (IOException e) {
													  	    e.printStackTrace();
													  	}
													  	
													  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
													  	        Image.SCALE_SMOOTH);
													  	ImageIcon imageIcon2 = new ImageIcon(Img2);
														b.get(i).setIcon(imageIcon2);
													  b.get(i).setText(a.get(i).getName());
										}
												else
													if(a.get(i).getName().equals("Kalycgos")) {
														//  ImageIcon l = new ImageIcon("images//Kalycgos.png");
														  BufferedImage img2 = null;
														  	try {
														  	    img2 = ImageIO.read(new File("images//Kalecgos.png"));
														  	} catch (IOException e) {
														  	    e.printStackTrace();
														  	}
														  	
														  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
														  	        Image.SCALE_SMOOTH);
														  	ImageIcon imageIcon2 = new ImageIcon(Img2);
															b.get(i).setIcon(imageIcon2);
														  b.get(i).setText(a.get(i).getName());
											}
													else
														if(a.get(i).getName().equals("Tirion Fordring")) {
															//  ImageIcon l = new ImageIcon("images//Tirionfordring.png");
															  BufferedImage img2 = null;
															  	try {
															  	    img2 = ImageIO.read(new File("images//Tirionfordring.png"));
															  	} catch (IOException e) {
															  	    e.printStackTrace();
															  	}
															  	
															  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
															  	        Image.SCALE_SMOOTH);
															  	ImageIcon imageIcon2 = new ImageIcon(Img2);
																b.get(i).setIcon(imageIcon2);
															  b.get(i).setText(a.get(i).getName());
												}
														else
															if(a.get(i).getName().equals("Prophet Velen")) {
															//	  ImageIcon l = new ImageIcon("images//Prophetvelen.png");
																  BufferedImage img2 = null;
																  	try {
																  	    img2 = ImageIO.read(new File("images//Prophetvelen.png"));
																  	} catch (IOException e) {
																  	    e.printStackTrace();
																  	}
																  	
																  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
																  	        Image.SCALE_SMOOTH);
																  	ImageIcon imageIcon2 = new ImageIcon(Img2);
																	b.get(i).setIcon(imageIcon2);
																  b.get(i).setText(a.get(i).getName());
													}
															else
																if(a.get(i).getName().equals("Wilfred Fizzlebang")) {
																	//  ImageIcon l = new ImageIcon("images//Wilfredfizzlebang.png");
																	  BufferedImage img2 = null;
																	  	try {
																	  	    img2 = ImageIO.read(new File("images//Wilfredfizzlebang.png"));
																	  	} catch (IOException e) {
																	  	    e.printStackTrace();
																	  	}
																	  	
																	  	Image Img2 = img2.getScaledInstance( (int) 78, (int) 130,
																	  	        Image.SCALE_SMOOTH);
																	  	ImageIcon imageIcon2 = new ImageIcon(Img2);
																		b.get(i).setIcon(imageIcon2);
																	  b.get(i).setText(a.get(i).getName());
														}
			
				}
			
		}
		for(int j = i ; j < b.size() ;j++) {
			b.get(j).setIcon(null);
			b.get(j).setText("");
		}
		
		
	}
    public  void mouseCard(int a) {
    	
			if(model.getCurrentHero().getHand().get(a) instanceof CurseOfWeakness ) {
			
		  	File ftestch = new File("images//Curseofweakness.gif");
		  	URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		    view.getChosen().setIcon(iconch);
		//  	view.getChosen().setOpaque(false);
		//    view.add(view.getChosen());
	}
			else 
	   if(model.getCurrentHero().getHand().get(a) instanceof DivineSpirit) {
			 //   ImageIcon l = new ImageIcon("images//Divinespirit.gif");
		   File ftestch = new File("images//Divinespirit.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		    view.getChosen().setIcon(iconch);
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof Flamestrike) {
			//	ImageIcon l = new ImageIcon("images//Flamestrike.gif");
			 File ftestch = new File("images//Flamestrike.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof HolyNova) {
			//	ImageIcon l = new ImageIcon("images//Holynova.gif");
			 File ftestch = new File("images//Holynova.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof KillCommand) {
			//	ImageIcon l = new ImageIcon("images//KillCommand.gif");
			 File ftestch = new File("images//KillCommand.gif");
			 URL imgch = null ;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}else 
		if(model.getCurrentHero().getHand().get(a) instanceof LevelUp) {
			//	ImageIcon l = new ImageIcon("images//Levelup.gif");
			 File ftestch = new File("images//Levelup.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof MultiShot) {
			//	ImageIcon l = new ImageIcon("images//Multishot.gif");
			 File ftestch = new File("images//Multishot.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof Polymorph) {
			//	ImageIcon l = new ImageIcon("images//Polymorph.gif");
			 File ftestch = new File("images//Polymorph.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
				
		}else
		if(model.getCurrentHero().getHand().get(a) instanceof Pyroblast) {
			//	ImageIcon l = new ImageIcon("images//Pyroblast.gif");
			 File ftestch = new File("images//Pyroblast.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			
	}else
		if(model.getCurrentHero().getHand().get(a) instanceof SealOfChampions) {
		//	ImageIcon l = new ImageIcon("images//Sealofchampions.gif");
			 File ftestch = new File("images//Sealofchampions.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
		}		
	else
		if(model.getCurrentHero().getHand().get(a) instanceof ShadowWordDeath) {
		//	ImageIcon l = new ImageIcon("images//Shadowworddeath.gif");
			 File ftestch = new File("images//Shadowworddeath.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			}
	else
		if(model.getCurrentHero().getHand().get(a) instanceof SiphonSoul) {
		//	ImageIcon l = new ImageIcon("images//Siphonsoul.gif");
			 File ftestch = new File("images//Siphonsoul.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			}
else
	if(model.getCurrentHero().getHand().get(a) instanceof TwistingNether) {
	//	ImageIcon l = new ImageIcon("images//Twistingnether.gif");
		 File ftestch = new File("images//Twistingnether.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		    view.getChosen().setIcon(iconch);
		}
	
		else
			if(model.getCurrentHero().getHand().get(a) instanceof Minion) {
				if(model.getCurrentHero().getHand().get(a).getName().equals("Sunwalker")) {
					//ImageIcon l = new ImageIcon("images//Sunwalker.gif");
					 File ftestch = new File("images//Sunwalker.gif");
					   URL imgch = null;
					  	try {
					  		imgch = ftestch.toURL();
					  	} catch (MalformedURLException e1) {
					  		e1.printStackTrace();
					  	}
					  	ImageIcon iconch = new ImageIcon(imgch);
					  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
					    view.getChosen().setIcon(iconch);
					}
			else
			    if(model.getCurrentHero().getHand().get(a).getName().equals("Argent Commander")) {
				//	ImageIcon l = new ImageIcon("images//Argentcommander.gif");
			    	 File ftestch = new File("images//Argentcommander.gif");
					   URL imgch = null;
					  	try {
					  		imgch = ftestch.toURL();
					  	} catch (MalformedURLException e1) {
					  		e1.printStackTrace();
					  	}
					  	ImageIcon iconch = new ImageIcon(imgch);
					  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
					    view.getChosen().setIcon(iconch);
				}
			  else
				 if(model.getCurrentHero().getHand().get(a).getName().equals("Colossus of the Moon")) {
				//    ImageIcon l = new ImageIcon("images//Colossusofthemoon.gif");
					 File ftestch = new File("images//Colossusofthemoon.gif");
					   URL imgch = null;
					  	try {
					  		imgch = ftestch.toURL();
					  	} catch (MalformedURLException e1) {
					  		e1.printStackTrace();
					  	}
					  	ImageIcon iconch = new ImageIcon(imgch);
					  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
					    view.getChosen().setIcon(iconch);
				}	
			else
				if(model.getCurrentHero().getHand().get(a).getName().equals("Icehowl")) {
					//  ImageIcon l = new ImageIcon("images//Icehowl.gif");
					 File ftestch = new File("images//Icehowl.gif");
					   URL imgch = null;
					  	try {
					  		imgch = ftestch.toURL();
					  	} catch (MalformedURLException e1) {
					  		e1.printStackTrace();
					  	}
					  	ImageIcon iconch = new ImageIcon(imgch);
					  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
					    view.getChosen().setIcon(iconch);
					}	
		else
		if(model.getCurrentHero().getHand().get(a).getName().equals("The LichKing")) {
			//	 ImageIcon l = new ImageIcon("images//Thelichking.gif");
			 File ftestch = new File("images//Thelichking.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			}
		else
		if(model.getCurrentHero().getHand().get(a).getName().equals("Chromaggus")) {
			//    ImageIcon l = new ImageIcon("images//Chromaggus.gif");
			 File ftestch = new File("images//Chromaggus.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			}
		else
		if(model.getCurrentHero().getHand().get(a).getName().equals("Core Hound")) {
			//	ImageIcon l = new ImageIcon("images//Corehound.gif");
			 File ftestch = new File("images//Corehound.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			    view.getChosen().setIcon(iconch);
			}
		else
			if(model.getCurrentHero().getHand().get(a).getName().equals("Boulderfist Ogre")) {
			//	  ImageIcon l = new ImageIcon("images//Boulderfistogre.gif");
				  BufferedImage img2 = null;
				  File ftestch = new File("images//Boulderfistogre.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				    view.getChosen().setIcon(iconch);
				}
		else
			if(model.getCurrentHero().getHand().get(a).getName().equals("Chilwind Yeti")) {
			//		ImageIcon l = new ImageIcon("images//Chillwindyeti.gif");
				 File ftestch = new File("images//Chillwindyeti.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				    view.getChosen().setIcon(iconch);
					}						
			else
				if(model.getCurrentHero().getHand().get(a).getName().equals("Wolfrider")) {
				//	  ImageIcon l = new ImageIcon("images//Wolfrider.gif");
					  		 File ftestch = new File("images//Wolfrider.gif");
							   URL imgch = null;
							  	try {
							  		imgch = ftestch.toURL();
							  	} catch (MalformedURLException e1) {
							  		e1.printStackTrace();
							  	}
							  	ImageIcon iconch = new ImageIcon(imgch);
							  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
							    view.getChosen().setIcon(iconch);
					}
				else
					if(model.getCurrentHero().getHand().get(a).getName().equals("Frostwolf Grunt")) {
					//	  ImageIcon l = new ImageIcon("images//Frostwolfgrunt.gif");
						 File ftestch = new File("images//Frostwolfgrunt.gif");
						   URL imgch = null;
						  	try {
						  		imgch = ftestch.toURL();
						  	} catch (MalformedURLException e1) {
						  		e1.printStackTrace();
						  	}
						  	ImageIcon iconch = new ImageIcon(imgch);
						  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
						    view.getChosen().setIcon(iconch);
						}
					else
						if(model.getCurrentHero().getHand().get(a).getName().equals("Bloodfen Raptor")) {
						//	  ImageIcon l = new ImageIcon("images//Bloodfenraptor.gif");
							 File ftestch = new File("images//Bloodfenraptor.gif");
							   URL imgch = null;
							  	try {
							  		imgch = ftestch.toURL();
							  	} catch (MalformedURLException e1) {
							  		e1.printStackTrace();
							  	}
							  	ImageIcon iconch = new ImageIcon(imgch);
							  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
							    view.getChosen().setIcon(iconch);
							}
						else
							if(model.getCurrentHero().getHand().get(a).getName().equals("Stonetusk Boar")) {
								//  ImageIcon l = new ImageIcon("images//Stonetuskboar.gif");
								 File ftestch = new File("images//Stonetuskboar.gif");
								   URL imgch = null;
								  	try {
								  		imgch = ftestch.toURL();
								  	} catch (MalformedURLException e1) {
								  		e1.printStackTrace();
								  	}
								  	ImageIcon iconch = new ImageIcon(imgch);
								  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
								    view.getChosen().setIcon(iconch);
								}else
										if(model.getCurrentHero().getHand().get(a).getName().equals("Goldshire Footman")) {
										//	  ImageIcon l = new ImageIcon("images//Goldshirefootman.gif");
											 File ftestch = new File("images//Goldshirefootman.gif");
											   URL imgch = null;
											  	try {
											  		imgch = ftestch.toURL();
											  	} catch (MalformedURLException e1) {
											  		e1.printStackTrace();
											  	}
											  	ImageIcon iconch = new ImageIcon(imgch);
											  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
											    view.getChosen().setIcon(iconch);
								}
										else
											if(model.getCurrentHero().getHand().get(a).getName().equals("King Krush")) {
											//	  ImageIcon l = new ImageIcon("images//Kingkrush.gif");
												 File ftestch = new File("images//Kingkrush.gif");
												   URL imgch = null;
												  	try {
												  		imgch = ftestch.toURL();
												  	} catch (MalformedURLException e1) {
												  		e1.printStackTrace();
												  	}
												  	ImageIcon iconch = new ImageIcon(imgch);
												  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
												    view.getChosen().setIcon(iconch);
									}
											else
												if(model.getCurrentHero().getHand().get(a).getName().equals("Kalycgos")) {
													//  ImageIcon l = new ImageIcon("images//Kalycgos.gif");
													 File ftestch = new File("images//Kalecgos.gif");
													   URL imgch = null;
													  	try {
													  		imgch = ftestch.toURL();
													  	} catch (MalformedURLException e1) {
													  		e1.printStackTrace();
													  	}
													  	ImageIcon iconch = new ImageIcon(imgch);
													  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
													    view.getChosen().setIcon(iconch);
										}
												else
													if(model.getCurrentHero().getHand().get(a).getName().equals("Tirion Fordring")) {
														//  ImageIcon l = new ImageIcon("images//Tirionfordring.gif");
														 File ftestch = new File("images//Tirionfordring.gif");
														   URL imgch = null;
														  	try {
														  		imgch = ftestch.toURL();
														  	} catch (MalformedURLException e1) {
														  		e1.printStackTrace();
														  	}
														  	ImageIcon iconch = new ImageIcon(imgch);
														  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
														    view.getChosen().setIcon(iconch);
											}
													else
														if(model.getCurrentHero().getHand().get(a).getName().equals("Prophet Velen")) {
														//	  ImageIcon l = new ImageIcon("images//Prophetvelen.gif");
															 File ftestch = new File("images//Prophetvelen.gif");
															   URL imgch = null;
															  	try {
															  		imgch = ftestch.toURL();
															  	} catch (MalformedURLException e1) {
															  		e1.printStackTrace();
															  	}
															  	ImageIcon iconch = new ImageIcon(imgch);
															  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
															    view.getChosen().setIcon(iconch);
												}
														else
															if(model.getCurrentHero().getHand().get(a).getName().equals("Wilfred Fizzlebang")) {
																//  ImageIcon l = new ImageIcon("images//Wilfredfizzlebang.gif");
																 File ftestch = new File("images//Wilfredfizzlebang.gif");
																   URL imgch = null;
																  	try {
																  		imgch = ftestch.toURL();
																  	} catch (MalformedURLException e1) {
																  		e1.printStackTrace();
																  	}
																  	ImageIcon iconch = new ImageIcon(imgch);
																  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
																    view.getChosen().setIcon(iconch);
													}
		
			}
		


}
    public  Icon getGif(Card a) {
		if(a instanceof CurseOfWeakness) {
		
	  	File ftestch = new File("images//Curseofweakness.gif");
	  	URL imgch = null;
	  	try {
	  		imgch = ftestch.toURL();
	  	} catch (MalformedURLException e1) {
	  		e1.printStackTrace();
	  	}
	  	ImageIcon iconch = new ImageIcon(imgch);
	  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
	    return iconch ;
	//  	view.getChosen().setOpaque(false);
	//    view.add(view.getChosen());
}
		else 
   if(a instanceof DivineSpirit) {
		 //   ImageIcon l = new ImageIcon("images//Divinespirit.gif");
	   File ftestch = new File("images//Divinespirit.gif");
	   URL imgch = null;
	  	try {
	  		imgch = ftestch.toURL();
	  	} catch (MalformedURLException e1) {
	  		e1.printStackTrace();
	  	}
	  	ImageIcon iconch = new ImageIcon(imgch);
	  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
	    return iconch;
	}else
	if(a instanceof Flamestrike) {
		//	ImageIcon l = new ImageIcon("images//Flamestrike.gif");
		 File ftestch = new File("images//Flamestrike.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}else
	if(a instanceof HolyNova) {
		//	ImageIcon l = new ImageIcon("images//Holynova.gif");
		 File ftestch = new File("images//Holynova.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}else
	if(a instanceof KillCommand) {
		//	ImageIcon l = new ImageIcon("images//KillCommand.gif");
		 File ftestch = new File("images//KillCommand.gif");
		 URL imgch = null ;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}else 
	if(a instanceof LevelUp) {
		//	ImageIcon l = new ImageIcon("images//Levelup.gif");
		 File ftestch = new File("images//Levelup.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}else
	if(a instanceof MultiShot) {
		//	ImageIcon l = new ImageIcon("images//Multishot.gif");
		 File ftestch = new File("images//Multishot.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}else
	if(a instanceof Polymorph) {
		//	ImageIcon l = new ImageIcon("images//Polymorph.gif");
		 File ftestch = new File("images//Polymorph.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
			
	}else
	if(a instanceof Pyroblast) {
		//	ImageIcon l = new ImageIcon("images//Pyroblast.gif");
		 File ftestch = new File("images//Pyroblast.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		
}else
	if(a instanceof SealOfChampions) {
	//	ImageIcon l = new ImageIcon("images//Sealofchampions.gif");
		 File ftestch = new File("images//Sealofchampions.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
	}		
else
	if(a instanceof ShadowWordDeath) {
	//	ImageIcon l = new ImageIcon("images//Shadowworddeath.gif");
		 File ftestch = new File("images//Shadowworddeath.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		}
else
	if(a instanceof SiphonSoul) {
	//	ImageIcon l = new ImageIcon("images//Siphonsoul.gif");
		 File ftestch = new File("images//Siphonsoul.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		}
else
if(a instanceof TwistingNether) {
//	ImageIcon l = new ImageIcon("images//Twistingnether.gif");
	 File ftestch = new File("images//Twistingnether.gif");
	   URL imgch = null;
	  	try {
	  		imgch = ftestch.toURL();
	  	} catch (MalformedURLException e1) {
	  		e1.printStackTrace();
	  	}
	  	ImageIcon iconch = new ImageIcon(imgch);
	  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
	  	return iconch;
	}

	else
		if(a instanceof Minion) {
			if(a.getName().equals("Sunwalker")) {
				//ImageIcon l = new ImageIcon("images//Sunwalker.gif");
				 File ftestch = new File("images//Sunwalker.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				  	return iconch;
				}
		else
		    if(a.getName().equals("Argent Commander")) {
			//	ImageIcon l = new ImageIcon("images//Argentcommander.gif");
		    	 File ftestch = new File("images//Argentcommander.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				  	return iconch;
			}
		  else
			 if(a.getName().equals("Colossus Of The Moon")) {
			//    ImageIcon l = new ImageIcon("images//Colossusofthemoon.gif");
				 File ftestch = new File("images//Colossusofthemoon.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				  	return iconch;
			}	
		else
			if(a.getName().equals("Icehowl")) {
				//  ImageIcon l = new ImageIcon("images//Icehowl.gif");
				 File ftestch = new File("images//Icehowl.gif");
				   URL imgch = null;
				  	try {
				  		imgch = ftestch.toURL();
				  	} catch (MalformedURLException e1) {
				  		e1.printStackTrace();
				  	}
				  	ImageIcon iconch = new ImageIcon(imgch);
				  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
				  	return iconch;
				}	
	else
	if(a.getName().equals("The LichKing")) {
		//	 ImageIcon l = new ImageIcon("images//Thelichking.gif");
		 File ftestch = new File("images//Thelichking.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		}
	else
	if(a.getName().equals("Chromaggus")) {
		//    ImageIcon l = new ImageIcon("images//Chromaggus.gif");
		 File ftestch = new File("images//Chromaggus.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		}
	else
	if(a.getName().equals("Core Hound")) {
		//	ImageIcon l = new ImageIcon("images//Corehound.gif");
		 File ftestch = new File("images//Corehound.gif");
		   URL imgch = null;
		  	try {
		  		imgch = ftestch.toURL();
		  	} catch (MalformedURLException e1) {
		  		e1.printStackTrace();
		  	}
		  	ImageIcon iconch = new ImageIcon(imgch);
		  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
		  	return iconch;
		}
	else
		if(a.getName().equals("Boulderfist Ogre")) {
		//	  ImageIcon l = new ImageIcon("images//Boulderfistogre.gif");
			  BufferedImage img2 = null;
			  File ftestch = new File("images//Boulderfistogre.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			  	return iconch;
			}
	else
		if(a.getName().equals("Chilwind Yeti")) {
		//		ImageIcon l = new ImageIcon("images//Chillwindyeti.gif");
			 File ftestch = new File("images//Chillwindyeti.gif");
			   URL imgch = null;
			  	try {
			  		imgch = ftestch.toURL();
			  	} catch (MalformedURLException e1) {
			  		e1.printStackTrace();
			  	}
			  	ImageIcon iconch = new ImageIcon(imgch);
			  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
			  	return iconch;
				}						
		else
			if(a.getName().equals("Wolfrider")) {
			//	  ImageIcon l = new ImageIcon("images//Wolfrider.gif");
				  		 File ftestch = new File("images//Wolfrider.gif");
						   URL imgch = null;
						  	try {
						  		imgch = ftestch.toURL();
						  	} catch (MalformedURLException e1) {
						  		e1.printStackTrace();
						  	}
						  	ImageIcon iconch = new ImageIcon(imgch);
						  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
						  	return iconch;
				}
			else
				if(a.getName().equals("Frostwolf Grunt")) {
				//	  ImageIcon l = new ImageIcon("images//Frostwolfgrunt.gif");
					 File ftestch = new File("images//Frostwolfgrunt.gif");
					   URL imgch = null;
					  	try {
					  		imgch = ftestch.toURL();
					  	} catch (MalformedURLException e1) {
					  		e1.printStackTrace();
					  	}
					  	ImageIcon iconch = new ImageIcon(imgch);
					  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
					  	return iconch;
					}
				else
					if(a.getName().equals("Bloodfen Raptor")) {
					//	  ImageIcon l = new ImageIcon("images//Bloodfenraptor.gif");
						 File ftestch = new File("images//Bloodfenraptor.gif");
						   URL imgch = null;
						  	try {
						  		imgch = ftestch.toURL();
						  	} catch (MalformedURLException e1) {
						  		e1.printStackTrace();
						  	}
						  	ImageIcon iconch = new ImageIcon(imgch);
						  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
						  	return iconch;
						}
					else
						if(a.getName().equals("Stonetusk Boar")) {
							//  ImageIcon l = new ImageIcon("images//Stonetuskboar.gif");
							 File ftestch = new File("images//Stonetuskboar.gif");
							   URL imgch = null;
							  	try {
							  		imgch = ftestch.toURL();
							  	} catch (MalformedURLException e1) {
							  		e1.printStackTrace();
							  	}
							  	ImageIcon iconch = new ImageIcon(imgch);
							  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
							  	return iconch;
							}else
									if(a.getName().equals("Goldshire Footman")) {
									//	  ImageIcon l = new ImageIcon("images//Goldshirefootman.gif");
										 File ftestch = new File("images//Goldshirefootman.gif");
										   URL imgch = null;
										  	try {
										  		imgch = ftestch.toURL();
										  	} catch (MalformedURLException e1) {
										  		e1.printStackTrace();
										  	}
										  	ImageIcon iconch = new ImageIcon(imgch);
										  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
										  	return iconch;
							}
									else
										if(a.getName().equals("King Krush")) {
										//	  ImageIcon l = new ImageIcon("images//Kingkrush.gif");
											 File ftestch = new File("images//Kingkrush.gif");
											   URL imgch = null;
											  	try {
											  		imgch = ftestch.toURL();
											  	} catch (MalformedURLException e1) {
											  		e1.printStackTrace();
											  	}
											  	ImageIcon iconch = new ImageIcon(imgch);
											  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
											  	return iconch;
								}
										else
											if(a.getName().equals("Kalycgos")) {
												//  ImageIcon l = new ImageIcon("images//Kalycgos.gif");
												 File ftestch = new File("images//Kalecgos.gif");
												   URL imgch = null;
												  	try {
												  		imgch = ftestch.toURL();
												  	} catch (MalformedURLException e1) {
												  		e1.printStackTrace();
												  	}
												  	ImageIcon iconch = new ImageIcon(imgch);
												  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
												  	return iconch;
									}
											else
												if(a.getName().equals("Tirion Fordring")) {
													//  ImageIcon l = new ImageIcon("images//Tirionfordring.gif");
													 File ftestch = new File("images//Tirionfordring.gif");
													   URL imgch = null;
													  	try {
													  		imgch = ftestch.toURL();
													  	} catch (MalformedURLException e1) {
													  		e1.printStackTrace();
													  	}
													  	ImageIcon iconch = new ImageIcon(imgch);
													  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
													  	return iconch;
										}
												else
													if(a.getName().equals("Prophet Velen")) {
													//	  ImageIcon l = new ImageIcon("images//Prophetvelen.gif");
														 File ftestch = new File("images//Prophetvelen.gif");
														   URL imgch = null;
														  	try {
														  		imgch = ftestch.toURL();
														  	} catch (MalformedURLException e1) {
														  		e1.printStackTrace();
														  	}
														  	ImageIcon iconch = new ImageIcon(imgch);
														  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
														  	return iconch;
											}
													else
														if(a.getName().equals("Wilfred Fizzlebang")) {
															//  ImageIcon l = new ImageIcon("images//Wilfredfizzlebang.gif");
															 File ftestch = new File("images//Wilfredfizzlebang.gif");
															   URL imgch = null;
															  	try {
															  		imgch = ftestch.toURL();
															  	} catch (MalformedURLException e1) {
															  		e1.printStackTrace();
															  	}
															  	ImageIcon iconch = new ImageIcon(imgch);
															  	iconch.setImage(iconch.getImage().getScaledInstance(235, 353,Image.SCALE_DEFAULT));
															  	return iconch;
												}
	
		}
		return null;
}
	@Override
	public void onGameOver() {
      if(model.getCurrentHero().getCurrentHP() <= 0) {
    	  if(model.getCurrentHero().equals(model.getFirstHero()))
    	  endview = new EndView( view.getHero1().getIcon() ,"Player 1" ,cont , c , l);
    	  if(model.getCurrentHero().equals(model.getSecondHero()))
        	  endview = new EndView( view.getHero2().getIcon() ,"Player 2" ,cont , c , l);
    	//  view.setVisible(false);
    	  cont.add(endview.getContentPane());
    //	  view.setContentPane(endview.getContentPane());
    //	  endview.setVisible(true);
    	  c.next(cont);
      }
      else {
    	  if(model.getCurrentHero().equals(model.getFirstHero()))
        	  endview = new EndView( view.getHero2().getIcon() ,"Player 1" ,cont , c , l);
        	  if(model.getCurrentHero().equals(model.getSecondHero()))
            	  endview = new EndView( view.getHero2().getIcon() ,"Player 2" ,cont , c , l);
    //	  view.setVisible(false);
    //	  view.setContentPane(endview.getContentPane());
    //	  endview.setVisible(true);
    	  cont.add(endview.getContentPane());
    	  c.next(cont);
      }
	}

	
	
	public void setModel(Game model) {
		this.model = model;
	}
	public GameView getView() {
		return view;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
      
		JButton p = (JButton)e.getSource();
     Timer	timer2 = new Timer(400, new ActionListener(){ 
			
			public void actionPerformed(ActionEvent arg0) {
				removeRed();
				
			}
	        });
		
		if(SelectedMinion == p ) {
			SelectedMinion = null ;
		}if(Power == p)
			Power = null ;
              else if(SelectedMinion1 == p) {
                     SelectedMinion1 = null ;
                     if(currentherosField.indexOf(p)==0)
  			    	   view.getRed11().setVisible(false);
  			       if(currentherosField.indexOf(p)==1)
  			    	   view.getRed12().setVisible(false);
  			       if(currentherosField.indexOf(p)==2)
  			    	   view.getRed13().setVisible(false);
  			       if(currentherosField.indexOf(p)==3)
  			    	   view.getRed14().setVisible(false);
  			       if(currentherosField.indexOf(p)==4)
  			    	   view.getRed15().setVisible(false);
  			       if(currentherosField.indexOf(p)==5)
  			    	   view.getRed16().setVisible(false);
  			       if(currentherosField.indexOf(p)==6)
  			    	   view.getRed17().setVisible(false);
              }
              else if(Power != null && (currentherosField.contains(p) || opponentsField.contains(p) || p.getActionCommand().equals("Current") 
            		  || p.getActionCommand().equals("Opponent")) && p.getIcon() != null){
            	  if(model.getCurrentHero() instanceof Mage && (p.getActionCommand().equals("Current"))) {
            	      try {
						((Mage)(model.getCurrentHero())).useHeroPower(model.getCurrentHero());
					} catch (NotEnoughManaException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
					}
            	  }
            		  else 
            			  if(model.getCurrentHero() instanceof Mage && (p.getActionCommand().equals("Opponent"))) {
            			  
            			  try {
      						((Mage)(model.getCurrentHero())).useHeroPower(model.getOpponent());
      					} catch (NotEnoughManaException e1) {
      						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
      					} catch (HeroPowerAlreadyUsedException e1) {
      						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
      					} catch (NotYourTurnException e1) {
      						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
      					} catch (FullHandException e1) {
      						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
      					} catch (FullFieldException e1) {
      						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
      					} catch (CloneNotSupportedException e1) {
      						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
      					}
            		  }else
            		  if(model.getCurrentHero() instanceof Mage  && (currentherosField.contains(p))) {
                	      try {
    						((Mage)(model.getCurrentHero())).useHeroPower(model.getCurrentHero().getField().get(currentherosField.indexOf(p)));
    					} catch (NotEnoughManaException e1) {
    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
    					} catch (HeroPowerAlreadyUsedException e1) {
    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
    					} catch (NotYourTurnException e1) {
    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
    					} catch (FullHandException e1) {
    						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
    					} catch (FullFieldException e1) {
    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
    					} catch (CloneNotSupportedException e1) {
    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
    					}
            		  }
                		  else 
                			  if(model.getCurrentHero() instanceof Mage  && (opponentsField.contains(p))) {
                			  try {
          						((Mage)(model.getCurrentHero())).useHeroPower(model.getOpponent().getField().get(opponentsField.indexOf(p)));
          					} catch (NotEnoughManaException e1) {
          						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
          					} catch (HeroPowerAlreadyUsedException e1) {
          						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
          					} catch (NotYourTurnException e1) {
          						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
          					} catch (FullHandException e1) {
          						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
          					} catch (FullFieldException e1) {
          						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
          					} catch (CloneNotSupportedException e1) {
          						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
          					}
                		  }
                			  else
                				  if(model.getCurrentHero() instanceof Priest && (p.getActionCommand().equals("Current"))) {
                        	      try {
              						((Priest)(model.getCurrentHero())).useHeroPower(model.getCurrentHero());
              					} catch (NotEnoughManaException e1) {
              						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
              					} catch (HeroPowerAlreadyUsedException e1) {
              						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
              					} catch (NotYourTurnException e1) {
              						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
              					} catch (FullHandException e1) {
              						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
              					} catch (FullFieldException e1) {
              						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
              					} catch (CloneNotSupportedException e1) {
              						// TODO Auto-generated catch block
              						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
              					}
                        	      System.out.print(true);
                          	  }
                          		  else 
                          			  if(model.getCurrentHero() instanceof Priest && (p.getActionCommand().equals("Opponent"))) {
                          			  
                          			  try {
                    						((Priest)(model.getCurrentHero())).useHeroPower(model.getOpponent());
                    				
                    					} catch (NotEnoughManaException e1) {
                    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    					} catch (HeroPowerAlreadyUsedException e1) {
                    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    					} catch (NotYourTurnException e1) {
                    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    					} catch (FullHandException e1) {
                    						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
                    					} catch (FullFieldException e1) {
                    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);;
                    					} catch (CloneNotSupportedException e1) {
                    						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    					}
    
                          		  }else
                          		  if(model.getCurrentHero() instanceof Priest  && (currentherosField.contains(p))) {
                              	      try {
                  						((Priest)(model.getCurrentHero())).useHeroPower(model.getCurrentHero().getField().get(currentherosField.indexOf(p)));
                  					} catch (NotEnoughManaException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					} catch (HeroPowerAlreadyUsedException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					} catch (NotYourTurnException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					} catch (FullHandException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					} catch (FullFieldException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					} catch (CloneNotSupportedException e1) {
                  						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                  					}
                              	    System.out.print(true);
                          		  }
                              		  else 
                              			  if(model.getCurrentHero() instanceof Priest  && (opponentsField.contains(p))) {
                              			  try {
                        						((Priest)(model.getCurrentHero())).useHeroPower(model.getOpponent().getField().get(opponentsField.indexOf(p)));
                        					} catch (NotEnoughManaException e1) {
                        						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                        					} catch (HeroPowerAlreadyUsedException e1) {
                        						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                        					} catch (NotYourTurnException e1) {
                        						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                        				
                        					} catch (FullHandException e1) {
                        						JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
                        					} catch (FullFieldException e1) {
                        						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                        					} catch (CloneNotSupportedException e1) {
                        						JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                        					}
                              			  System.out.print(true);
                              		  }
            	  Power = null ;
            	  }
            	  
              else
            	  if(opponentsField.contains(p) && currentherosField.contains(SelectedMinion1) && SelectedMinion1.getIcon()!= null && p.getIcon() != null ) {		
       			   int a = opponentsField.indexOf(p);
       			   int b = currentherosField.indexOf(SelectedMinion1);
       		       if(a==0)
       		    	   view.getRed21().setVisible(true);
       		       if(a==1)
       		    	   view.getRed22().setVisible(true);
       		       if(a==2)
       		    	   view.getRed23().setVisible(true);
       		       if(a==3)
       		    	   view.getRed24().setVisible(true);
       		       if(a==4)
       		    	   view.getRed25().setVisible(true);
       		       if(a==5)
       		    	   view.getRed26().setVisible(true);
       		       if(a==6)
       		    	   view.getRed27().setVisible(true);
       		       
       			   try {
       				model.getCurrentHero().attackWithMinion(model.getCurrentHero().getField().get(b), model.getOpponent().getField().get(a));
       			} catch (CannotAttackException e1) {
       				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
       			} catch (NotYourTurnException e1) {
       				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
       			} catch (TauntBypassException e1) {
       				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
       			} catch (InvalidTargetException e1) {
       				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
       			} catch (NotSummonedException e1) {
       				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
       			}
       			   timer2.start();
       				SelectedMinion1 = null ;
                    SelectedMinion = null ;
              //      if(a==0)
        	//	    	   view.getRed21().setVisible(false);
        	//	       if(a==1)
        	//	    	   view.getRed22().setVisible(false);
        	//	       if(a==2)
        	//	    	   view.getRed23().setVisible(false);
        	//	       if(a==3)
        	//	    	   view.getRed24().setVisible(false);
        	//	       if(a==4)
        	//	    	   view.getRed25().setVisible(false);
        	//	       if(a==5)
        	//	    	   view.getRed26().setVisible(false);
        	//	       if(a==6)
        	//	    	   view.getRed27().setVisible(false);
       			}else
		if(currentheros.contains(p) && p.getIcon() != null && model.getCurrentHero().getHand().get(currentheros.indexOf(p)) instanceof Minion ) {		
		   int b = currentheros.indexOf(p);
		   boolean flag=true;
		   Minion M =  (Minion) model.getCurrentHero().getHand().get(b);
		   System.out.println(b);
			   try {
				flag = true;
				model.getCurrentHero().playMinion(M);
			   
			   }
				
			 catch (NotYourTurnException e1) {
				 flag=false;
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotEnoughManaException e1) {
				flag= false;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (FullFieldException e1) {
				flag = false;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			   if(M.getName().equals("Argent Commander") && flag==true) {
  					File soundFile1 = new File("images//ArgentcommanderA.wav");
  					AudioInputStream audioIn1 = null;
  					try {
  						audioIn1 = AudioSystem.getAudioInputStream(soundFile1);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip1 = null;
  					try {
  						clip1= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip1.open(audioIn1);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip1.loop(0);
  					clip1.start(); 
  					
  					
  				}	if(M.getName().equals("Frostwolf Grunt") && flag==true) {
  					File soundFile2 = new File("images//FrostwolfgruntA.wav");
  					AudioInputStream audioIn2 = null;
  					try {
  						audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip2 = null;
  					try {
  						clip2= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip2.open(audioIn2);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip2.loop(0);
  					clip2.start(); 
  					
  					
  				}	if(M.getName().equals("Goldshire Footman")&& flag==true) {
  					File soundFile3 = new File("images//GoldshireFootmanA.wav");
  					AudioInputStream audioIn3= null;
  					try {
  						audioIn3 = AudioSystem.getAudioInputStream(soundFile3);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip3 = null;
  					try {
  						clip3= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip3.open(audioIn3);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip3.loop(0);
  					clip3.start(); 
  					
  					
  				}	if(M.getName().equals("Icehowl")&& flag==true) {
  					File soundFile4 = new File("images//IcehowlA.wav");
  					AudioInputStream audioIn4 = null;
  					try {
  						audioIn4 = AudioSystem.getAudioInputStream(soundFile4);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip4 = null;
  					try {
  						clip4= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip4.open(audioIn4);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip4.loop(0);
  					clip4.start(); 
  					
  					
  				}	if(M.getName().equals("Kalycgos")&& flag==true) {
  					File soundFile5 = new File("images//KalecgosA.wav");
  					AudioInputStream audioIn5 = null;
  					try {
  						audioIn5 = AudioSystem.getAudioInputStream(soundFile5);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip5 = null;
  					try {
  						clip5= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip5.open(audioIn5);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip5.loop(0);
  					clip5.start(); 
  					
  					
  				}	if(M.getName().equals("King Krush")&& flag==true) {
  					File soundFile6 = new File("images//KingkrushA.wav");
  					AudioInputStream audioIn6 = null;
  					try {
  						audioIn6 = AudioSystem.getAudioInputStream(soundFile6);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip6 = null;
  					try {
  						clip6= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip6.open(audioIn6);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip6.loop(0);
  					clip6.start(); 
  					
  					
  				}	if(M.getName().equals("Prophet Velen")&& flag==true) {
  					File soundFile7 = new File("images//ProphetvelenA.wav");
  					AudioInputStream audioIn7 = null;
  					try {
  						audioIn7 = AudioSystem.getAudioInputStream(soundFile7);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip7 = null;
  					try {
  						clip7= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip7.open(audioIn7);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip7.loop(0);
  					clip7.start(); 
  					
  					
  				}	if(M.getName().equals("Stonetusk Boar")&& flag==true) {
  					File soundFile8 = new File("images//StonetuskboarA.wav");
  					AudioInputStream audioIn8 = null;
  					try {
  						audioIn8 = AudioSystem.getAudioInputStream(soundFile8);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip8 = null;
  					try {
  						clip8= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip8.open(audioIn8);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip8.loop(0);
  					clip8.start(); 
  					
  					
  				}	if(M.getName().equals("Sunwalker")&& flag==true) {
  					File soundFile9 = new File("images//SunwalkerA.wav");
  					AudioInputStream audioIn9 = null;
  					try {
  						audioIn9 = AudioSystem.getAudioInputStream(soundFile9);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip9 = null;
  					try {
  						clip9= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip9.open(audioIn9);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip9.loop(0);
  					clip9.start(); 
  					
  					
  				}	if(M.getName().equals("Bloodfen Raptor")&& flag==true) {
  					File soundFile10 = new File("images//BloodfenraptorA.wav");
  					AudioInputStream audioIn10 = null;
  					try {
  						audioIn10 = AudioSystem.getAudioInputStream(soundFile10);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip10 = null;
  					try {
  						clip10= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip10.open(audioIn10);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip10.loop(0);
  					clip10.start(); 
  					
  					
  				}	if(M.getName().equals("Boulderfist Ogre")&& flag==true) {
  					File soundFile11= new File("images//BoulderfistogreA.wav");
  					AudioInputStream audioIn11 = null;
  					try {
  						audioIn11 = AudioSystem.getAudioInputStream(soundFile11);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip11 = null;
  					try {
  						clip11= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip11.open(audioIn11);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip11.loop(0);
  					clip11.start(); 
  					
  					
  				}	if(M.getName().equals("Chilwind Yeti")&& flag==true) {
  					File soundFile12 = new File("images//ChillwindyetiA.wav");
  					AudioInputStream audioIn12 = null;
  					try {
  						audioIn12 = AudioSystem.getAudioInputStream(soundFile12);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip12 = null;
  					try {
  						clip12= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip12.open(audioIn12);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip12.loop(0);
  					clip12.start(); 
  					
  					
  				}	if(M.getName().equals("Chromaggus")&& flag==true) {
  					File soundFile13 = new File("images//ChromaggusA.wav");
  					AudioInputStream audioIn13 = null;
  					try {
  						audioIn13 = AudioSystem.getAudioInputStream(soundFile13);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip13 = null;
  					try {
  						clip13= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip13.open(audioIn13);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip13.loop(0);
  					clip13.start(); 
  					
  					
  				}	if(M.getName().equals("Colossus of the Moon")&& flag==true) {
  					File soundFile14 = new File("images//ColossusofthemoonA.wav");
  					AudioInputStream audioIn14 = null;
  					try {
  						audioIn14 = AudioSystem.getAudioInputStream(soundFile14);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip14 = null;
  					try {
  						clip14= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip14.open(audioIn14);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip14.loop(0);
  					clip14.start(); 
  					
  					
  				}	if(M.getName().equals("Core Hound")&& flag==true) {
  					File soundFile15 = new File("images//CorehoundA.wav");
  					AudioInputStream audioIn15 = null;
  					try {
  						audioIn15 = AudioSystem.getAudioInputStream(soundFile15);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip15 = null;
  					try {
  						clip15= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip15.open(audioIn15);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip15.loop(0);
  					clip15.start(); 
  					
  					
  				}if(M.getName().equals("The LichKing")&& flag==true) {
  					File soundFile16 = new File("images//ThelichkingA.wav");
  					AudioInputStream audioIn16 = null;
  					try {
  						audioIn16 = AudioSystem.getAudioInputStream(soundFile16);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip16 = null;
  					try {
  						clip16= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip16.open(audioIn16);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip16.loop(0);
  					clip16.start(); 
  					
  					
  				}if(M.getName().equals("Tirion Fordring")&& flag==true) {
  					File soundFile17 = new File("images//TirionfordringA.wav");
  					AudioInputStream audioIn17 = null;
  					try {
  						audioIn17 = AudioSystem.getAudioInputStream(soundFile17);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip17 = null;
  					try {
  						clip17= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip17.open(audioIn17);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip17.loop(0);
  					clip17.start(); 
  					
  					
  				}if(M.getName().equals("Wilfred Fizzlebang")&& flag==true) {
  					File soundFile18 = new File("images//WilfredfizzlebangA.wav");
  					AudioInputStream audioIn18 = null;
  					try {
  						audioIn18 = AudioSystem.getAudioInputStream(soundFile18);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip18 = null;
  					try {
  						clip18= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip18.open(audioIn18);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip18.loop(0);
  					clip18.start(); 
  					
  					
  				}if(M.getName().equals("Wolfrider")&& flag==true) {
  					File soundFile19 = new File("images//WolfriderA.wav");
  					AudioInputStream audioIn19 = null;
  					try {
  						audioIn19 = AudioSystem.getAudioInputStream(soundFile19);
  					} catch (UnsupportedAudioFileException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					} catch (IOException e2) {
  						// TODO Auto-generated catch block
  						e2.printStackTrace();
  					}
  					Clip clip19 = null;
  					try {
  						clip19= AudioSystem.getClip();
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					try {
  						clip19.open(audioIn19);
  					} catch (LineUnavailableException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					} catch (IOException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
  					clip19.loop(0);
  					clip19.start(); 
  					
  					
  				}
			  nuul();
			  setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
			   SelectedMinion = null ;
                           SelectedMinion1 = null ;
  
		}
               else 
		  if(currentheros.contains(p) && p.getIcon()!= null&& model.getCurrentHero().getHand().get(currentheros.indexOf(p)) instanceof AOESpell ) {
				try {
					model.getCurrentHero().castSpell((AOESpell)model.getCurrentHero().getHand().get(currentheros.indexOf(p)), model.getOpponent().getField());
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				}
				setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
                SelectedMinion = null ;
                SelectedMinion1 = null ;
				
		}else
			if(currentheros.contains(p) && p.getIcon()!= null&&model.getCurrentHero().getHand().get(currentheros.indexOf(p)) instanceof FieldSpell) {
			
			try {
				model.getCurrentHero().castSpell((FieldSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(p)));
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
		SelectedMinion = null ;
                SelectedMinion1 = null ;
		
           }
          else	
	   if(SelectedMinion != null && currentheros.contains(SelectedMinion) 
	   && model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof HeroTargetSpell 
	   && (p.getActionCommand().equals("Current") ))  {
		try {
			model.getCurrentHero().castSpell((HeroTargetSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) , model.getCurrentHero());
		} catch (NotYourTurnException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
		} catch (NotEnoughManaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
		}
		setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
		SelectedMinion = null ;
                SelectedMinion1 = null ;
		
	}else
		if(SelectedMinion != null && currentheros.contains(SelectedMinion) && model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof HeroTargetSpell && (p.getActionCommand().equals("Opponent") ))  {
			try {
				model.getCurrentHero().castSpell((HeroTargetSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) , model.getOpponent());
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());	
			SelectedMinion = null ;
			SelectedMinion1 = null ;
		}
	else
		if(SelectedMinion != null && currentheros.contains(SelectedMinion) && model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof LeechingSpell
		&& currentherosField.contains(p) && (model.getCurrentHero().getField().get(currentherosField.indexOf(p)) instanceof Minion ) && p.getIcon()!=null) {
			try {
				model.getCurrentHero().castSpell((LeechingSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)), model.getCurrentHero().getField().get(currentherosField.indexOf(p)));
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
			SelectedMinion = null ;
			SelectedMinion1 = null ;
	}else
		if(SelectedMinion != null && currentheros.contains(SelectedMinion) && model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof LeechingSpell &&  opponentsField.contains(p)&&
		(model.getOpponent().getField().get(opponentsField.indexOf(p)) instanceof Minion ) && p.getIcon()!=null) {
			try {
				model.getCurrentHero().castSpell((LeechingSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)), model.getOpponent().getField().get(opponentsField.indexOf(p)));
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
			SelectedMinion = null ;
			SelectedMinion1 = null ;
	}
		else 
		if(SelectedMinion != null && currentheros.contains(SelectedMinion) && 
		model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof MinionTargetSpell
		&& currentherosField.contains(p) && (model.getCurrentHero().getField().get(currentherosField.indexOf(p)) instanceof Minion) && p.getIcon()!=null ) {
			try {
				model.getCurrentHero().castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)),model.getCurrentHero().getField().get(currentherosField.indexOf(p)));
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
			SelectedMinion = null ;
                        SelectedMinion1 = null ;
		}else 
			if(SelectedMinion != null && currentheros.contains(SelectedMinion) && model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)) instanceof MinionTargetSpell && opponentsField.contains(p) &&(model.getOpponent().getField().get(opponentsField.indexOf(p)) instanceof Minion)&& p.getIcon()!=null ) {
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell)model.getCurrentHero().getHand().get(currentheros.indexOf(SelectedMinion)),model.getOpponent().getField().get(opponentsField.indexOf(p)));
				} catch (NotYourTurnException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughManaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
				}
				setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
				SelectedMinion = null ;
                                SelectedMinion1 = null ;
			}
			else
		
		if(e.getActionCommand().equals("Opponent") && currentherosField.contains(SelectedMinion1) && SelectedMinion1.getIcon() != null ) {		
		//	   int a = opponentsField.indexOf(p);
			   int b = currentherosField.indexOf(SelectedMinion1);
			   System.out.print(true);
			//   currentherosField.get(b).setBorder(new LineBorder(Color.RED));
			   try {
				model.getCurrentHero().attackWithMinion(model.getCurrentHero().getField().get(b), model.getOpponent());
			} catch (CannotAttackException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (TauntBypassException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotSummonedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			}	
			   setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
				SelectedMinion1 = null ;
                                SelectedMinion = null ;
			}
		
	else
		if(e.getActionCommand().equals("End Turn")) {
			try {
				model.endTurn();
			} catch (FullHandException e1) {
				JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Icon l = view.getHero1().getIcon() ;
			view.getHero1().setIcon(view.getHero2().getIcon());
			view.getHero2().setIcon(l);
		//	view.getHero1().setIcon(null);
	//		view.getHero2().setIcon(null);
			setCurrent(model.getCurrentHero().getName(), model.getCurrentHero());
			setOpponent(model.getOpponent().getName(),model.getOpponent());
			  view.getDeck1cards().setText(new Integer(model.getCurrentHero().getDeck().size()).toString());
		         if(Integer.parseInt(view.getDeck2cards().getText())<10) {	
		              view.getDeck2cards().setBounds(1150, 75, 130, 160);}
		              else { view.getDeck2cards().setBounds(1142, 75, 130, 160);
		            	  
		              }
		         
				  view.getDeck2cards().setText(new Integer(model.getOpponent().getDeck().size()).toString());
				  if(Integer.parseInt(view.getDeck1cards().getText())<10) {
		          view.getDeck1cards().setBounds(1150, 695, 130, 160);}
		          else
		        	  view.getDeck1cards().setBounds(1142, 695, 130, 160);
		        	  
		          
		        nuul();
  
		        if(model.getCurrentHero().equals(model.getFirstHero())) {
			    	  view.setlocationMana1down();
			    	  view.setlocationMana2up();
			      }
			      if(model.getCurrentHero().equals(model.getSecondHero())) {
			    	  view.setlocationMana2down();
			    	  view.setlocationMana1up();
			      }
			      setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
		    	  setMana2(model.getOpponent().getCurrentManaCrystals(),model.getOpponent().getTotalManaCrystals());
                        SelectedMinion = null ;
                        SelectedMinion1 = null ;
            Icon j = view.getHero1power().getIcon() ;         
            view.getHero1power().setIcon(view.getHero2power().getIcon());
            view.getHero2power().setIcon(j);
	}else
		if(e.getActionCommand().equals("heropower") && 
				!((model.getCurrentHero().getName().equals("Jaina Proudmoore"))  || (model.getCurrentHero().getName().equals("Anduin Wrynn")))) {
			try {
				model.getCurrentHero().useHeroPower();
				
			} catch (NotEnoughManaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"alert", JOptionPane.ERROR_MESSAGE);
				
			} catch (HeroPowerAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (NotYourTurnException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (FullHandException e1) {
				JOptionPane.showMessageDialog(null, getGif(e1.getBurned()), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (FullFieldException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
                      SelectedMinion = null ;
                      SelectedMinion1 = null ;
		}
		else
			if(currentherosField.contains(p) && p.getIcon() != null ) {
			int	b = currentherosField.indexOf(p) ;
				SelectedMinion1 = p ;
				if(b==0)
			    	   view.getRed11().setVisible(true);
			       if(b==1)
			    	   view.getRed12().setVisible(true);
			       if(b==2)
			    	   view.getRed13().setVisible(true);
			       if(b==3)
			    	   view.getRed14().setVisible(true);
			       if(b==4)
			    	   view.getRed15().setVisible(true);
			       if(b==5)
			    	   view.getRed16().setVisible(true);
			       if(b==6)
			    	   view.getRed17().setVisible(true);
			       
			}
			else
				if(currentheros.contains(p) && p.getIcon()!=null) {
					System.out.print(currentheros.indexOf(p));
				SelectedMinion = p  ;
				}
          generate(model.getCurrentHero().getHand() , currentheros) ;
			generateOpponent(model.getOpponent().getHand(),opponents);
			view.revalidate();
			view.repaint();
			generateField1(model.getCurrentHero().getField(),currentherosField);
			generateField2(model.getOpponent().getField(),opponentsField);
			if(SelectedMinion1 == null) {
		    	   view.getRed11().setVisible(false);
		    	   view.getRed12().setVisible(false);
		    	   view.getRed13().setVisible(false);
		    	   view.getRed14().setVisible(false);
		    	   view.getRed15().setVisible(false);
		    	   view.getRed16().setVisible(false);
		    	   view.getRed17().setVisible(false);
			}else {
				int	b = currentherosField.indexOf(SelectedMinion1) ;
				if(b==0)
			    	   view.getRed11().setVisible(true);
				view.getRed12().setVisible(false);
		    	   view.getRed13().setVisible(false);
		    	   view.getRed14().setVisible(false);
		    	   view.getRed15().setVisible(false);
		    	   view.getRed16().setVisible(false);
		    	   view.getRed17().setVisible(false);
			       if(b==1) {view.getRed11().setVisible(false);
		    	   
			    	   view.getRed12().setVisible(true);
			    	   view.getRed13().setVisible(false);
			    	   view.getRed14().setVisible(false);
			    	   view.getRed15().setVisible(false);
			    	   view.getRed16().setVisible(false);
			    	   view.getRed17().setVisible(false);
			       }
			       if(b==2) {view.getRed11().setVisible(false);
		    	   view.getRed12().setVisible(false);
			    	   view.getRed13().setVisible(true);
			    	   view.getRed14().setVisible(false);
			    	   view.getRed15().setVisible(false);
			    	   view.getRed16().setVisible(false);
			    	   view.getRed17().setVisible(false);
			       }
			       if(b==3) {
			    	   view.getRed11().setVisible(false);
			    	   view.getRed12().setVisible(false);
			    	   view.getRed14().setVisible(true);
			    	   view.getRed13().setVisible(false);
			    	   view.getRed15().setVisible(false);
			    	   view.getRed16().setVisible(false);
			    	   view.getRed17().setVisible(false);
			       }
			       if(b==4) {
			    	   view.getRed11().setVisible(false);
			    	   view.getRed12().setVisible(false);
			    	   view.getRed15().setVisible(true);
			    	   view.getRed13().setVisible(false);
			    	   view.getRed14().setVisible(false);
			    	 view.getRed16().setVisible(false);
			    	   view.getRed17().setVisible(false);
			       }
			       if(b==5) {
			    	   view.getRed11().setVisible(false);
			    	   view.getRed12().setVisible(false);
			    	   view.getRed16().setVisible(true);
			    	   view.getRed13().setVisible(false);
			    	   view.getRed14().setVisible(false);
			    	   view.getRed15().setVisible(false);
			    	   
			    	   view.getRed17().setVisible(false);
			       }
			       if(b==6) {
			    	   view.getRed11().setVisible(false);
			    	   view.getRed12().setVisible(false);
			    	   view.getRed17().setVisible(true);
			    	   view.getRed13().setVisible(false);
			    	   view.getRed14().setVisible(false);
			    	   view.getRed15().setVisible(false);
			    	   view.getRed16().setVisible(false);
			    	   
			       }
				
			}
			if(p.getActionCommand().equals("heropower") && (model.getCurrentHero().getName().equals("Jaina Proudmoore") || 
					model.getCurrentHero().getName().equals("Anduin Wrynn"))) {
				Power = p ;
			}
			view.setH2((new Integer(model.getCurrentHero().getCurrentHP())).toString());
	    	view.setH1((new Integer(model.getOpponent().getCurrentHP())).toString());
			view.revalidate();
			view.repaint();
			setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
			PowerH();
			view.getDeck1cards().setText(new Integer(model.getCurrentHero().getDeck().size()).toString());
	         if(Integer.parseInt(view.getDeck2cards().getText())<10) {	
	              view.getDeck2cards().setBounds(1150, 75, 130, 160);}
	              else { view.getDeck2cards().setBounds(1142, 75, 130, 160);
	            	  
	              }
	         
			  view.getDeck2cards().setText(new Integer(model.getOpponent().getDeck().size()).toString());
			  if(Integer.parseInt(view.getDeck1cards().getText())<10) {
	          view.getDeck1cards().setBounds(1150, 695, 130, 160);}
	          else
	        	  view.getDeck1cards().setBounds(1142, 695, 130, 160);
	        	  
			
			
  			}
	public void removeRed() {
	       view.getRed21().setVisible(false);
 	   view.getRed22().setVisible(false);
 	   view.getRed27().setVisible(false);
 	   view.getRed23().setVisible(false);
 	   view.getRed24().setVisible(false);
 	   view.getRed25().setVisible(false);
 	   view.getRed26().setVisible(false);
	}
    public void set1() {
    	int i ;
    	ArrayList<Minion> a = model.getCurrentHero().getField();
    	if(a.size() == 0) {	
    		view.setvisibility11(false);
    		view.setvisibility12(false);
    		view.setvisibility13(false);
    		view.setvisibility14(false);
    		view.setvisibility15(false);
    		view.setvisibility16(false);
    		view.setvisibility17(false);
    	}else {
    	for( i = 0 ; i< a.size() ; i++ ) {
    		if(i == 0  ) {
    		   view.setvisibility11(true);
               view.setA11(new Integer(a.get(i).getAttack()).toString());
               view.setH11(new Integer(a.get(i).getCurrentHP()).toString());
               
			}
			else 
			if(i==1 ){
				
	             view.setA12(new Integer(a.get(i).getAttack()).toString());
	             view.setH12(new Integer(a.get(i).getCurrentHP()).toString());
	             view.setvisibility12(true);
			}
			else
				if(i==2 ) {
					
		             view.setA13(new Integer(a.get(i).getAttack()).toString());
		             view.setH13(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility13(true);
				}
		    else
				if(i==3 ){
					
		             view.setA14(new Integer(a.get(i).getAttack()).toString());
		             view.setH14(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility14(true);
				}
			else
				if(i==4 ){
					
		             view.setA15(new Integer(a.get(i).getAttack()).toString());
		             view.setH15(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility15(true);
				}
			else
				if(i==5 ){
					
		             view.setA16(new Integer(a.get(i).getAttack()).toString());
		             view.setH16(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility16(true);
				}
			else
				if(i==6 ){
					
		             view.setA17(new Integer(a.get(i).getAttack()).toString());
		             view.setH17(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility17(true);
				}
    	}
	       if(i==1) {
 	   
	       view.setvisibility12(false);	    	  
	       view.setvisibility13(false);
	       view.setvisibility14(false);	    	  
	       view.setvisibility15(false);
	       view.setvisibility16(false);	    	  
	       view.setvisibility17(false);
	       }
	       if(i==2) {   	  
	       view.setvisibility13(false);
	       view.setvisibility14(false);	    	  
	       view.setvisibility15(false);
	       view.setvisibility16(false);	    	  
	       view.setvisibility17(false);
	       }
	       if(i==3) {
	    	   view.setvisibility14(false);	    	  
		       view.setvisibility15(false);
		       view.setvisibility16(false);	    	  
		       view.setvisibility17(false);
	       }
	       if(i==4) {
	    	   view.setvisibility15(false);
		       view.setvisibility16(false);	    	  
		       view.setvisibility17(false);
	       }
	       if(i==5) {
	    	   view.setvisibility16(false);	    	  
		       view.setvisibility17(false);
	       }
	       if(i==6) {    	  
		       view.setvisibility17(false);
	       }
    	
    }
    	
    }
    public void set2() {
    	int i ;
    	ArrayList<Minion> a = model.getOpponent().getField();
    	if(a.size() == 0) {	
    		view.setvisibility21(false);
    		view.setvisibility22(false);
    		view.setvisibility23(false);
    		view.setvisibility24(false);
    		view.setvisibility25(false);
    		view.setvisibility26(false);
    		view.setvisibility27(false);
    	}else {
    	for( i = 0 ; i< a.size() ; i++ ) {
    		if(i == 0  ) {
               
               view.setA21(new Integer(a.get(i).getAttack()).toString());
               view.setH21(new Integer(a.get(i).getCurrentHP()).toString());
               view.setvisibility21(true);
			}
			else 
			if(i==1 ){
				
	             view.setA22(new Integer(a.get(i).getAttack()).toString());
	             view.setH22(new Integer(a.get(i).getCurrentHP()).toString());
	             view.setvisibility22(true);
			}
			else
				if(i==2 ) {
					
		             view.setA23(new Integer(a.get(i).getAttack()).toString());
		             view.setH23(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility23(true);
				}
		    else
				if(i==3 ){
					
		             view.setA24(new Integer(a.get(i).getAttack()).toString());
		             view.setH24(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility24(true);
				}
			else
				if(i==4 ){
					
		             view.setA25(new Integer(a.get(i).getAttack()).toString());
		             view.setH25(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility25(true);
				}
			else
				if(i==5 ){
					
		             view.setA26(new Integer(a.get(i).getAttack()).toString());
		             view.setH26(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility26(true);
				}
			else
				if(i==6 ){
					
		             view.setA27(new Integer(a.get(i).getAttack()).toString());
		             view.setH27(new Integer(a.get(i).getCurrentHP()).toString());
		             view.setvisibility27(true);
				}
    	}
	       if(i==1) {
 	   
	       view.setvisibility22(false);	    	  
	       view.setvisibility23(false);
	       view.setvisibility24(false);	    	  
	       view.setvisibility25(false);
	       view.setvisibility26(false);	    	  
	       view.setvisibility27(false);
	       }
	       if(i==2) {    	  
	       view.setvisibility23(false);
	       view.setvisibility24(false);	    	  
	       view.setvisibility25(false);
	       view.setvisibility26(false);	    	  
	       view.setvisibility27(false);
	       }
	       if(i==3) {
	    	   view.setvisibility24(false);	    	  
		       view.setvisibility25(false);
		       view.setvisibility26(false);	    	  
		       view.setvisibility27(false);
	       }
	       if(i==4) {
	    	   view.setvisibility25(false);
		       view.setvisibility26(false);	    	  
		       view.setvisibility27(false);
	       }
	       if(i==5) {
	    	   view.setvisibility26(false);	    	  
		       view.setvisibility27(false);
	       }
	       if(i==6) {    	  
		       view.setvisibility27(false);
	       }
	       setMana1(model.getCurrentHero().getCurrentManaCrystals(),model.getCurrentHero().getTotalManaCrystals());
    }
    	
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton p = (JButton)e.getSource();
		int l = currentheros.indexOf(p) ;
		if(l >= 0 && p.getIcon() != null) {
		mouseCard(l) ;
		}
				
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JButton p = (JButton)e.getSource();
		view.getChosen().setIcon(null);
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
