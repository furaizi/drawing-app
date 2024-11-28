package org.example.model.shapes;

import org.example.model.shapes.interfaces.IRectangle;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape implements IRectangle {

    private int rectX, rectY, width, height;

    public Rectangle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        drawRect(g2d);
    }

    @Override
    public void drawRect(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(rectX, rectY, width, height));
        g2d.setColor(contourColor);
        g2d.draw(new Rectangle2D.Double(rectX, rectY, width, height));
    }

    @Override
    protected void recalculate() {
        rectX = Math.min(startPoint.x, endPoint.x);
        rectY = Math.min(startPoint.y, endPoint.y);

        width = Math.abs(endPoint.x - startPoint.x);
        height = Math.abs(endPoint.y - startPoint.y);
    }
}
