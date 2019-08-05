package Whiteboard;

/* Created by Vidush Namah on 2/15/2017 */

import Interface.Board;
import Services.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIThread implements Runnable {

    public volatile static Board Canvas;
    public static UIFrame Frame;

    @Override
    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                Canvas = new Board();
                Frame = new UIFrame(Canvas);

                Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                Frame.setVisible(true);
                Frame.setResizable(true);
                Frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                Frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if (JOptionPane.showConfirmDialog(Frame,
                                "CONFIRM ACTION: CLOSE WINDOW?",
                                "Goodbye", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            Connection.DisconnectFromServer();
                            System.exit(0);
                        }
                        else return;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}