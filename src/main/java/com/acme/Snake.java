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
    private boolean isAlive;
    private boolean isCollision;

    Snake(Tile head) {
        this.head = head;
        body = new LinkedList<Tile>();
        direction = Direction.NONE;
        isAlive = true;
        isCollision = false;
    }

    private final void eat() {
        body.addFirst(head);
        head = head.getNextTile(direction);
    }

    private final void move() {
        body.addFirst(head);
        head = head.getNextTile(direction);
        body.removeLast();
    }

    private final void die() {
        isAlive = false;
    }

    @Override
    public final boolean isOn(Tile tile) {
        if (head.isTheSameAs(tile))
            return true;
        body.forEach(bodyTile -> {
            bodyTile.isTheSameAs(tile);
        });
        return false;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent, LinkedList<Piece> pieces) {
        if (isAlive && direction != Direction.NONE) {
            isCollision = false;
            Tile nextTile = head.getNextTile(direction);
            pieces.forEach(piece -> {
                if (piece.isOn(nextTile)) {
                    this.isCollision = true;
                    switch (piece.getPieceType()) {
                        case FOOD:
                            eat();
                            break;
                        case SNAKE:
                            die();
                            break;
                        case WALL:
                            die();
                            break;
                        default:
                            break;
                    }
                }
            });
            if (isCollision == false) {
                move();
            }
        }
    }

    @Override
    public final pieceType getPieceType() {
        return Piece.pieceType.SNAKE;
    }

    @Override
    public final void paint(Graphics graphics) {
        // draw the head tile
        head.paint(graphics, Color.WHITE);

        // draw the body tiles
        body.forEach(n -> {
            n.paint(graphics, Color.RED);
        });
    }

    @Override
    public final void keyPressed(KeyEvent event) {
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
