package org.example.model.shapes;

import org.example.model.shapes.interfaces.IEllipse;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape implements IEllipse {

    private int ellipseX, ellipseY, halfWidth, halfHeight;
    private Point center;

    public Ellipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        center = startPoint;
        ellipseX = startPoint.x;
        ellipseY = startPoint.y;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        drawEllipse(g2d);
    }

    @Override
    public void drawEllipse(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.draw(new Ellipse2D.Double(ellipseX, ellipseY, 2 * halfWidth, 2 * halfHeight));
    }

    @Override
    protected void recalculate() {
        halfWidth = Math.abs(endPoint.x - center.x);
        halfHeight = Math.abs(endPoint.y - center.y);

        ellipseX = center.x - halfWidth;
        ellipseY = center.y - halfHeight;
    }
}
