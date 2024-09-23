package com.acme;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public interface Piece {
    public void actionPerformed(ActionEvent actionEvent);
    public boolean collidesWith(Piece piece);
    public void keyPressed(KeyEvent event);
    public void paint(Graphics graphics);
}
