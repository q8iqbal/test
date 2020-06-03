package tictactoe.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import javax.swing.JOptionPane;
import tictactoe.model.Connection;
import tictactoe.model.GameProperty;
import tictactoe.view.GameFrame;
import tictactoe.view.GamePanel;

public abstract class GameController {
    protected GameProperty gameProperty;
    protected int[][] fields;
    protected int currentPlayer;
    protected int thisPlayer;
    protected GamePanel gamePanel;
    protected Socket socket;
    protected Connection connection;
    
    public abstract void packetReceived(Object object);
    public abstract void inputReceived(int x, int y);

    public GameController(int thisPlayer) {
        gameProperty = new GameProperty();
        this.thisPlayer = thisPlayer;
        gamePanel = new GamePanel(this);
        GameFrame gameFrame = new GameFrame(this, gameProperty.getWIDTH(), gameProperty.getHEIGHT());
        fields = new int[3][3];
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        currentPlayer = gameProperty.getPLAYER_ONE();
    }
    
    public void close(){
        connection.close();
        try {
            socket.close();
        } catch (IOException ex) {
            
        }
    }   
    
    public int[][] getFields() {
        return fields;
    }

    protected boolean isMyTurn() {
        return thisPlayer == currentPlayer;
    }
    
    protected void showWinner(int winner) {
        if(winner == gameProperty.getNOBODY()) {
            JOptionPane.showMessageDialog(null, "TIE!");
        }else {
            JOptionPane.showMessageDialog(null, "The player " + winner + " has won the game!");
        }
    }  
    
    
}