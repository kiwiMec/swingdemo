package com.acme;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class SnakeGame {

    public static void main(String[] args) {

        // create the game panels
        JPanel cards = new JPanel(new CardLayout());
        Settings settings = new Settings(cards);
        cards.add(settings, "SETTINGS");
        Board board = new Board(settings);
        cards.add(board, "GAMEBOARD");

        // create the window and add the panels
        JFrame frame = new JFrame("frame");
        frame.setSize(settings.getBoardWidth(), settings.getBoardHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cards);
        frame.setVisible(true);

        // show game board first
        ((CardLayout)(cards.getLayout())).show(cards, "GAMEBOARD");
        cards.transferFocus();
    }

}
