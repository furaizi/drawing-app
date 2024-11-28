package org.example.model.shapes;

import org.example.model.shapes.interfaces.ILine;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Shape implements ILine {

    public Line(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        drawLine(g2d);
    }

    @Override
    public void drawLine(Graphics2D g2d) {
        g2d.setColor(contourColor);
        g2d.draw(new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
    }

    @Override
    protected void recalculate() {}
}
