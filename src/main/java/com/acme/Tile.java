package com.acme;

import java.awt.Color;
import java.awt.Graphics;

public final class Tile {

    private int x, y, width, height;

    Tile(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    final public Tile getNextTile(Direction direction) {
        int x = this.x;
        int y = this.y;
        switch (direction) {
            case UP:
                y=y-1;
                break;
            case DOWN:
                y=y+1;
                break;
            case LEFT:
                x=x-1;
                break;
            case RIGHT:
                x=x+1;
                break;
            default:
        }
        return new Tile(x, y, width, height);
    }

    final public boolean isTheSameAs(Tile other) {
        return this.x == other.x && this.y == other.y;
    }

    final public void paint(Graphics graphics, Color color){

        graphics.setColor(color);
        graphics.fillRect(x * width, y * width, width, width);
    }
}
