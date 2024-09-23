package com.acme;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake implements Piece {
    
    private Direction direction;
    private Tile head;
    private LinkedList<Tile> body;

    Snake(Tile head) {
        this.direction = Direction.NONE;
        this.head = head;
        this.body = new LinkedList<Tile>();
    }

    @Override
    public boolean collidesWith(Piece piece) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collision'");
    }

    @Override
    public void move() {
        // TODO detect collisions
        body.addFirst(head);
        head = head.getNextTile(direction);
        body.removeLast();
    }

    @Override
    public void paint(Graphics graphics) {
        // draw the head tile
        head.paint(graphics, Color.WHITE);

        // draw the body tiles
        body.forEach(n -> {n.paint(graphics, Color.RED);});
    }

}
