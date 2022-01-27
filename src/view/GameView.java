package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameView extends JFrame{
private JPanel im;
private JLabel image;
private EndView endview ;
private JButton Field1card1;
private JButton Field1card2;
private JButton Field1card3;
private JButton Field1card4;
private JButton Field1card5;
private JButton Field1card6;
private JButton Field1card7;
private JButton Field2card1;
private JButton Field2card2;
private JButton Field2card3;
private JButton Field2card4;
private JButton Field2card5;
private JButton Field2card6;
private JButton Field2card7;
private JPanel Hero1hand;
private JPanel Hero2hand;
private JPanel Manabarcrystals1;
private JLabel Manabarframe1;
private JPanel Manabarcrystals2;
private JLabel Manabarframe2;
private JLabel Manatext1;
private JLabel Manatext2 ;
private JLabel Manacrystals ;
private JLabel Chosen;
private JLabel Deck1;
private JTextArea Deck1cards;
private JLabel Deck2;
private JTextArea Deck2cards;
private JButton Endturn;
private JButton Hero1 ;
private JButton Hero2 ;
private JLabel Hero1power ;
private JButton Hero2power;
private JPanel Hero2field2 ;
private JPanel Hero1field1 ;
private JPanel Hero2field1 ;
private JPanel Hero1field2 ;

////////
private JLabel shield11;
private JLabel shield12;
private JLabel shield13;
private JLabel shield14;
private JLabel shield15;
private JLabel shield16;
private JLabel shield17;
private JLabel shield21;
private JLabel shield22;
private JLabel shield23;
private JLabel shield24;
private JLabel shield25;
private JLabel shield26;
private JLabel shield27;

private JLabel red11;
private JLabel red12;
private JLabel red13;
private JLabel red14;
private JLabel red15;
private JLabel red16;
private JLabel red17;
private JLabel red21;
private JLabel red22;
private JLabel red23;
private JLabel red24;
private JLabel red25;
private JLabel red26;
private JLabel red27;

private JLabel attack11,attack12,attack13,attack14,attack15,attack16,attack17;
private JLabel textlayer1A_11,textlayer1A_12,textlayer1A_13,textlayer1A_14,textlayer1A_15,textlayer1A_16,textlayer1A_17;
private JLabel textlayer2A_11,textlayer2A_12,textlayer2A_13,textlayer2A_14,textlayer2A_15,textlayer2A_16,textlayer2A_17;
private String A11,A12,A13,A14,A15,A16,A17;

private JLabel health11,health12,health13,health14,health15,health16,health17;
private JLabel textlayer1H_11,textlayer1H_12,textlayer1H_13,textlayer1H_14,textlayer1H_15,textlayer1H_16,textlayer1H_17;
private JLabel textlayer2H_11,textlayer2H_12,textlayer2H_13,textlayer2H_14,textlayer2H_15,textlayer2H_16,textlayer2H_17;
private String H11,H12,H13,H14,H15,H16,H17;

private JLabel attack21,attack22,attack23,attack24,attack25,attack26,attack27;
private JLabel textlayer1A_21,textlayer1A_22,textlayer1A_23,textlayer1A_24,textlayer1A_25,textlayer1A_26,textlayer1A_27;
private JLabel textlayer2A_21,textlayer2A_22,textlayer2A_23,textlayer2A_24,textlayer2A_25,textlayer2A_26,textlayer2A_27;
private String A21,A22,A23,A24,A25,A26,A27;

private JLabel health21,health22,health23,health24,health25,health26,health27;
private JLabel textlayer1H_21,textlayer1H_22,textlayer1H_23,textlayer1H_24,textlayer1H_25,textlayer1H_26,textlayer1H_27;
private JLabel textlayer2H_21,textlayer2H_22,textlayer2H_23,textlayer2H_24,textlayer2H_25,textlayer2H_26,textlayer2H_27;
private String H21,H22,H23,H24,H25,H26,H27;

private JLabel health1, health2;
private JLabel textlayer1H_1, textlayer1H_2;
private JLabel textlayer2H_1, textlayer2H_2;
private String H1, H2;

public JLabel getManacrystals() {
	return Manacrystals;
}

public JPanel getHero2field2() {
	return Hero2field2;
}

public JPanel getHero1field1() {
	return Hero1field1;
}

public GameView() {
	Font backgroundfont = null;

	try {
	backgroundfont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(25f);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
	}
	catch(IOException | FontFormatException e) {

	}
	Font Game14 = null;

	try {
	Game14 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(14f);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
	}
	catch(IOException | FontFormatException e) {

	}
	 // this.setVisible(true);
////Panel1 for Hero 1 field1
 	Hero1field1 = new JPanel();
 	Hero1field1 = new JPanel();
 	Hero1field1.setBounds(300, 530, 650, 150);
 	this.add(Hero1field1);
 	Hero1field1.setLayout(new GridLayout(1,4,0,0));
 	Hero1field1.setOpaque(false);
 	this.add(Hero1field1);
 	
 	
 ////Panel2 for Hero 1 field1
 	Hero1field2 = new JPanel();
 	Hero1field2 = new JPanel();
 	Hero1field2.setBounds(400, 410, 450, 150);
 	this.add(Hero1field2);
 	Hero1field2.setLayout(new GridLayout(1,3,0,0));
 	Hero1field2.setOpaque(false);
 	this.add(Hero1field2);
 	
 	
////Panel3 for Hero 2 field2
 	Hero2field2 = new JPanel();
 	Hero2field2 = new JPanel();
 	Hero2field2.setBounds(400, 235, 450, 150);
 	this.add(Hero2field2);
 	Hero2field2.setLayout(new GridLayout(1,3,0,0));
 	Hero2field2.setOpaque(false);
 	this.add(Hero2field2);
 	
 	
 ////Panel4 for Hero 2 field1
 	Hero2field1 = new JPanel();
 	Hero2field1 = new JPanel();
 	Hero2field1.setBounds(300, 110, 650, 150);
 	this.add(Hero2field1);
 	Hero2field1.setLayout(new GridLayout(1,4,0,0));
 	Hero2field1.setOpaque(false);
 	this.add(Hero2field1);

	

	//Power for hero2
    Hero2power = new JButton();
    Hero2power.setBounds(90, 475, 140, 100);
    Hero2power.setActionCommand("heropower");
	Hero2power.setOpaque(false);
	Hero2power.setContentAreaFilled(false);
	Hero2power.setBorderPainted(false);
    this.add(Hero2power);

//Power for hero1
      Hero1power = new JLabel();
      Hero1power.setBounds(120, 30, 140, 100);
   	  Hero1power.setOpaque(false);

      this.add(Hero1power);
    
	Field1card1 = new JButton();
	Field1card2 = new JButton();
	Field1card3 = new JButton();
	Field1card4 = new JButton();
	Field1card5 = new JButton();
	Field1card6 = new JButton();
	Field1card7= new JButton();
	Field2card1 = new JButton();
	Field2card2 = new JButton();
	Field2card3 = new JButton();
	Field2card4 = new JButton();
	Field2card5 = new JButton();
	Field2card6 = new JButton();
	Field2card7 = new JButton();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setBounds(320, 140, 1300, 845);
//Ending the turn
	Endturn = new JButton();
	BufferedImage imge = null;
	try {
	    imge= ImageIO.read(new File("images//Button.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	Image Imge = imge.getScaledInstance( (int) 150, (int) 150,
	        Image.SCALE_SMOOTH);
	
	ImageIcon imageIcone = new ImageIcon(Imge);
	Endturn.setText("End Turn");
	Endturn.setHorizontalTextPosition(SwingConstants.CENTER);
	Endturn.setFont(backgroundfont);
	Endturn.setIcon(imageIcone);
	Endturn.setOpaque(false);
	Endturn.setContentAreaFilled(false);
	Endturn.setBorderPainted(false);
	Endturn.setBounds(25, 330, 150, 150);
	this.add(Endturn);

	
//Hero1
 //   Hero1=new JLabel();
  //  Hero1 = new JLabel();
 //   Hero1.setBounds(-7,10,215, 300);
  //  Hero1.setOpaque(false);
  //  this.add(Hero1);
//Hero2
  //  Hero2=new JLabel();
  //  Hero2.setBounds(-7,515,215, 300);
 //   File ftesth2 = new File(H2);
  // 	URL imgh2 = null;
  // 	try {
  // 		imgh2 = ftesth2.toURL();
  // 	} catch (MalformedURLException e1) {
  // 		e1.printStackTrace();
  // 	}
  // 	ImageIcon iconh2 = new ImageIcon(imgh2);
  // 	iconh2.setImage(iconh2.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
 //  	Hero2.setIcon(iconh2);
 //   Hero2.setOpaque(false);
  //  this.add(Hero2);
  
  
 //Hand Panel for hero1
    Hero1hand = new JPanel();
    Hero1hand.setBounds(200, 667, 850, 140);
    this.add(Hero1hand);
    Hero1hand.setLayout(new GridLayout(1,10,0,0));
    Hero1hand.setOpaque(false);
    
    

    
    
 //  Hand Panel for hero2
    Hero2hand = new JPanel();
    Hero2hand.setBounds(200, -43, 830, 140);
    this.add(Hero2hand);
    Hero2hand.setLayout(new GridLayout(1,10,0,0));
    Hero2hand.setOpaque(false);
    

// Chosen Card
    Chosen = new JLabel();
    Chosen.setBounds(1040,215,230, 353);
  	Chosen.setOpaque(false);
    this.add(Chosen);
    
    
    Font Game12 = null;
    
   	try {
   		Game12 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(19f);
   		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
   	}
   	catch(IOException | FontFormatException e) {

   	}	
    
    
 // Mana bar1
   	Manatext1 = new JLabel();
    Manabarcrystals1 = new JPanel();  
    Manabarcrystals1.setBounds((int)1056, (int)560, 210, 80);
    Manabarcrystals1.setLayout(new GridLayout(1,10,0,0));
    Manabarcrystals1.setOpaque(false);
    Manabarframe1 = new JLabel();
    Manabarframe1.setLayout(null);
    Manabarframe1.setBounds(1046, 560, 280, 80);
    Manabarframe1.setOpaque(false);
    Manabarframe1.setLayout(null);
    Manatext1.setText("Player 1");
    Manatext1.setFont(Game12);
    Manatext1.setForeground(Color.white);
    Manatext1.setBounds(10, 0, 100, 30);
    Manabarframe1.add(Manatext1);
    
    
    BufferedImage mana = null;
  	try {
  	    mana = ImageIO.read(new File("images//Manabar.png"));
  	} catch (IOException e) {
  	    e.printStackTrace();
  	}
  	
  	Image mana1 = mana.getScaledInstance( (int) 230, (int) 30,
  	        Image.SCALE_SMOOTH);
  	ImageIcon manaicon = new ImageIcon(mana1);
  	Manabarframe1.setIcon(manaicon);  
    this.add(Manabarcrystals1);

    
 // Mana bar2
    Manatext2 = new JLabel();
    Manabarcrystals2 = new JPanel();
    Manabarcrystals2.setBounds((int)1056, 165, 210, 80);
    Manabarcrystals2.setLayout(new GridLayout(1,10,0,0));
    Manabarcrystals2.setOpaque(false);
    Manabarframe2 = new JLabel();
    Manabarframe2.setBounds(1046, 165, 280, 80);
    Manabarframe2.setOpaque(false);
    Manabarframe2.setLayout(null);
    Manatext2 = new JLabel();
    Manatext1 = new JLabel();
   	Manatext2.setText("Player 2");
    Manatext2.setFont(Game12);
    Manatext2.setForeground(Color.white);
    Manatext2.setBounds(10, 0, 100, 30);
    Manabarframe2.add(Manatext2);
    
    Manabarframe2.setIcon(manaicon); 
    this.add(Manabarcrystals2);
    this.add(Manabarframe2);
    this.add(Manabarframe1);
    

////Deck1 number of cards
   Deck1cards = new JTextArea() ;
   Deck1cards.setBounds(1142, 75, 130, 160);
   Deck1cards.setOpaque(false);
   Deck1cards.setEditable(false);
   Deck1cards.setFont(backgroundfont);
   this.add(Deck1cards);
   Deck2cards = new JTextArea() ;
   Deck2cards.setBounds(1142, 700, 130, 160);
   Deck2cards.setOpaque(false);
   Deck2cards.setEditable(false);
   Deck2cards.setFont(backgroundfont);
   Deck2cards.setEditable(false);
   this.add(Deck2cards);
// Deck1 
   Deck1 = new JLabel();
   Deck1.setBounds(1090, 10, 130, 160);
   File ftestd1 = new File("images//Cardback.gif");
  	URL imgd1 = null;
  	try {
  		imgd1 = ftestd1.toURL();
  	} catch (MalformedURLException e1) {
  		e1.printStackTrace();
  	}
  	ImageIcon icond1 = new ImageIcon(imgd1);
  	icond1.setImage(icond1.getImage().getScaledInstance(130, 160,Image.SCALE_DEFAULT));
  	Deck1.setIcon(icond1);
  	this.add(Deck1);
//Deck2 
   Deck2 = new JLabel();
   Deck2.setBounds(1090, 635 , 130, 160);
  	Deck2.setIcon(icond1);
  	
  	this.add(Deck2);


 
     // Field1 for Hero1
   	Field1card1 = new JButton();
   	Field1card1.setBounds(282,470,140, 200);
 
   	Field1card1.setOpaque(false);
   	Field1card1.setContentAreaFilled(false);
   	Field1card1.setBorderPainted(false);
   	Hero1field1.add(Field1card1);
   //Field2 for Hero1
   	Field1card2 = new JButton();
   	Field1card2.setBounds(282,300,140, 200);
   
   	Field1card2.setOpaque(false);
   	Field1card2.setContentAreaFilled(false);
   	Field1card2.setBorderPainted(false);
   	Hero1field1.add(Field1card2);
   	
   	
   //Field3 for Hero1
     	Field1card3 = new JButton();
   	Field1card3.setBounds(282,70,140, 200);
     /*	BufferedImage imgf3 = null;
     	try {
     	    imgf3 = ImageIO.read(new File("images//IMG_3513.PNG"));
     	} catch (IOException e) {
     	    e.printStackTrace();
     	}
     	
     	Image Imgf3 = imgf3.getScaledInstance( (int) 190, (int) 250,
     	        Image.SCALE_SMOOTH);
     	ImageIcon imageIconf3 = new ImageIcon(Imgf3);
   	Field1card3.setIcon(imageIconf3);*/
   	Field1card3.setOpaque(false);
   	Field1card3.setContentAreaFilled(false);
   	Field1card3.setBorderPainted(false);
       Hero1field1.add(Field1card3);
   //Field4 for Hero1
   	Field1card4 = new JButton();
   	Field1card4.setBounds(200,250,90, 140);
  /*   	BufferedImage imgf4 = null;
     	try {
     	    imgf4= ImageIO.read(new File("images//IMG_3513.PNG"));
     	} catch (IOException e) {
     	    e.printStackTrace();
     	}
     	
     	Image Imgf4 = imgf4.getScaledInstance( (int) 190, (int) 250,
     	        Image.SCALE_SMOOTH);
     	ImageIcon imageIconf4 = new ImageIcon(Imgf4);
   	Field1card4.setIcon(imageIconf4);*/
   	Field1card4.setOpaque(false);
   	Field1card4.setContentAreaFilled(false);
   	Field1card4.setBorderPainted(false);
       Hero1field1.add(Field1card4);
       
       
   //Field5 for Hero1
   	Field1card5 = new JButton();
   	Field1card5.setBounds(200,420,300, 140);
  /*   	BufferedImage imgf5 = null;
     	try {
     	    imgf5 = ImageIO.read(new File("images//IMG_3473.PNG"));
     	} catch (IOException e) {
     	    e.printStackTrace();
     	}
     	
     	Image Imgf5 = imgf5.getScaledInstance( (int) 300, (int) 150,
     	        Image.SCALE_SMOOTH);
     	ImageIcon imageIconf5 = new ImageIcon(Imgf5);
   	Field1card5.setIcon(imageIconf5);*/
   	Field1card5.setOpaque(false);
   	Field1card5.setContentAreaFilled(false);
   	Field1card5.setBorderPainted(false);
   	Hero1field2.add(Field1card5);
   //Field6 for Hero1
   	Field1card6 = new JButton();
   	Field1card6.setBounds(200,420,300, 140);
  /*   	BufferedImage imgf6 = null;
     	try {
     	    imgf6 = ImageIO.read(new File("images//IMG_3473.PNG"));
     	} catch (IOException e) {
     	    e.printStackTrace();
     	}
     	
     	Image Imgf6 = imgf6.getScaledInstance( (int) 300, (int) 150,
     	        Image.SCALE_SMOOTH);
     	ImageIcon imageIconf6 = new ImageIcon(Imgf6);
   	Field1card6.setIcon(imageIconf6);*/
   	Field1card6.setOpaque(false);
     	Field1card6.setContentAreaFilled(false);
   	Field1card6.setBorderPainted(false);
   	Hero1field2.add(Field1card6);
   //Field7 for Hero1
   	Field1card7 = new JButton();
 	Field1card7.setBounds(200,420,300, 140);
    /*   	BufferedImage imgf7 = null;
       	try {
       	    imgf7 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf7 = imgf7.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf7 = new ImageIcon(Imgf7);
       	Field1card7.setIcon(imageIconf7); */
       	Field1card7.setOpaque(false);
       	Field1card7.setContentAreaFilled(false);
       	Field1card7.setBorderPainted(false);
       	Hero1field2.add(Field1card7);
           
           
    //Field1 for Hero2
       	Field2card1 = new JButton();
      	Field2card1.setBounds(282,470,140, 150);
      /* 	BufferedImage imgf21 = null;
       	try {
       	    imgf21 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf21 = imgf21.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf21 = new ImageIcon(Imgf21);
       	Field2card1.setIcon(imageIconf21); */
       	Field2card1.setOpaque(false);
       	Field2card1.setContentAreaFilled(false);
       	Field2card1.setBorderPainted(false);
       	Hero2field1.add(Field2card1);
    //Field2 for Hero2
       	Field2card2 = new JButton();
       	Field2card2.setBounds(282,470,300, 150);
     /*  	BufferedImage imgf22 = null;
       	try {
       	    imgf22 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf22 = imgf22.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf22 = new ImageIcon(Imgf22);
       	Field2card2.setIcon(imageIconf22); */
       	Field2card2.setOpaque(false);
       	Field2card2.setContentAreaFilled(false);
       	Field2card2.setBorderPainted(false);
       	Hero2field1.add(Field2card2);
    //Field3 for Hero2
       	Field2card3 = new JButton();
       	Field2card3.setBounds(282,470,300, 150);
   /*    	BufferedImage imgf23 = null;
       	try {
       	    imgf23 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf23 = imgf23.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf23 = new ImageIcon(Imgf23);
       	Field2card3.setIcon(imageIconf23); */
       	Field2card3.setOpaque(false);
       	Field2card3.setContentAreaFilled(false);
       	Field2card3.setBorderPainted(false);
       	Hero2field1.add(Field2card3);
    //Field4 for Hero2
       	Field2card4 = new JButton();
       	Field2card4.setBounds(282,470,300, 150);
   /*    	BufferedImage imgf24 = null;
       	try {
       	    imgf24 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf24 = imgf24.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf24 = new ImageIcon(Imgf24);
       	Field2card4.setIcon(imageIconf24); */
       	Field2card4.setOpaque(false);
       	Field2card4.setContentAreaFilled(false);
       	Field2card4.setBorderPainted(false);
       	Hero2field1.add(Field2card4);
    //Field5 for Hero2
       	Field2card5 = new JButton();
       	Field2card5.setBounds(282,470,300, 150);
   /*    	BufferedImage imgf25 = null;
       	try {
       	    imgf25 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf25 = imgf25.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf25 = new ImageIcon(Imgf21);
       	Field2card5.setIcon(imageIconf25); */
       	Field2card5.setOpaque(false);
       	Field2card5.setContentAreaFilled(false);
       	Field2card5.setBorderPainted(false);
       	Hero2field2.add(Field2card5);
    //Field6 for Hero2
       	Field2card6 = new JButton();
       	Field2card6.setBounds(282,470,300, 150);
   /*    	BufferedImage imgf26 = null;
       	try {
       	    imgf26 = ImageIO.read(new File("images//IMG_3473.PNG"));
       	} catch (IOException e) {
       	    e.printStackTrace();
       	}
       	
       	Image Imgf26 = imgf26.getScaledInstance( (int) 300, (int) 150,
       	        Image.SCALE_SMOOTH);
       	ImageIcon imageIconf26 = new ImageIcon(Imgf26);
       	Field2card6.setIcon(imageIconf26); */
       	Field2card6.setOpaque(false);
       	Field2card6.setContentAreaFilled(false);
       	Field2card6.setBorderPainted(false);
       	Hero2field2.add(Field2card6);
    //Field7 for Hero2
           Field2card7 = new JButton();
           Field2card7.setBounds(282,470,300, 150);
   //    	BufferedImage imgf27 = null;
   //    	try {
   //    	    imgf27 = ImageIO.read(new File("images//IMG_3473.PNG"));
   //    	} catch (IOException e) {
   //    	    e.printStackTrace();
   //    	}
   //    	
   //    	Image Imgf27 = imgf27.getScaledInstance( (int) 300, (int) 150,
   //    	        Image.SCALE_SMOOTH);
  //     	ImageIcon imageIconf27 = new ImageIcon(Imgf27);
  //     	Field2card7.setIcon(imageIconf27);
       	Field2card7.setOpaque(false);
       	Field2card7.setContentAreaFilled(false);
       	Field2card7.setBorderPainted(false);
       	Hero2field2.add(Field2card7);
    
      	
//////////////////////////////////////////////////////

///////////// New work, copy starting from here la7d 
//   		 el main method  add it to your file.

BufferedImage red = null;
try {
red = ImageIO.read(new File("images//Redattack.png"));
} catch (IOException e) {
e.printStackTrace();
}

Image redd = red.getScaledInstance( (int) 85, (int) 100,
Image.SCALE_SMOOTH);
ImageIcon redattack = new ImageIcon(redd);





BufferedImage yellow = null;
try {
yellow = ImageIO.read(new File("images//YellowShield.PNG"));
} catch (IOException e) {
e.printStackTrace();
}
Image yelloww = yellow.getScaledInstance( (int) 85, (int) 120,
Image.SCALE_SMOOTH);
ImageIcon yellowshield = new ImageIcon(yelloww);



/////sheild1 & attack1 for field1card1
shield11 = new JLabel();
Field1card1.setLayout(null);
shield11.setBounds(37, 15, 90, 120);
shield11.setIcon(yellowshield);
shield11.setVisible(false);
Field1card1.add(shield11);


red11 = new JLabel();
red11.setBounds(37, 27, 85, 100);
red11.setIcon(redattack);
red11.setVisible(false);
Field1card1.add(red11);





/////sheild2 & attack2 for field1card2	
shield12 = new JLabel();
Field1card2.setLayout(null);
shield12.setBounds(37, 15, 90, 120);
shield12.setIcon(yellowshield);
shield12.setVisible(false);
Field1card2.add(shield12);


red12 = new JLabel();
red12.setBounds(37, 27, 85, 100);
red12.setIcon(redattack);
red12.setVisible(false);
Field1card2.add(red12);


/////sheild3 & attack3 for field1card3	
shield13 = new JLabel();
Field1card3.setLayout(null);
shield13.setBounds(37, 15, 90, 120);
shield13.setIcon(yellowshield);
shield13.setVisible(false);
Field1card3.add(shield13); 


red13 = new JLabel();
red13.setBounds(37, 27, 85, 100);
red13.setIcon(redattack);
red13.setVisible(false);
Field1card3.add(red13);


/////sheild4 & attack4 for field1card4	
shield14 = new JLabel();
Field1card4.setLayout(null);
shield14.setBounds(37, 15, 90, 120);
shield14.setIcon(yellowshield);
shield14.setVisible(false);
Field1card4.add(shield14);


red14 = new JLabel();
red14.setBounds(37, 27, 85, 100);
red14.setIcon(redattack);
red14.setVisible(false);
Field1card4.add(red14);


/////sheild5 & attack5 for field1card5	
shield15 = new JLabel();
Field1card5.setLayout(null);
shield15.setBounds(33, 15, 90, 120);
shield15.setIcon(yellowshield);
shield15.setVisible(false);
Field1card5.add(shield15);


red15 = new JLabel();
red15.setBounds(33, 27, 85, 100);
red15.setIcon(redattack);
red15.setVisible(false);
Field1card5.add(red15);


/////sheild6 & attack6 for field1card6	
shield16 = new JLabel();
Field1card6.setLayout(null);
shield16.setBounds(33, 15, 90, 120);
shield16.setIcon(yellowshield);
shield16.setVisible(false);
Field1card6.add(shield16);


red16 = new JLabel();
red16.setBounds(33, 27, 85, 100);
red16.setIcon(redattack);
red16.setVisible(false);
Field1card6.add(red16);


/////sheild7 & attack7 for field1card7
shield17 = new JLabel();
Field1card7.setLayout(null);
shield17.setBounds(33, 15, 90, 120);
shield17.setIcon(yellowshield);
shield17.setVisible(false);
Field1card7.add(shield17);


red17 = new JLabel();
red17.setBounds(33, 27, 85, 100);
red17.setIcon(redattack);

red17.setVisible(false);
Field1card7.add(red17);


/////sheild1 & attack1 for field2card1
shield21 = new JLabel();
Field2card1.setLayout(null);
shield21.setBounds(37, 15, 90, 120);
shield21.setIcon(yellowshield);
shield21.setVisible(false);
Field2card1.add(shield21);

red21 = new JLabel();
red21.setBounds(37, 27, 85, 100);
red21.setIcon(redattack);
red21.setVisible(false);
Field2card1.add(red21);


/////sheild2 & attack2 for field2card2	
shield22 = new JLabel();
Field2card2.setLayout(null);
shield22.setBounds(37, 15, 90, 120);
shield22.setIcon(yellowshield);
shield22.setVisible(false);
Field2card2.add(shield22);

red22 = new JLabel();
red22.setBounds(37, 27, 85, 100);
red22.setIcon(redattack);
red22.setVisible(false);
Field2card2.add(red22);

/////sheild3 & attack3 for Field2card3	
shield23 = new JLabel();
Field2card3.setLayout(null);
shield23.setBounds(37, 15, 90, 120);
shield23.setIcon(yellowshield);
shield23.setVisible(false);
Field2card3.add(shield23);

red23 = new JLabel();
red23.setBounds(37, 27, 85, 100);
red23.setIcon(redattack);
red23.setVisible(false);
Field2card3.add(red23);



/////sheild4 & attack4 for Field2card4	
shield24 = new JLabel();
Field2card4.setLayout(null);
shield24.setBounds(37, 15, 90, 120);
shield24.setIcon(yellowshield);
shield24.setVisible(false);
Field2card4.add(shield24);

red24 = new JLabel();
red24.setBounds(37, 27, 85, 100);
red24.setIcon(redattack);
red24.setVisible(false);
Field2card4.add(red24);


/////sheild5 & attack5 for Field2card5	
shield25 = new JLabel();
Field2card5.setLayout(null);
shield25.setBounds(37, 15, 90, 120);
shield25.setIcon(yellowshield);
shield25.setVisible(false);
Field2card5.add(shield25);


red25 = new JLabel();
red25.setBounds(37, 27, 85, 100);
red25.setIcon(redattack);
red25.setVisible(false);
Field2card5.add(red25);


/////sheild6 & attack6 for Field2card6	
shield26 = new JLabel();
Field2card6.setLayout(null);
shield26.setBounds(37, 15, 90, 120);
shield26.setIcon(yellowshield);
shield26.setVisible(false);
Field2card6.add(shield26);


red26 = new JLabel();
red26.setBounds(37, 27, 85, 100);
red26.setIcon(redattack);
red26.setVisible(false);
Field2card6.add(red26);


/////sheild7 & attack7 for Field2card7	
shield27 = new JLabel();
Field2card7.setLayout(null);
shield27.setBounds(37, 15, 90, 120);
shield27.setIcon(yellowshield);
shield27.setVisible(false);
Field2card7.add(shield27);

red27 = new JLabel();
red27.setBounds(37, 27, 85, 100);
red27.setIcon(redattack);
red27.setVisible(false);
Field2card7.add(red27);
///////////////////////////////////////////////////////////    

BufferedImage Attack = null;
try {
Attack = ImageIO.read(new File("images//Attack.PNG"));
} catch (IOException e) {
e.printStackTrace();
}

Image Attack1 = Attack.getScaledInstance( (int) 33, (int) 37,
Image.SCALE_SMOOTH);
ImageIcon Attackicon = new ImageIcon(Attack1);


BufferedImage Health = null;
try {
Health = ImageIO.read(new File("images//Health.PNG"));
} catch (IOException e) {
e.printStackTrace();
}


Image Health1 = Health.getScaledInstance( (int) 23, (int) 32,
Image.SCALE_SMOOTH);
ImageIcon Healthicon = new ImageIcon(Health1);



Font gamefont = null;

try {
gamefont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//Gamefont.ttf")).deriveFont(25f);
GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//Gamefont.ttf")));
}
catch(IOException | FontFormatException e) {

}



//if(A11.length()>1 ){
//textlayer1A_11.setBounds(27, 38, 37, 23);
//textlayer2A_11.setBounds(27, 38, 37, 23);
//}
//else {
//if(A11.equals("1"))
//textlayer2A_11.setForeground(Color.RED);
//textlayer1A_11.setBounds(32, 31, 33, 37);
//textlayer2A_11.setBounds(32, 31, 33, 37);
//}



//textlayer1H_11.setBounds(102, 71, 45, 50);
//textlayer2H_11.setBounds(102, 71, 45, 50);
//textlayer2H_11.setForeground(Color.RED);


/////attack and health for Field1card1
attack11 = new JLabel();
textlayer1A_11 =new JLabel();
textlayer2A_11 =new JLabel();
A11 = new String();	
textlayer1A_11.setText(A11);	
textlayer1A_11.setFont(gamefont);
textlayer1A_11.setOpaque(false);
textlayer2A_11.setText(A11);
textlayer2A_11.setFont(backgroundfont);
textlayer2A_11.setForeground(Color.white);
textlayer2A_11.setOpaque(false);
textlayer1A_11.setBounds(32, 31, 33, 37);
textlayer2A_11.setBounds(32, 31, 33, 37);
attack11.setIcon(Attackicon);
attack11.setBounds(20, 30, 33, 37);
attack11.setOpaque(false);
textlayer1A_11.setOpaque(false);
textlayer2A_11.setOpaque(false);
attack11.setVisible(false);
textlayer1A_11.setVisible(false);
textlayer2A_11.setVisible(false);
Field1card1.add(textlayer1A_11);
Field1card1.add(textlayer2A_11);
Field1card1.add(attack11);




health11 = new JLabel();
textlayer1H_11 =new JLabel();
textlayer2H_11 =new JLabel();
H11 = new String();	
textlayer1H_11.setText(H11);	
textlayer1H_11.setFont(gamefont);
textlayer1H_11.setOpaque(false);
textlayer2H_11.setText(H11);
textlayer2H_11.setFont(backgroundfont);
textlayer2H_11.setForeground(Color.white);
textlayer2H_11.setOpaque(false);
textlayer1H_11.setBounds(105, 71, 45, 50);
textlayer2H_11.setBounds(105, 71, 45, 50);
health11.setIcon(Healthicon);
health11.setBounds(101, 70, 45, 50);
health11.setOpaque(false);
textlayer1H_11.setOpaque(false);
textlayer2H_11.setOpaque(false);
health11.setVisible(false);
textlayer1H_11.setVisible(false);
textlayer2H_11.setVisible(false);
Field1card1.add(textlayer1H_11);
Field1card1.add(textlayer2H_11);
Field1card1.add(health11);





/////attack and health for Field1card2
attack12 = new JLabel();
textlayer1A_12 =new JLabel();
textlayer2A_12 =new JLabel();
A12 = new String();	
textlayer1A_12.setText(A12);	
textlayer1A_12.setFont(gamefont);
textlayer1A_12.setOpaque(false);
textlayer2A_12.setText(A12);
textlayer2A_12.setFont(backgroundfont);
textlayer2A_12.setForeground(Color.white);
textlayer2A_12.setOpaque(false);
textlayer2A_12.setBounds(32, 31, 33, 37);
textlayer1A_12.setBounds(32, 31, 33, 37);
attack12.setIcon(Attackicon);
attack12.setBounds(20, 30, 33, 37);
attack12.setOpaque(false);
textlayer1A_12.setOpaque(false);
textlayer2A_12.setOpaque(false);
attack12.setVisible(false);
textlayer1A_12.setVisible(false);
textlayer2A_12.setVisible(false);
Field1card2.add(textlayer1A_12);
Field1card2.add(textlayer2A_12);
Field1card2.add(attack12);




health12 = new JLabel();
textlayer1H_12 =new JLabel();
textlayer2H_12 =new JLabel();
H12 = new String();	
textlayer1H_12.setText(H12);	
textlayer1H_12.setFont(gamefont);
textlayer1H_12.setOpaque(false);
textlayer2H_12.setText(H12);
textlayer2H_12.setFont(backgroundfont);
textlayer2H_12.setForeground(Color.white);
textlayer2H_12.setOpaque(false);
textlayer1H_12.setBounds(105, 71, 45, 50);
textlayer2H_12.setBounds(105, 71, 45, 50);
health12.setIcon(Healthicon);
health12.setBounds(101, 70, 45, 50);
health12.setOpaque(false);
textlayer1H_12.setOpaque(false);
textlayer2H_12.setOpaque(false);
health12.setVisible(false);
textlayer1H_12.setVisible(false);
textlayer2H_12.setVisible(false);
Field1card2.add(textlayer1H_12);
Field1card2.add(textlayer2H_12);
Field1card2.add(health12);	




/////attack and health for Field1card3
attack13 = new JLabel();
textlayer1A_13 =new JLabel();
textlayer2A_13 =new JLabel();
A13 = new String();	
textlayer1A_13.setText(A13);	
textlayer1A_13.setFont(gamefont);
textlayer1A_13.setOpaque(false);
textlayer2A_13.setText(A13);
textlayer2A_13.setFont(backgroundfont);
textlayer2A_13.setForeground(Color.white);
textlayer2A_13.setOpaque(false);
textlayer2A_13.setBounds(32, 31, 33, 37);
textlayer1A_13.setBounds(32, 31, 33, 37);
attack13.setIcon(Attackicon);
attack13.setBounds(20, 30, 33, 37);
attack13.setOpaque(false);
textlayer1A_13.setOpaque(false);
textlayer2A_13.setOpaque(false);
attack13.setVisible(false);
textlayer1A_13.setVisible(false);
textlayer2A_13.setVisible(false);
Field1card3.add(textlayer1A_13);
Field1card3.add(textlayer2A_13);
Field1card3.add(attack13);




health13 = new JLabel();
textlayer1H_13 =new JLabel();
textlayer2H_13 =new JLabel();
H13 = new String();	
textlayer1H_13.setText(H13);	
textlayer1H_13.setFont(gamefont);
textlayer1H_13.setOpaque(false);
textlayer2H_13.setText(H13);
textlayer2H_13.setFont(backgroundfont);
textlayer2H_13.setForeground(Color.white);
textlayer2H_13.setOpaque(false);
textlayer1H_13.setBounds(105, 71, 45, 50);
textlayer2H_13.setBounds(105, 71, 45, 50);
health13.setIcon(Healthicon);
health13.setBounds(101, 70, 45, 50);
health13.setOpaque(false);
textlayer1H_13.setOpaque(false);
textlayer2H_13.setOpaque(false);
health13.setVisible(false);
textlayer1H_13.setVisible(false);
textlayer2H_13.setVisible(false);
Field1card3.add(textlayer1H_13);
Field1card3.add(textlayer2H_13);
Field1card3.add(health13);



/////attack and health for Field1card4
attack14 = new JLabel();
textlayer1A_14 =new JLabel();
textlayer2A_14 =new JLabel();
A14 = new String();	
textlayer1A_14.setText(A14);	
textlayer1A_14.setFont(gamefont);
textlayer1A_14.setOpaque(false);
textlayer2A_14.setText(A14);
textlayer2A_14.setFont(backgroundfont);
textlayer2A_14.setForeground(Color.white);
textlayer2A_14.setOpaque(false);
textlayer2A_14.setBounds(32, 31, 33, 37);
textlayer1A_14.setBounds(32, 31, 33, 37);
attack14.setIcon(Attackicon);
attack14.setBounds(20, 30, 33, 37);
attack14.setOpaque(false);
textlayer1A_14.setOpaque(false);
textlayer2A_14.setOpaque(false);
attack14.setVisible(false);
textlayer1A_14.setVisible(false);
textlayer2A_14.setVisible(false);
Field1card4.add(textlayer1A_14);
Field1card4.add(textlayer2A_14);
Field1card4.add(attack14);




health14 = new JLabel();
textlayer1H_14 =new JLabel();
textlayer2H_14 =new JLabel();
H14 = new String();	
textlayer1H_14.setText(H14);	
textlayer1H_14.setFont(gamefont);
textlayer1H_14.setOpaque(false);
textlayer2H_14.setText(H14);
textlayer2H_14.setFont(backgroundfont);
textlayer2H_14.setForeground(Color.white);
textlayer2H_14.setOpaque(false);
textlayer1H_14.setBounds(105, 71, 45, 50);
textlayer2H_14.setBounds(105, 71, 45, 50);
health14.setIcon(Healthicon);
health14.setBounds(101, 70, 45, 50);
health14.setOpaque(false);
textlayer1H_14.setOpaque(false);
textlayer2H_14.setOpaque(false);
health14.setVisible(false);
textlayer1H_14.setVisible(false);
textlayer2H_14.setVisible(false);
Field1card4.add(textlayer1H_14);
Field1card4.add(textlayer2H_14);
Field1card4.add(health14);



/////attack and health for Field1card5
attack15 = new JLabel();
textlayer1A_15 =new JLabel();
textlayer2A_15 =new JLabel();
A15 = new String();	
textlayer1A_15.setText(A15);	
textlayer1A_15.setFont(gamefont);
textlayer1A_15.setOpaque(false);
textlayer2A_15.setText(A15);
textlayer2A_15.setFont(backgroundfont);
textlayer2A_15.setForeground(Color.white);
textlayer2A_15.setOpaque(false);
textlayer1A_15.setBounds(32, 31, 33, 37);
textlayer2A_15.setBounds(32, 31, 33, 37);
attack15.setIcon(Attackicon);
attack15.setBounds(20, 30, 33, 37);
attack15.setOpaque(false);
textlayer1A_15.setOpaque(false);
textlayer2A_15.setOpaque(false);
attack15.setVisible(false);
textlayer1A_15.setVisible(false);
textlayer2A_15.setVisible(false);
Field1card5.add(textlayer1A_15);
Field1card5.add(textlayer2A_15);
Field1card5.add(attack15);




health15 = new JLabel();
textlayer1H_15 =new JLabel();
textlayer2H_15 =new JLabel();
H15 = new String();	
textlayer1H_15.setText(H15);	
textlayer1H_15.setFont(gamefont);
textlayer1H_15.setOpaque(false);
textlayer2H_15.setText(H15);
textlayer2H_15.setFont(backgroundfont);
textlayer2H_15.setForeground(Color.white);
textlayer2H_15.setOpaque(false);
textlayer1H_15.setBounds(105, 71, 45, 50);
textlayer2H_15.setBounds(105, 71, 45, 50);
health15.setIcon(Healthicon);
health15.setBounds(101, 70, 45, 50);
health15.setOpaque(false);
textlayer1H_15.setOpaque(false);
textlayer2H_15.setOpaque(false);
health15.setVisible(false);
textlayer1H_15.setVisible(false);
textlayer2H_15.setVisible(false);
Field1card5.add(textlayer1H_15);
Field1card5.add(textlayer2H_15);
Field1card5.add(health15);



/////attack and health for Field1card6
attack16 = new JLabel();
textlayer1A_16 =new JLabel();
textlayer2A_16 =new JLabel();
A16 = new String();	
textlayer1A_16.setText(A16);	
textlayer1A_16.setFont(gamefont);
textlayer1A_16.setOpaque(false);
textlayer2A_16.setText(A16);
textlayer2A_16.setFont(backgroundfont);
textlayer2A_16.setForeground(Color.white);
textlayer2A_16.setOpaque(false);
textlayer1A_16.setBounds(32, 31, 33, 37);
textlayer2A_16.setBounds(32, 31, 33, 37);
attack16.setIcon(Attackicon);
attack16.setBounds(20, 30, 33, 37);
attack16.setOpaque(false);
textlayer1A_16.setOpaque(false);
textlayer2A_16.setOpaque(false);
attack16.setVisible(false);
textlayer1A_16.setVisible(false);
textlayer2A_16.setVisible(false);
Field1card6.add(textlayer1A_16);
Field1card6.add(textlayer2A_16);
Field1card6.add(attack16);




health16 = new JLabel();
textlayer1H_16 =new JLabel();
textlayer2H_16 =new JLabel();
H16 = new String();	
textlayer1H_16.setText(H16);	
textlayer1H_16.setFont(gamefont);
textlayer1H_16.setOpaque(false);
textlayer2H_16.setText(H16);
textlayer2H_16.setFont(backgroundfont);
textlayer2H_16.setForeground(Color.white);
textlayer2H_16.setOpaque(false);
textlayer1H_16.setBounds(105, 71, 45, 50);
textlayer2H_16.setBounds(105, 71, 45, 50);
health16.setIcon(Healthicon);
health16.setBounds(101, 70, 45, 50);
health16.setOpaque(false);
textlayer1H_16.setOpaque(false);
textlayer2H_16.setOpaque(false);
health16.setVisible(false);
textlayer1H_16.setVisible(false);
textlayer2H_16.setVisible(false);
Field1card6.add(textlayer1H_16);
Field1card6.add(textlayer2H_16);
Field1card6.add(health16);



/////attack and health for Field1card7
attack17 = new JLabel();
textlayer1A_17 =new JLabel();
textlayer2A_17 =new JLabel();
A17 = new String();	
textlayer1A_17.setText(A17);	
textlayer1A_17.setFont(gamefont);
textlayer1A_17.setOpaque(false);
textlayer2A_17.setText(A17);
textlayer2A_17.setFont(backgroundfont);
textlayer2A_17.setForeground(Color.white);
textlayer2A_17.setOpaque(false);
textlayer1A_17.setBounds(32, 31, 33, 37);
textlayer2A_17.setBounds(32, 31, 33, 37);
attack17.setIcon(Attackicon);
attack17.setBounds(20, 30, 33, 37);
attack17.setOpaque(false);
textlayer1A_17.setOpaque(false);
textlayer2A_17.setOpaque(false);
attack17.setVisible(false);
textlayer1A_17.setVisible(false);
textlayer2A_17.setVisible(false);
Field1card7.add(textlayer1A_17);
Field1card7.add(textlayer2A_17);
Field1card7.add(attack17);




health17 = new JLabel();
textlayer1H_17 =new JLabel();
textlayer2H_17 =new JLabel();
H17 = new String();	
textlayer1H_17.setText(H17);	
textlayer1H_17.setFont(gamefont);
textlayer1H_17.setOpaque(false);
textlayer2H_17.setText(H17);
textlayer2H_17.setFont(backgroundfont);
textlayer2H_17.setForeground(Color.white);
textlayer2H_17.setOpaque(false);
textlayer1H_17.setBounds(105, 71, 45, 50);
textlayer2H_17.setBounds(105, 71, 45, 50);
health17.setIcon(Healthicon);
health17.setBounds(101, 70, 45, 50);
health17.setOpaque(false);
textlayer1H_17.setOpaque(false);
textlayer2H_17.setOpaque(false);
health17.setVisible(false);
textlayer1H_17.setVisible(false);
textlayer2H_17.setVisible(false);
Field1card7.add(textlayer1H_17);
Field1card7.add(textlayer2H_17);
Field1card7.add(health17);



/////attack and health for Field2card1
attack21 = new JLabel();
textlayer1A_21 =new JLabel();
textlayer2A_21 =new JLabel();
A21 = new String();	
textlayer1A_21.setText(A21);	
textlayer1A_21.setFont(gamefont);
textlayer1A_21.setOpaque(false);
textlayer2A_21.setText(A21);
textlayer2A_21.setFont(backgroundfont);
textlayer2A_21.setForeground(Color.white);
textlayer2A_21.setOpaque(false);
textlayer1A_21.setBounds(32, 31, 33, 37);
textlayer2A_21.setBounds(32, 31, 33, 37);
attack21.setIcon(Attackicon);
attack21.setBounds(20, 30, 33, 37);
attack21.setOpaque(false);
textlayer1A_21.setOpaque(false);
textlayer2A_21.setOpaque(false);
attack21.setVisible(false);
textlayer1A_21.setVisible(false);
textlayer2A_21.setVisible(false);
Field2card1.add(textlayer1A_21);
Field2card1.add(textlayer2A_21);
Field2card1.add(attack21);




health21 = new JLabel();
textlayer1H_21 =new JLabel();
textlayer2H_21 =new JLabel();
H21 = new String();	
textlayer1H_21.setText(H21);	
textlayer1H_21.setFont(gamefont);
textlayer1H_21.setOpaque(false);
textlayer2H_21.setText(H21);
textlayer2H_21.setFont(backgroundfont);
textlayer2H_21.setForeground(Color.white);
textlayer2H_21.setOpaque(false);
textlayer1H_21.setBounds(105, 71, 45, 50);
textlayer2H_21.setBounds(105, 71, 45, 50);
health21.setIcon(Healthicon);
health21.setBounds(101, 70, 45, 50);
health21.setOpaque(false);
textlayer1H_21.setOpaque(false);
textlayer2H_21.setOpaque(false);
health21.setVisible(false);
textlayer1H_21.setVisible(false);
textlayer2H_21.setVisible(false);
Field2card1.add(textlayer1H_21);
Field2card1.add(textlayer2H_21);
Field2card1.add(health21);




/////attack and health for Field2card2
attack22 = new JLabel();
textlayer1A_22 =new JLabel();
textlayer2A_22 =new JLabel();
A22 = new String();	
textlayer1A_22.setText(A22);	
textlayer1A_22.setFont(gamefont);
textlayer1A_22.setOpaque(false);
textlayer2A_22.setText(A22);
textlayer2A_22.setFont(backgroundfont);
textlayer2A_22.setForeground(Color.white);
textlayer2A_22.setOpaque(false);
textlayer1A_22.setBounds(32, 31, 33, 37);
textlayer2A_22.setBounds(32, 31, 33, 37);
attack22.setIcon(Attackicon);
attack22.setBounds(20, 30, 33, 37);
attack22.setOpaque(false);
textlayer1A_22.setOpaque(false);
textlayer2A_22.setOpaque(false);
attack22.setVisible(false);
textlayer1A_22.setVisible(false);
textlayer2A_22.setVisible(false);
Field2card2.add(textlayer1A_22);
Field2card2.add(textlayer2A_22);
Field2card2.add(attack22);




health22 = new JLabel();
textlayer1H_22 =new JLabel();
textlayer2H_22 =new JLabel();
H22 = new String();	
textlayer1H_22.setText(H22);	
textlayer1H_22.setFont(gamefont);
textlayer1H_22.setOpaque(false);
textlayer2H_22.setText(H22);
textlayer2H_22.setFont(backgroundfont);
textlayer2H_22.setForeground(Color.white);
textlayer2H_22.setOpaque(false);
textlayer1H_22.setBounds(105, 71, 45, 50);
textlayer2H_22.setBounds(105, 71, 45, 50);
health22.setIcon(Healthicon);
health22.setBounds(101, 70, 45, 50);
health22.setOpaque(false);
textlayer1H_22.setOpaque(false);
textlayer2H_22.setOpaque(false);
health22.setVisible(false);
textlayer1H_22.setVisible(false);
textlayer2H_22.setVisible(false);
Field2card2.add(textlayer1H_22);
Field2card2.add(textlayer2H_22);
Field2card2.add(health22);



/////attack and health for Field2card3
attack23 = new JLabel();
textlayer1A_23 =new JLabel();
textlayer2A_23 =new JLabel();
A23 = new String();	
textlayer1A_23.setText(A23);	
textlayer1A_23.setFont(gamefont);
textlayer1A_23.setOpaque(false);
textlayer2A_23.setText(A23);
textlayer2A_23.setFont(backgroundfont);
textlayer2A_23.setForeground(Color.white);
textlayer2A_23.setOpaque(false);
textlayer1A_23.setBounds(32, 31, 33, 37);
textlayer2A_23.setBounds(32, 31, 33, 37);
attack23.setIcon(Attackicon);
attack23.setBounds(20, 30, 33, 37);
attack23.setOpaque(false);
textlayer1A_23.setOpaque(false);
textlayer2A_23.setOpaque(false);
attack23.setVisible(false);
textlayer1A_23.setVisible(false);
textlayer2A_23.setVisible(false);
Field2card3.add(textlayer1A_23);
Field2card3.add(textlayer2A_23);
Field2card3.add(attack23);




health23 = new JLabel();
textlayer1H_23 =new JLabel();
textlayer2H_23 =new JLabel();
H23 = new String();	
textlayer1H_23.setText(H23);	
textlayer1H_23.setFont(gamefont);
textlayer1H_23.setOpaque(false);
textlayer2H_23.setText(H23);
textlayer2H_23.setFont(backgroundfont);
textlayer2H_23.setForeground(Color.white);
textlayer2H_23.setOpaque(false);
textlayer1H_23.setBounds(105, 71, 45, 50);
textlayer2H_23.setBounds(105, 71, 45, 50);
health23.setIcon(Healthicon);
health23.setBounds(101, 70, 45, 50);
health23.setOpaque(false);
textlayer1H_23.setOpaque(false);
textlayer2H_23.setOpaque(false);
health23.setVisible(false);
textlayer1H_23.setVisible(false);
textlayer2H_23.setVisible(false);
Field2card3.add(textlayer1H_23);
Field2card3.add(textlayer2H_23);
Field2card3.add(health23);




/////attack and health for Field2card4
attack24 = new JLabel();
textlayer1A_24 =new JLabel();
textlayer2A_24 =new JLabel();
A24 = new String();	
textlayer1A_24.setText(A24);	
textlayer1A_24.setFont(gamefont);
textlayer1A_24.setOpaque(false);
textlayer2A_24.setText(A24);
textlayer2A_24.setFont(backgroundfont);
textlayer2A_24.setForeground(Color.white);
textlayer2A_24.setOpaque(false);
textlayer1A_24.setBounds(32, 31, 33, 37);
textlayer2A_24.setBounds(32, 31, 33, 37);
attack24.setIcon(Attackicon);
attack24.setBounds(20, 30, 33, 37);
attack24.setOpaque(false);
textlayer1A_24.setOpaque(false);
textlayer2A_24.setOpaque(false);
attack24.setVisible(false);
textlayer1A_24.setVisible(false);
textlayer2A_24.setVisible(false);
Field2card4.add(textlayer1A_24);
Field2card4.add(textlayer2A_24);
Field2card4.add(attack24);




health24 = new JLabel();
textlayer1H_24 =new JLabel();
textlayer2H_24 =new JLabel();
H24 = new String();	
textlayer1H_24.setText(H24);	
textlayer1H_24.setFont(gamefont);
textlayer1H_24.setOpaque(false);
textlayer2H_24.setText(H24);
textlayer2H_24.setFont(backgroundfont);
textlayer2H_24.setForeground(Color.white);
textlayer2H_24.setOpaque(false);
textlayer1H_24.setBounds(105, 71, 45, 50);
textlayer2H_24.setBounds(105, 71, 45, 50);
health24.setIcon(Healthicon);
health24.setBounds(101, 70, 45, 50);
health24.setOpaque(false);
textlayer1H_24.setOpaque(false);
textlayer2H_24.setOpaque(false);
health24.setVisible(false);
textlayer1H_24.setVisible(false);
textlayer2H_24.setVisible(false);
Field2card4.add(textlayer1H_24);
Field2card4.add(textlayer2H_24);
Field2card4.add(health24);



/////attack and health for Field2card5
attack25 = new JLabel();
textlayer1A_25 =new JLabel();
textlayer2A_25 =new JLabel();
A25 = new String();	
textlayer1A_25.setText(A25);	
textlayer1A_25.setFont(gamefont);
textlayer1A_25.setOpaque(false);
textlayer2A_25.setText(A25);
textlayer2A_25.setFont(backgroundfont);
textlayer2A_25.setForeground(Color.white);
textlayer2A_25.setOpaque(false);
textlayer1A_25.setBounds(32, 31, 33, 37);
textlayer2A_25.setBounds(32, 31, 33, 37);
attack25.setIcon(Attackicon);
attack25.setBounds(20, 30, 33, 37);
attack25.setOpaque(false);
textlayer1A_25.setOpaque(false);
textlayer2A_25.setOpaque(false);
attack25.setVisible(false);
textlayer1A_25.setVisible(false);
textlayer2A_25.setVisible(false);
Field2card5.add(textlayer1A_25);
Field2card5.add(textlayer2A_25);
Field2card5.add(attack25);




health25 = new JLabel();
textlayer1H_25 =new JLabel();
textlayer2H_25 =new JLabel();
H25 = new String();	
textlayer1H_25.setText(H25);	
textlayer1H_25.setFont(gamefont);
textlayer1H_25.setOpaque(false);
textlayer2H_25.setText(H25);
textlayer2H_25.setFont(backgroundfont);
textlayer2H_25.setForeground(Color.white);
textlayer2H_25.setOpaque(false);
textlayer1H_25.setBounds(105, 71, 45, 50);
textlayer2H_25.setBounds(105, 71, 45, 50);
health25.setIcon(Healthicon);
health25.setBounds(101, 70, 45, 50);
health25.setOpaque(false);
textlayer1H_25.setOpaque(false);
textlayer2H_25.setOpaque(false);
health25.setVisible(false);
textlayer1H_25.setVisible(false);
textlayer2H_25.setVisible(false);
Field2card5.add(textlayer1H_25);
Field2card5.add(textlayer2H_25);
Field2card5.add(health25);



/////attack and health for Field2card6
attack26 = new JLabel();
textlayer1A_26 =new JLabel();
textlayer2A_26 =new JLabel();
A26 = new String();	
textlayer1A_26.setText(A26);	
textlayer1A_26.setFont(gamefont);
textlayer1A_26.setOpaque(false);
textlayer2A_26.setText(A26);
textlayer2A_26.setFont(backgroundfont);
textlayer2A_26.setForeground(Color.white);
textlayer2A_26.setOpaque(false);
textlayer1A_26.setBounds(32, 31, 33, 37);
textlayer2A_26.setBounds(32, 31, 33, 37);
attack26.setIcon(Attackicon);
attack26.setBounds(20, 30, 33, 37);
attack26.setOpaque(false);
textlayer1A_26.setOpaque(false);
textlayer2A_26.setOpaque(false);
attack26.setVisible(false);
textlayer1A_26.setVisible(false);
textlayer2A_26.setVisible(false);
Field2card6.add(textlayer1A_26);
Field2card6.add(textlayer2A_26);
Field2card6.add(attack26);




health26 = new JLabel();
textlayer1H_26 =new JLabel();
textlayer2H_26 =new JLabel();
H26 = new String();	
textlayer1H_26.setText(H26);	
textlayer1H_26.setFont(gamefont);
textlayer1H_26.setOpaque(false);
textlayer2H_26.setText(H26);
textlayer2H_26.setFont(backgroundfont);
textlayer2H_26.setForeground(Color.white);
textlayer2H_26.setOpaque(false);
textlayer1H_26.setBounds(105, 71, 45, 50);
textlayer2H_26.setBounds(105, 71, 45, 50);
health26.setIcon(Healthicon);
health26.setBounds(101, 70, 45, 50);
health26.setOpaque(false);
textlayer1H_26.setOpaque(false);
textlayer2H_26.setOpaque(false);
health26.setVisible(false);
textlayer1H_26.setVisible(false);
textlayer2H_26.setVisible(false);
Field2card6.add(textlayer1H_26);
Field2card6.add(textlayer2H_26);
Field2card6.add(health26);



/////attack and health for Field2card7
attack27 = new JLabel();
textlayer1A_27 =new JLabel();
textlayer2A_27 =new JLabel();
A27 = new String();	
textlayer1A_27.setText(A27);	
textlayer1A_27.setFont(gamefont);
textlayer1A_27.setOpaque(false);
textlayer2A_27.setText(A27);
textlayer2A_27.setFont(backgroundfont);
textlayer2A_27.setForeground(Color.white);
textlayer2A_27.setOpaque(false);
textlayer1A_27.setBounds(32, 31, 33, 37);
textlayer2A_27.setBounds(32, 31, 33, 37);
attack27.setIcon(Attackicon);
attack27.setBounds(20, 30, 33, 37);
attack27.setOpaque(false);
textlayer1A_27.setOpaque(false);
textlayer2A_27.setOpaque(false);
attack27.setVisible(false);
textlayer1A_27.setVisible(false);
textlayer2A_27.setVisible(false);
Field2card7.add(textlayer1A_27);
Field2card7.add(textlayer2A_27);
Field2card7.add(attack27);




health27 = new JLabel();
textlayer1H_27 =new JLabel();
textlayer2H_27 =new JLabel();
H27 = new String();	
textlayer1H_27.setText(H27);	
textlayer1H_27.setFont(gamefont);
textlayer1H_27.setOpaque(false);
textlayer2H_27.setText(H27);
textlayer2H_27.setFont(backgroundfont);
textlayer2H_27.setForeground(Color.white);
textlayer2H_27.setOpaque(false);
textlayer1H_27.setBounds(105, 71, 45, 50);
textlayer2H_27.setBounds(105, 71, 45, 50);
health27.setIcon(Healthicon);
health27.setBounds(101, 70, 45, 50);
health27.setOpaque(false);
textlayer1H_27.setOpaque(false);
textlayer2H_27.setOpaque(false);
health27.setVisible(false);
textlayer1H_27.setVisible(false);
textlayer2H_27.setVisible(false);
Field2card7.add(textlayer1H_27);
Field2card7.add(textlayer2H_27);
Field2card7.add(health27);


//////////////////////////////////////  

BufferedImage Health11 = null;
try {
	Health11 = ImageIO.read(new File("images//Health.PNG"));
} catch (IOException e) {
  e.printStackTrace();
}


Image Health111 = Health.getScaledInstance( (int) 48, (int) 68,
      Image.SCALE_SMOOTH);
ImageIcon Healthheroicon = new ImageIcon(Health111);
  	

Font gamefont1 = null;

	try {
		gamefont1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//Gamefont.ttf")).deriveFont(45f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//Gamefont.ttf")));
	}
	catch(IOException | FontFormatException e) {
	
	}
	
	
	System.out.println(gamefont.getSize());
	
Font backgroundfont1 = null;

	try {
		backgroundfont1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")).deriveFont(45f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts//background.ttf")));
	}
	catch(IOException | FontFormatException e) {
	
	}	
		
	
//	
//textlayer1H_1.setBounds(162, 159, 60, 80);
//textlayer2H_1.setBounds(162, 159, 60, 80);
//	health1.setBounds(160, 157, 60, 80);
	//////////////////////////////////////////////////////
	//Hero1
  //  Hero1=new JButton();
 //   Hero1 = new JButton();
  //  Hero1.setBounds(-7,515,215, 300);
  /*  File ftesth1 = new File(H1);
	URL imgh1 = null;
	try {
		imgh1 = ftesth1.toURL();
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
	ImageIcon iconh1 = new ImageIcon(imgh1);
	iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
	Hero1.setIcon(iconh1);*/
 //   Hero1.setOpaque(false);
 //   Hero1.setContentAreaFilled(false);
//	Hero1.setBorderPainted(false);
//	Hero1.setContentAreaFilled(false);
 //   this.add(Hero1);
//Hero2
  //  Hero2=new JButton();
  //  Hero2.setBounds(-7,10,215, 300);
   /* File ftesth2 = new File(H2);
   	URL imgh2 = null;
   	try {
   		imgh2 = ftesth2.toURL();
   	} catch (MalformedURLException e1) {
   		e1.printStackTrace();
   	}
   	ImageIcon iconh2 = new ImageIcon(imgh2);
   	iconh2.setImage(iconh2.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
   	Hero2.setIcon(iconh2);*/
  //  Hero2.setOpaque(false);
  //  Hero2.setContentAreaFilled(false);
//	Hero2.setBorderPainted(false);
//	Hero2.setContentAreaFilled(false);
 //   this.add(Hero2);
    
/////////////////////////////////////////////////////////    
//Hero1
Hero1=new JButton();
Hero1.setLayout(null);
health1 = new JLabel();
textlayer1H_1 = new JLabel();
textlayer2H_1 = new JLabel();
H1 = new String();		
textlayer1H_1.setText(H1);	
textlayer1H_1.setFont(gamefont1);
textlayer1H_1.setOpaque(false);
textlayer2H_1.setText(H1);
textlayer2H_1.setFont(backgroundfont1);
textlayer2H_1.setForeground(Color.white);
textlayer2H_1.setOpaque(false);
health1.setIcon(Healthheroicon);
health1.setOpaque(false);
textlayer1H_1.setBounds(162, 159, 60, 80);
textlayer2H_1.setBounds(162, 159, 60, 80);
health1.setBounds(160, 157, 60, 80);
Hero1.setBounds(-7,40,215, 300);
//File ftesth1 = new File("images//MageB.gif");
//URL imgh1 = null;
//try {
//	imgh1 = ftesth1.toURL();
//} catch (MalformedURLException e1) {
//	e1.printStackTrace();
//}
//ImageIcon iconh1 = new ImageIcon(imgh1);
//iconh1.setImage(iconh1.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
//Hero1.setIcon(iconh1);
Hero1.setOpaque(false);
Hero1.setContentAreaFilled(false);
Hero1.setBorderPainted(false);
Hero1.add(textlayer1H_1);
Hero1.add(textlayer2H_1);
Hero1.add(health1);
this.add(Hero1);



//Hero2
Hero2=new JButton();
Hero2.setLayout(null);
health2 = new JLabel();
textlayer1H_2 = new JLabel();
textlayer2H_2 = new JLabel();
H2 = new String();
textlayer1H_2.setText(H2);	
textlayer1H_2.setFont(gamefont1);
textlayer1H_2.setOpaque(false);
textlayer2H_2.setText(H2);
textlayer2H_2.setFont(backgroundfont1);
textlayer2H_2.setForeground(Color.white);
textlayer2H_2.setOpaque(false);
health2.setIcon(Healthheroicon);
health2.setOpaque(false);
textlayer1H_2.setBounds(162, 159, 60, 80);
textlayer2H_2.setBounds(162, 159, 60, 80);
health2.setBounds(160, 157, 60, 80);
Hero2.setBounds(-7,485,215, 300);
//File ftesth2 = new File("images//HunterB.gif");
//	URL imgh2 = null;
//	try {
//		imgh2 = ftesth2.toURL();
//	} catch (MalformedURLException e1) {
//		e1.printStackTrace();
//	}
//	ImageIcon iconh2 = new ImageIcon(imgh2);
//	iconh2.setImage(iconh2.getImage().getScaledInstance(215, 300,Image.SCALE_DEFAULT));
//	Hero2.setIcon(iconh2);
	Hero2.setOpaque(false);
	Hero2.setContentAreaFilled(false);
	Hero2.setBorderPainted(false);
Hero2.add(textlayer1H_2);
Hero2.add(textlayer2H_2);
Hero2.add(health2);
this.add(Hero2);

		








    
    
   
    
    
    
    
    
    
    
    
    
    
 //Background
    im = new JPanel();
    JLabel image = new JLabel();
    
    
    
	image = new JLabel(new ImageIcon("images//Gamebackground.jpg"));
	im.setLayout(new GridLayout(0,1));
	im.add(image);
    this.add(im);

    
	this.revalidate();
	this.repaint();
	
	
	
	
	
	
	
}

