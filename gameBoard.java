import java.awt.*;

import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.lang.Object; 
import java.util.*;
import javax.swing.*;

public class gameBoard extends JFrame {
   JLayeredPane layeredPane; 
   JPanel board; 
   
   public gameBoard() {
      Dimension boardSize = new Dimension(1170, 882); 
      Dimension paneSize = new Dimension(1170, 950);
   
   
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(paneSize); 
   
      /*board = new JPanel(); 
      layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
      board.setLayout( new GridLayout(2, 2) ); 
      board.setPreferredSize( boardSize );  
      board.setBounds(0, 0, boardSize.width, boardSize.height);
      
      String[] imageArray = {"deadwoodboardstopleft.jpg", "deadwoodboardstopright.jpg", "deadwoodboardsbotleft.jpg", "deadwoodboardsbotright.jpg"};
      
      for (int i = 0; i < 4; i++){
         JLabel image = new JLabel(new ImageIcon(imageArray[i]));
         board.add(image);
      }  */     
      JPanel controls = new JPanel();
      controls.setLayout(new GridLayout(1, 4));
      controls.setPreferredSize(paneSize);
      controls.setBounds(0,boardSize.height, paneSize.width, paneSize.height);
      JButton act = new JButton("Act");
      act.setPreferredSize(new Dimension(100, 60));
      JButton move = new JButton("Move");
      move.setPreferredSize(new Dimension(100, 60));
      controls.add(act);
      controls.add(move);
      layeredPane.add(controls, JLayeredPane.DEFAULT_LAYER);
   }
}