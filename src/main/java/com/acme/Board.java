package com.acme;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener {

    Timer actionLoop;
    Settings settings;

    Board(Settings settings) {

        this.settings = settings;

        setPreferredSize(new Dimension(settings.getWidth(), settings.getHeight()));
        setBackground(Color.GREEN);
        addKeyListener(this);
        setFocusable(true);

        actionLoop = new Timer(100, this);
        actionLoop.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                CardLayout cardLayout = (CardLayout) (settings.getCards().getLayout());
                cardLayout.show(settings.getCards(), "SETTINGS");
                settings.getCards().transferFocus();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not needed
    }

}
