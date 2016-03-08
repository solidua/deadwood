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



public class gameBoard extends JFrame implements ActionListener {
   JLayeredPane layeredPane; 
   JPanel board; 
   JLabel gameInfo = new JLabel("Days Left: " + Deadwood.daysLeft);
   JLabel[] playerInfo = {new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4"), new JLabel("Player 5"), new JLabel("Player 6"), new JLabel("Player 7"), new JLabel("Player 8")};;
   JLabel playerPiece;
   ImageIcon[] playerImages;
   JPanel info;
   int xAdjustment;
   int yAdjustment;
   static int numPlayers;
   
   public gameBoard() {
      Dimension boardSize = new Dimension(1170, 882); 
      Dimension paneSize = new Dimension(1300, 950);
   
   
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(paneSize); 
   
      board = new JPanel(); 
      layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
      board.setLayout( new GridLayout(2, 2) ); 
      board.setPreferredSize( boardSize );  
      board.setBounds(0, 0, boardSize.width, boardSize.height);
      
      String[] imageArray = {"deadwoodboardstopleft.jpg", "deadwoodboardstopright.jpg", "deadwoodboardsbotleft.jpg", "deadwoodboardsbotright.jpg"};
      
      for (int i = 0; i < 4; i++){
         JLabel image = new JLabel(new ImageIcon(imageArray[i]));
         board.add(image);
      }       
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
   
   //Mouse clicking events
   //I have no JLabels to use as players to test this
   public void mousePressed(MouseEvent click){
      playerPiece = null;
      Component c = board.findComponentAt(click.getX(), click.getY());
      
      if(c instanceof JPanel) return;
      
      Point parentLocation = c.getParent().getLocation();
      xAdjustment = parentLocation.x - click.getX();
      yAdjustment = parentLocation.y - click.getY();
      playerPiece = (JLabel)c;
      playerPiece.setLocation(click.getX() + xAdjustment, click.getY() + yAdjustment);
      layeredPane.add(playerPiece, JLayeredPane.DRAG_LAYER);
   }
   
   public void mouseReleased(MouseEvent click){
      if(playerPiece == null) return;
      playerPiece.setVisible(false);
      Component c = board.findComponentAt(click.getX(), click.getY());
      
      if(c instanceof JLabel){
         Container parent = c.getParent();
         parent.remove(0);
         parent.add(playerPiece);      
      }else{
         Container parent = (Container)c;
         parent.add(playerPiece);
      }
      playerPiece.setVisible(true);
   }
   
   //Creates icons for player, puts images in playerImages[]
   public void makePlayerIcons(Player[] players){
      int numPlayers = players.length;
      String[] playerNames = new String[numPlayers];
      playerImages = new ImageIcon[numPlayers];
      Font font = new Font("Tahoma", Font.PLAIN, 11);
      FontRenderContext frc = new FontRenderContext(null, true, true);
      for(int i = 0; i < numPlayers; i++){
         //Make player icons
         playerNames[i] = "P" + i;         
         Rectangle2D bounds = font.getStringBounds(playerNames[i], frc);
         int w = (int)bounds.getWidth();
         int h = (int)bounds.getHeight();
         BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
         Graphics2D g = image.createGraphics();
         g.setColor(Color.WHITE);
         g.fillRect(0, 0, w, h);
         g.setColor(Color.BLACK);
         g.setFont(font);
         g.drawString(playerNames[i], (float)bounds.getX(), (float)bounds.getY());
         g.dispose();
         playerImages[i] = new ImageIcon(image);
         bounds = null;
         g = null;
         image = null;         
      }
   }
   
   
   public int displayPlayerInfo(Player[] players, int playerNum){
      int numPlayers = players.length;    
      gameInfo.setText("Days Left: " + Deadwood.daysLeft);
      for(int i = 0; i < numPlayers; i++){             
         //Update info panel
         playerInfo[i].setVerticalTextPosition(JLabel.TOP);
         playerInfo[i].setHorizontalTextPosition(JLabel.LEFT);
         //If better solution than "use HTML to have multiple lines" is found, implement here
         if(i == playerNum && playerNum >= 0){
            playerInfo[i].setText("<html>Player " + (i + 1) + 
                                  "<br>It is your Turn!" + 
                                  "<br>Credits: " + players[i].getCredits() + 
                                  "<br>Money: " + players[i].getMoney() + 
                                  "<br>Rank: " + players[i].getRank() + "</html>");
         }else{         
            playerInfo[i].setText("<html>Player " + (i + 1) + 
                                  "<br>Credits: " + players[i].getCredits() + 
                                  "<br>Money: " + players[i].getMoney() + 
                                  "<br>Rank: " + players[i].getRank() + "</html>");
         }
      }
      for(; numPlayers < 8; numPlayers++){
         playerInfo[numPlayers].setText("");
      }
      return 0;
   }
   
   public int getNumPlayers(){
      return numPlayers;
   }
   
   //Picking number of players handled by dropdown menu in gameBoard.findPlayers()
   //This does nothing currently   
   @Override
   public void actionPerformed (ActionEvent e){
      switch(e.getActionCommand()) {
		case "2":
         numPlayers = 2; 
         break;
		case "3": 
			numPlayers = 3;
			break; 
      case "4":
         numPlayers = 4;
         break;
		case "5": 
			numPlayers = 5;
			break; 
		case "6": 
			numPlayers = 6;
			break; 
		case "7": 
         numPlayers = 7;
         break;
		case "8":
			numPlayers = 8;
			break; 
		}
   }
}