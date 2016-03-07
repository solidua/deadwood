import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class gameBoard extends JFrame {
   JLayeredPane layeredPane; 
   JPanel gameBoard; 
   
   public gameBoard() {
      Dimension boardSize = new Dimension(600, 600); 
   
   
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(boardSize); 
   
      gameBoard = new JPanel(); 
      layeredPane.add(gameBoard, JLayeredPane.DEFAULT_LAYER);
      gameBoard.setLayout( new GridLayout(8, 8) ); 
      gameBoard.setPreferredSize( boardSize );  
      gameBoard.setBounds(0, 0, boardSize.width, boardSize.height);
     
   }
}