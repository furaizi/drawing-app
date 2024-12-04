package org.example.model.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    private final Ellipse2D ellipse2D = new Ellipse2D.Double();
    private final Point center;

    public Ellipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        center = startPoint;
        recalculate();
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(contourColor);
        g2d.draw(ellipse2D);
    }

    @Override
    protected void recalculate() {
        var halfWidth = Math.abs(endPoint.x - center.x);
        var halfHeight = Math.abs(endPoint.y - center.y);

        var ellipseX = center.x - halfWidth;
        var ellipseY = center.y - halfHeight;

        ellipse2D.setFrame(ellipseX, ellipseY, 2 * halfWidth, 2 * halfHeight);
    }
}
