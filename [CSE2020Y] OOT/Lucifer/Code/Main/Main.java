package Main; /**
 * Created by Vidush H. Namah on 05 Mar 2016.
 **/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

    private static JFrame frame = new JFrame();
    public static GUI UI = null;

    public static JFrame getMotherOfAllFrames() {
        return frame;
    }

    public static void main (String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                initFrame();
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
    }

    public static void destroy() {

    }

    public static void initFrame() {
        //SINGLETON PATTERN
        UI = GUI.initGUI();

        if(UI!=null) {
            frame.setVisible(true);
            frame.setTitle("LUCIFER V1.0");
            frame.setPreferredSize(new Dimension(1024, 768));

            frame.setLayout(new BorderLayout());
            frame.add(UI);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.pack();
        }
    }
}

