package org.example;

import java.awt.*;

public class Ball extends EntityBaseClass implements GameEntity{
    private boolean movingUp, movingLeft;
    private final GameRender render;

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }


    private int speed;
    private Color color;

    public Ball(int xPos, int yPos, int speed, int width, Color color, GameRender render) {
        super(xPos, yPos, width, width);
        this.speed = speed;
        this.color = color;
        this.render = render;
    }
    @Override
    public void update() {
        if (getX() > render.getWidth() - 25)
            movingLeft = true;
        if (getX() < 0)
            movingLeft = false;
        if (movingLeft)
            setX(getX() - speed);
        else setX(getX() + speed);

        if (getY() > render.getHeight() - 25)
            movingUp = true;
        if (getY() < 0)
            movingUp = false;
        if (movingUp)
            setY(getY() - speed);
        else setY(getY() + speed);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
