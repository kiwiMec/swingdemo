package com.acme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Food implements Piece {

    enum State {
        FRESH,
        SOUR,
        ROTTEN,
        DECOMPOSED,
        EATEN
    }

    private Tile tile;
    private State state;

    Food(Tile tile) {
        this.tile = tile;
        state = State.FRESH;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent, Board board, ArrayList<Piece> pieces) {
        // food isn't very active
    }

    @Override
    public boolean isOn(Tile tile) {
        return this.tile.isTheSameAs(tile);
    }

    @Override
    public pieceType getPieceType() {
        return Piece.pieceType.FOOD;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // food doesn't really care about keys
    }

    @Override
    public void paint(Graphics graphics) {
        switch (state) {
            case FRESH:
            tile.paint(graphics, Color.GREEN);
            break;
            case SOUR:
            tile.paint(graphics, Color.ORANGE);
            break;
            case ROTTEN:
            tile.paint(graphics, Color.MAGENTA);
            break;
            case EATEN,DECOMPOSED:
            // we do not draw fruit that has been eaten or has decomposed
            break;
        }
    }
}
