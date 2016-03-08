import java.awt.*;

import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.lang.Object; 
import java.util.*;
import javax.swing.*;

public class gameBoard extends JFrame implements ActionListener {
   JLayeredPane layeredPane; 
   JPanel board; 
   JButton[] playerButtons = {new JButton("2 Players"), new JButton("3 Players"), new JButton("4 Players"), new JButton("5 Players"), new JButton("6 Players"), new JButton("7 Players"), new JButton("8 Players")};;
   JPanel info;
   int numPlayers;
   
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
      info.setLayout(new GridLayout(8,1));
      info.setPreferredSize(new Dimension(130,950));
      info.setBounds(boardSize.width, 0, 130, 950);
      for (int j = 0; j < 7; j++){
         info.add(playerButtons[j]);   
      }
      layeredPane.add(info, JLayeredPane.DEFAULT_LAYER);
   }
   public int findPlayers(){
      for (int q = 0; q < 7; q++){
         playerButtons[q].addActionListener(this);
         playerButtons[q].setActionCommand(String.valueOf(q + 2));
      }
      return numPlayers;
   }
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