public void setlocationMana1up() {
	 Manabarframe1.setBounds(1046, 165, 280, 80);
}

public void setlocationMana1down() {
	Manabarframe1.setBounds(1046, 560, 280, 80);
   
}

public void setlocationMana2up() {
	 Manabarframe2.setBounds(1046, 165, 280, 80);
}

public void setlocationMana2down() {
	Manabarframe2.setBounds(1046, 560, 280, 80);
   
}

public void setManabarcrystals1() {
	Manabarcrystals1.removeAll();
}

public void setManabarcrystals2() {
	Manabarcrystals2.removeAll();
    
}

public JPanel getManabarcrystals1() {
	return Manabarcrystals1;
}

public JLabel getManabarframe1() {
	return Manabarframe1;
}

public JPanel getManabarcrystals2() {
	return Manabarcrystals2;
}

public JLabel getManabarframe2() {
	return Manabarframe2;
}

public JLabel getManatext1() {
	return Manatext1;
}

public JLabel getManatext2() {
	return Manatext2;
}

public JPanel getHero2field1() {
	return Hero2field1;
}

public JPanel getHero1field2() {
	return Hero1field2;
}

public JLabel getHero1power() {
	return Hero1power;
}

public JButton getHero2power() {
	return Hero2power;
}

public JButton getField1card1() {
	return Field1card1;
}

