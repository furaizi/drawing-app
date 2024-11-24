package org.example.model.shapes;

import org.example.model.shapes.interfaces.IEllipse;
import org.example.model.shapes.interfaces.ILine;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class LineOO extends Shape implements ILine, IEllipse {

    private static final int RADIUS = 15;
    private static final int DIAMETER = 2 * RADIUS;

    public LineOO(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        drawLine(g2d);
        drawEllipse(g2d);
    }

    @Override
    protected void recalculate() {}

    @Override
    public void drawEllipse(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(new Ellipse2D.Double(startPoint.x - RADIUS, startPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.fill(new Ellipse2D.Double(endPoint.x - RADIUS, endPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.setColor(Color.BLACK);
        g2d.draw(new Ellipse2D.Double(startPoint.x - RADIUS, startPoint.y - RADIUS, DIAMETER, DIAMETER));
        g2d.draw(new Ellipse2D.Double(endPoint.x - RADIUS, endPoint.y - RADIUS, DIAMETER, DIAMETER));

    }

    @Override
    public void drawLine(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y));
    }
}
