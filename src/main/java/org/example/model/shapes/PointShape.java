package org.example.model.shapes;

import org.example.model.shapes.interfaces.IPointShape;

import java.awt.*;

public class PointShape extends Shape implements IPointShape {

    private static final int SIZE = 10;
    public PointShape(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void paintComponent(Graphics g) {
        drawPoint(g);
    }

    @Override
    public void drawPoint(Graphics g) {
        g.setColor(contourColor);
        g.fillOval(endPoint.x - SIZE/2, endPoint.y - SIZE/2, SIZE, SIZE);
    }
    @Override
    protected void draw(Graphics2D g2d) {}

    @Override
    protected void recalculate() {
        startPoint = endPoint;
    }
}
