package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing 2d");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameRender render = new GameRender(800, 600);
        frame.add(render);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        render.startRenderThread();
    }
}