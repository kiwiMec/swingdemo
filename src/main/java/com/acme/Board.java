package com.acme;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.LinkedList;

public class Board extends JPanel implements ActionListener, KeyListener {

    LinkedList<Piece> pieces;
    Settings settings;
    Timer actionLoop;

    Board(Settings settings) {

        this.settings = settings;

        setPreferredSize(new Dimension(settings.getBoardWidth(), settings.getBoardHeight()));
        setBackground(Color.darkGray);
        addKeyListener(this);
        setFocusable(true);

        pieces = new LinkedList<Piece>();

        pieces.add(new Walls(settings));
        pieces.add(new Snake(new Tile(10, 10, settings.getTileWidth(), settings.getTileHeight())));

        actionLoop = new Timer(100, this);
        actionLoop.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        // paint the standard bits and bobs
        super.paintComponent(graphics);
        // then paint the pieces
        pieces.forEach(piece -> {
            piece.paint(graphics);
        });
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            // change to the settings screen
            case KeyEvent.VK_ESCAPE:
                CardLayout cardLayout = (CardLayout) (settings.getCards().getLayout());
                cardLayout.show(settings.getCards(), "SETTINGS");
                settings.getCards().transferFocus();
                break;
            // let the pieces know that the key has been pressed
            default:
                pieces.forEach(piece -> {
                    piece.keyPressed(keyEvent);
                });
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // perform any actions prior to painting the pieces
        pieces.forEach(piece -> {
            piece.actionPerformed(actionEvent, pieces);
        });
        // then repaint everything
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /* not needed */ }

    @Override
    public void keyReleased(KeyEvent e) {
        /* not needed */ }
}
