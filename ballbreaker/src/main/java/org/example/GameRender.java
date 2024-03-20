package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRender extends JPanel implements Runnable {
    private int width, height;
    private Thread renderThread = null;
    private Brick brick_rows[];
    private Player player;
    private Ball ball;
    private ControlsHandler controlsHandler;
    private final int FPS = 60;
    private int collisionCounter = 0;
    public GameRender(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        controlsHandler = new ControlsHandler();
        this.addKeyListener(controlsHandler);
        this.setFocusable(true);

        player = new Player(width / 2 - 50, height - 100, 100, 20, 10,new Color(85, 0, 0), controlsHandler, this);
        ball = new Ball(player.getX() + player.getWidth() / 2, player.getY() - player.getHeight() * 2, 10, 25, Color.WHITE, this);

        int brick_row_count = 10;
        int brick_width = (width - (10 + brick_row_count * 5 - 5)) / brick_row_count;
        int brick_height = 20;
        brick_rows = new Brick[brick_row_count * 4];
        int currentXpos = 5;
        int currentYpos = 100;

        for (int i = 0; i < brick_rows.length / 4; i++) {
            brick_rows[i] = new Brick(10, currentXpos, currentYpos, brick_width, brick_height, Color.GREEN);
            brick_rows[i + 10] = new Brick(10, currentXpos, currentYpos + brick_height + 5, brick_width, brick_height, Color.RED);
            brick_rows[i + 20] = new Brick(10, currentXpos, currentYpos + (2 * brick_height) + 10, brick_width, brick_height, Color.MAGENTA);
            brick_rows[i + 30] = new Brick(10, currentXpos, currentYpos + (3 * brick_height) + 15, brick_width, brick_height, Color.YELLOW);
            currentXpos += brick_width + 5;
        }
    }

    public void startRenderThread() {
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(this.renderThread != null) {
            currentTime = System.nanoTime();
            delta = delta + (currentTime - lastTime) / drawInterval;
            timer = timer + currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1e9) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        ball.update();
        if (player.intersects(ball)) {
            ball.setMovingUp(true);
            System.out.println("COLLISION! " + collisionCounter++);
        }
        for (int i = 0; i < brick_rows.length; i++) {
            if (brick_rows[i].intersects(ball) && brick_rows[i].isAlive()) {
                ball.setMovingUp(!ball.isMovingUp());
                brick_rows[i].setAlive(false);
            }
        }
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Brick brick : brick_rows) {
            if (brick.isAlive())
                brick.draw(g2d);
        }
        player.draw(g2d);
        ball.draw(g2d);
    }
}
