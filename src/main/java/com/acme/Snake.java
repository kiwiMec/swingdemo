package com.acme;

import java.util.LinkedList;

public class Snake {
    
    private Direction direction;
    private Tile head;
    private LinkedList<Tile> body;

    Snake(Tile head) {
        this.direction = Direction.NONE;
        this.head = head;
        this.body = new LinkedList<Tile>();
    }

    public void move() {
        body.addFirst(head);
        head = head.getNextTile(direction);
        body.removeLast();
    }
}