public JButton getField1card2() {
	return Field1card2;
}

public JButton getField1card3() {
	return Field1card3;
}

public JButton getField1card4() {
	return Field1card4;
}

public JButton getField1card5() {
	return Field1card5;
}

public JButton getField1card6() {
	return Field1card6;
}

public JButton getField1card7() {
	return Field1card7;
}

public JButton getField2card1() {
	return Field2card1;
}

public JButton getField2card2() {
	return Field2card2;
}

public JButton getField2card3() {
	return Field2card3;
}

public JButton getField2card4() {
	return Field2card4;
}

public JButton getField2card5() {
	return Field2card5;
}

public JButton getField2card6() {
	return Field2card6;
}

public JButton getField2card7() {
	return Field2card7;
}

public JButton getHero1() {
	return Hero1;
}
public JButton getHero2() {
	return Hero2;
}
public JPanel getHero1hand() {
	return Hero1hand;
}
public JPanel getHero2hand() {
	return Hero2hand;
}

public JLabel getChosen() {
	return Chosen;
}
public JLabel getDeck1() {
	return Deck1;
}
public JTextArea getDeck1cards() {
	return Deck1cards;
}
public JLabel getDeck2() {
	return Deck2;
}
public JTextArea getDeck2cards() {
	return Deck2cards;
}
public JButton getEndturn() {
	return Endturn;
}
///////////////////////////

