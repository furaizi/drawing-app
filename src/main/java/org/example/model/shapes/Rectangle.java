package org.example.model.shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    private final Rectangle2D rectangle2D = new Rectangle2D.Double();

    public Rectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        recalculate();
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(rectangle2D);

        g2d.setColor(contourColor);
        g2d.draw(rectangle2D);
    }

    public int getWidth() {
        return Math.abs(endPoint.x - startPoint.x);
    }

    public int getHeight() {
        return Math.abs(endPoint.y - startPoint.y);
    }

    @Override
    protected void recalculate() {
        var rectX = Math.min(startPoint.x, endPoint.x);
        var rectY = Math.min(startPoint.y, endPoint.y);

        rectangle2D.setRect(rectX, rectY, getWidth(), getHeight());
    }
}
