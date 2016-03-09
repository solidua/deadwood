import java.awt.*;

import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.lang.Object; 
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;



public class gameBoard extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane; 
    JPanel board;
    JPanel info;
    
    JLabel gameInfo = new JLabel("Days Left: " + Deadwood.daysLeft);
    JLabel[] playerInfo = {new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4"), new JLabel("Player 5"), new JLabel("Player 6"), new JLabel("Player 7"), new JLabel("Player 8")};;
    JLabel playerPiece;
    JLabel[] playerLabels;
    
    ImageIcon[] playerImages;
    
    int xAdjustment;
    int yAdjustment;
    static int numPlayers;
    Dimension boardSize = new Dimension(1170, 882);
    Dimension paneSize = new Dimension(1300, 950);
   
    //Constructor
    public gameBoard() {
	   layeredPane = new JLayeredPane();
	   getContentPane().add(layeredPane);
	   layeredPane.setPreferredSize(paneSize);
      
      //Add the playable area to the gameBoard
	   board = new JPanel(); 
	   layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
	   board.setLayout( new GridLayout(2, 2) ); 
	   board.setPreferredSize( boardSize );  
	   board.setBounds(0, 0, boardSize.width, boardSize.height);      
	   String[] backgroundArray = {"deadwoodboardstopleft.jpg", "deadwoodboardstopright.jpg", "deadwoodboardsbotleft.jpg", "deadwoodboardsbotright.jpg"};      
	   for (int i = 0; i < 4; i++){
	      JLabel background = new JLabel(new ImageIcon(backgroundArray[i]));
	      board.add(background);
	   }
      
      //Add lower buttons to gameBoard
	   JPanel controls = new JPanel();
	   controls.setLayout(new GridLayout(1, 4));
	   controls.setPreferredSize(new Dimension (1170, 68));
	   controls.setBounds(0,boardSize.height, 1170, 68);
	   JButton act = new JButton("Act");
	   act.setPreferredSize(new Dimension(50, 30));
	   JButton move = new JButton("Rehearse");
	   move.setPreferredSize(new Dimension(50, 30));
	   JButton upgradeMoney = new JButton("Upgrade with Money");
	   upgradeMoney.setPreferredSize(new Dimension(50, 30));
	   JButton upgradeCredit = new JButton("Upgrade with Credit");
	   upgradeCredit.setPreferredSize(new Dimension(50, 30));
	   controls.add(act);
	   controls.add(move);
	   controls.add(upgradeMoney);
	   controls.add(upgradeCredit);
	   layeredPane.add(controls, JLayeredPane.DEFAULT_LAYER);
      
      //Add right info panel
   	info = new JPanel();   
   	info.setLayout(new GridLayout(9,1));
   	info.setPreferredSize(new Dimension(130,950));
   	info.setBounds(boardSize.width, 0, 130, 950);
   	info.add(gameInfo);
   	for (int j = 0; j < 8; j++){
   	    info.add(playerInfo[j]);  
   	}
   	layeredPane.add(info, JLayeredPane.DEFAULT_LAYER);
    }
   
    public static void gameStart(){
	   JOptionPane.showMessageDialog(null, "Welcome to Deadwood!");
    }
   
    //Asks user for input of number of players
    public static int findPlayers(){   
   	Object[] possibilities = {"2", "3", "4", "5", "6", "7", "8"};
   	String picked = (String)JOptionPane.showInputDialog(
   							    null,                                  //what JFrame
   							    "Choose your number of players.\n",    //Text in box
   							    "Pick Number of Players",              //Text labeling box
   							    JOptionPane.DEFAULT_OPTION,            //What automatic buttons
   							    null,                                  //What icon
   							    possibilities,                         //Things in dropdown menu
   							    "0"                                    //Default option
   							    );
   	Integer numPlayers = Integer.parseInt(picked);
   	return numPlayers;
    }
   
    //Implementation of MouseListener and MouseMotionListener interfaces
    public void mousePressed(MouseEvent click){
    }   
    public void mouseReleased(MouseEvent click){	   
    }    
    public void mouseDragged(MouseEvent me){      
    }    
    public void mouseClicked(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
   
    //Creates icons for player, puts images in playerImages[]
    public void makePlayerIcons(Player[] players){
	   int numPlayers = players.length;
   	String[] playerNames = new String[numPlayers];
   	playerImages = new ImageIcon[numPlayers];
   	Font font = new Font("Tahoma", Font.PLAIN, 24);
   	FontRenderContext frc = new FontRenderContext(null, true, true);
      Color[] playerColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.RED, Color.ORANGE, Color.MAGENTA};
   	for(int i = 0; i < numPlayers; i++){
   	    //Make player icons
   	    playerNames[i] = "P" + i;         
   	    Rectangle2D bounds = font.getStringBounds(playerNames[i], frc);
   	    int w = 40;
   	    int h = 40;
   	    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
   	    Graphics2D g = image.createGraphics();
   	    g.setColor(Color.WHITE);
   	    g.fillRect(0, 0, w, h);          
   	    g.setColor(playerColors[i]);
   	    g.setFont(font);
   	    g.fillOval(w/2 - 10, h/2 - 10, 20, 20); 
   	    g.dispose();
   	    playerImages[i] = new ImageIcon(image);
   	    bounds = null;
   	    g = null;
   	    image = null;         
   	}
      putIconsOnBoard();
   }
    
    //Puts player icons in the Trailers room
    public void putIconsOnBoard(){	       
      numPlayers = playerImages.length;
      playerLabels = new JLabel[numPlayers];
      
	   for(int i = 0; i < numPlayers; i++){         
         playerLabels[i] = new JLabel(playerImages[i]);
         playerLabels[i].setBounds(940 + (i%4)*playerImages[i].getIconWidth() + 25, 260 + (i/4)*playerImages[i].getIconHeight(), playerImages[i].getIconWidth(), playerImages[i].getIconHeight());
         playerLabels[i].addMouseListener(new MouseAdapter(){
               @Override 
               public void mousePressed(MouseEvent click){
                  playerPiece = null;
                  JComponent c = (JComponent) click.getSource();
                  if(c instanceof JPanel) return; 
                  playerPiece = (JLabel) c; 
                  
                  xAdjustment = click.getX(); 
                  yAdjustment = click.getY(); 
               }
         });
         
         playerLabels[i].addMouseMotionListener(new MouseMotionAdapter(){
               @Override
               public void mouseDragged(MouseEvent me){
                  if(playerPiece == null) return;
                  playerPiece.setLocation(playerPiece.getLocation().x + me.getX() - xAdjustment, playerPiece.getLocation().y + me.getY() - yAdjustment);
               }
         });
                  
         layeredPane.add(playerLabels[i], new Integer(1), 0);
	   }
    }
   
   //Updates the side info panel
   public int displayPlayerInfo(Player[] players, int playerNum){
	   int numPlayers = players.length;    
	   gameInfo.setText("Days Left: " + Deadwood.daysLeft);
	   for(int i = 0; i < numPlayers; i++){             
	       playerInfo[i].setVerticalTextPosition(JLabel.TOP);
	       playerInfo[i].setHorizontalTextPosition(JLabel.LEFT);
          String[] playerColors = {"Black", "Blue", "Cyan", "Green", "Yellow", "Red", "Orange", "Magenta"};
	       if(i == playerNum && playerNum >= 0){
	   	      playerInfo[i].setText("<html>Player " + (i + 1) + 
                     "<br>You are " + playerColors[i] +
	   			      "<br>It is your Turn!" + 
	   			      "<br>Credits: " + players[i].getCredits() + 
	   			      "<br>Money: " + players[i].getMoney() + 
	   			      "<br>Rank: " + players[i].getRank() + "</html>");
	       }else{         
	   	      playerInfo[i].setText("<html>Player " + (i + 1) + 
                     "<br>You are " + playerColors[i].toString() +
	   			      "<br>Credits: " + players[i].getCredits() + 
	   			      "<br>Money: " + players[i].getMoney() + 
	   			      "<br>Rank: " + players[i].getRank() + "</html>");
	       }
	   }
      //Players not in the game removed from info panel
	   for(; numPlayers < 8; numPlayers++){
	       playerInfo[numPlayers].setText("");
	   }
	   return 0;
   }

    public int getNumPlayers(){
	   return numPlayers;
    }
   
}