public JLabel getShield11() {
return shield11;
}

public JLabel getShield12() {
return shield12;
}

public JLabel getShield13() {
return shield13;
}

public JLabel getShield14() {
return shield14;
}

public JLabel getShield15() {
return shield15;
}

public JLabel getShield16() {
return shield16;
}

public JLabel getShield17() {
return shield17;
}

public JLabel getShield21() {
return shield21;
}

public JLabel getShield22() {
return shield22;
}

public JLabel getShield23() {
return shield23;
}

public JLabel getShield24() {
return shield24;
}

public JLabel getShield25() {
return shield25;
}

public JLabel getShield26() {
return shield26;
}

public JLabel getShield27() {
return shield27;
}

public JLabel getRed11() {
return red11;
}

public JLabel getRed12() {
return red12;
}

public JLabel getRed13() {
return red13;
}

public JLabel getRed14() {
return red14;
}

public JLabel getRed15() {
return red15;
}

public JLabel getRed16() {
return red16;
}

public JLabel getRed17() {
return red17;
}

public JLabel getRed21() {
return red21;
}

public JLabel getRed22() {
return red22;
}

public JLabel getRed23() {
return red23;
}

public JLabel getRed24() {
return red24;
}

public JLabel getRed25() {
return red25;
}

public JLabel getRed26() {
return red26;
}

