//package view;




//USE THIS VIEW FOR TESTING ONLY















//import  sun.audio.*;    //import the sun.audio package
//import  java.io.*;
//import java.applet.Applet;
//import java.applet.AudioClip;
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.FontFormatException;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.GraphicsEnvironment;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.Shape;
//import java.awt.Toolkit;
//import java.awt.font.FontRenderContext;
//import java.awt.font.GlyphVector;
//import java.awt.font.TextLayout;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.*;
//
//public class GameView2 extends JFrame{
//	private JPanel All;
//	private JLabel card;
//	private JLabel health;
//	private JLabel attack;
//	private JTextArea test;
//	
//	public GameView2() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		   this.setVisible(true);
//			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//			this.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
//			// from a wave File
//			File soundFile = new File("images//ChillingMusic.wav");
//			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//			Clip clip = AudioSystem.getClip();
//			clip.open(audioIn);
//			clip.start(); 
//			 this.revalidate();
//			this.repaint() ;  }
//			   
//			   public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
//			      new GameView2();
//			   }
//			}