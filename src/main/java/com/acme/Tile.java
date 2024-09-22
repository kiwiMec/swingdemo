package com.acme;

public final class Tile {

    private int x, y;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
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
        return new Tile(x, y);
    }

    final public boolean isTheSameAs(Tile other) {
        return this.x == other.x && this.y == other.y;
    }

}
