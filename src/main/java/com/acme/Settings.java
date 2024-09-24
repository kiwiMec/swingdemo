package com.acme;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Settings extends JPanel implements KeyListener {

    private final JPanel cards;
    private int boardWidth, boardHeight, tileWidth, tileHeight, numberOfSnakes;
    private boolean usePills;
    private JFrame frame;

    Settings(JPanel cards, JFrame frame) {
        // store the initial settings
        this.cards = cards;
        this.frame = frame;
        this.boardWidth = 600;
        this.boardHeight = 600;
        this.tileWidth = 25;
        this.tileHeight = 25;
        this.numberOfSnakes = 1;
        this.usePills = true;
        // set up this panel
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.LIGHT_GRAY);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                usePills = !usePills;
                break;
            case KeyEvent.VK_ESCAPE:
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, "GAMEBOARD");
                cards.transferFocus();
                break;
        }
    }

    public JPanel getCards() {
        return this.cards;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public int getNumberOfSnakes() {
        return this.numberOfSnakes;
    }

    public boolean usePills() {
        return this.usePills;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not needed
    }

}
