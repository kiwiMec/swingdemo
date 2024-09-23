package com.acme;

import java.awt.Graphics;

public interface Piece {
    public boolean collidesWith(Piece piece);
    public void paint(Graphics graphics);
    public void move();
}
