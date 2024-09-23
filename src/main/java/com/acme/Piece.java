package com.acme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.util.LinkedList;

public interface Piece {

    public enum pieceType {
        WALL, FOOD, SNAKE
    };

    public void actionPerformed(ActionEvent actionEvent, LinkedList<Piece> pieces);

    public boolean isOn(Tile tile);

    public pieceType getPieceType();

    public void keyPressed(KeyEvent event);

    public void paint(Graphics graphics);
}
