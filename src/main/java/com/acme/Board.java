package com.acme;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;


public class Board extends JPanel implements ActionListener, KeyListener {

    ArrayList<Piece> pieces;
    Random random;
    Settings settings;
    Timer actionLoop;
    JFrame frame;
    int foodEaten;

    Board(Settings settings, JFrame frame) {

        this.settings = settings;
        this.frame = frame;

        setPreferredSize(new Dimension(settings.getBoardWidth(), settings.getBoardHeight()));
        setBackground(Color.darkGray);
        addKeyListener(this);
        setFocusable(true);
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent arg0) {
                frame.setTitle("Snakegame: fruit eaten = " + foodEaten);
            }
            public void focusLost(FocusEvent focusEvent) {
            }
        });

        random = new Random();

        addInitialPieces();

        actionLoop = new Timer(200, this);
        actionLoop.start();
    }

    private void addInitialPieces() {
        foodEaten = 0;
        frame.setTitle("Snakegame: fruit eaten = " + foodEaten);
        pieces = new ArrayList<Piece>();
        pieces.add(new Walls(settings));
        pieces.add(new Snake(getUnoccupiedTile()));
        addFood();
    }

    public Tile getUnoccupiedTile() {
        Tile tile = getRandomTile();
        while (isOccupied(tile)) {
            tile = getRandomTile();
        }
        return tile;
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
            // exit the game
            case KeyEvent.VK_Q:
                if(keyEvent.isMetaDown()){
                    System.exit(0);
                }
                break;
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
                if (food.getState() == Food.State.EATEN) {
                    pieces.remove(piece);
                    addFood();
                    foodEaten++;
                    frame.setTitle("Snakegame: fruit eaten = " + foodEaten);
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
