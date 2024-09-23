package com.acme;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
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

    private void eat() {
        body.addFirst(head);
        head = head.getNextTile(direction);
    }

    private void move() {
        body.addFirst(head);
        head = head.getNextTile(direction);
        body.removeLast();
    }

    private void die() {
    }

    @Override
    public boolean collidesWith(Piece piece) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO detect collisions and respond accordingly
        move();
        //eat();
        //die();
    }

    @Override
    public void paint(Graphics graphics) {
        // draw the head tile
        head.paint(graphics, Color.WHITE);

        // draw the body tiles
        body.forEach(n -> {
            n.paint(graphics, Color.RED);
        });
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        switch (keyPressed) {
            case KeyEvent.VK_UP:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;

            default:
                break;
        }

    }

}