public JLabel getRed27() {
return red27;
}


public void setA11(String a11) {
	A11 = a11;
	if(A11.length()>1 ){
	textlayer1A_11.setBounds(27, 38, 37, 23);
	textlayer2A_11.setBounds(27, 38, 37, 23);
	}
	else {
		if(A11.equals("1"))
		textlayer1A_11.setBounds(32, 31, 33, 37);
    	textlayer2A_11.setBounds(32, 31, 33, 37);
	}
	textlayer1A_11.setText(A11);
	textlayer2A_11.setText(A11);
}


public void setA12(String a12) {
	A12 = a12;
	if(A12.length()>1 ){
		textlayer1A_12.setBounds(27, 38, 37, 23);
		textlayer2A_12.setBounds(27, 38, 37, 23);
		}
		else {
			if(A12.equals("1"))
			textlayer1A_12.setBounds(32, 31, 33, 37);
	    	textlayer2A_12.setBounds(32, 31, 33, 37);
		}
	textlayer1A_12.setText(A12);
	textlayer2A_12.setText(A12);
}

public void setA13(String a13) {
	A13 = a13;
	if(A13.length()>1 ){
		textlayer1A_13.setBounds(27, 38, 37, 23);
		textlayer2A_13.setBounds(27, 38, 37, 23);
		}
		else {
			if(A13.equals("1"))

			textlayer1A_13.setBounds(32, 31, 33, 37);
	    	textlayer2A_13.setBounds(32, 31, 33, 37);
		}
	textlayer1A_13.setText(A13);
	textlayer2A_13.setText(A13);
}

