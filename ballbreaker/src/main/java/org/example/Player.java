package org.example;

import java.awt.*;

public class Player extends EntityBaseClass implements GameEntity {
    private Color color, lightColor;
    private ControlsHandler controlsHandler;
    private GameRender render;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int speed;

    public Player(int xPos, int yPos, int width, int height, int speed, Color color, ControlsHandler controlsHandler, GameRender render) {
        super(xPos, yPos, width, height);
        this.color = color;
        this.speed = speed;
        this.lightColor = new Color(240, 0, 0);
        this.controlsHandler = controlsHandler;
        this.render = render;
    }
    @Override
    public void update() {
        if (controlsHandler.isLeftPressed()) {
            setX(getX() - speed);
            if (getX() < 0)
                setX(0);
        }
        if (controlsHandler.isRightPressed()) {
            setX(getX() + speed);
            if (getX() + getWidth() > render.getWidth())
                setX(render.getWidth() - getWidth());
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRoundRect(getX(), getY(), getWidth(), getHeight(), getHeight(), getHeight());
        g2d.setColor(lightColor);
        g2d.fillRoundRect(getX() + 5, getY() + 5, getWidth() - 10, 10, 8, 8);
    }
}
