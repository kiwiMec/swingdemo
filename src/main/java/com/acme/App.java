package com.acme;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class App {

    public static void main(String[] args) {

        // create the game panels
        JPanel cards = new JPanel(new CardLayout());
        Settings settings = new Settings(cards);
        cards.add(new Settings(cards), "SETTINGS");
        Board board = new Board(settings);
        cards.add(board, "BOARD");

        // create the window and add the panels
        JFrame frame = new JFrame("frame");
        frame.setSize(settings.getWidth(), settings.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cards);
        frame.setVisible(true);
    }

}
