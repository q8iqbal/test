package tictactoe.controller;

import java.io.IOException;
import java.net.Socket;
import tictactoe.model.Connection;
import tictactoe.model.GameEndPacket;
import tictactoe.model.UpdatePacket;

/**
 *
 * @author fajar
 */
public class ClientController extends GameController {
    public ClientController() {
        super(2);//assign wich is player
        try {
            socket = new Socket("localhost", gameProperty.getPORT());
            connection = new Connection(this, socket);
        } catch (IOException ex) {
        }
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof UpdatePacket) {
            UpdatePacket packet = (UpdatePacket) object;
            fields = packet.getFields();
            currentPlayer = packet.getCurrentPlayer();
        } else if(object instanceof GameEndPacket) {
            GameEndPacket packet = (GameEndPacket) object;
            showWinner(packet.getWinner());
        }
        gamePanel.repaint();
    }
    
}