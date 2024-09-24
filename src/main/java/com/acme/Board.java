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

import com.acme.Food.State;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener, KeyListener {

    ArrayList<Piece> pieces;
    Random random;
    Settings settings;
    Timer actionLoop;

    Board(Settings settings) {

        this.settings = settings;

        setPreferredSize(new Dimension(settings.getBoardWidth(), settings.getBoardHeight()));
        setBackground(Color.darkGray);
        addKeyListener(this);
        setFocusable(true);

        random = new Random();

        addInitialPieces();

        actionLoop = new Timer(200, this);
        actionLoop.start();
    }

    private void addInitialPieces() {
        pieces = new ArrayList<Piece>();
        pieces.add(new Walls(settings));
        pieces.add(new Snake(new Tile(10, 10, settings.getTileWidth(), settings.getTileHeight())));
        addFood();
    }

    private Tile getRandomTile() {
        int x = random.nextInt(settings.getBoardWidth() / settings.getTileWidth());
        int y = random.nextInt(settings.getBoardHeight() / settings.getTileHeight());
        return new Tile(x, y, settings.getTileWidth(), settings.getTileHeight());
    }

    private boolean isOccupied(Tile tile) {
        return !isVacant(tile);
    }

    private boolean isVacant(Tile tile) {
        Iterator<Piece> it = pieces.iterator();
        while (it.hasNext()) {
            if (it.next().isOn(tile)) {
                return false;
            }
        }
        return true;
    }

    public Food getNewFood() {
        Tile tile = getRandomTile();
        while (isOccupied(tile)) {
            tile = getRandomTile();
        }
        return new Food(tile);
    }

    public void addFood() {
        pieces.add(getNewFood());
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
            // restart the game
            case KeyEvent.VK_R:
                pieces.clear();
                addInitialPieces();
                break;
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
            piece.actionPerformed(actionEvent, this, pieces);
        });
        /*
         * replace any eaten fruit
         * 
         * When we modify the list of Pieces we have to use an array to
         * access the individual pieces, as iterators get grumpy about having
         * their underlying data structure changed while the iterator is still
         * being used.
         */
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.getPieceType() == Piece.pieceType.FOOD) {
                Food food = (Food) (piece);
                if (food.getState() == State.EATEN) {
                    pieces.remove(piece);
                    addFood();
                }
            }
        }
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