public void setA14(String a14) {
	A14 = a14;
	if(A14.length()>1 ){
		textlayer1A_14.setBounds(27, 38, 37, 23);
		textlayer2A_14.setBounds(27, 38, 37, 23);
		}
		else {
			if(A14.equals("1"))
			textlayer1A_14.setBounds(32, 31, 33, 37);
	    	textlayer2A_14.setBounds(32, 31, 33, 37);
		}
	textlayer1A_14.setText(A14);
	textlayer2A_14.setText(A14);
}

public void setA15(String a15) {
	A15 = a15;
	if(A15.length()>1 ){
		textlayer1A_15.setBounds(27, 38, 37, 23);
		textlayer2A_15.setBounds(27, 38, 37, 23);
		}
		else {
			if(A15.equals("1"))

			textlayer1A_15.setBounds(32, 31, 33, 37);
	    	textlayer2A_15.setBounds(32, 31, 33, 37);
		}
	textlayer1A_15.setText(A15);
	textlayer2A_15.setText(A15);
}

public void setA16(String a16) {
	A16 = a16;
	if(A16.length()>1 ){
		textlayer1A_16.setBounds(27, 38, 37, 23);
		textlayer2A_16.setBounds(27, 38, 37, 23);
		}
		else {
			if(A16.equals("1"))
			textlayer1A_16.setBounds(32, 31, 33, 37);
	    	textlayer2A_16.setBounds(32, 31, 33, 37);
		}
	textlayer1A_16.setText(A16);
	textlayer2A_16.setText(A16);
}

