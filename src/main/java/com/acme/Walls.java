package com.acme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

public class Walls implements Piece {

    LinkedList<Tile> outerWalls;

    public Walls(Settings settings) {
        outerWalls = new LinkedList<Tile>();
        // wallsdefine the boundary of the board so that the snake can not run out of
        // sight
        int heightInTiles = settings.getBoardHeight() / settings.getTileHeight();
        int widthInTiles = settings.getBoardWidth() / settings.getTileWidth();
        for (int x = 0; x < widthInTiles; x++) {
            // top Wall
            outerWalls.add(new Tile(x, -1, settings.getTileWidth(), settings.getTileHeight()));
            // bottom Wall
            outerWalls.add(new Tile(x, heightInTiles - 1, settings.getTileWidth(), settings.getTileHeight()));
        }
        for (int y = 0; y < heightInTiles; y++) {
            // left Wall
            outerWalls.add(new Tile(-1, y, settings.getTileWidth(), settings.getTileHeight()));
            // right Wall
            outerWalls.add(new Tile(widthInTiles, y, settings.getTileWidth(), settings.getTileHeight()));
        }
    }

    @Override
    public pieceType getPieceType() {
        return Piece.pieceType.WALL;
    }

    @Override
    public boolean isOn(Tile tile) {
        Iterator<Tile> it = outerWalls.iterator();
        while (it.hasNext()) {
            if (it.next().isTheSameAs(tile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics graphics) {
        // we don't actually need to paint anything as the walls should be transparent
        outerWalls.forEach(tile -> {
            tile.paint(graphics, Color.lightGray);
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent, Board board, ArrayList<Piece> pieces) {
        // walls are pretty inactive things really
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // walls don't care about keys
    }

}
