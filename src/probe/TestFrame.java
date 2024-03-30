package probe;

import chat.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class TestFrame extends JFrame {

    public static void createGUI() {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TestWindowListener winListener = new TestWindowListener();
        frame.addWindowListener(winListener);

        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }

    public static class TestWindowListener implements WindowListener {

        public void windowActivated(WindowEvent e) {
            System.out.println("windowActivated");
        }

        public void windowClosed(WindowEvent e) {
            System.out.println("windowClosed");
        }

        public void windowClosing(WindowEvent e) {
            System.out.println("windowClosing");
        }

        public void windowDeactivated(WindowEvent e) {
            System.out.println("windowDeactivated");
        }

        public void windowDeiconified(WindowEvent e) {
            System.out.println("windowDeiconified");
        }

        public void windowIconified(WindowEvent e) {
            System.out.println("windowIconified");
        }

        public void windowOpened(WindowEvent e) {
            System.out.println("windowOpened");
        }
    }
}
