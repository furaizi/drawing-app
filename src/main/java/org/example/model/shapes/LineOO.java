package org.example.model.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class LineOO extends Shape {

    private static final int RADIUS = 15;
    private static final int DIAMETER = 2 * RADIUS;

    public LineOO(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(contourColor);
        drawLine(g2d);
        drawEllipses(g2d);
    }

    @Override
    protected void recalculate() {}


    private void drawEllipses(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(new Ellipse2D.Double(startPoint.x - RADIUS, startPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.fill(new Ellipse2D.Double(endPoint.x - RADIUS, endPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.setColor(contourColor);
        g2d.draw(new Ellipse2D.Double(startPoint.x - RADIUS, startPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.draw(new Ellipse2D.Double(endPoint.x - RADIUS, endPoint.y - RADIUS, DIAMETER, DIAMETER));

    }

    private void drawLine(Graphics2D g2d) {
        g2d.draw(new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
    }
}