public void setA17(String a17) {
	A17 = a17;
	if(A17.length()>1 ){
		textlayer1A_17.setBounds(27, 38, 37, 23);
		textlayer2A_17.setBounds(27, 38, 37, 23);
		}
		else {
			if(A17.equals("1"))

			textlayer1A_17.setBounds(32, 31, 33, 37);
	    	textlayer2A_17.setBounds(32, 31, 33, 37);
		}
	textlayer1A_17.setText(A17);
	textlayer2A_17.setText(A17);
}


public void setA21(String a21) {
	A21 = a21;
	if(A21.length()>1 ){
		textlayer1A_21.setBounds(27, 38, 37, 23);
		textlayer2A_21.setBounds(27, 38, 37, 23);
		}
		else {
			if(A21.equals("1"))

			textlayer1A_21.setBounds(32, 31, 33, 37);
	    	textlayer2A_21.setBounds(32, 31, 33, 37);
		}
	textlayer1A_21.setText(A21);
	textlayer2A_21.setText(A21);
}

public void setA22(String a22) {
	A22 = a22;
	if(A22.length()>1 ){
		textlayer1A_22.setBounds(27, 38, 37, 23);
		textlayer2A_22.setBounds(27, 38, 37, 23);
		}
		else {
			if(A22.equals("1"))

			textlayer1A_22.setBounds(32, 31, 33, 37);
	    	textlayer2A_22.setBounds(32, 31, 33, 37);
		}
	textlayer1A_22.setText(A22);
	textlayer2A_22.setText(A22);
}

public void setA23(String a23) {
	A23 = a23;
	if(A23.length()>1 ){
		textlayer1A_23.setBounds(27, 38, 37, 23);
		textlayer2A_23.setBounds(27, 38, 37, 23);
		}
		else {
			if(A23.equals("1"))

			textlayer1A_23.setBounds(32, 31, 33, 37);
	    	textlayer2A_23.setBounds(32, 31, 33, 37);
		}
	textlayer1A_23.setText(A23);
	textlayer2A_23.setText(A23);
}

public void setA24(String a24) {
	A24 = a24;
	if(A24.length()>1 ){
		textlayer1A_24.setBounds(27, 38, 37, 23);
		textlayer2A_24.setBounds(27, 38, 37, 23);
		}
		else {
			textlayer1A_24.setBounds(32, 31, 33, 37);
	    	textlayer2A_24.setBounds(32, 31, 33, 37);
		}
	textlayer1A_24.setText(A24);
	textlayer2A_24.setText(A24);
}

public void setA25(String a25) {
	A25 = a25;
	if(A25.length()>1 ){
		textlayer1A_25.setBounds(27, 38, 37, 23);
		textlayer2A_25.setBounds(27, 38, 37, 23);
		}
		else {
			textlayer1A_25.setBounds(32, 31, 33, 37);
	    	textlayer2A_25.setBounds(32, 31, 33, 37);
		}
	textlayer1A_25.setText(A25);
	textlayer2A_25.setText(A25);
}

public void setA26(String a26) {
	A26 = a26;
	if(A26.length()>1 ){
		textlayer1A_26.setBounds(27, 38, 37, 23);
		textlayer2A_26.setBounds(27, 38, 37, 23);
		}
		else {
			textlayer1A_26.setBounds(32, 31, 33, 37);
	    	textlayer2A_26.setBounds(32, 31, 33, 37);
		}
	textlayer1A_26.setText(A26);
	textlayer2A_26.setText(A26);
}

public void setA27(String a27) {
	A27 = a27;
	if(A27.length()>1 ){
		textlayer1A_27.setBounds(27, 38, 37, 23);
		textlayer2A_27.setBounds(27, 38, 37, 23);
		}
		else {
			textlayer1A_27.setBounds(32, 31, 33, 37);
	    	textlayer2A_27.setBounds(32, 31, 33, 37);
		}
	textlayer1A_27.setText(A27);
	textlayer2A_27.setText(A27);
}

public void setH11(String h11) {
	H11 = h11;
	if(H11.length()>1 ){
		textlayer1H_11.setBounds(100, 71, 45, 50);
		textlayer2H_11.setBounds(100, 71, 45, 50);
		}
		else {
			if(H11.equals("1")) {
			textlayer1H_11.setBounds(106, 71, 45, 50);
			textlayer2H_11.setBounds(106, 71, 45, 50);
			}
			textlayer1H_11.setBounds(105, 71, 45, 50);
	       	textlayer2H_11.setBounds(105, 71, 45, 50);
		}
	textlayer1H_11.setText(H11);
	textlayer2H_11.setText(H11);
}

public void setH12(String h12) {
	H12 = h12;
	if(H12.length()>1 ){
		textlayer1H_12.setBounds(100, 71, 45, 50);
		textlayer2H_12.setBounds(100, 71, 45, 50);
		}
		else {
			if(H12.equals("1")) {
			textlayer1H_12.setBounds(106, 71, 45, 50);
			textlayer2H_12.setBounds(106, 71, 45, 50);
			}
			textlayer1H_12.setBounds(105, 71, 45, 50);
	       	textlayer2H_12.setBounds(105, 71, 45, 50);
		}
	textlayer1H_12.setText(H12);
	textlayer2H_12.setText(H12);
}

public void setH13(String h13) {
	H13 = h13;
	if(H13.length()>1 ){
		textlayer1H_13.setBounds(100, 71, 45, 50);
		textlayer2H_13.setBounds(100, 71, 45, 50);
		}
		else {
			if(H13.equals("1")) {

			textlayer1H_13.setBounds(106, 71, 45, 50);
			textlayer2H_13.setBounds(106, 71, 45, 50);
			}
			textlayer1H_13.setBounds(105, 71, 45, 50);
	       	textlayer2H_13.setBounds(105, 71, 45, 50);
		}
	textlayer1H_13.setText(H13);
	textlayer2H_13.setText(H13);
}

public void setH14(String h14) {
	H14 = h14;
	if(H14.length()>1 ){
		textlayer1H_14.setBounds(100, 71, 45, 50);
		textlayer2H_14.setBounds(100, 71, 45, 50);
		}
		else {
			if(H14.equals("1")) {
	
			textlayer1H_14.setBounds(106, 71, 45, 50);
			textlayer2H_14.setBounds(106, 71, 45, 50);
			}
			textlayer1H_14.setBounds(105, 71, 45, 50);
	       	textlayer2H_14.setBounds(105, 71, 45, 50);
		}
	textlayer1H_14.setText(H14);
	textlayer2H_14.setText(H14);
}

public void setH15(String h15) {
	H15 = h15;
	if(H15.length()>1 ){
		textlayer1H_15.setBounds(100, 71, 45, 50);
		textlayer2H_15.setBounds(100, 71, 45, 50);
		}
		else {
			if(H15.equals("1")) {
	
			textlayer1H_15.setBounds(106, 71, 45, 50);
			textlayer2H_15.setBounds(106, 71, 45, 50);
			}
			textlayer1H_15.setBounds(105, 71, 45, 50);
	       	textlayer2H_15.setBounds(105, 71, 45, 50);
		}
	textlayer1H_15.setText(H15);
	textlayer2H_15.setText(H15);
}

