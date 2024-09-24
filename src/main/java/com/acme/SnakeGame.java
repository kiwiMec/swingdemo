package com.acme;

import java.awt.CardLayout;
import java.awt.Color;
 import javax.swing.JFrame;
import javax.swing.JPanel;

public final class SnakeGame {

    public static void main(String[] args) {

        // create the window
        JFrame frame = new JFrame("SnakeGame");

        // create the game panels
        JPanel cards = new JPanel(new CardLayout());
        Settings settings = new Settings(cards, frame);
        cards.add(settings, "SETTINGS");
        Board board = new Board(settings, frame);
        cards.add(board, "GAMEBOARD");

        // set up the window and add the panels
        frame.setSize(settings.getBoardWidth(), settings.getBoardHeight()+3);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cards);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);

        // show game board first
        ((CardLayout)(cards.getLayout())).show(cards, "GAMEBOARD");
        cards.transferFocus();
    }

}
