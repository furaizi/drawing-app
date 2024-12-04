package org.example.model.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Shape {

    public Line(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(contourColor);
        g2d.draw(new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
    }

    @Override
    protected void recalculate() {}
}