public void setH16(String h16) {
	H16 = h16;
	if(H16.length()>1 ){
		textlayer1H_16.setBounds(100, 71, 45, 50);
		textlayer2H_16.setBounds(100, 71, 45, 50);
		}
		else {
			if(H16.equals("1")) {

			textlayer1H_16.setBounds(106, 71, 45, 50);
			textlayer2H_16.setBounds(106, 71, 45, 50);
			}
			textlayer1H_16.setBounds(105, 71, 45, 50);
	       	textlayer2H_16.setBounds(105, 71, 45, 50);
		}
	textlayer1H_16.setText(H16);
	textlayer2H_16.setText(H16);
}

public void setH17(String h17) {
	H17 = h17;
	if(H17.length()>1 ){
		textlayer1H_17.setBounds(100, 71, 45, 50);
		textlayer2H_17.setBounds(100, 71, 45, 50);
		}
		else {
			if(H17.equals("1")) {
		
			textlayer1H_17.setBounds(106, 71, 45, 50);
			textlayer2H_17.setBounds(106, 71, 45, 50);
			}
			textlayer1H_17.setBounds(105, 71, 45, 50);
	       	textlayer2H_17.setBounds(105, 71, 45, 50);
		}
	textlayer1H_17.setText(H17);
	textlayer2H_17.setText(H17);
}

public void setH21(String h21) {
	H21 = h21;
	if(H21.length()>1 ){
		textlayer1H_21.setBounds(100, 71, 45, 50);
		textlayer2H_21.setBounds(100, 71, 45, 50);
		}
		else {
			if(H21.equals("1")) {
			textlayer1H_21.setBounds(106, 71, 45, 50);
			textlayer2H_21.setBounds(106, 71, 45, 50);
			}
			textlayer1H_21.setBounds(105, 71, 45, 50);
	       	textlayer2H_21.setBounds(105, 71, 45, 50);
		}
	textlayer1H_21.setText(H21);
	textlayer2H_21.setText(H21);
}

public void setH22(String h22) {
	H22 = h22;
	if(H22.length()>1 ){
		textlayer1H_22.setBounds(100, 71, 45, 50);
		textlayer2H_22.setBounds(100, 71, 45, 50);
		}
		else {
			if(H22.equals("1")) {
			textlayer1H_22.setBounds(106, 71, 45, 50);
			textlayer2H_22.setBounds(106, 71, 45, 50);
			}
			textlayer1H_22.setBounds(105, 71, 45, 50);
	       	textlayer2H_22.setBounds(105, 71, 45, 50);
		}
	textlayer1H_22.setText(H22);
	textlayer2H_22.setText(H22);
}

public void setH23(String h23) {
	H23 = h23;
	if(H23.length()>1 ){
		textlayer1H_23.setBounds(100, 71, 45, 50);
		textlayer2H_23.setBounds(100, 71, 45, 50);
		}
		else {
			if(H23.equals("1")) {

			textlayer1H_23.setBounds(106, 71, 45, 50);
			textlayer2H_23.setBounds(106, 71, 45, 50);
			}
			textlayer1H_23.setBounds(105, 71, 45, 50);
	       	textlayer2H_23.setBounds(105, 71, 45, 50);
		}
	textlayer1H_23.setText(H23);
	textlayer2H_23.setText(H23);
}

public void setH24(String h24) {
	H24 = h24;
	if(H24.length()>1 ){
		textlayer1H_24.setBounds(100, 71, 45, 50);
		textlayer2H_24.setBounds(100, 71, 45, 50);
		}
		else {
			if(H24.equals("1")) {

			textlayer1H_24.setBounds(106, 71, 45, 50);
			textlayer2H_24.setBounds(106, 71, 45, 50);
			}
			textlayer1H_24.setBounds(105, 71, 45, 50);
	       	textlayer2H_24.setBounds(105, 71, 45, 50);
		}
	textlayer1H_24.setText(H24);
	textlayer2H_24.setText(H24);
}

public void setH25(String h25) {
	H25 = h25;
	if(H25.length()>1 ){
		textlayer1H_25.setBounds(100, 71, 45, 50);
		textlayer2H_25.setBounds(100, 71, 45, 50);
		}
		else {
			if(H25.equals("1")) {
			
			textlayer1H_25.setBounds(106, 71, 45, 50);
			textlayer2H_25.setBounds(106, 71, 45, 50);
			}
			textlayer1H_25.setBounds(105, 71, 45, 50);
	       	textlayer2H_25.setBounds(105, 71, 45, 50);
		}
	textlayer1H_25.setText(H25);
	textlayer2H_25.setText(H25);
}

public void setH26(String h26) {
	H26 = h26;
	if(H26.length()>1 ){
		textlayer1H_26.setBounds(100, 71, 45, 50);
		textlayer2H_26.setBounds(100, 71, 45, 50);
		}
		else {
			if(H26.equals("1")) {
		
			textlayer1H_26.setBounds(106, 71, 45, 50);
			textlayer2H_26.setBounds(106, 71, 45, 50);
			}
			textlayer1H_26.setBounds(105, 71, 45, 50);
	       	textlayer2H_26.setBounds(105, 71, 45, 50);
		}
	textlayer1H_26.setText(H26);
	textlayer2H_26.setText(H26);
}

public void setH27(String h27) {
	H27 = h27;
	if(H27.length()>1 ){
		textlayer1H_27.setBounds(100, 71, 45, 50);
		textlayer2H_27.setBounds(100, 71, 45, 50);
		}
		else {
			if(H27.equals("1")) {

			textlayer1H_27.setBounds(106, 71, 45, 50);
			textlayer2H_27.setBounds(106, 71, 45, 50);
			}
			textlayer1H_27.setBounds(105, 71, 45, 50);
	       	textlayer2H_27.setBounds(105, 71, 45, 50);
		}
	textlayer1H_27.setText(H27);
	textlayer2H_27.setText(H27);
}

public void setH1(String h1) {
	H1 = h1;
	if(H1.length()>1 ){
		 textlayer1H_1.setBounds(162, 159, 60, 80);
		   textlayer2H_1.setBounds(162, 159, 60, 80);
		}
		else {
			if(H1.equals("1")) {

			textlayer1H_1.setBounds(172, 160, 60, 80);
			textlayer2H_1.setBounds(172, 160, 60, 80);
			}
			textlayer1H_1.setBounds(171, 160, 60, 80);
	       	textlayer2H_1.setBounds(171, 160, 60, 80);
		}
	textlayer1H_1.setText(H1);
	textlayer2H_1.setText(H1);
}


public void setH2(String h2) {
	H2 = h2;
	if(H2.length()>1 ){
		 textlayer1H_2.setBounds(162, 159, 60, 80);
		   textlayer2H_2.setBounds(162, 159, 60, 80);
		}
		else {
			if(H2.equals("1")) {
			textlayer1H_2.setBounds(172, 160, 60, 80);
			textlayer2H_2.setBounds(172, 160, 60, 80);
			}
			textlayer1H_2.setBounds(171, 160, 60, 80);
	       	textlayer2H_2.setBounds(171, 160, 60, 80);
		}
	textlayer1H_2.setText(H2);
	textlayer2H_2.setText(H2);
}





public void setvisibility11(Boolean b) {
attack11.setVisible(b);
textlayer1A_11.setVisible(b);
textlayer2A_11.setVisible(b);
health11.setVisible(b);
textlayer1H_11.setVisible(b);
textlayer2H_11.setVisible(b);
}

public void setvisibility12(Boolean b) {
attack12.setVisible(b);
textlayer1A_12.setVisible(b);
textlayer2A_12.setVisible(b);
health12.setVisible(b);
textlayer1H_12.setVisible(b);
textlayer2H_12.setVisible(b);
}

public void setvisibility13(Boolean b) {
attack13.setVisible(b);
textlayer1A_13.setVisible(b);
textlayer2A_13.setVisible(b);
health13.setVisible(b);
textlayer1H_13.setVisible(b);
textlayer2H_13.setVisible(b);
}

public void setvisibility14(Boolean b) {
attack14.setVisible(b);
textlayer1A_14.setVisible(b);
textlayer2A_14.setVisible(b);
health14.setVisible(b);
textlayer1H_14.setVisible(b);
textlayer2H_14.setVisible(b);
}

public void setvisibility15(Boolean b) {
attack15.setVisible(b);
textlayer1A_15.setVisible(b);
textlayer2A_15.setVisible(b);
health15.setVisible(b);
textlayer1H_15.setVisible(b);
textlayer2H_15.setVisible(b);
}

public void setvisibility16(Boolean b) {
attack16.setVisible(b);
textlayer1A_16.setVisible(b);
textlayer2A_16.setVisible(b);
health16.setVisible(b);
textlayer1H_16.setVisible(b);
textlayer2H_16.setVisible(b);
}

public void setvisibility17(Boolean b) {
attack17.setVisible(b);
textlayer1A_17.setVisible(b);
textlayer2A_17.setVisible(b);
health17.setVisible(b);
textlayer1H_17.setVisible(b);
textlayer2H_17.setVisible(b);
}

public void setvisibility21(Boolean b) {
attack21.setVisible(b);
textlayer1A_21.setVisible(b);
textlayer2A_21.setVisible(b);
health21.setVisible(b);
textlayer1H_21.setVisible(b);
textlayer2H_21.setVisible(b);
}

public void setvisibility22(Boolean b) {
attack22.setVisible(b);
textlayer1A_22.setVisible(b);
textlayer2A_22.setVisible(b);
health22.setVisible(b);
textlayer1H_22.setVisible(b);
textlayer2H_22.setVisible(b);
}

public void setvisibility23(Boolean b) {
attack23.setVisible(b);
textlayer1A_23.setVisible(b);
textlayer2A_23.setVisible(b);
health23.setVisible(b);
textlayer1H_23.setVisible(b);
textlayer2H_23.setVisible(b);
}

public void setvisibility24(Boolean b) {
attack24.setVisible(b);
textlayer1A_24.setVisible(b);
textlayer2A_24.setVisible(b);
health24.setVisible(b);
textlayer1H_24.setVisible(b);
textlayer2H_24.setVisible(b);
}

public void setvisibility25(Boolean b) {
attack25.setVisible(b);
textlayer1A_25.setVisible(b);
textlayer2A_25.setVisible(b);
health25.setVisible(b);
textlayer1H_25.setVisible(b);
textlayer2H_25.setVisible(b);
}

public void setvisibility26(Boolean b) {
attack26.setVisible(b);
textlayer1A_26.setVisible(b);
textlayer2A_26.setVisible(b);
health26.setVisible(b);
textlayer1H_26.setVisible(b);
textlayer2H_26.setVisible(b);
}

public void setvisibility27(Boolean b) {
attack27.setVisible(b);
textlayer1A_27.setVisible(b);
textlayer2A_27.setVisible(b);
health27.setVisible(b);
textlayer1H_27.setVisible(b);
textlayer2H_27.setVisible(b);
}


public void setvisibility1(Boolean b) {
	health1.setVisible(b);
	textlayer1H_1.setVisible(b);
	textlayer2H_1.setVisible(b);
}

public void setvisibility2(Boolean b) {
	health2.setVisible(b);
	textlayer1H_2.setVisible(b);
	textlayer2H_2.setVisible(b);
}





public static void main (String[]  a) {
new GameView();
//System.out.println()


}
}