package org.example;

import javax.swing.*;
import java.awt.*;

public class Brick extends EntityBaseClass implements GameEntity {
    private int brickHP;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private boolean isAlive = true;
    private Color color;
    public Brick(int hp, int xPos, int yPos, int width, int height, Color color) {
        super(xPos, yPos, width, height);
        this.brickHP = hp;
        this.color = color;
    }

    public int getBrickHP() {
        return this.brickHP;
    }

    public void setBrickHP(int newHP) {
        this.brickHP = newHP;
    }

    @Override
    public void update() {

    }

//    @Override
//    public boolean intersects(EntityBaseClass other) {
//        return false;
//    }



    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawLine(getX() + 2, getY() + getHeight() - 2, getX() + 2, getY() + 2);
        g2d.drawLine(getX() + 2, getY() + 2, getX() + getWidth() - 2, getY() + 2);
        g2d.setStroke(new BasicStroke(6f));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(getX() + getWidth() - 2, getY() + 2, getX() + getWidth() - 2, getY() + getHeight() - 2);
        g2d.drawLine(getX() + getWidth() - 2, getY() + getHeight() - 2, getX() + 2, getY() + getHeight() - 2);
    }
}
